package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
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

/**
 * @Description:
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName:
 * @Date: 2018年05月24-16:54
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/contract")
public class ZJTriggerContractStatusController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ZJTriggerContractStatusController.class);

    private static final String BLOCK_ID = "blockId";
    private static final String USER_ID = "userId";
    private static final String CONTRACT_STATUS = "contractStatus";
    private static final String REMARK = "remark";

    @Resource
    private IBlockContracService blockContracService;

    @RequestMapping(value = "/triggerContractStatus.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String triggerContractStatus(HttpServletRequest httpServletRequest) {
        String blockId = httpServletRequest.getParameter(BLOCK_ID);
        String userId = httpServletRequest.getParameter(USER_ID);
        String remark = httpServletRequest.getParameter(REMARK);

        blockContracService.updateContractStatus(Long.valueOf(blockId.trim()),
                 httpServletRequest.getParameter(CONTRACT_STATUS).trim() ,
                Long.valueOf(userId.trim()),
                remark);
        return retContent(null);
    }

    /**
     * 参数校验
     *
     * @param httpServletRequest
     */
    private void checkStatus(HttpServletRequest httpServletRequest) {
        String status = httpServletRequest.getParameter(CONTRACT_STATUS);
        CheckValueCodeUtils.checkValueCode(false, status.trim(), ValueCodeEnums.CONTRACT_STATUS);
    }
}
