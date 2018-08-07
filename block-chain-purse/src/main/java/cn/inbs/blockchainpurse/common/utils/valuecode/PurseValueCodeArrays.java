package cn.inbs.blockchainpurse.common.utils.valuecode;

/**
 * @Description: 码值常量类
 * @Package: cn.inbs.blockchainpurse.common.utils.valuecode
 * @ClassName: PurseValueCodeArrays
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 12:24
 * @Version: 1.0
 */
public class PurseValueCodeArrays {
    /**
     * 短信验证码类型：1-用户注册,2-找回交易密码,3-忘记密码,4-手机认证
     */
    public static final String MESSAGE_CODE_STYPE = "1,2,3,4";
    public static final String[] MESSAGE_CODE_STYPES = dataToArray(MESSAGE_CODE_STYPE);

    private static String[] dataToArray(String dataStr) {
        return dataStr.split(",");
    }
}
