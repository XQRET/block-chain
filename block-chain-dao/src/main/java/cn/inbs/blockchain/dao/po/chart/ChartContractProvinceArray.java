package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: ChartContractProvinceArray.java
 * @Date: 2018/7/10 11:03
 * @author: create by zhangmingyang
 **/
public class ChartContractProvinceArray implements Serializable {
    private List<ContractProvinceSingle> contractProvinceSingleList;

    public List<ContractProvinceSingle> getContractProvinceSingleList() {
        return contractProvinceSingleList;
    }

    public void setContractProvinceSingleList(List<ContractProvinceSingle> contractProvinceSingleList) {
        this.contractProvinceSingleList = contractProvinceSingleList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
