package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.contract.inputparam.QueryContractPresaleBasicInfoInput;
import cn.inbs.blockchain.service.contract.IContractSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询预售合约基本信息
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName: ContractPresaleBasicInfo
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 18:07
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/contract")
public class QueryContractPresaleBasicInfoController extends BaseController {
    @Resource
    private IContractSaleService contractSaleService;

    @RequestMapping(value = "/queryContractPresaleBasicInfo.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryContractPresaleBasicInfo(QueryContractPresaleBasicInfoInput input) {
        return retContent(contractSaleService.queryContractPresaleBasicInfo(input));
    }
}
