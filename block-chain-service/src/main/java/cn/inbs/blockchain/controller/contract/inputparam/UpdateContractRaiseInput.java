package cn.inbs.blockchain.controller.contract.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 修改合约募集状态入参
 * @Package: cn.inbs.blockchain.controller.contract.inputparam
 * @ClassName: UpdateContractRaiseInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 11:13
 * @Version: 1.0
 */
public class UpdateContractRaiseInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "合约ID")
    private String contractId;//合约ID

    @IsNotNull(fieldDescription = "募集状态")
    @ValueCodeCheck(fieldDescription = "募集状态", codeValue = ValueCodeArrays.RAISE_STATUS_STR)
    private String raiseStatus;//募集状态

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRaiseStatus() {
        return raiseStatus;
    }

    public void setRaiseStatus(String raiseStatus) {
        this.raiseStatus = raiseStatus;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
