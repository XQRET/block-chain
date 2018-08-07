package cn.inbs.blockchain.service.cockpit;

import cn.inbs.blockchain.dao.po.chart.*;

/**
 * @Description: 数据统计服务
 * @Package: cn.inbs.blockchain.service.cockpit
 * @ClassName: INewCockpitService.java
 * @Date: 2018/7/10 16:18
 * @author: create by zhangmingyang
 **/
public interface INewCockpitService {
    /**
     * 图表头部八个基础数据
     *
     * @return
     */
    ChartHeaderBaseData queryChartHeaderBaseData();

    /**
     * 近期数据统计
     *
     * @return
     */
    ChartRecentMonthDataArray queryRecentSomeMonthData();

    /**
     * 获取top+all
     *
     * @return
     */
    ChartCompanyTopArray queryChartCompanyTopArray();

    /**
     * 根据省份统计合约
     *
     * @return
     */
    ChartContractProvinceArray queryByCountContractByProvince();

    /**
     * 收入统计
     *
     * @return
     */
    ChartSomeMonthIncomeArray queryBySomeMonthIncome();
}
