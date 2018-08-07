package cn.inbs.blockchainpurse.common.web;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.advice.ControllerBeforeAdvice;
import cn.inbs.blockchainpurse.common.cache.UserTokenCache;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @Description: controller前置控制器
 * @Package: cn.inbs.blockchainpurse.common.web
 * @ClassName: PurseControllerAdvice
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/24 10:37
 * @Version: 1.0
 */
public class PurseControllerAdvice extends ControllerBeforeAdvice {
    private static String[] PASS_URL = {"/purseUserRegister.dx", "/purseUserLogin.dx"};

    @Override
    public void doBefore(Method method, Object[] inputParams, Object o) {
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            String[] values = annotation.value();
            String value = values[0];
            boolean isPass = false;
            for (String s : PASS_URL) {
                if (value.equals(s)) {
                    isPass = true;
                    break;
                }
            }

            if (!isPass) {
                String loginToken = null;
                for (Object inputParam : inputParams) {
                    if (inputParam instanceof BaseControllerInput) {
                        BaseControllerInput baseControllerInput = (BaseControllerInput) inputParam;
                        loginToken = baseControllerInput.getPurseToken();
                    }
                }

                if (null == loginToken) {
                    throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0007);
                } else {
                    String[] split = loginToken.split("-");
                    String purseName = split[0];
                    String tokenInCache = UserTokenCache.getTokenInCache(purseName);
                    if (null == tokenInCache) {
                        throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0003);
                    } else {
                        if (!tokenInCache.equals(loginToken)) {
                            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0006);
                        }
                    }
                }
            }
        }
    }
}
