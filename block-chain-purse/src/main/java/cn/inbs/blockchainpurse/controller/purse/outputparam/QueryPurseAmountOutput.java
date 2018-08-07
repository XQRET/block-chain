package cn.inbs.blockchainpurse.controller.purse.outputparam;

import cn.inbs.blockchain.dao.purse.po.PurseAmount;
import com.alibaba.fastjson.JSON;

public class QueryPurseAmountOutput {
    private String currency;//币种
    private PurseAmount purseAmount;//余额信息

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PurseAmount getPurseAmount() {
        return purseAmount;
    }

    public void setPurseAmount(PurseAmount purseAmount) {
        this.purseAmount = purseAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
