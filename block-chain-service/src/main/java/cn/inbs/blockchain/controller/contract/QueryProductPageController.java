package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.ContractDetail;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @AUTHOR leijian
 * @Date Created in 2018/7/16
 **/
@Controller
@RequestMapping(value = "/contract")
public class QueryProductPageController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger("QueryProductPageController");

    @Resource
    private IBlockContracService blockContracService;

    //长租查询条数top3
    public static final Integer PAGECOUNT = 3;

    /**
     * 查询长租
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryApartment.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryApartment(HttpServletRequest request) {
        Map<String, Object> parameterMap = new HashMap<String, Object>(3);
        parameterMap.put("importType", ContractConstants.CONTRACT_TYPE_0);
        parameterMap.put("contractType", ContractConstants.CONTRACT_LEASE_TYPE_0);
        parameterMap.put("pageCount", PAGECOUNT);
        return retContent(blockContracService.selectContractByContractType(parameterMap,getRootPath(request)));
    }

    /**
     * 查询海外房产
     */
    @RequestMapping(value = "/queryOverseas.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryOverseas(HttpServletRequest request) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>(3);
        parameterMap.put("importType", ContractConstants.CONTRACT_TYPE_0);
        parameterMap.put("contractType", ContractConstants.CONTRACT_LEASE_TYPE_1);
        parameterMap.put("pageCount", PAGECOUNT);
        List<ContractDetail> contractDetailList = blockContracService.selectContractByContractType(parameterMap, getRootPath(request));
        for (ContractDetail contractDetail : contractDetailList) {
            Date dateAfterOrBeforeMonth = DateUtils.getDateAfterOrBeforeMonth(contractDetail.getBlockContract().getRegistDate(), 1);
            contractDetail.getBlockContract().setContractExpire(dateAfterOrBeforeMonth);
        }
        return retContent(contractDetailList);
    }

}
