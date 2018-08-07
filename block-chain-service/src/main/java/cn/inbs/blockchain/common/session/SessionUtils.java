package cn.inbs.blockchain.common.session;

import cn.inbs.blockchain.common.cache.CacheIndex;
import cn.inbs.blockchain.common.cache.RedisCache;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.session
 * @ClassName:
 * @Date: 2018年04月26-20:20
 * @Author: createBy:zhangmingyang
 **/
public class SessionUtils {
    private static SessionRedisCache<UserSession> sessionRedis;

    public SessionUtils(RedisTemplate<String, UserSession> redisTemplate, int timeout) {
        sessionRedis = new SessionRedisCache<UserSession>(new RedisCache<UserSession>(UserSession.class, redisTemplate, CacheIndex.USER_SESSION.ordinal()),
                timeout,
                null);
    }

    public static void setLogined(HttpServletRequest request,
                                  HttpServletResponse response,
                                  UserSession session) {
        sessionRedis.createSession(request, response, session);
    }

    public static void updateLogined(HttpServletRequest request,
                                     HttpServletResponse response,
                                     UserSession session) {
        sessionRedis.updateSession(request, response, session);
    }

    public static void update(String sessionId,
                              UserSession session) {
        sessionRedis.updateBySessionId(sessionId, session);
    }

    public static UserSession getLogined(HttpServletRequest request) {
        return sessionRedis.getSession(request);
    }

    public static boolean isLogined(HttpServletRequest request) {
        return sessionRedis.hasSession(request);
    }

    public static UserSession getBySession(String sessionId) {
        return sessionRedis.getSeesionBySessionId(sessionId);
    }

    public static UserSession getAndCheckLoginByHttpContext(HttpServletRequest request) {
        UserSession logined = getLogined(request);
        if (null == logined) {
            throw new BusinessException(BusinessErrorConstants.USER_0009);
        } else {
            return logined;
        }
    }
}
