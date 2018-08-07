package cn.inbs.blockchain.common.commonbean;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName:
 * @Date: 2018年06月08-11:33
 * @Author: createBy:zhangmingyang
 **/
public class ThirdQueryContractDetailInfo extends BaseCommonBean {

    /**
     * 是否需要验签
     * 0需要1不需要
     */
    public static final String validate_0 = "0";
    public static final String validate_1 = "1";
    private String validate;
    /**
     * 资金端区块ID
     */
    public static final String FUNDS_COMPANY_BLOCK_ID = "fundsCompanyBlockId";
    private String fundsCompanyBlockId;

    /**
     * 资产端公钥
     */
    public static final String ASSETS_COMPANY_PUBLICKEY = "assetsCompanyPublicKey";
    private String assetsCompanyPublicKey;

    /**
     * 合约ID
     */
    public static final String CONTRACT_ID = "contractId";
    private String contractId;

    /**
     * 签名数据
     */
    public static final String SIGN = "sign";
    private String sign;

    public String getFundsCompanyBlockId() {
        return fundsCompanyBlockId;
    }

    public void setFundsCompanyBlockId(String fundsCompanyBlockId) {
        this.fundsCompanyBlockId = fundsCompanyBlockId;
    }

    public String getAssetsCompanyPublicKey() {
        return assetsCompanyPublicKey;
    }

    public void setAssetsCompanyPublicKey(String assetsCompanyPublicKey) {
        this.assetsCompanyPublicKey = assetsCompanyPublicKey;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    @Override
    public String toString() {
        return "ThirdQueryContractDetailInfo{" +
                "validate='" + validate + '\'' +
                ", fundsCompanyBlockId='" + fundsCompanyBlockId + '\'' +
                ", assetsCompanyPublicKey='" + assetsCompanyPublicKey + '\'' +
                ", contractId='" + contractId + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
