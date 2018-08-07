package cn.inbs.blockchain.controller.contract.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * @Description: 添加合约到预售列表入参
 * @Package: cn.inbs.blockchain.controller.contract.inputparam
 * @ClassName: AddContract2PresaleListInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 12:15
 * @Version: 1.0
 */
public class AddContract2PresaleListInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "合约类型")
    @ValueCodeCheck(fieldDescription = "合约类型", codeValue = ValueCodeArrays.CONTRACT_PRODUCT_TYPE_STR)
    private String contractProductType;//合约类型

    @IsNotNull(fieldDescription = "合约ID")
    private String contractId;//合约ID

    @IsNotNull(fieldDescription = "合约拆分份数")
    private Integer contractServings;//合约拆分份数

    @IsNotNull(fieldDescription = "合约年化利率")
    private BigDecimal yearOfRate;//合约年化利率

    public String getContractProductType() {
        return contractProductType;
    }

    public void setContractProductType(String contractProductType) {
        this.contractProductType = contractProductType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Integer getContractServings() {
        return contractServings;
    }

    public void setContractServings(Integer contractServings) {
        this.contractServings = contractServings;
    }

    public BigDecimal getYearOfRate() {
        return yearOfRate;
    }

    public void setYearOfRate(BigDecimal yearOfRate) {
        this.yearOfRate = yearOfRate;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
