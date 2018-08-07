package cn.inbs.blockchainpurse.controller.sendmessage.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.RegexCheck;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchainpurse.common.constants.RegexConstants;
import cn.inbs.blockchainpurse.common.utils.valuecode.PurseValueCodeArrays;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 短信发送入参
 * @Package: cn.inbs.blockchainpurse.controller.user.inputparam
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
public class SendMessageCodeInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "手机号码")
    @RegexCheck(fieldDescription = "手机号码", regex = RegexConstants.MOBILE_REGEX)
    private String mobile;
    @ValueCodeCheck(fieldDescription = "短信验证码类型", codeValue = PurseValueCodeArrays.MESSAGE_CODE_STYPE)
    private String stype;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
