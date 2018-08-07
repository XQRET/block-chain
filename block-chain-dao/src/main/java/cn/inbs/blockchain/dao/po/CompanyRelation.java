package cn.inbs.blockchain.dao.po;

import java.util.Date;

public class CompanyRelation {
    private Long id;//
    private String zcCompanyBlockId;//资产端机构区块ID
    private String zjCompanyBlockId;//资金端机构区块ID
    private String relationStatus;//关联状态(0-待关联,1-关联成功,2-关联拒绝)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZcCompanyBlockId() {
        return zcCompanyBlockId;
    }

    public void setZcCompanyBlockId(String zcCompanyBlockId) {
        this.zcCompanyBlockId = zcCompanyBlockId;
    }

    public String getZjCompanyBlockId() {
        return zjCompanyBlockId;
    }

    public void setZjCompanyBlockId(String zjCompanyBlockId) {
        this.zjCompanyBlockId = zjCompanyBlockId;
    }

    public String getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
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
}