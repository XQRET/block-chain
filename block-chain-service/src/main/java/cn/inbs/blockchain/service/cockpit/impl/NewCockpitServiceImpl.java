package cn.inbs.blockchain.service.cockpit.impl;

import cn.inbs.blockchain.common.cache.ChartCacheUtils;
import cn.inbs.blockchain.dao.po.chart.*;
import cn.inbs.blockchain.service.chart.IChartService;
import cn.inbs.blockchain.service.cockpit.INewCockpitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.cockpit.impl
 * @ClassName: NewCockpitServiceImpl.java
 * @Date: 2018/7/10 16:18
 * @author: create by zhangmingyang
 **/
@Service("newCockpitServiceImpl")
public class NewCockpitServiceImpl implements INewCockpitService {
    @Resource
    private IChartService chartService;

    @Override
    public ChartHeaderBaseData queryChartHeaderBaseData() {
        ChartHeaderBaseData chartHeaderBaseData;

        chartHeaderBaseData = ChartCacheUtils.getChartHeaderBaseData2Cache();
        if (null == chartHeaderBaseData) {
            chartHeaderBaseData = chartService.queryChartHeaderBaseData();
        }
        return chartHeaderBaseData;
    }

    @Override
    public ChartRecentMonthDataArray queryRecentSomeMonthData() {
        ChartRecentMonthDataArray chartRecentMonthDataArray;

        chartRecentMonthDataArray = ChartCacheUtils.getChartRecentMonthDataArray2Cache();
        if (null == chartRecentMonthDataArray) {
            chartRecentMonthDataArray = chartService.queryRecentSomeMonthData(12);
        }
        return chartRecentMonthDataArray;
    }

    @Override
    public ChartCompanyTopArray queryChartCompanyTopArray() {
        ChartCompanyTopArray chartCompanyTopArray;

        chartCompanyTopArray = ChartCacheUtils.getChartCompanyTopArray2Cache();
        if (null == chartCompanyTopArray) {
            chartCompanyTopArray = chartService.queryChartCompanyTopArray(5);
        }
        return chartCompanyTopArray;
    }

    @Override
    public ChartContractProvinceArray queryByCountContractByProvince() {
        ChartContractProvinceArray chartContractProvinceArray;

        chartContractProvinceArray = ChartCacheUtils.getChartContractProvinceArray2Cache();
        if (null == chartContractProvinceArray) {
            chartContractProvinceArray = chartService.queryByCountContractByProvince();
        }
        return chartContractProvinceArray;
    }

    @Override
    public ChartSomeMonthIncomeArray queryBySomeMonthIncome() {
        ChartSomeMonthIncomeArray chartSomeMonthIncomeArray;

        chartSomeMonthIncomeArray = ChartCacheUtils.getChartSomeMonthIncomeArray2Cache();
        if (null == chartSomeMonthIncomeArray) {
            chartSomeMonthIncomeArray = chartService.queryBySomeMonthIncome(6);
        }
        return chartSomeMonthIncomeArray;
    }
}
