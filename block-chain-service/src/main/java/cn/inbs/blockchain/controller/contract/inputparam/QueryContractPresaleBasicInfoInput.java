package cn.inbs.blockchain.controller.contract.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 查询预售合约基本信息入参
 * @Package: cn.inbs.blockchain.controller.contract.inputparam
 * @ClassName: QueryContractPresaleBasicInfoInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 18:09
 * @Version: 1.0
 */
public class QueryContractPresaleBasicInfoInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "合约ID")
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
