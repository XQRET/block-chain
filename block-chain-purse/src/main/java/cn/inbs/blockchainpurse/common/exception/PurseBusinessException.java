package cn.inbs.blockchainpurse.common.exception;

import cn.inbs.blockchain.common.exception.PlatformException;

/**
 * @Description: 区块链钱包业务异常类
 * @Package: cn.inbs.blockchainpurse.common.exception
 * @ClassName: PurseBusinessException
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 12:18
 * @Version: 1.0
 */
public class PurseBusinessException extends PlatformException {

    public PurseBusinessException() {
    }

    public PurseBusinessException(Throwable e) {
        super(e);
    }

    public PurseBusinessException(String errorCode, String... params) {
        super(errorCode, params);
    }

    public PurseBusinessException(Throwable e, String errorCode, String... params) {
        super(e, errorCode, params);
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
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
