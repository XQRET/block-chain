package cn.inbs.blockchainpurse.service.sendmessage;

import cn.inbs.blockchainpurse.controller.sendmessage.inputparam.SendMessageCodeInput;

/**
 * @Description: 75952
 * @Package: cn.inbs.blockchainpurse.service.sendmessage
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
public interface ISendMessageService {
    /**
     * 获取短信验证码
     *
     * @param input
     */
    void getMessageCode(SendMessageCodeInput input);
}
