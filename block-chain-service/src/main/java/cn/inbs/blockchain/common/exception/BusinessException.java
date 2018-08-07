package cn.inbs.blockchain.common.exception;
/**
 * @Description:平台异常
 * @Package: cn.inbs.platform.exception
 * @ClassName:PlatformException.java
 * @Date: 2018年04月20-13:49
 * @Author: createBy:zhangmingyang
 **/

public class BusinessException extends PlatformException {
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    public BusinessException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String errorCode, String... params) {
        super(errorCode, params);
    }

    public BusinessException(Throwable e, String errorCode, String... params) {
        super(e, errorCode, params);
    }

    @Override
    public String getErrorCode() {
        return super.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public void setErrorCode(String errorCode) {
        super.setErrorCode(errorCode);
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
    }
}
