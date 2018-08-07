package cn.inbs.blockchainpurse.controller.user.outputparam;

import com.alibaba.fastjson.JSON;

public class PurseUserLoginOutput {
    private String purseToken;//token

    public String getPurseToken() {
        return purseToken;
    }

    public void setPurseToken(String purseToken) {
        this.purseToken = purseToken;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
