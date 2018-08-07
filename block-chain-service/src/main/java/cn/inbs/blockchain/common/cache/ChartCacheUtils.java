package cn.inbs.blockchain.common.cache;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.CacheIndex;
import cn.inbs.blockchain.common.cache.RedisCache;
import cn.inbs.blockchain.dao.po.chart.*;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: ChartCacheUtils.java
 * @Date: 2018/7/6 17:07
 * @author: create by zhangmingyang
 **/
public class ChartCacheUtils {
    private static final int CACHE_TIME_OUT = 60 * 3;

    /**
     * 八个基础数据
     */
    private static BaseCache<ChartHeaderBaseData> chartHeaderBaseDataCache;
    private static final String CHART_HEADER_BASE_DATA_KEY = "chart:chartHeaderBaseData";

    /**
     * 近数据月统计
     */
    private static BaseCache<ChartRecentMonthDataArray> chartRecentMonthDataArrayCache;
    private static final String CHART_RECENT_MONTH_DATA_ARRAY_KEY = "chart:chartRecentMonthDataArray";

    /**
     * top
     */
    private static BaseCache<ChartCompanyTopArray> chartCompanyTopArrayCache;
    private static final String CHART_COMPANY_TOP_ARRAY_KEY = "chart:chartCompanyTopArray";

    /**
     * 根据省份统计
     */
    private static BaseCache<ChartContractProvinceArray> chartContractProvinceArrayCache;
    private static final String CHART_CONTRACT_PROVINCE_ARRAY_KEY = "chart:chartContractProvinceArray";

    /**
     * 收入统计
     */
    private static BaseCache<ChartSomeMonthIncomeArray> chartSomeMonthIncomeArrayCache;
    private static final String CHART_SOME_MONTH_INCOME_ARRAY_KEY = "chart:chartSomeMonthIncomeArray";

    public ChartCacheUtils(RedisTemplate<String, ChartHeaderBaseData> chartHeaderBaseDataRedisTemplate,
                           RedisTemplate<String, ChartRecentMonthDataArray> chartRecentMonthDataArrayRedisTemplate,
                           RedisTemplate<String, ChartCompanyTopArray> chartCompanyTopArrayRedisTemplate,
                           RedisTemplate<String, ChartContractProvinceArray> chartContractProvinceArrayRedisTemplate,
                           RedisTemplate<String, ChartSomeMonthIncomeArray> chartSomeMonthIncomeArrayRedisTemplate) {
        chartHeaderBaseDataCache = new RedisCache<ChartHeaderBaseData>(ChartHeaderBaseData.class, chartHeaderBaseDataRedisTemplate, CacheIndex.CHART_DATA.ordinal());//基本数据统计
        chartRecentMonthDataArrayCache = new RedisCache<ChartRecentMonthDataArray>(ChartRecentMonthDataArray.class, chartRecentMonthDataArrayRedisTemplate, CacheIndex.CHART_DATA.ordinal());//近期月数统计
        chartCompanyTopArrayCache = new RedisCache<ChartCompanyTopArray>(ChartCompanyTopArray.class, chartCompanyTopArrayRedisTemplate, CacheIndex.CHART_DATA.ordinal());//top统计
        chartContractProvinceArrayCache = new RedisCache<ChartContractProvinceArray>(ChartContractProvinceArray.class, chartContractProvinceArrayRedisTemplate, CacheIndex.CHART_DATA.ordinal());//省份
        chartSomeMonthIncomeArrayCache = new RedisCache<ChartSomeMonthIncomeArray>(ChartSomeMonthIncomeArray.class, chartSomeMonthIncomeArrayRedisTemplate, CacheIndex.CHART_DATA.ordinal());//收入
    }

    /*==================================================================八项基本数据*/

    /**
     * 头部八项基本数据
     *
     * @param chartHeaderBaseData
     */
    public static void createChartHeaderBaseDataCache(ChartHeaderBaseData chartHeaderBaseData) {
        chartHeaderBaseDataCache.put(CHART_HEADER_BASE_DATA_KEY, chartHeaderBaseData, CACHE_TIME_OUT);
    }

    /**
     * 从缓存获取八项基本数据
     *
     * @return
     */
    public static ChartHeaderBaseData getChartHeaderBaseData2Cache() {
        return chartHeaderBaseDataCache.get(CHART_HEADER_BASE_DATA_KEY);
    }


    /*==================================================================近数月数据统计*/

    /**
     * 近数月数据统计
     *
     * @param chartRecentMonthDataArray
     */
    public static void createChartRecentMonthDataArrayCache(ChartRecentMonthDataArray chartRecentMonthDataArray) {
        chartRecentMonthDataArrayCache.put(CHART_RECENT_MONTH_DATA_ARRAY_KEY, chartRecentMonthDataArray, CACHE_TIME_OUT);
    }

    /**
     * 从缓存获取近数月数据统计
     *
     * @return
     */
    public static ChartRecentMonthDataArray getChartRecentMonthDataArray2Cache() {
        return chartRecentMonthDataArrayCache.get(CHART_RECENT_MONTH_DATA_ARRAY_KEY);
    }


    /*==================================================================top*/

    /**
     * 缓存top
     *
     * @param chartCompanyTopArray
     */
    public static void createChartCompanyTopArrayCache(ChartCompanyTopArray chartCompanyTopArray) {
        chartCompanyTopArrayCache.put(CHART_COMPANY_TOP_ARRAY_KEY, chartCompanyTopArray, CACHE_TIME_OUT);
    }

    /**
     * 从缓存获取top
     *
     * @return
     */
    public static ChartCompanyTopArray getChartCompanyTopArray2Cache() {
        return chartCompanyTopArrayCache.get(CHART_COMPANY_TOP_ARRAY_KEY);
    }


    /*==================================================================根据省份统计*/

    /**
     * 缓存省份统计
     *
     * @param chartContractProvinceArray
     */
    public static void createChartContractProvinceArrayCache(ChartContractProvinceArray chartContractProvinceArray) {
        chartContractProvinceArrayCache.put(CHART_CONTRACT_PROVINCE_ARRAY_KEY, chartContractProvinceArray);
    }

    /**
     * 从缓存获取省份统计
     *
     * @return
     */
    public static ChartContractProvinceArray getChartContractProvinceArray2Cache() {
        return chartContractProvinceArrayCache.get(CHART_CONTRACT_PROVINCE_ARRAY_KEY);
    }

    /*==================================================================收入统计*/

    /**
     * 缓存收入统计
     *
     * @param chartSomeMonthIncomeArray
     */
    public static void createChartSomeMonthIncomeArrayCache(ChartSomeMonthIncomeArray chartSomeMonthIncomeArray) {
        chartSomeMonthIncomeArrayCache.put(CHART_SOME_MONTH_INCOME_ARRAY_KEY, chartSomeMonthIncomeArray);
    }

    /**
     * 从缓存获取收入统计
     *
     * @return
     */
    public static ChartSomeMonthIncomeArray getChartSomeMonthIncomeArray2Cache() {
        return chartSomeMonthIncomeArrayCache.get(CHART_SOME_MONTH_INCOME_ARRAY_KEY);
    }
}
