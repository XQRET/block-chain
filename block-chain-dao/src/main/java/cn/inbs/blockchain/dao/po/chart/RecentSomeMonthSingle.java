package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: RecentSomeMonthSingle.java
 * @Date: 2018/7/7 13:49
 * @author: create by zhangmingyang
 **/
public class RecentSomeMonthSingle implements Serializable {
    private static final BigDecimal AMOUNT_DEFAULT_VALUE = new BigDecimal(0);//金额默认值
    private String dataBelongMonthTime;
    private Integer contractMonthCount;
    private BigDecimal contractMonthAmount = AMOUNT_DEFAULT_VALUE;

    public String getDataBelongMonthTime() {
        return dataBelongMonthTime;
    }

    public void setDataBelongMonthTime(String dataBelongMonthTime) {
        this.dataBelongMonthTime = dataBelongMonthTime;
    }

    public Integer getContractMonthCount() {
        return contractMonthCount;
    }

    public void setContractMonthCount(Integer contractMonthCount) {
        this.contractMonthCount = contractMonthCount;
    }

    public BigDecimal getContractMonthAmount() {
        return contractMonthAmount;
    }

    public void setContractMonthAmount(BigDecimal contractMonthAmount) {
        this.contractMonthAmount = contractMonthAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }



}
