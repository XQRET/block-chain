package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.contract.inputparam.AddContract2PresaleListInput;
import cn.inbs.blockchain.service.contract.IContractSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 添加合约到预售列表
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName: AddContract2PresaleListController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 12:12
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/contract")
public class AddContract2PresaleListController extends BaseController {
    @Resource
    private IContractSaleService contractSaleService;

    @RequestMapping(value = "/addContractToPresaleList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addContractToPresaleList(AddContract2PresaleListInput input) {
        contractSaleService.insertByAddContractToPresaleList(input);
        return retContent(null);
    }
}
