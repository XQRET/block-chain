package cn.inbs.blockchain.common.web;

import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.exception.PlatformException;
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
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public String businessExceptionFilter(BusinessException businessException) {
        printLogger(businessException);
        OutputJson outputJson = new OutputJson(OutputJson.FAILED_CODE, businessException.getErrorMessage(), null);
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
        if (throwable instanceof BusinessException) {
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
