package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ContractSerial extends BaseDaoBean {

    private Long id;//ID
    private String contractId;//合约ID
    private String contractBlockId;//合约区块链ID
    private String contractStatus;//状态；10-合约注册,20-合约触发-通过,21-合约触发-通过,30-合约执行,40-合约注销
    private String companyBlockId;//合约操作区块链地址
    private String contractRemark;//合约概要
    private String executeRemark;//合约操作机构备注
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Integer repaymentTrem;//备注字段1(暂不启用)
    private String repaymentStatus;//备注字段2(暂不启用)
    private String remark3;//备注字段3(暂不启用)
    private String remark4;//备注字段4(暂不启用)
    private String remark5;//备注字段5(暂不启用)

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

    public String getContractBlockId() {
        return contractBlockId;
    }

    public void setContractBlockId(String contractBlockId) {
        this.contractBlockId = contractBlockId;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getCompanyBlockId() {
        return companyBlockId;
    }

    public void setCompanyBlockId(String companyBlockId) {
        this.companyBlockId = companyBlockId;
    }

    public String getContractRemark() {
        return contractRemark;
    }

    public void setContractRemark(String contractRemark) {
        this.contractRemark = contractRemark;
    }

    public String getExecuteRemark() {
        return executeRemark;
    }

    public void setExecuteRemark(String executeRemark) {
        this.executeRemark = executeRemark;
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

    public Integer getRepaymentTrem() {
        return repaymentTrem;
    }

    public void setRepaymentTrem(Integer repaymentTrem) {
        this.repaymentTrem = repaymentTrem;
    }

    public String getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    @Override
    public String toString() {
        return "ContractSerial{" +
                "id=" + id +
                ", contractId='" + contractId + '\'' +
                ", contractBlockId='" + contractBlockId + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", companyBlockId='" + companyBlockId + '\'' +
                ", contractRemark='" + contractRemark + '\'' +
                ", executeRemark='" + executeRemark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", repaymentTrem=" + repaymentTrem +
                ", repaymentStatus='" + repaymentStatus + '\'' +
                ", remark3='" + remark3 + '\'' +
                ", remark4='" + remark4 + '\'' +
                ", remark5='" + remark5 + '\'' +
                '}';
    }

    /**
     * 根据流水时间排序
     *
     * @param serials
     * @return
     */
    public static List<ContractSerial> orderSerialsByCreateTime(List<ContractSerial> serials) {
        Collections.sort(serials, new Comparator<ContractSerial>() {
            @Override
            public int compare(ContractSerial o1, ContractSerial o2) {
                if (o2.getCreateTime().compareTo(o1.getCreateTime()) == 0) {
                    if (o2.getId() - o1.getId() > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                }
            }
        });
        return serials;
    }
}