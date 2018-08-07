package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 收入统计
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: ChartSomeMonthIncomeArray.java
 * @Date: 2018/7/10 15:04
 * @author: create by zhangmingyang
 **/
public class ChartSomeMonthIncomeArray implements Serializable {
    private List<SomeMonthIncomeSingle> someMonthIncomeSingleList;

    public List<SomeMonthIncomeSingle> getSomeMonthIncomeSingleList() {
        return someMonthIncomeSingleList;
    }

    public void setSomeMonthIncomeSingleList(List<SomeMonthIncomeSingle> someMonthIncomeSingleList) {
        this.someMonthIncomeSingleList = someMonthIncomeSingleList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
