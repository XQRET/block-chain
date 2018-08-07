package cn.inbs.blockchainpurse.controller.user.outputparam;

import com.alibaba.fastjson.JSON;

public class PurseUserRegisterOutput {
    private String purseName;//钱包名称
    private String privateKey;//私钥
    private String purseAddress;//钱包地址

    public String getPurseName() {
        return purseName;
    }

    public void setPurseName(String purseName) {
        this.purseName = purseName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPurseAddress() {
        return purseAddress;
    }

    public void setPurseAddress(String purseAddress) {
        this.purseAddress = purseAddress;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
