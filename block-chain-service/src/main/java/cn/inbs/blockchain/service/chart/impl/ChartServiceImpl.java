package cn.inbs.blockchain.service.chart.impl;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.common.utils.ComparatorUtils;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.dao.chart.ChartMapper;
import cn.inbs.blockchain.dao.po.chart.*;
import cn.inbs.blockchain.service.chart.IChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 数据统计接口实现类
 * @Package: cn.inbs.blockchain.service.chart.impl
 * @ClassName: ChartServiceImpl.java
 * @Date: 2018/7/7 11:22
 * @author: create by zhangmingyang
 **/

@Service("chartService")
public class ChartServiceImpl implements IChartService {
    private static Logger logger = LoggerFactory.getLogger(ChartServiceImpl.class);

    /**
     * 金额单位(万元)
     */
    private static final BigDecimal AMOUNT_UNIT = new BigDecimal(10000);

    /**
     * 收入利率
     */
    private static final BigDecimal INIT_INCOME_RATE = new BigDecimal(0.015);

    /**
     * 省份列表
     */
    private static final String[] PROVINCE_ARRAY = {
            "北京", "天津", "上海", "重庆", "河北", "河南", "云南", "辽宁", "湖南", "安徽",
            "山东", "新疆", "江苏", "浙江", "江西", "湖北", "广西", "甘肃", "山西", "陕西",
            "吉林", "福建", "贵州", "广东", "青海", "西藏", "四川", "宁夏", "海南", "台湾",
            "香港", "澳门", "黑龙江", "内蒙古", "南海诸岛"};

    @Resource
    private ChartMapper chartMapper;

    /**
     * 获取头部基础数据
     *
     * @return
     */
    @Override
    public ChartHeaderBaseData queryChartHeaderBaseData() {
        ChartHeaderBaseData chartHeaderBaseData = new ChartHeaderBaseData();

        /*1.========================================合约个数统计*/
        Map<String, Object> contractCountConditionMap = new HashMap<String, Object>();
        //1.1.平台合约总数（个）
        int totalContractCount = chartMapper.selectContractCountByCondition(contractCountConditionMap);
        chartHeaderBaseData.setTotalContractCount(totalContractCount);

        //1.2.平台生效合约数（个）
        contractCountConditionMap.clear();
        contractCountConditionMap.put("contractStatusList", getEffectiveStatusList());
        int effectiveContractCount = chartMapper.selectContractCountByCondition(contractCountConditionMap);
        chartHeaderBaseData.setEffectiveContractCount(effectiveContractCount);

        //1.3.当月新增合约个数（个）
        contractCountConditionMap.clear();
        contractCountConditionMap.put("registStartDate", DateUtils.getMonthStartTime(new Date()));//当月开始时间
        int currentMonthNewContractCount = chartMapper.selectContractCountByCondition(contractCountConditionMap);
        chartHeaderBaseData.setCurrentMonthNewContractCount(currentMonthNewContractCount);

        /*2.========================================公司用户统计*/
        //2.1.资产端用户总个数（个）
        int assetCompanyCount = chartMapper.selectCompanyTotalCountByType(CompanyConstants.COMPANY_TYPE_ZC);
        chartHeaderBaseData.setAssetCompanyCount(assetCompanyCount);

        //2.2.资金端用户总个数（个）
        int capitalCompanyCount = chartMapper.selectCompanyTotalCountByType(CompanyConstants.COMPANY_TYPE_ZJ);
        chartHeaderBaseData.setCapitalCompanyCount(capitalCompanyCount);

        /*3.========================================合约金额统计*/
        Map<String, Object> contractAmountConditionMap = new HashMap<String, Object>();
        //3.1.平台合约总金额（万元）
        BigDecimal totalContractAmount = chartMapper.selectContractAmountByCondition(contractAmountConditionMap);
        if (null != totalContractAmount) {
            chartHeaderBaseData.setTotalContractAmount(totalContractAmount.divide(AMOUNT_UNIT, 2, BigDecimal.ROUND_HALF_DOWN));
        }

        //3.2.平台生效合约金额（万元）
        contractAmountConditionMap.clear();
        contractAmountConditionMap.put("contractStatusList", getEffectiveStatusList());
        BigDecimal effectiveContractAmount = chartMapper.selectContractAmountByCondition(contractAmountConditionMap);
        if (null != effectiveContractAmount) {
            chartHeaderBaseData.setEffectiveContractAmount(effectiveContractAmount.divide(AMOUNT_UNIT, 2, BigDecimal.ROUND_HALF_DOWN));
        }

        //3.3.当月新增合约金额（万元）
        contractAmountConditionMap.clear();
        contractAmountConditionMap.put("registStartDate", DateUtils.getMonthStartTime(new Date()));//当月开始时间
        BigDecimal currentMonthNewAmount = chartMapper.selectContractAmountByCondition(contractCountConditionMap);
        if (null != currentMonthNewAmount) {
            chartHeaderBaseData.setCurrentMonthNewAmount(currentMonthNewAmount.divide(AMOUNT_UNIT, 2, BigDecimal.ROUND_HALF_DOWN));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("表头部基本数据:[{}]", chartHeaderBaseData.toString());
        }

        return chartHeaderBaseData;
    }

