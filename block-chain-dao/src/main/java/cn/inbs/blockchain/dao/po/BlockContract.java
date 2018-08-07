package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BlockContract extends BaseDaoBean {
    private Long id;//
    private String contractId;//合约ID(唯一)
    private String contractStatus;//状态:10-合约注册,20-合约触发(通过),30-合约执行(拒绝),40-合约注销
    private String importType;//0-http导入,1-批量导入
    private String contractType;//0-长租,1-澳洲
    private String contractRegister;//登记方
    private String contractSigner;//合约签订人
    private BigDecimal interestRate;//合约利率
    private String repaymentWay;//还款方式(取值：1 等本等息、2 先息后本)
    private BigDecimal amount;//合约额度(单位:元)
    private Integer term;//合约期数(单位:月)
    private String bank;//资金银行
    private String houseCode;//房产标号
    private String houseInfo;//房屋信息
    private String remark;//合约概要
    private Date registDate;//登记日期
    private Date contractToDate;//合约到期日期
    private Date updateTime;//合约信息修改时间
    private String contractBlockId;//房产证标号区块ID
    private Long companyId;//公司Id
    private String registCompanyBlockId;//公司区块Id
    private String contractName;//合约名称
    private Integer refuseTimes;//合约拒绝次数
    private String triggerCompanyBlockId;//合约触发公司
    private String idNo; //身份证号
    private String phoneNumber; //手机号码
    private BigDecimal overdueAmount; //平台逾期金额
    private Integer overdueNum; //平台逾期次数
    private Integer creditOverdueNum; //征信逾期次数
    private Integer creditLoanNum; //征信借款次数
    private String importUniqueSign; //导入唯一标示
    private String attachInfoJson; //附加信息json
    private Date contractExpire;     //合约筹备到期时间



    private List<ContractFileUrl> files; //上传文件集合
    
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

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractRegister() {
        return contractRegister;
    }

    public void setContractRegister(String contractRegister) {
        this.contractRegister = contractRegister;
    }

    public String getContractSigner() {
        return contractSigner;
    }

    public void setContractSigner(String contractSigner) {
        this.contractSigner = contractSigner;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getRepaymentWay() {
        return repaymentWay;
    }

    public void setRepaymentWay(String repaymentWay) {
        this.repaymentWay = repaymentWay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getContractToDate() {
        return contractToDate;
    }

    public void setContractToDate(Date contractToDate) {
        this.contractToDate = contractToDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContractBlockId() {
        return contractBlockId;
    }

    public void setContractBlockId(String contractBlockId) {
        this.contractBlockId = contractBlockId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

	public String getRegistCompanyBlockId() {
		return registCompanyBlockId;
	}

	public void setRegistCompanyBlockId(String registCompanyBlockId) {
		this.registCompanyBlockId = registCompanyBlockId;
	}

	public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getRefuseTimes() {
        return refuseTimes;
    }

    public void setRefuseTimes(Integer refuseTimes) {
        this.refuseTimes = refuseTimes;
    }

    public String getTriggerCompanyBlockId() {
        return triggerCompanyBlockId;
    }

    public void setTriggerCompanyBlockId(String triggerCompanyBlockId) {
        this.triggerCompanyBlockId = triggerCompanyBlockId;
    }

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Integer getOverdueNum() {
		return overdueNum;
	}

	public void setOverdueNum(Integer overdueNum) {
		this.overdueNum = overdueNum;
	}

	public Integer getCreditOverdueNum() {
		return creditOverdueNum;
	}

	public void setCreditOverdueNum(Integer creditOverdueNum) {
		this.creditOverdueNum = creditOverdueNum;
	}

	public Integer getCreditLoanNum() {
		return creditLoanNum;
	}

	public void setCreditLoanNum(Integer creditLoanNum) {
		this.creditLoanNum = creditLoanNum;
	}

	public String getImportUniqueSign() {
		return importUniqueSign;
	}

	public void setImportUniqueSign(String importUniqueSign) {
		this.importUniqueSign = importUniqueSign;
	}

	public String getAttachInfoJson() {
		return attachInfoJson;
	}

	public void setAttachInfoJson(String attachInfoJson) {
		this.attachInfoJson = attachInfoJson;
	}

	public List<ContractFileUrl> getFiles() {
		return files;
	}

	public void setFiles(List<ContractFileUrl> files) {
		this.files = files;
	}

    public Date getContractExpire() {
        return contractExpire;
    }

    public void setContractExpire(Date contractExpire) {
        this.contractExpire = contractExpire;
    }

    @Override
    public String toString() {
        return "BlockContract{" +
                "id=" + id +
                ", idNo='" + idNo + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contractId='" + contractId + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", importType='" + importType + '\'' +
                ", contractRegister='" + contractRegister + '\'' +
                ", contractSigner='" + contractSigner + '\'' +
                ", interestRate=" + interestRate +
                ", repaymentWay='" + repaymentWay + '\'' +
                ", amount=" + amount +
                ", term=" + term +
                ", bank='" + bank + '\'' +
                ", houseCode='" + houseCode + '\'' +
                ", houseInfo='" + houseInfo + '\'' +
                ", remark='" + remark + '\'' +
                ", registDate=" + registDate +
                ", contractToDate=" + contractToDate +
                ", updateTime=" + updateTime +
                ", contractBlockId='" + contractBlockId + '\'' +
                ", companyId=" + companyId +
                ", registCompanyBlockId='" + registCompanyBlockId + '\'' +
                ", contractName='" + contractName + '\'' +
                ", refuseTimes=" + refuseTimes +
                ", triggerCompanyBlockId='" + triggerCompanyBlockId + '\'' +
                '}';
    }
	
	
	
}