package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR leijian
 * @Date Created in 2018/6/7
 * 合约注销查询注销详情
 **/
@Controller
@RequestMapping(value = "/contract")
public class QueryLogoutDetailContractController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(QueryLogoutDetailContractController.class);

    @Resource
    private IBlockContracService blockContracService;

    private static final String CONTRACT_ID = "contractId";
    private static final String CONTRACT_STATUS = "status";
    private static final String BLOCK_ID = "blockId";
    private static final String USER_ID = "userId";

    @RequestMapping(value = "/queryLogoutDetailContract.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryLogoutDetailContract(HttpServletRequest httpServletRequest) {

        String contractId = httpServletRequest.getParameter(CONTRACT_ID).trim();
        String contractStatus = httpServletRequest.getParameter(CONTRACT_STATUS).trim();
        String companyName=blockContracService.queryLogoutDetailContract(contractId,contractStatus);
        if (logger.isDebugEnabled()) {
            logger.debug("合约注销公司名称:[{}]", companyName);
        }
        Map<String,String> map=new HashMap();
        map.put("companyName",companyName);
        return retContent(map);
    }

    @RequestMapping(value = "/updateWaitLogoutDetail.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateWaitLogoutDetail(HttpServletRequest httpServletRequest) {

        String blockId = httpServletRequest.getParameter(BLOCK_ID).trim();
        String userId = httpServletRequest.getParameter(USER_ID).trim();
        String contractStatus = httpServletRequest.getParameter(CONTRACT_STATUS).trim();

        blockContracService.updateWaitLogoutDetail(Long.valueOf(blockId),contractStatus,Long.valueOf(userId));
        if (logger.isDebugEnabled()) {
            logger.debug("合约:[{}]详细信息:[{}]用户id:[{}]", blockId, contractStatus,userId);
        }

        return retContent(null);
    }
}
