package cn.inbs.blockchain.plugin.employee;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.UUidGenerator;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.TransService;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.RedisInstance;
import cn.inbs.blockchain.common.utils.RegexValidateUtil;

/**
 * 员工信息
* @author tanglei
* @date 2017-10-24
 */
public class EmployeeService extends TransService {

	//能用成员变量就不能静态变量减少栈内存开销
	private Logger logger = Logger.getLogger(EmployeeService.class);


	@Override
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse) {
		String strTransName = cdoRequest.getStringValue("strTransName");
		Return ret = null;
		try {
			Method m = this.getClass().getDeclaredMethod(strTransName,CDO.class,CDO.class);
			Object retOb = m.invoke(this, cdoRequest,cdoResponse);
			ret = (Return)retOb;
			return ret;
		} catch(NoSuchMethodException e) {
			//如果没有找到对应的Java方法那么去XML文件尝试
			return this.servicePlugin.handleDataTrans(cdoRequest, cdoResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return Return.valueOf(-20,e.getMessage(),"UnsupportedTrans called");
		}
	}

	/**
	 * 登陆前验证  bossBeforeLogin和doBossLogin会调用
	* @param cdoRequest
	* @param cdoResponse
	* @return
	* @author tanglei
	* @date 2017-10-29
	 */
	private Return bossLoginVerify(CDO cdoRequest, CDO cdoResponse){
		String strMobile = cdoRequest.getStringValue("strMobile");
		if(StringUtils.isEmpty(strMobile)){
			return Return.valueOf(-3, "请输入手机号码");
		}

		CDO beforeRequest = CDO.newRequest("EmployeeService", "bossLoginVerify");
		beforeRequest.setStringValue("strMobile", strMobile);
		Return ret = this.servicePlugin.handleDataTrans(beforeRequest, cdoResponse);
		return ret;
	}

	/**
	 * 登陆前
	* @param cdoRequest
	* @param cdoResponse
	* @return
	* @author tanglei
	* @date 2017-10-29
	 */
	public Return bossBeforeLogin(CDO cdoRequest, CDO cdoResponse){
		CDO verifyResponise = new CDO();
		Return ret = bossLoginVerify(cdoRequest,verifyResponise);
		if(ret.getCode()!=0){
			return ret;
		}

		String strRandom = UUidGenerator.genenator();
		CDO cdoEmployee = verifyResponise.getCDOValue("cdoEmployee");
		long lId = cdoEmployee.getLongValue("lId");
		cdoResponse.setStringValue("strSalt", cdoEmployee.getStringValue("strSalt"));//生成用户的时候生产的随机盐，主要是强加密用户密码，不容易破解
		cdoResponse.setStringValue("strRandom", strRandom);
		boolean setFlag = RedisInstance.getIns().set(Constant.REDIS_bossRandom + lId, strRandom, Constant.REDIS_nBossExpireTime); //保存随机数，在登陆的时候用
		if(setFlag == false){
			return Return.valueOf(-7, "服务器异常");
		}
		return Return.OK;
	}

	/**
	 * 登陆
	* @param cdoRequest
	* @param cdoResponse
	* @return
	* @author tanglei
	* @date 2017-10-29
	 */
	public Return doBossLogin(CDO cdoRequest, CDO cdoResponse){
		Return ret = bossLoginVerify(cdoRequest,cdoResponse);
		if(ret.getCode()!=0){
			return ret;
		}

		String strLoginToken = cdoRequest.getStringValue("strLoginToken");
		if(StringUtils.isEmpty(strLoginToken)){
			return Return.valueOf(-4, "strLoginToken不存在");
		}

		CDO cdoEmployee = cdoResponse.getCDOValue("cdoEmployee");
		long lId = cdoEmployee.getLongValue("lId");
		String strPassword = cdoEmployee.getStringValue("strPassword");
		String strRandom = RedisInstance.getIns().get(Constant.REDIS_bossRandom + lId);
		String strLoginTokenServer = com.cdoframework.cdolib.security.MD5.md5_hex((com.cdoframework.cdolib.security.MD5.md5_hex(strRandom).toLowerCase() + strPassword)).toLowerCase();
		boolean bLoginSuccess = strLoginToken.equalsIgnoreCase(strLoginTokenServer);
		if(bLoginSuccess == false){
			return Return.valueOf(-5, "密码错误");
		}

		//获取用户角色权限信息
		CDO roleRequest = CDO.newRequest("EmployeeRoleService", "getRoleBylEmployeeId");
		CDO roleResponse = new CDO();
		roleRequest.setLongValue("lEmployeeId", lId);
		ret = this.servicePlugin.handleDataTrans(roleRequest, roleResponse);
		if(ret.getCode() != 0){
			return Return.valueOf(-6, "查询用户权限信息出现异常");
		}

		if (roleResponse.exists("cdosRole")) {
			CDO[] cdosRole = roleResponse.getCDOArrayValue("cdosRole");
			cdoResponse.setCDOArrayValue("cdosRole", cdosRole);
		} else {
			//一个权限都没有设置一个空的数组
			cdoResponse.setCDOArrayValue("cdosRole", new CDO[]{});
		}
		return Return.OK;
	}

	/**
	 * 修改密码
	* @param cdoRequest
	* @param cdoResponse
	* @return
	* @author tanglei
	* @date 2017-10-30
	 */
	public Return updatePassword(CDO cdoRequest, CDO cdoResponse){
		CDO cdoUser = cdoRequest.getCDOValue(Constant.Key_cdoBossUser);
		Long lEmployeeId = cdoUser.getLongValue("lEmployeeId");
		if(lEmployeeId == null || lEmployeeId == 0){
			return Return.valueOf(-3, "请登陆后再操作");
		}

		if(!cdoRequest.exists("strOldPwd") || StringUtils.isEmpty(cdoRequest.getStringValue("strOldPwd"))){
			return Return.valueOf(-4, "请输入旧密码");
		}

		if(!cdoRequest.exists("strNewPwd") || StringUtils.isEmpty(cdoRequest.getStringValue("strNewPwd"))){
			return Return.valueOf(-5, "请输入新密码");
		}

		String strOldPwd = cdoRequest.getStringValue("strOldPwd");
		String strNewPwd = cdoRequest.getStringValue("strNewPwd");
		CDO cdoReq = CDO.newRequest("EmployeeService", "getEmployeeById");
		CDO cdoRes = new CDO();
		cdoReq.setLongValue("lId", lEmployeeId);
		Return ret = this.servicePlugin.handleDataTrans(cdoReq, cdoRes);
		if(ret.getCode()!=0){
			return ret;
		}

		CDO cdoEmployee = cdoRes.getCDOValue("cdoEmployee");
		String strPassword = cdoEmployee.getStringValue("strPassword");
		String strSalt = cdoEmployee.getStringValue("strSalt");

		String strOldPwdmd5 = com.cdoframework.cdolib.security.MD5.md5_hex(strSalt + strOldPwd).toLowerCase();
		boolean bFlag = strPassword.equalsIgnoreCase(strOldPwdmd5);
		if(bFlag == false){
			return Return.valueOf(-6, "原密码错误");
		}

		String strNewPwdmd5 = com.cdoframework.cdolib.security.MD5.md5_hex(strSalt + strNewPwd).toLowerCase();
		CDO updateCdoReq = CDO.newRequest("EmployeeService", "updatePwd");
		CDO updateCdoRes = new CDO();
		updateCdoReq.setLongValue("lId", lEmployeeId);
		updateCdoReq.setStringValue("strPassword", strNewPwdmd5);
		ret = this.servicePlugin.handleDataTrans(updateCdoReq, updateCdoRes);
		return ret;
	}

	/**
	 * 创建员工
	* @param cdoRequest
	* @param cdoResponse
	* @return
	* @author tanglei
	* @date 2017-11-1
	 */
	public Return createEmployee(CDO cdoRequest, CDO cdoResponse){
		if(!cdoRequest.exists("strMobile") || StringUtils.isEmpty(cdoRequest.getStringValue("strMobile"))){
			return Return.valueOf(-1, "请输入手机号码");
		}

		String strMobile = cdoRequest.getStringValue("strMobile");
		if(RegexValidateUtil.checkCellphone(strMobile) == false){
			return Return.valueOf(-2, "手机号格式不正确");
		}
		return this.servicePlugin.handleDataTrans(cdoRequest, cdoResponse);
	}
}
