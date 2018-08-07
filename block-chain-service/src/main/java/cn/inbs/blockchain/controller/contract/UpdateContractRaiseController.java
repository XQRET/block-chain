package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.contract.inputparam.UpdateContractRaiseInput;
import cn.inbs.blockchain.service.contract.IContractSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 修改合约募集状态
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName: ContractRaiseController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 11:08
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/contract")
public class UpdateContractRaiseController extends BaseController {
    @Resource
    private IContractSaleService contractSaleService;

    @RequestMapping(value = "/updateContractRaise.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateContractRaise(UpdateContractRaiseInput input) {
        contractSaleService.updateContractRaiseStatus(input);
        return retContent(null);
    }
}
