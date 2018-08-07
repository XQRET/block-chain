package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月02-12:56
 * @Author: createBy:zhangmingyang
 **/
public class EmailUtils {

    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 校验邮箱
     *
     * @param email
     */
    public static void checkEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new BusinessException(BusinessErrorConstants.EM_CODE_0001, email);
        }
    }
}
