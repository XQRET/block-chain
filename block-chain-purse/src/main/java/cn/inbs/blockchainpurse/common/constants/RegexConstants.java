package cn.inbs.blockchainpurse.common.constants;

/**
 * @Description: 所有这则表达式常量类
 * @Package: cn.inbs.blockchainpurse.common.constants
 * @ClassName: RegexConstants
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/23 16:30
 * @Version: 1.0
 */
public class RegexConstants {
    /**
     * 数字与英文正则
     */
    public static String LETTER_NUM = "[A-Za-z0-9\\-]*";

    /**
     * 纯英文
     */
    public static String LETTER = "[a-zA-Z]*";

    /**
     * 钱包名称正则表达式
     */
    public static final String PURSE_NAME = "[A-Za-z0-9\\-]*";

    /**
     * 手机号正则表达式
     */
    public static final String MOBILE_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
}
