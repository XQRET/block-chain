package cn.inbs.blockchainpurse.controller.authentication.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.RegexCheck;
import cn.inbs.blockchainpurse.common.constants.RegexConstants;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @Description: 手机认证
 * @Package: cn.inbs.blockchainpurse.controller.purse.inputparam
 * @ClassName: QueryPurseAmountInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 18:10
 * @Version: 1.0
 */
public class MobileAuthInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "手机号码")
    @RegexCheck(fieldDescription = "手机号码", regex = RegexConstants.MOBILE_REGEX)
    private String mobile;
    @IsNotNull(fieldDescription = "验证码")
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
