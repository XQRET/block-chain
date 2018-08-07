package cn.inbs.blockchain.dao.vo.cockpit;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 合约地区分布VO
 *
 * @author chenhao 2018年6月28日 下午5:37:40
 */
public class RegionVO {

    @JSONField(name = "name")
    private String region;
    @JSONField(name = "value")
    private Integer number;

    public RegionVO(String region, Integer number) {
        super();
        this.region = region;
        this.number = number;
    }

    public RegionVO() {
    }

    public Integer getNumber() {
        return number;
    }

    public String getRegion() {
        return region;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
