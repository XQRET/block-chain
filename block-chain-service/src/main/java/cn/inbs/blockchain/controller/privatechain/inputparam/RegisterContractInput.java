package cn.inbs.blockchain.controller.privatechain.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;


/**
 * @Description: 合约报备入参结构体
 * @PackageName: cn.inbs.blockchain.controller.privatechain.inputparam
 * @ClassName: RegisterContractInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/17 14:33
 */
public class RegisterContractInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "签名key")
    private String sign;//签名key

    @IsNotNull(fieldDescription = "登记方")
    private String register;//登记方

    @IsNotNull(fieldDescription = "合约签订人")
    private String signer;//合约签订人

    @IsNotNull(fieldDescription = "利率")
    private String interestRate;//+(利率)

    @IsNotNull(fieldDescription = "还款方式")
    @ValueCodeCheck(codeValue = ValueCodeArrays.CONTRACT_REPAYMENT_WAY_STR, fieldDescription = "还款方式")
    private String repaymentWay;//还款方式(取值：1 等额本息、2 先息后本)

    @IsNotNull(fieldDescription = "合约额度")
    private String amount;//合约额度(单位:元)

    @IsNotNull(fieldDescription = "合约期数")
    private String term;//合约期数(单位:月)

    private String bank;//资金银行

    @IsNotNull(fieldDescription = "房产信息")
    private String houseInfo;//房产信息

    @IsNotNull(fieldDescription = "房产编号")
    private String houseCode;//房产编号

    @IsNotNull(fieldDescription = "公司区块链ID")
    private String companyBlockId;//公司区块链ID

    @IsNotNull(fieldDescription = "获取房屋信息URl")
    private String houseInfoUrl;//获取房屋信息URl

    @IsNotNull(fieldDescription = "获取个人信息URL")
    private String personInfoUrl;//获取个人信息URL

    @IsNotNull(fieldDescription = "下载文件URL")
    private String downloadFileInfoUrl;//下载文件URL

    private String idNo; //身份证

    private String overdueAmount; //平台逾期金额

    private String overdueNum; //平台逾期次数

    private String creditOverdueNum; //征信逾期次数

    private String creditLoanNum; //征信借款次数

    private String houseImgUrls; //房源图片

    @IsNotNull(fieldDescription = "合约类型")
    @ValueCodeCheck(codeValue = ValueCodeArrays.CONTRACT_TYPE_STR, fieldDescription = "合约类型")
    private String contractType; //合约类型 (0-长租,1-澳洲)

    private String houseAddress; //房屋地址
    private String houseApartments; //房产类型
    private String houseType; //户型
    private String houseSize; //房屋大小
    private String houseTotalAmount; //房屋总价值
    private String numberOfHairstyles; //发型数量

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getRepaymentWay() {
        return repaymentWay;
    }

    public void setRepaymentWay(String repaymentWay) {
        this.repaymentWay = repaymentWay;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getCompanyBlockId() {
        return companyBlockId;
    }

    public void setCompanyBlockId(String companyBlockId) {
        this.companyBlockId = companyBlockId;
    }

    public String getHouseInfoUrl() {
        return houseInfoUrl;
    }

    public void setHouseInfoUrl(String houseInfoUrl) {
        this.houseInfoUrl = houseInfoUrl;
    }

    public String getPersonInfoUrl() {
        return personInfoUrl;
    }

    public void setPersonInfoUrl(String personInfoUrl) {
        this.personInfoUrl = personInfoUrl;
    }

    public String getDownloadFileInfoUrl() {
        return downloadFileInfoUrl;
    }

    public void setDownloadFileInfoUrl(String downloadFileInfoUrl) {
        this.downloadFileInfoUrl = downloadFileInfoUrl;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(String overdueNum) {
        this.overdueNum = overdueNum;
    }

    public String getCreditOverdueNum() {
        return creditOverdueNum;
    }

    public void setCreditOverdueNum(String creditOverdueNum) {
        this.creditOverdueNum = creditOverdueNum;
    }

    public String getCreditLoanNum() {
        return creditLoanNum;
    }

    public void setCreditLoanNum(String creditLoanNum) {
        this.creditLoanNum = creditLoanNum;
    }

    public String getHouseImgUrls() {
        return houseImgUrls;
    }

    public void setHouseImgUrls(String houseImgUrls) {
        this.houseImgUrls = houseImgUrls;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseApartments() {
        return houseApartments;
    }

    public void setHouseApartments(String houseApartments) {
        this.houseApartments = houseApartments;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(String houseSize) {
        this.houseSize = houseSize;
    }

    public String getHouseTotalAmount() {
        return houseTotalAmount;
    }

    public void setHouseTotalAmount(String houseTotalAmount) {
        this.houseTotalAmount = houseTotalAmount;
    }

    public String getNumberOfHairstyles() {
        return numberOfHairstyles;
    }

    public void setNumberOfHairstyles(String numberOfHairstyles) {
        this.numberOfHairstyles = numberOfHairstyles;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
