package cn.inbs.blockchainpurse.controller.authentication;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.authentication.inputparam.MobileAuthInput;
import cn.inbs.blockchainpurse.service.authentication.IPurseMobileAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 手机认证
 * @Package: cn.inbs.blockchainpurse.controller
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/30
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/auth")
public class PurseMobileAuthController extends BaseController {

    @Autowired
    private IPurseMobileAuthService iPurseMobileAuthService;
    @RequestMapping(value = "/moblieAuth.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String moblieAuth(MobileAuthInput input) {
        iPurseMobileAuthService.updateMobileAuth(input);
        return retContent(null);
    }
}
