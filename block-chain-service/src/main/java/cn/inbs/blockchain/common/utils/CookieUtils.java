package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Description: Cookie工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月31-11:08
 * @Author: createBy:zhangmingyang
 **/
public class CookieUtils {
    private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * 从cookie中获取用户Id
     *
     * @param httpServletRequest
     * @return
     */
    public static Long getUserIdInCookie(HttpServletRequest httpServletRequest) {
        String cookieValueByCookieName = getCookieValueByCookieName(httpServletRequest, Constant.COOKIE_NAME_EMPLOYEEID);
        if (null == cookieValueByCookieName) {
            return null;
        } else {
            return Long.valueOf(cookieValueByCookieName);
        }
    }

    /**
     * 从cookie中获取公司区块ID
     *
     * @param httpServletRequest
     * @return
     */
    public static String getCompanyBlockIdInCookie(HttpServletRequest httpServletRequest) {
        return getCookieValueByCookieName(httpServletRequest, CompanyConstants.COOKIE_NAME_COMPANY_BLOCK_ID);
    }

    /**
     * 从cookie中获取公司类型
     *
     * @param httpServletRequest
     * @return
     */
    public static String getCompanyTypeInCookie(HttpServletRequest httpServletRequest) {
        return getCookieValueByCookieName(httpServletRequest, CompanyConstants.COOKIE_NAME_COMPANY_TYPE);
    }

    /**
     * 根据CookieName获取CookieValue
     *
     * @param httpServletRequest
     * @param cookieName
     * @return
     */
    private static String getCookieValueByCookieName(HttpServletRequest httpServletRequest,
                                                     String cookieName) {
        //获取当前cookie
        Cookie[] cookies = httpServletRequest.getCookies();

        String cookieValue = null;

        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    try {
                        cookieValue = URLDecoder.decode(cookie.getValue(), CommonConstants.COMMON_ENCODING);
                    } catch (UnsupportedEncodingException e) {
                        logger.error("从cookie中获取:[{}]异常", cookieName, e);
                    }
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("get cookie : [{}] value :[{}].", cookieName, cookieValue);
        }

        return cookieValue;
    }
}
