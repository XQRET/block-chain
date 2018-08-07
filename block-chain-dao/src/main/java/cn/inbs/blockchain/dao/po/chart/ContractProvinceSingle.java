package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: ContractProvinceSingle.java
 * @Date: 2018/7/10 10:53
 * @author: create by zhangmingyang
 **/
public class ContractProvinceSingle implements Serializable {
    private String provinceName;
    private Long contractCount;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getContractCount() {
        return contractCount;
    }

    public void setContractCount(Long contractCount) {
        this.contractCount = contractCount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
