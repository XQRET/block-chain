package cn.inbs.blockchain.controller.contract.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 查询合约详情信息
 * @Package: cn.inbs.blockchain.controller.contract.inputparam
 * @ClassName: QueryDetailContractInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 17:38
 * @Version: 1.0
 */
public class QueryDetailContractInput extends BaseControllerInput {
    private String id;
    private String contractId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
