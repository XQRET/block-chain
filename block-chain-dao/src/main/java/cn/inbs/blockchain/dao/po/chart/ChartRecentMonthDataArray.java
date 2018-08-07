package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: ChartRecentMonthDataArray.java
 * @Date: 2018/7/7 15:32
 * @author: create by zhangmingyang
 **/
public class ChartRecentMonthDataArray implements Serializable {
    private List<RecentSomeMonthSingle> recentSomeMonthDataList;

    public List<RecentSomeMonthSingle> getRecentSomeMonthDataList() {
        return recentSomeMonthDataList;
    }

    public void setRecentSomeMonthDataList(List<RecentSomeMonthSingle> recentSomeMonthDataList) {
        this.recentSomeMonthDataList = recentSomeMonthDataList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
