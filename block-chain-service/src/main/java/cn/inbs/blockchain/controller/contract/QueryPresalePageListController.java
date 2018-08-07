package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.contract.inputparam.QueryPresalePageListInput;
import cn.inbs.blockchain.service.contract.IContractSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 预售产品分页列表
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName: QueryPresalePageListInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 17:57
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/contract")
public class QueryPresalePageListController extends BaseController {
    @Resource
    private IContractSaleService contractSaleService;

    @RequestMapping(value = "/queryPresalePageList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryPresalePageList(QueryPresalePageListInput input) {
        return retContent(contractSaleService.queryPresalePageList(input));
    }
}
