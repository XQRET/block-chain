package cn.inbs.blockchain.plugin.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.security.DES;
import com.cdoframework.cdolib.servlet.PrefixWebService;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务调用前，这里主要是验证登陆的验证码
 *
 * @author tanglei
 * @date 2017-10-29
 */
public class EmployeePrefixService extends PrefixWebService {
    private static Logger logger = LoggerFactory.getLogger(EmployeePrefixService.class);

    /**
     * 校验验证码请求列表
     */
    private static final String[] DO_CHECK_TRANS_NAME_ARRAY = {"bossBeforeLogin", "userRegister", "userResetPassword"};

    @Override
    public Return handleTrans(HttpServletRequest request,
                              HttpServletResponse response,
                              CDO cdoRequest,
                              CDO cdoResponse) {
        if (logger.isDebugEnabled()) {
            logger.debug("进入调用服务前处理,请求参数:{}", cdoResponse.toXML());
        }

        //获取请求名
        String strTransName = cdoRequest.getStringValue(Constant.CDO_strTransName);

        //执行拦截校验
        if (isCheckPhotoCodeByTrabsName(strTransName)) {
            //获取输入的验证码
            String curVerifyCode = cdoRequest.getStringValue("strVerifyCode");

            //获取cookie
            Cookie cookie = Utility.getCookie(request, Constant.COOKIE_VERIFYCODE_NAME);

            //获取cookid中加密验证码
            String sysVerifyCode = null;
            if (cookie != null) {
                sysVerifyCode = cookie.getValue();
            }

            //非空校验
            if (sysVerifyCode == null) {
                return Return.valueOf(-401, Constant.MSG_VERIFYCODE_ERROR);
            }

            //cookie验证码解密
            DES des = Utility.getDES(request.getSession().getServletContext(), request.getSession(), Constant.VERIFYCODE_DES_KEY);
            sysVerifyCode = Utility.DESDecrypt(sysVerifyCode, des);//解密

            //匹配校验
            if (!sysVerifyCode.equalsIgnoreCase(curVerifyCode)) {
                return Return.valueOf(-402, Constant.MSG_VERIFYCODE_ERROR);
            }
        }
        return super.handleTrans(request, response, cdoRequest, cdoResponse);
    }

    /**
     * 图形验证码拦截匹配
     *
     * @param transName
     * @return
     */
    private boolean isCheckPhotoCodeByTrabsName(String transName) {
        boolean isCheck = false;
        for (String check : DO_CHECK_TRANS_NAME_ARRAY) {
            if (check.equals(transName)) {
                isCheck = true;
                break;
            }
        }

        return isCheck;
    }
}
