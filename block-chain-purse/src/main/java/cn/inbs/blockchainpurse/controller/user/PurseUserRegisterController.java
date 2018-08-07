package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserRegisterInput;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 钱包注册接口
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: PurseUserRegisterController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/23 16:22
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseUser")
public class PurseUserRegisterController extends BaseController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/purseUserRegister.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String purseUserRegister(PurseUserRegisterInput input) throws Exception {
        return retContent(userService.insertUserByRegister(input));
    }
}
