package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.user.inputparam.SetPurseTransactionPasswordInput;
import cn.inbs.blockchainpurse.service.user.IPurseUserExtraInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 设置钱包交易密码
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: SetPurseTransactionPasswordController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:45
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseUser")
public class SetPurseTransactionPasswordController extends BaseController {
    @Resource
    private IPurseUserExtraInfoService userExtraInfoService;

    @RequestMapping(value = "/setPurseTransactionPassword.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String setPurseTransactionPassword(SetPurseTransactionPasswordInput input) {
        userExtraInfoService.updateBySetPurseTransactionPassword(input);
        return retContent(null);
    }
}