    /**
     * 近数月合约统计
     *
     * @param monthCount 近期多少个(月)
     * @return
     */
    @Override
    public ChartRecentMonthDataArray queryRecentSomeMonthData(Integer monthCount) {
        String dateFormat = DateUtils.DateFormat.DATE_FORMAT_11;

        List<RecentSomeMonthSingle> recentSomeMonthDataList = new ArrayList<RecentSomeMonthSingle>();
        Date currentDate = new Date();
        for (int i = 0; i < monthCount; i++) {
            RecentSomeMonthSingle currentRecentSomeMonthData = new RecentSomeMonthSingle();

            //当前月前i个月的最开始时间
            Date currentMonth = DateUtils.getDateAfterOrBeforeMonth(currentDate, -i);
            Date currentMonthStartTime = DateUtils.getMonthStartTime(currentMonth);

            //获取currentMonth下个月最开始时间
            Date nextMonth = DateUtils.getDateAfterOrBeforeMonth(currentMonth, 1);
            Date nextMonthStartTime = DateUtils.getMonthStartTime(nextMonth);

            //当前月合约总数
            Map<String, Object> conditionCountMap = new HashMap<String, Object>();
            conditionCountMap.put("registStartDate", currentMonthStartTime);
            conditionCountMap.put("registEndDate", nextMonthStartTime);
            int currentMonthContractTotalCount = chartMapper.selectContractCountByCondition(conditionCountMap);
            currentRecentSomeMonthData.setContractMonthCount(currentMonthContractTotalCount);


            //当前月合约总金额
            Map<String, Object> conditionAmountMap = new HashMap<String, Object>();
            conditionAmountMap.put("registStartDate", currentMonthStartTime);
            conditionAmountMap.put("registEndDate", nextMonthStartTime);
            BigDecimal currentMonthContractTotalAmount = chartMapper.selectContractAmountByCondition(conditionAmountMap);
            if (null != currentMonthContractTotalAmount) {
                currentMonthContractTotalAmount = currentMonthContractTotalAmount.divide(AMOUNT_UNIT, 2, BigDecimal.ROUND_HALF_DOWN);
                currentRecentSomeMonthData.setContractMonthAmount(currentMonthContractTotalAmount);
            }

            //月份
            currentRecentSomeMonthData.setDataBelongMonthTime(DateUtils.formatDate(currentMonth, dateFormat));

            recentSomeMonthDataList.add(currentRecentSomeMonthData);
        }
        recentSomeMonthDataList = ComparatorUtils.orderRecentSomeMonthData(recentSomeMonthDataList, dateFormat);

        ChartRecentMonthDataArray recentMonthDataArray = new ChartRecentMonthDataArray();
        recentMonthDataArray.setRecentSomeMonthDataList(recentSomeMonthDataList);

        if (logger.isDebugEnabled()) {
            logger.debug("近数月统计数据:[{}]", recentMonthDataArray.toString());
        }

        return recentMonthDataArray;
    }


    /**
     * top统计
     *
     * @param topCount
     * @return
     */
    @Override
    public ChartCompanyTopArray queryChartCompanyTopArray(Integer topCount) {
        ChartCompanyTopArray chartCompanyTopArray = new ChartCompanyTopArray();
        /*========================================查询资产端top*/
        List<Map<String, Object>> zcTopList = chartMapper.selectZcTop(null);
        chartCompanyTopArray.setZcTop(conversionData(zcTopList, topCount));

        /*========================================查询资产端all*/
        chartCompanyTopArray.setZcAll(conversionData(zcTopList, zcTopList.size()));

        /*========================================查询资金端top*/
        List<Map<String, Object>> zjTopList = chartMapper.selectZjTop(null);
        chartCompanyTopArray.setzJTop(conversionData(zjTopList, topCount));

        /*========================================查询资金端all*/
        chartCompanyTopArray.setZjAll(conversionData(zjTopList, zcTopList.size()));

        if (logger.isDebugEnabled()) {
            logger.debug("top统计:[{}]", chartCompanyTopArray);
        }
        return chartCompanyTopArray;
    }

