package cn.inbs.blockchain.service.chart;

import cn.inbs.blockchain.dao.po.chart.*;

/**
 * @Description: 数据统计接口
 * @Package: cn.inbs.blockchain.service.chart
 * @ClassName: IChartService.java
 * @Date: 2018/7/7 11:22
 * @author: create by zhangmingyang
 **/
public interface IChartService {
    /**
     * 图表头部八个基础数据
     *
     * @return
     */
    ChartHeaderBaseData queryChartHeaderBaseData();

    /**
     * 近期数据统计
     *
     * @param monthCount 近期多少个(月)
     * @return
     */
    ChartRecentMonthDataArray queryRecentSomeMonthData(Integer monthCount);

    /**
     * 获取top
     *
     * @param topCount
     * @return
     */
    ChartCompanyTopArray queryChartCompanyTopArray(Integer topCount);

    /**
     * 根据省份统计合约
     *
     * @return
     */
    ChartContractProvinceArray queryByCountContractByProvince();

    /**
     * 收入统计
     *
     * @param monthCount
     * @return
     */
    ChartSomeMonthIncomeArray queryBySomeMonthIncome(Integer monthCount);
}
