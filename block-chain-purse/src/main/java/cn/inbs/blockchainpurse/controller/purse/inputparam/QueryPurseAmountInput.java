package cn.inbs.blockchainpurse.controller.purse.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @Description: 查询钱包余额接口入参
 * @Package: cn.inbs.blockchainpurse.controller.purse.inputparam
 * @ClassName: QueryPurseAmountInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 18:10
 * @Version: 1.0
 */
public class QueryPurseAmountInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "币种")
    private ArrayList<String> currencies;//转换币种

    public ArrayList<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<String> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
