package cn.inbs.blockchain.common.session;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: session缓存到redis
 * @Package: cn.inbs.blockchain.common.session
 * @ClassName:
 * @Date: 2018年04月26-18:41
 * @Author: createBy:zhangmingyang
 **/
public class SessionRedisCache<T extends SessionObject> {
    private static Logger logger = LoggerFactory.getLogger(SessionRedisCache.class);

    private static final String COOKIE_NAME = "sessionFlag";//cookie名称
    private static final String SESSION_REDIS_KEY = "applogin:userid:"; //redis 前缀前缀
    private static final String SESSION_ID_START = "sessionid_"; //redis 前缀前缀

    private BaseCache<T> sessionCache;//session缓存对象
    private int sessionTimeOut = 24 * 60 * 60;//session默认保存时间
    private String cookiePath = "/";

    public SessionRedisCache(BaseCache<T> sessionCache, int sessionTimeOut, String cookiePath) {
        this.sessionCache = sessionCache;
        this.sessionTimeOut = sessionTimeOut;
        this.cookiePath = cookiePath;
    }

    /**
     * 创建session
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param sessionObject
     */
    public void createSession(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              T sessionObject) {
        if (null != sessionObject &&
                null != httpServletRequest &&
                null != httpServletResponse) {
            //组装及设置sessionId
            String sessionId = SESSION_ID_START + sessionObject.getUserId();
            sessionObject.setSessionId(sessionId);

            //创建Cookie
            createCookie(httpServletRequest, httpServletResponse, sessionId);

            //创建缓存
            this.sessionCache.put(SESSION_REDIS_KEY + sessionId, sessionObject, this.sessionTimeOut);
            if (logger.isDebugEnabled()) {
                logger.debug("session创建缓存成功");
            }
        } else {
            removeSession(httpServletRequest, httpServletResponse);
        }
    }

    /**
     * 更新session
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param sessionObject
     */
    public void updateSession(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              T sessionObject) {
        if (null != sessionObject) {
            String sessionId = getSessionIdInCookie(httpServletRequest);
            if (StringUtils.isEmpty(sessionId)) {
                this.sessionCache.update(SESSION_REDIS_KEY + sessionId, sessionObject, this.sessionTimeOut);
            }
        } else {
            removeSession(httpServletRequest, httpServletResponse);
        }
    }

    /**
     * 根据sessionId修改
     *
     * @param sessionId
     * @param tessionObject
     */
    public void updateBySessionId(String sessionId, T tessionObject) {
        if (StringUtils.isNotEmpty(sessionId) && null == tessionObject) {
            this.sessionCache.get(SESSION_REDIS_KEY + sessionId);
        }
    }

    /**
     * 获取session
     *
     * @param httpServletRequest
     * @return
     */
    public T getSession(HttpServletRequest httpServletRequest) {
        String sessionId = getSessionIdInCookie(httpServletRequest);
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        } else {
            return this.sessionCache.get(SESSION_REDIS_KEY + sessionId);
        }
    }

    /**
     * 根据sessionId获取session
     *
     * @param sessionId
     * @return
     */
    public T getSeesionBySessionId(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        } else {
            return this.sessionCache.get(SESSION_REDIS_KEY + sessionId);
        }
    }

    /**
     * 判断是否存在session
     *
     * @param httpServletRequest
     * @return
     */
    public boolean hasSession(HttpServletRequest httpServletRequest) {
        return null != getSession(httpServletRequest);
    }

    /**
     * 清空session
     *
     * @param httpServletRequest
     * @param httpServletResponse
     */
    private void removeSession(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
        String sessionId = getSessionIdInCookie(httpServletRequest);
        if (StringUtils.isNotEmpty(sessionId)) {
            this.sessionCache.del(SESSION_REDIS_KEY + sessionId);
            createCookie(httpServletRequest, httpServletResponse, null);
        }
    }

    /**
     * 创建Cookie
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param sessionId
     */
    private void createCookie(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              String sessionId) {
        Cookie cookie = new Cookie(COOKIE_NAME, sessionId);
        cookie.setPath((this.cookiePath == null) ? httpServletRequest.getContextPath() : this.cookiePath);//路径
        cookie.setMaxAge((sessionId == null) ? 0 : this.sessionTimeOut);//保存时间
        httpServletResponse.addCookie(cookie);

        if (logger.isDebugEnabled()) {
            logger.debug("创建Cookie:[{}]成功", sessionId);
        }
    }

    /**
     * 获取Cookie
     *
     * @param request
     * @return
     */
    private String getSessionIdInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
