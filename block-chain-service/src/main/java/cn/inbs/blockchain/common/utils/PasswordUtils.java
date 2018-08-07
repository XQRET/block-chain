package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.regex.Pattern;

/**
 * @Description: 密码工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月02-11:29
 * @Author: createBy:zhangmingyang
 **/
public class PasswordUtils {
//    private static Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    private static final int PASSWORD_LENGTH = 6;
    private static final String PASSWORD_REGEX = "^[a-z0-9A-Z]{6,30}$";

    /**
     * 校验密码
     *
     * @param password
     */
    public static void checkPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            //非空校验
            throw new BusinessException(BusinessErrorConstants.PASSWORD_0001);
        } else {
            if (password.length() < PASSWORD_LENGTH) {
                //长度校验
                throw new BusinessException(BusinessErrorConstants.PASSWORD_0001);
            } else {
                //格式校验(正则校验)
                if (!Pattern.matches(PASSWORD_REGEX, password)) {
                    throw new BusinessException(BusinessErrorConstants.PASSWORD_0001);
                }
            }
        }
    }

    private static final String USER_PWD_KEY = "user.pwd.key";//秘钥

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return DigestUtils.md5Hex(password + PropertyUtils.getStringValue(USER_PWD_KEY, null));
    }
}
