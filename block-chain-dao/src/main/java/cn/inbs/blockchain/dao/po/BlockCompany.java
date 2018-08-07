package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.util.Date;

public class BlockCompany extends BaseDaoBean {
    private Long id;//公司ID
    private String companyName;//公司名称
    private String companyType;//公司类型(0-资产端,1-资金端)
    private String provinceName;//省
    private String cityName;//市
    private String companyAddress;//地址
    private String businessLicenseUrl;//注册证书
    private String businessLicenseCode;//工商注册号
    private String companyLinkman;//联系人
    private String companyLinkmanPhone;//联系人手机号
    private String identityUrl;//身份证图片
    private String companyStatus;//0-未认证,1-待审核,2-审核通过,3-审核未通过
    private String checkRemark;//审核描述信息
    private String companyEmail;//邮箱
    private String companyBlockId;//公司区块链地址
    private Long nodeId;//节点地址编号
    private String companyPrivateKey;//公司私钥
    private String companyPublicKey;//公司公钥
    private Long employeeId;//用户ID
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
    }

    public String getCompanyLinkman() {
        return companyLinkman;
    }

    public void setCompanyLinkman(String companyLinkman) {
        this.companyLinkman = companyLinkman;
    }

    public String getCompanyLinkmanPhone() {
        return companyLinkmanPhone;
    }

    public void setCompanyLinkmanPhone(String companyLinkmanPhone) {
        this.companyLinkmanPhone = companyLinkmanPhone;
    }

    public String getIdentityUrl() {
        return identityUrl;
    }

    public void setIdentityUrl(String identityUrl) {
        this.identityUrl = identityUrl;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyBlockId() {
        return companyBlockId;
    }

    public void setCompanyBlockId(String companyBlockId) {
        this.companyBlockId = companyBlockId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getCompanyPrivateKey() {
        return companyPrivateKey;
    }

    public void setCompanyPrivateKey(String companyPrivateKey) {
        this.companyPrivateKey = companyPrivateKey;
    }

    public String getCompanyPublicKey() {
        return companyPublicKey;
    }

    public void setCompanyPublicKey(String companyPublicKey) {
        this.companyPublicKey = companyPublicKey;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
        return "BlockCompany{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyType='" + companyType + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", businessLicenseUrl='" + businessLicenseUrl + '\'' +
                ", businessLicenseCode='" + businessLicenseCode + '\'' +
                ", companyLinkman='" + companyLinkman + '\'' +
                ", companyLinkmanPhone='" + companyLinkmanPhone + '\'' +
                ", identityUrl='" + identityUrl + '\'' +
                ", companyStatus='" + companyStatus + '\'' +
                ", checkRemark='" + checkRemark + '\'' +
                ", companyemail='" + companyEmail + '\'' +
                ", companyBlockId='" + companyBlockId + '\'' +
                ", nodeId=" + nodeId +
                ", companyPrivateKey='" + companyPrivateKey + '\'' +
                ", companyPublicKey='" + companyPublicKey + '\'' +
                ", employeeId=" + employeeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}