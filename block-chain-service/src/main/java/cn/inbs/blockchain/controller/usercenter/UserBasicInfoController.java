package cn.inbs.blockchain.controller.usercenter;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.controller.boss.BaseController;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class UserBasicInfoController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserBasicInfoController.class);

	/**
	 * 基本信息展示
	 * @param request
	 * @return
	 */
	public CDO queryCompany(HttpServletRequest request) {
		CDO cdoLoginer=(CDO)request.getAttribute(Constant.Key_cdoBossUser);
		String strMobile=cdoLoginer.getStringValue(Constant.Key_Boss_strMobile);
		if(logger.isInfoEnabled()){
			logger.info("用户:[{}]手机", strMobile);
		}
		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "UserCenterService");
		cdoRequest.setStringValue("strTransName", "userCenter");
		cdoRequest.setStringValue("strMobile", strMobile);
		CDO cdoResponse = new CDO();
		Return ret = super.handleTrans(request,null,cdoRequest,cdoResponse);
		if(ret.getCode()!=0)
		{
			return null;
		}
		return cdoResponse;
	}

}
