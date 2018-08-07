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
 * @ClassName: SomeMonthIncomeSingle.java
 * @Date: 2018/7/10 15:01
 * @author: create by zhangmingyang
 **/
public class SomeMonthIncomeSingle implements Serializable {
    private static final BigDecimal AMOUNT_DEFAULT_VALUE = new BigDecimal(0);//金额默认值
    private BigDecimal incomeAmount = AMOUNT_DEFAULT_VALUE;//月收入
    private String monthStr;//月份

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
