package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.exception.PlatformErrorConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: Http工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年04月27-18:12
 * @Author: createBy:zhangmingyang
 **/
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);


    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * 获取远程客户端IP
     *
     * @param httpServletRequest
     * @return
     */
    public static String getRemoteClientIP(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader(X_FORWARDED_FOR);
        if (checkIPVal(ip)) {
            ip = httpServletRequest.getHeader(PROXY_CLIENT_IP);
        }
        if (checkIPVal(ip)) {
            ip = httpServletRequest.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (checkIPVal(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }

    private static final String GET_REMOTE_CLIENT_IP_NULL_VAL = "unknown";

    /**
     * 判断ip值
     *
     * @param ip
     * @return
     */
    private static boolean checkIPVal(String ip) {
        return StringUtils.isEmpty(ip) || GET_REMOTE_CLIENT_IP_NULL_VAL.equals(ip);
    }


    private static final String IP_LIST_SPLIT = ",";

    /**
     * 禁止IP
     *
     * @param httpServletRequest
     */
    public static void banIP(HttpServletRequest httpServletRequest, String banSwitchStr, List<String> ipArrsy) {
        //开关打开进行校验
        if (String.valueOf(Boolean.TRUE).equals(banSwitchStr)) {
            //获取IP
            String requestIp = getRemoteClientIP(httpServletRequest);

            //获取允许访问Ip,未配置所有IP允许访问,配置进行校验
            if (CollectionUtils.isNotEmpty(ipArrsy)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("外部请求IP:{}", requestIp);
                }
                boolean isAllow = false;
                for (String s : ipArrsy) {
                    if (requestIp.equals(s)) {
                        isAllow = true;
                        break;
                    }
                }

                if (!isAllow) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("非法人员:{}访问该系统,已被拦截", requestIp);
                    }
                    throw new PlatformException(PlatformErrorConstants.PLATFORM_0004, requestIp);
                }
            }
        }
    }
}
