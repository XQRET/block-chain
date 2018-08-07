package cn.inbs.blockchain.service.cdo.usercenter;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.TransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.cdo.user
 * @ClassName:
 * @Date: 2018年05月11-17:10
 * @Author: createBy:zhangmingyang
 **/
public class UserCenterService extends TransService {
    private static Logger logger = LoggerFactory.getLogger(UserCenterService.class);

    // 全局数组
    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

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
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage(),e);
            }
            return Return.valueOf(-20,e.getMessage(),"UnsupportedTrans called");
        }
    }

    /**
     * 密码验证
     * @param cdoRequest
     * @param cdoResponse
     * @return
     */
    public Return checkPasswd(CDO cdoRequest, CDO cdoResponse){
        String password="";
        String strMobile="";
        if(!cdoRequest.exists("strPasswd")){
            return Return.valueOf(-1,"密码不能为空！");
        }else{
            password=cdoRequest.getStringValue("strPasswd");
        }
        if(!cdoRequest.exists("strMobile")){
            return Return.valueOf(-1,"电话不能为空！");
        }else{
            strMobile=cdoRequest.getStringValue("strMobile");
        }
        CDO cdoInfoRequest=new CDO();
        CDO cdoInfoResponse=new CDO();
        cdoInfoRequest.setStringValue("strServiceName", "UserCenterService");  //类
        cdoInfoRequest.setStringValue("strTransName", "userCenter"); //类对应的方法
        cdoInfoRequest.setStringValue("strMobile", strMobile);
        Return ret = this.servicePlugin.handleDataTrans(cdoInfoRequest,cdoInfoResponse);
        if(ret.getCode()!=0){
            if(logger.isErrorEnabled()){
                logger.error("根据手机没有获取到CDO对象！");
            }
        }else {
            CDO cdo= cdoInfoResponse.getCDOValue("cdoCompany");
            if(!(cdo.exists("strPassword") && cdo.exists("strSalt"))){
                if(logger.isErrorEnabled()){
                    logger.error("CDO对象未包含指定参数！");
                }
                return null;
            }else{
                String strSaltSql=cdo.getStringValue("strSalt"); //盐
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    String encryptPasswd=strSaltSql+""+password;
                    String encryptPD=byteToString(md.digest(encryptPasswd.getBytes()));
                    String passwordSql=cdo.getStringValue("strPassword"); //数据库查询出的密码
                    if(!encryptPD.equals(passwordSql)){
                        if(logger.isInfoEnabled()){
                            logger.info("数据库密码[{}]-------------输入密码[{}]", passwordSql, encryptPD);
                        }
                        return Return.valueOf(-1,"密码不匹配！");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new BusinessException(BusinessErrorConstants.BASIC_0001);
                }
            }
        }
        return Return.OK;
    }

}
