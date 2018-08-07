package cn.inbs.blockchain.common.utils.valuecode;

/**
 * @Description:码值数据字典
 * @Package: cn.inbs.blockchain.common.utils.valuecode
 * @ClassName:
 * @Date: 2018年05月21-12:16
 * @Author: createBy:zhangmingyang
 **/
public enum ValueCodeEnums {
    /**
     * 合约状态码值校验
     */
    CONTRACT_STATUS("合约状态", ValueCodeArrays.CONTRACT_STATUS),
    COMPANY_TYPE("公司类型", ValueCodeArrays.COMPANY_TYPE),
    COMPANY_STATUS("公司状态", ValueCodeArrays.COMPANY_STATUS),
    RELATION_STATUS("关联状态", ValueCodeArrays.RELATION_STATUS),
    REQ_CONTRACT_EXECUTE_SERIAL_STATUS("合约执行流水状态", ValueCodeArrays.REQ_CONTRACT_EXECUTE_SERIAL_STATUS),
    CONTRACT_TYPE("合约类型", ValueCodeArrays.CONTRACT_TYPE),
    CONTRACT_REPAYMENT_WAY("合约支付方式", ValueCodeArrays.CONTRACT_REPAYMENT_WAY),;
    private String description;
    private String[] codeArray;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCodeArray() {
        return codeArray;
    }

    public void setCodeArray(String[] codeArray) {
        this.codeArray = codeArray;
    }

    ValueCodeEnums(String description, String[] codeArray) {
        this.description = description;
        this.codeArray = codeArray;
    }
}
