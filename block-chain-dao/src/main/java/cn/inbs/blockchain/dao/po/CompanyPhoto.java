package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.util.Date;

public class CompanyPhoto extends BaseDaoBean {
    private Long id;//
    private Long companyId;//公司ID
    private String type;//图片类型(1-公司图片,2-身份证图片)
    private String url;//图片url
    private String orderCode;//排序编码
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CompanyPhoto{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}