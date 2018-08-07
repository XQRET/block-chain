package cn.inbs.blockchainpurse.common.cache;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 用户token 缓存
 * @Package: cn.inbs.blockchainpurse.common.cache
 * @ClassName: UserTokenCache
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 12:26
 * @Version: 1.0
 */
public class UserTokenCache {
    /**
     * 八个基础数据
     */
    private static BaseCache<String> tokenCache;
    private static final String TOKEN_CACHE_KEY = "userToken:";
    private static final long TOKEN_TIME_OUT = 60 * 60 * 24 * 3;

    public UserTokenCache(RedisTemplate<String, String> tokenRedisTemplate) {
        tokenCache = new RedisCache<>(String.class, tokenRedisTemplate, PurseCacheIndex.USER_TOKEN.ordinal());
    }

    public static void createTokenCache(String purseName, String value) {
        tokenCache.put(TOKEN_CACHE_KEY + purseName, value, TOKEN_TIME_OUT);
    }

    public static String getTokenInCache(String purseName) {
        return tokenCache.get(TOKEN_CACHE_KEY + purseName);
    }


}
