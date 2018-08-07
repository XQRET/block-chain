package cn.inbs.blockchainpurse.common.cache;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 币种转换缓存
 * @Package: cn.inbs.blockchainpurse.common.cache
 * @ClassName: ConversionCurrencyCache
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 12:26
 * @Version: 1.0
 */
public class ConversionCurrencyCache {
    private static BaseCache<String> currencyCache;
    private static final String CURRENCY_CACHE_KEY = "conversionCurrency:";
    private static final long CURRENCY_TIME_OUT = 60 * 60 * 3;

    public ConversionCurrencyCache(RedisTemplate<String, String> currencyRedisTemplate) {
        currencyCache = new RedisCache<>(String.class, currencyRedisTemplate, PurseCacheIndex.CONVERSION_CURRENCY.ordinal());
    }

    public static void createConversionCurrencyCache(String conversionCurrency, String value) {
        currencyCache.put(CURRENCY_CACHE_KEY + conversionCurrency, value, CURRENCY_TIME_OUT);
    }

    public static String getConversionCurrencyInCache(String conversionCurrency) {
        return currencyCache.get(CURRENCY_CACHE_KEY + conversionCurrency);
    }

}
