package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.service.user.IPurseUserExtraInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 检查用户是否存在交易密码
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: CheckUserTransactionPasswordController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:13
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseUser")
public class CheckUserTransactionPasswordController extends BaseController {
    @Resource
    private IPurseUserExtraInfoService userExtraInfoService;

    @RequestMapping(value = "/checkUserTransactionPassword.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String checkUserTransactionPassword(BaseControllerInput input) {
        return retContent(userExtraInfoService.queryByCheckTransactionPasswordIsExist(input));
    }
}
