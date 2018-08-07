package cn.inbs.blockchainpurse.common.cache;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.RedisCache;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 短信验证码缓存
 * @Package: cn.inbs.blockchainpurse.common.cache
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
public class MessageCodeCache {

    /**
     * 八个基础数据
     */
    private static BaseCache<String> messageCodeCache;
    private static final String MESSAGE_CODE_CACHE_KEY = "messageCode:";
    private static final long TOKEN_TIME_OUT = 60 * 60;

    public MessageCodeCache(RedisTemplate<String, String> messageCodeRedisTemplate) {
        messageCodeCache = new RedisCache<>(String.class, messageCodeRedisTemplate, PurseCacheIndex.USER_TOKEN.ordinal());
    }

    public static void createMessageCodeCache(String mobile, String stype, String messageCode) {
        messageCodeCache.put(MESSAGE_CODE_CACHE_KEY + mobile + stype, messageCode, PropertyUtils.getLongValue(PurseConfigPropertyConstants.MESSAGE_CODE_TIME_OUT, TOKEN_TIME_OUT));
    }

    public static String getMessageCodeInCache(String mobile, String stype) {
        return messageCodeCache.get(MESSAGE_CODE_CACHE_KEY + mobile + stype);
    }


}
