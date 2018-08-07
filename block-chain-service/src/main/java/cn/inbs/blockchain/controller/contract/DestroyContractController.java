package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.utils.CookieUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 资产端在公有链销毁合约
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName:
 * @Date: 2018年06月06-10:10
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/contract")
public class DestroyContractController extends BaseController {

    private static final String CONTRACT_ID = "contractId";
    @Resource
    private IBlockContracService blockContracService;

    @RequestMapping(value = "/destroyContractByPublicChain.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String destroyContractByPublicChain(HttpServletRequest httpServletRequest) {
        //获取请求参数合约自增Id
        String contractId = httpServletRequest.getParameter(CONTRACT_ID);

        //在Cookie中获取公司区块ID
        String companyBlockIdInCookie = CookieUtils.getCompanyBlockIdInCookie(httpServletRequest);

        //执行合约销毁
        blockContracService.updateContractByPublicChainDestroy(Long.valueOf(contractId), companyBlockIdInCookie);

        return retContent(null);
    }
}
