package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 驾驶舱头部(8个)基础数据
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName: ChartHeaderBaseData.java
 * @Date: 2018/7/6 17:09
 * @author: create by zhangmingyang
 **/
public class ChartHeaderBaseData implements Serializable {
    private static final BigDecimal AMOUNT_DEFAULT_VALUE = new BigDecimal(0);//金额默认值

    private Integer currentMonthNewContractCount;//当月新增合约个数
    private Integer assetCompanyCount;//资产端用户数（个）
    private Integer capitalCompanyCount;// 资金端用户数（个）
    private Integer effectiveContractCount;//平台生效合约数（个）
    private Integer totalContractCount;//平台合约总数（个）
    private BigDecimal currentMonthNewAmount = AMOUNT_DEFAULT_VALUE;//当月新增合约金额（万元）
    private BigDecimal effectiveContractAmount = AMOUNT_DEFAULT_VALUE;//平台生效合约金额（万元）
    private BigDecimal totalContractAmount = AMOUNT_DEFAULT_VALUE;//平台合约总金额（万元）

    public Integer getEffectiveContractCount() {
        return effectiveContractCount;
    }

    public void setEffectiveContractCount(Integer effectiveContractCount) {
        this.effectiveContractCount = effectiveContractCount;
    }

    public BigDecimal getEffectiveContractAmount() {
        return effectiveContractAmount;
    }

    public void setEffectiveContractAmount(BigDecimal effectiveContractAmount) {
        this.effectiveContractAmount = effectiveContractAmount;
    }

    public Integer getCurrentMonthNewContractCount() {
        return currentMonthNewContractCount;
    }

    public void setCurrentMonthNewContractCount(Integer currentMonthNewContractCount) {
        this.currentMonthNewContractCount = currentMonthNewContractCount;
    }

    public Integer getAssetCompanyCount() {
        return assetCompanyCount;
    }

    public void setAssetCompanyCount(Integer assetCompanyCount) {
        this.assetCompanyCount = assetCompanyCount;
    }

    public Integer getCapitalCompanyCount() {
        return capitalCompanyCount;
    }

    public void setCapitalCompanyCount(Integer capitalCompanyCount) {
        this.capitalCompanyCount = capitalCompanyCount;
    }


    public Integer getTotalContractCount() {
        return totalContractCount;
    }

    public void setTotalContractCount(Integer totalContractCount) {
        this.totalContractCount = totalContractCount;
    }

    public BigDecimal getCurrentMonthNewAmount() {
        return currentMonthNewAmount;
    }

    public void setCurrentMonthNewAmount(BigDecimal currentMonthNewAmount) {
        this.currentMonthNewAmount = currentMonthNewAmount;
    }

    public BigDecimal getTotalContractAmount() {
        return totalContractAmount;
    }

    public void setTotalContractAmount(BigDecimal totalContractAmount) {
        this.totalContractAmount = totalContractAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
