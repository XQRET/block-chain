package cn.inbs.blockchain.service.schedulejob.job;

import cn.inbs.blockchain.common.schedulejob.jobcenter.IScheduleJob;
import cn.inbs.blockchain.common.cache.ChartCacheUtils;
import cn.inbs.blockchain.service.chart.IChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 定时任务查询图表数据放入redis
 * @Package: cn.inbs.blockchain.service.schedulejob.job
 * @ClassName: QueryChartScheduleJob.java
 * @Date: 2018/7/6 17:02
 * @author: create by zhangmingyang
 **/
@Service("queryChartScheduleJob")
public class QueryChartScheduleJob implements IScheduleJob {
    private static Logger logger = LoggerFactory.getLogger(QueryChartScheduleJob.class);

    @Resource
    private IChartService chartService;

    @Override
    public void runScheduleJob() {
        //驾驶舱头部八个统计数据
        ChartCacheUtils.createChartHeaderBaseDataCache(chartService.queryChartHeaderBaseData());

        //近12个月数据统计
        ChartCacheUtils.createChartRecentMonthDataArrayCache(chartService.queryRecentSomeMonthData(12));

        //资金资产top
        ChartCacheUtils.createChartCompanyTopArrayCache(chartService.queryChartCompanyTopArray(5));

        //根据省份统计
        ChartCacheUtils.createChartContractProvinceArrayCache(chartService.queryByCountContractByProvince());

        //收入统计
        ChartCacheUtils.createChartSomeMonthIncomeArrayCache(chartService.queryBySomeMonthIncome(6));
    }
}
