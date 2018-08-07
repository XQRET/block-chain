package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.exception.PlatformErrorConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:ExceptionUtils.java
 * @Date: 2018年04月21-16:06
 * @Author: createBy:zhangmingyang
 **/
public class ExceptionUtils {
    private static Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    private static final String SYM = ">>>";

    private static final String SYSTEM_ERROR_MESSAGE = "异常,请您稍后重试";

    /**
     * 根据给定异常类型获取最终导致异常的信息
     *
     * @param throwable
     * @return
     */
    public static String getFinallyExceptionMessage(Throwable throwable) {
        if (null == throwable) {
            if (logger.isErrorEnabled()) {
                logger.error("根据异常获取最终错误消息时,请给定相应的异常类型");
            }
            throw new PlatformException(PlatformErrorConstants.PLATFORM_0001);
        }

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (throwable.getCause() == null) {
                return throwable.getMessage();
            } else {
                throwable = throwable.getCause();
            }
        }
        return throwable.getMessage();
    }

    /**
     * 获取当前异常的所有信息
     *
     * @param throwable
     * @return
     */
    public static String getExceptionAllCauseMessaeg(Throwable throwable) {
        if (null == throwable) {
            if (logger.isErrorEnabled()) {
                logger.error("获取所有异常信息时,请给定相应的异常类型");
            }
            throw new PlatformException(PlatformErrorConstants.PLATFORM_0002);
        }

        StringBuilder stringBuilder = new StringBuilder(throwable.getMessage());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (throwable.getCause() == null) {
                return stringBuilder.toString();
            } else {
                stringBuilder.append(SYM);
                throwable = throwable.getCause();
                stringBuilder.append(throwable.getMessage());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 根据异常类型获取返回异常信息
     *
     * @param throwable
     * @return
     */
    public static String getResultMessage(Throwable throwable) {
        if (throwable instanceof PlatformException) {
            return ((PlatformException) throwable).getErrorMessage();
        } else {
            return SYSTEM_ERROR_MESSAGE;
        }
    }

    /**
     * 组织返回消息
     *
     * @param businessException
     * @return
     */
    public static String createResultMesage(PlatformException businessException) {
        if (null != businessException) {
            return businessException.getErrorMessage();
        } else {
            return null;
        }
    }
}
