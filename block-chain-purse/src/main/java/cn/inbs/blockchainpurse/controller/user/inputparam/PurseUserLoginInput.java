package cn.inbs.blockchainpurse.controller.user.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.LengthCheck;
import cn.inbs.blockchain.common.annotation.param.RegexCheck;
import cn.inbs.blockchainpurse.common.constants.RegexConstants;
import com.alibaba.fastjson.JSON;

public class PurseUserLoginInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "钱包名称")
    @RegexCheck(fieldDescription = "钱包名称", regex = RegexConstants.PURSE_NAME)
    @LengthCheck(fieldDescription = "钱包名称", minLength = 3, maxLength = 15)
    private String purseName; //钱包名称

    @IsNotNull(fieldDescription = "钱包密码")
    private String pursePassword;//钱包密码

    public String getPurseName() {
        return purseName;
    }

    public void setPurseName(String purseName) {
        this.purseName = purseName;
    }

    public String getPursePassword() {
        return pursePassword;
    }

    public void setPursePassword(String pursePassword) {
        this.pursePassword = pursePassword;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
