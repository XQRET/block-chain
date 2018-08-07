package cn.inbs.blockchainpurse.common.web;

import cn.inbs.blockchain.common.exception.PlatformException;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 前端返回异常处理拦截器
 * @Package: cn.inbs.blockchain.common.web
 * @ClassName:
 * @Date: 2018年04月27-10:33
 * @Author: createBy:zhangmingyang
 **/
@ControllerAdvice
public class ControllerResultFilter {
    private static Logger logger = LoggerFactory.getLogger(ControllerResultFilter.class);

    /**
     * 业务异常
     *
     * @param businessException
     * @return
     */
    @ExceptionHandler(PurseBusinessException.class)
    @ResponseBody
    public String businessExceptionFilter(PurseBusinessException businessException) {
        printLogger(businessException);
        OutputJson outputJson;
        if (PurseBusinessErrorConstants.PURSE_USER_0007.equals(businessException.getErrorCode())) {
            //token 不存在
            outputJson = new OutputJson(OutputJson.FAILED_TOKEN_NULL, businessException.getErrorMessage(), null);
        } else if (PurseBusinessErrorConstants.PURSE_USER_0006.equals(businessException.getErrorCode())) {
            //异地登录
            outputJson = new OutputJson(OutputJson.FAILED_OFF_SITE_LOGININ, businessException.getErrorMessage(), null);
        } else if (PurseBusinessErrorConstants.PURSE_USER_0003.equals(businessException.getErrorCode())) {
            //登录超时
            outputJson = new OutputJson(OutputJson.FAILED_LOGIN_TIMEOUT, businessException.getErrorMessage(), null);
        } else {
            outputJson = new OutputJson(OutputJson.FAILED_CODE, businessException.getErrorMessage(), null);
        }
        return outputJson.toString();
    }

    /**
     * 平台异常
     *
     * @param businessException
     * @return
     */
    @ExceptionHandler(PlatformException.class)
    @ResponseBody
    public String platformExceptionFilter(PlatformException businessException) {
        printLogger(businessException);
        OutputJson outputJson = new OutputJson(OutputJson.FAILED_CODE, businessException.getErrorMessage(), null);
        return outputJson.toString();
    }

    /**
     * 全局异常
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String exceptionFilter(Throwable throwable) {
        printLogger(throwable);
        OutputJson outputJson = new OutputJson(OutputJson.FAILED_CODE, OutputJson.SYS_EXCEPTION_MESSAGE, null);
        return outputJson.toString();
    }

    /**
     * 输出异常日志
     *
     * @param throwable
     */
    private void printLogger(Throwable throwable) {
        if (throwable instanceof PurseBusinessException) {
            if (logger.isWarnEnabled()) {
                logger.warn("业务异常,cause by:", throwable);
            }
        } else if (throwable instanceof PlatformException) {
            if (logger.isErrorEnabled()) {
                logger.error("平台异常,cuase by:", throwable);
            }
        } else {
            if (logger.isErrorEnabled()) {
                logger.error("系统错误,cuase by:", throwable);
            }
        }
    }
}
