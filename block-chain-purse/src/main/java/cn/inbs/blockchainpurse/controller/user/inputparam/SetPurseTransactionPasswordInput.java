package cn.inbs.blockchainpurse.controller.user.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 设置钱包交易密码请求参数
 * @Package: cn.inbs.blockchainpurse.controller.user.inputparam
 * @ClassName: SetPurseTransactionPasswordInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:48
 * @Version: 1.0
 */
public class SetPurseTransactionPasswordInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "钱包交易密码")
    private String purseTransactionPassword;//钱包密码

    public String getPurseTransactionPassword() {
        return purseTransactionPassword;
    }

    public void setPurseTransactionPassword(String purseTransactionPassword) {
        this.purseTransactionPassword = purseTransactionPassword;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
