package cn.inbs.blockchain.common.exception;

import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 平台异常
 * @Package: cn.inbs.blockchain.common.exception
 * @ClassName: PlatformException
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 11:14
 * @Version: 1.0
 */
public class PlatformException extends RuntimeException {
    private static Logger logger = LoggerFactory.getLogger(PlatformException.class);

    private static final String PARAM_PLACEHOLDER_SYM = "\\{}";//参数占位符
    private static final String SYM = " : ";//错误码异常消息分隔符


    private String errorCode;//错误码
    private String errorMessage;//错误消息

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    public PlatformException() {
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public PlatformException(Throwable e) {
        super(e);
        this.errorMessage = e.getMessage();
    }

    /**
     * @param errorCode 错误码
     */
    private PlatformException(String errorCode) {
        super(assemblyErrorMessage(errorCode));
        this.errorCode = errorCode;
        this.errorMessage = assemblyErrorMessage(errorCode);
    }

    /**
     * @param errorCode 错误码
     * @param params    错误消息组织参数
     */
    public PlatformException(String errorCode, String... params) {
        super(assemblyErrorMessage(errorCode, params));
        this.errorCode = errorCode;
        this.errorMessage = assemblyErrorMessage(errorCode, params);
    }

    /**
     * @param e         异常
     * @param errorCode 错误码
     * @param params    错误消息组织参数
     */
    public PlatformException(Throwable e, String errorCode, String... params) {
        super(assemblyErrorMessage(errorCode, params), e);
        this.errorCode = errorCode;
        this.errorMessage = assemblyErrorMessage(errorCode, params);
    }


    /**
     * ===========================================================================================
     */
    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 组织错误消息
     *
     * @param errorCode
     * @param params
     * @return
     */
    private static String assemblyErrorMessage(String errorCode,
                                               String... params) {
        //占位符匹配
        String propertyMessage = PropertyUtils.getStringValue(errorCode, null);

        if (StringUtils.isEmpty(propertyMessage)) {
            if (logger.isWarnEnabled()) {
                logger.warn("错误码:[{}]未定义", errorCode);
            }
            return errorCode;
        } else {
            String assemblyExcMessage = propertyMessage;
            if (null != params) {
                for (String param : params) {
                    assemblyExcMessage = assemblyExcMessage.replaceFirst(PARAM_PLACEHOLDER_SYM, param);
                }
            }
            return assemblyExcMessage;
        }
    }

}
