package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.ContractTriggerInfo;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 查询合约触发进度
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName:
 * @Date: 2018年05月24-14:37
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/contract")
public class QueryContractTriggeringInfoController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryContractTriggeringInfoController.class);

    private static final String BLOCK_ID = "id";
    private static final String USER_ID = "userId";

    @Resource
    private IBlockContracService blockContracService;

    @RequestMapping(value = "/queryContractTriggering.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryContractTriggering(HttpServletRequest httpServletRequest) {
        String blockId = httpServletRequest.getParameter(BLOCK_ID).trim();
        String userId = httpServletRequest.getParameter(USER_ID).trim();

        Map<String, List<ContractTriggerInfo>> map = blockContracService.queryContractTriggering(Long.valueOf(blockId), Long.valueOf(userId));
        if (logger.isDebugEnabled()) {
            logger.debug("查询待触发列表:", map.toString());
        }

        return retContent(map);
    }
}
