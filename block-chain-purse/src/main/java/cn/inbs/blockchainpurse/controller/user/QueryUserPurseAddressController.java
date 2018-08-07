package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchainpurse.common.assemblystructure.UserDetailsBean;
import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询用户钱包地址
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: QueryUserPurseAddressController
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/2 11:05
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseUser")
public class QueryUserPurseAddressController extends BaseController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/queryUserPurseAddress.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryUserPurseAddress(BaseControllerInput input) {
        //查询详细信息
        UserDetailsBean userDetailsBean = userService.queryUserDetailsInfo(input.getPurseToken());

        //只返回钱包地址
        PurseUser returnPurseUser = new PurseUser();
        returnPurseUser.setPurseAddress(userDetailsBean.getPurseUser().getPurseAddress());
        return retContent(returnPurseUser);
    }
}
