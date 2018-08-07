package cn.inbs.blockchain.dao.po.chart;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po.chart
 * @ClassName: ChartCompanyTopArray.java
 * @Date: 2018/7/7 16:06
 * @author: create by zhangmingyang
 **/
public class ChartCompanyTopArray implements Serializable {
    private List<CompanyTopSingle> zcTop;
    private List<CompanyTopSingle> zJTop;
    private List<CompanyTopSingle> zcAll;
    private List<CompanyTopSingle> zjAll;

    public List<CompanyTopSingle> getZcAll() {
        return zcAll;
    }

    public void setZcAll(List<CompanyTopSingle> zcAll) {
        this.zcAll = zcAll;
    }

    public List<CompanyTopSingle> getZjAll() {
        return zjAll;
    }

    public void setZjAll(List<CompanyTopSingle> zjAll) {
        this.zjAll = zjAll;
    }

    public List<CompanyTopSingle> getZcTop() {
        return zcTop;
    }

    public void setZcTop(List<CompanyTopSingle> zcTop) {
        this.zcTop = zcTop;
    }

    public List<CompanyTopSingle> getzJTop() {
        return zJTop;
    }

    public void setzJTop(List<CompanyTopSingle> zJTop) {
        this.zJTop = zJTop;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
