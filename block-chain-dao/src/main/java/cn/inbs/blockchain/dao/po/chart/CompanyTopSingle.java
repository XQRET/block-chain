package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: CompanyTopSingle.java
 * @Date: 2018/7/7 16:01
 * @author: create by zhangmingyang
 **/
public class CompanyTopSingle implements Serializable {
    private String companyName;//公司名称
    private BigDecimal totalAmount;//总金额

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
