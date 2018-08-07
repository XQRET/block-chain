package cn.inbs.blockchainpurse.controller.sendmessage;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.sendmessage.inputparam.SendMessageCodeInput;
import cn.inbs.blockchainpurse.service.sendmessage.ISendMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 75952
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/sendMessage")
public class PurseMessageSendController extends BaseController {

    @Resource
    private ISendMessageService sendMessageService;

    @RequestMapping(value = "/sendMessageCode.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String setPurseTransactionPassword(SendMessageCodeInput input) {
        sendMessageService.getMessageCode(input);
        return retContent(null);
    }
}
