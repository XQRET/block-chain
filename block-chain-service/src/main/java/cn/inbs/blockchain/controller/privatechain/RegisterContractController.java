package cn.inbs.blockchain.controller.privatechain;


import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.common.web.BaseController;

import cn.inbs.blockchain.controller.privatechain.inputparam.RegisterContractInput;
import cn.inbs.blockchain.service.contract.IPrivateChainContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.TreeMap;

/**
 * @Description: 私有链合约报备
 * @Package: cn.inbs.blockchain.controller.privatechain
 * @ClassName:
 * @Date: 2018年05月14-17:37
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping("/contract")
public class RegisterContractController extends BaseController {

    @Resource
    private IPrivateChainContractService privateChainContractService;

    @RequestMapping(value = "/registerContract.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String registerContract(RegisterContractInput input,
                                   HttpServletRequest httpServletRequest) throws Exception {
        //IP拦截
//        HttpUtil.banIP(httpServletRequest);

        //执行合约注册业务
        TreeMap<String, String> registerResult = privateChainContractService.insertContract(input);

        //生成签约数据
        String resultSign = RSAUtils.sign(registerResult, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
        registerResult.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, resultSign);
        return retContent(registerResult);
    }
}
