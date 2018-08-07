package cn.inbs.blockchainpurse.controller.purse;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.purse.inputparam.QueryPurseAmountInput;
import cn.inbs.blockchainpurse.service.purse.IPurseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询钱包余额接口
 * @Package: cn.inbs.blockchainpurse.controller.purse
 * @ClassName: QueryPurseAmountController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 17:42
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseAmount")
public class QueryPurseAmountController extends BaseController {
    @Resource
    private IPurseService purseService;

    @RequestMapping(value = "/queryPurseAmount.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryPurseAmount(QueryPurseAmountInput input) {
        return retContent(purseService.queryPurseAmount(input));
    }
}
