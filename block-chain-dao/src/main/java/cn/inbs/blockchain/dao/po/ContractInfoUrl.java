package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.util.Date;

public class ContractInfoUrl extends BaseDaoBean {
    private Long id;//
    private String contractId;//公司ID
    private String urlType;//图片类型(1-公司图片,2-身份证图片)
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
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
        return "ContractInfoUrl{" +
                "id=" + id +
                ", contractId='" + contractId + '\'' +
                ", urlType='" + urlType + '\'' +
                ", url='" + url + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}