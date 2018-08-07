package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserLoginInput;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/purseUser")
public class PurseUserLoginController extends BaseController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/purseUserLogin.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String purseUserLogin(PurseUserLoginInput input) throws Exception {
        return retContent(userService.queryUserByLogin(input));
    }
}
