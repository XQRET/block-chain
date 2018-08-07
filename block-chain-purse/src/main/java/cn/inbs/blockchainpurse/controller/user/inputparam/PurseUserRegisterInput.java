package cn.inbs.blockchainpurse.controller.user.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.LengthCheck;
import cn.inbs.blockchain.common.annotation.param.RegexCheck;
import cn.inbs.blockchainpurse.common.constants.RegexConstants;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 钱包注册入参
 * @Package: cn.inbs.blockchainpurse.controller.user.inputparam
 * @ClassName: PurseUserRegisterInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/23 16:22
 * @Version: 1.0
 */
public class PurseUserRegisterInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "钱包名称")
    @RegexCheck(fieldDescription = "钱包名称", regex = RegexConstants.PURSE_NAME)
    @LengthCheck(fieldDescription = "钱包名称", minLength = 3, maxLength = 15)
    private String purseName; //钱包名称

    @IsNotNull(fieldDescription = "钱包密码")
    private String pursePassword;//钱包密码

    private String invitationCode;//邀请码

    private String imei;//手机IMEI

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

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