    /**
     * 省份统计
     *
     * @return
     */
    @Override
    public ChartContractProvinceArray queryByCountContractByProvince() {
        //数据初始化
        List<ContractProvinceSingle> contractProvinceSingleList = new ArrayList<ContractProvinceSingle>();
        for (String province : PROVINCE_ARRAY) {
            ContractProvinceSingle contractProvinceSingle = new ContractProvinceSingle();
            contractProvinceSingle.setContractCount(0L);
            contractProvinceSingle.setProvinceName(province);
            contractProvinceSingleList.add(contractProvinceSingle);
        }

        //根据省份统计合约,执行值替换
        List<Map<String, Object>> dataList = chartMapper.selectContractNumGroupByProvince();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> map : dataList) {
                Object province = map.get("province");
                if (null != province) {
                    String provinceName = (String) province;
                    for (ContractProvinceSingle contractProvinceSingle : contractProvinceSingleList) {
                        if (provinceName.equals(contractProvinceSingle.getProvinceName())) {
                            Object contractTotalCount = map.get("contractTotalCount");
                            if (null != contractTotalCount) {
                                contractProvinceSingle.setContractCount((Long) contractTotalCount);
                            }
                            break;
                        }
                    }
                }
            }
        }

        ChartContractProvinceArray chartContractProvinceArray = new ChartContractProvinceArray();
        chartContractProvinceArray.setContractProvinceSingleList(contractProvinceSingleList);

        if (logger.isDebugEnabled()) {
            logger.debug("省份统计:[{}]", chartContractProvinceArray.toString());
        }
        return chartContractProvinceArray;
    }

    @Override
    public ChartSomeMonthIncomeArray queryBySomeMonthIncome(Integer monthCount) {
        String dateFormat = DateUtils.DateFormat.DATE_FORMAT_11;
        Date currentDate = new Date();

        List<SomeMonthIncomeSingle> someMonthIncomeSingleList = new ArrayList<SomeMonthIncomeSingle>();
        for (int i = 0; i < monthCount; i++) {
            SomeMonthIncomeSingle someMonthIncomeSingle = new SomeMonthIncomeSingle();

            //当前月前i个月的最开始时间
            Date currentMonth = DateUtils.getDateAfterOrBeforeMonth(currentDate, -i);
            Date currentMonthStartTime = DateUtils.getMonthStartTime(currentMonth);

            //获取currentMonth下个月最开始时间
            Date nextMonth = DateUtils.getDateAfterOrBeforeMonth(currentMonth, 1);
            Date nextMonthStartTime = DateUtils.getMonthStartTime(nextMonth);

            Map<String, Object> conditionMap = new HashMap<String, Object>();
            conditionMap.put("registStartDate", currentMonthStartTime);
            conditionMap.put("registEndDate", nextMonthStartTime);
            BigDecimal monthSumAmount = chartMapper.selectContractAmountByCondition(conditionMap);

            if (null != monthSumAmount) {
                monthSumAmount = monthSumAmount.multiply(INIT_INCOME_RATE);//收入=总额*利率
                monthSumAmount = monthSumAmount.divide(AMOUNT_UNIT, 2, BigDecimal.ROUND_HALF_DOWN);//换算为万元
                someMonthIncomeSingle.setIncomeAmount(monthSumAmount);
            }
            someMonthIncomeSingle.setMonthStr(DateUtils.formatDate(currentMonth, dateFormat));

            someMonthIncomeSingleList.add(someMonthIncomeSingle);
        }

        //将数据按时间排序
        someMonthIncomeSingleList = ComparatorUtils.orderRecentSomeMonthIncome(someMonthIncomeSingleList, dateFormat);

        //组装返回值
        ChartSomeMonthIncomeArray chartSomeMonthIncomeArray = new ChartSomeMonthIncomeArray();
        chartSomeMonthIncomeArray.setSomeMonthIncomeSingleList(someMonthIncomeSingleList);

        if (logger.isDebugEnabled()) {
            logger.debug("近期收入统计:[{}]", chartSomeMonthIncomeArray.toString());
        }
        return chartSomeMonthIncomeArray;
    }


    /**
     * 资金,资产金额top结果参数转换
     *
     * @param dataList
     * @param getDataSize 获取数据列表数
     * @return
     */
    private List<CompanyTopSingle> conversionData(List<Map<String, Object>> dataList, Integer getDataSize) {
        List<CompanyTopSingle> companyTopSingleList = new ArrayList<CompanyTopSingle>();

        //防止数组越界
        if (dataList.size() < getDataSize) {
            getDataSize = dataList.size();
        }

        for (int i = 0; i < getDataSize; i++) {
            Map<String, Object> map = dataList.get(i);
            CompanyTopSingle companyTopSingle = new CompanyTopSingle();
            //公司名称
            Object companyName = map.get("companyName");
            if (null != companyName) {
                companyTopSingle.setCompanyName((String) companyName);
            }

            //总资金
            Object topAmount = map.get("topAmount");
            if (null != topAmount) {
                companyTopSingle.setTotalAmount((BigDecimal) topAmount);
            }

            companyTopSingleList.add(companyTopSingle);
        }

        return companyTopSingleList;
    }

    /**
     * 有效合约状态列表
     *
     * @return
     */
    private static List<String> getEffectiveStatusList() {
        List<String> contractStatusList = new ArrayList<String>();
        contractStatusList.add(ContractConstants.CONTRACT_STATUS_ZC);
        contractStatusList.add(ContractConstants.CONTRACT_STATUS_CF_PASS);
        contractStatusList.add(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);
        contractStatusList.add(ContractConstants.CONTRACT_STATUS_ZX);
        contractStatusList.add(ContractConstants.CONTRACT_STATUS_ZX_FINISHED);
        return contractStatusList;
    }
}
