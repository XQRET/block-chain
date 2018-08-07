package cn.inbs.blockchain.service.cdo.user;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.utils.ExceptionUtils;
import cn.inbs.blockchain.common.utils.MobileUtils;
import cn.inbs.blockchain.common.utils.PasswordUtils;
import cn.inbs.blockchain.service.cdo.BaseCdoService;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.cdo.user
 * @ClassName:
 * @Date: 2018年05月21-16:43
 * @Author: createBy:zhangmingyang
 **/
public class UserPasswordService extends BaseCdoService {
    private static final String MOBLIE = "strMobile";//手机号
    private static final String CODE = "strSmsCode";//短信
    private static final String PASSWORD = "strPassword";//密码

    private static final int USER_RESET_PASSWORD_ERROR_CODE = -1;


    @Override
    protected Return doHandleTrans(CDO cdoRequest, CDO cdoResponse) throws Exception {
        String invokeMethodName = getInvokeMethodName(null, cdoRequest);

        Method method = this.getClass().getDeclaredMethod(invokeMethodName, CDO.class, CDO.class);
        return (Return) method.invoke(this, cdoRequest, cdoResponse);
    }


    public Return userResetPassword(CDO cdoRequest, CDO cdoResponse) throws NoSuchAlgorithmException {
        //校验手机号
        String mobile;
        if (!cdoRequest.exists(MOBLIE)) {
            return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, "请输入机号");
        } else {
            mobile = cdoRequest.getStringValue(MOBLIE).trim();
            try {
                MobileUtils.checkMobile(mobile);
            } catch (Exception e) {
                return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验用户密码
        String password;
        if (!cdoRequest.exists(PASSWORD)) {
            return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, "请输入待设置密码");
        } else {
            password = cdoRequest.getStringValue(PASSWORD).trim();
            try {
                PasswordUtils.checkPassword(password);
            } catch (Exception e) {
                return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验验证码
        String code;
        if (!cdoRequest.exists(CODE)) {
            return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, "请给定验证码");
        } else {
            code = cdoRequest.getStringValue(CODE).trim();
            try {
                MobileUtils.checkVerificationCode2Cache(mobile, code, CommonConfigPerpertyConstants.SEND_VERIFICATION_CODE_MESSAGE_TEMPLATE_KEY);
            } catch (Exception e) {
                return Return.valueOf(USER_RESET_PASSWORD_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //查询用户信息
        CDO queryCdoRes = new CDO();
        CDO queryCdoReq = CDO.newRequest("UserPasswordService", "queryUserByMobile");
        queryCdoReq.setStringValue(MOBLIE, mobile);
        Return ret = this.servicePlugin.handleDataTrans(queryCdoReq, queryCdoRes);
        CDO cdoEmployee = queryCdoRes.getCDOValue("cdoEmployee");

        //密码加密
        CDO resetCdoReq = CDO.newRequest("UserPasswordService", "userResetPassword");
        resetCdoReq.setStringValue("strPasswd", password);
        resetCdoReq.setLongValue("lId", cdoEmployee.getLongValue("lId"));
        resetCdoReq.setStringValue("strSalt", cdoEmployee.getStringValue("strSalt"));

        return this.servicePlugin.handleDataTrans(resetCdoReq, cdoResponse);
    }
}
