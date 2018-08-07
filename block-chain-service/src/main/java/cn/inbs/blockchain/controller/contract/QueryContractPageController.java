package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.service.contract.IContractPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:合约分页查询服务
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName:
 * @Date: 2018年05月18-10:33
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/contract")
public class QueryContractPageController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryContractPageController.class);

    @Resource
    private IContractPageService contractPageService;

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryPage(HttpServletRequest httpServletRequest) {
        //参数校验
        PagePo pagePo = checkParam(httpServletRequest);

        //分页查询(根据标识查询数据[isFundsTrigger!=null时为查询资金端合约触发列表])
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        Object isFundsTrigger = conditionParamMap.get(ContractConstants.PAGE_IS_FUNDS_TRIGGER);
        PagePo result;
        if (null == isFundsTrigger) {
            result = contractPageService.queryContractPage(pagePo);
        } else {
            result = contractPageService.queryContractPageByFundsTrigger(pagePo);
        }

        return retContent(result);
    }

    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @return
     */
    private PagePo checkParam(HttpServletRequest httpServletRequest) {
        PagePo pagePo = new PagePo();

        //每页条数
        String pageCountStr = httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_PAGE_COUNT);//每页条数
        if (null != pageCountStr) {
            try {
                Integer pageCount = Integer.valueOf(pageCountStr);
                pagePo.setPageCount(pageCount);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取每页条数值:{}不合法", pageCountStr, e);
                }
            }
        }

        //页数
        String pageIndexStr = httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_PAGE_INDEX);//页数
        if (null != pageIndexStr) {
            try {
                Integer pageIndex = Integer.valueOf(pageIndexStr);
                pagePo.setPageIndex(pageIndex);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取页数值:{}不合法", pageIndexStr, e);
                }
            }
        }

        //获取分页查询条件
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(CompanyConstants.USER_ID, httpServletRequest.getParameter(CompanyConstants.USER_ID));//用户ID
        map.put(ContractConstants.PAGE_CONDITION_START_INDEX, pagePo.getPageStartCountIndex());//也开始行数
        map.put(ContractConstants.PAGE_CONDITION_PAGE_COUNT, pagePo.getPageCount());//查询行数
        map.put(ContractConstants.PAGE_CONDITION_REGISTER, StringUtils.space2Null(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_REGISTER)));//登记人
        map.put(ContractConstants.PAGE_CONDITION_SIGNER, StringUtils.space2Null(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_SIGNER)));//签订人
        map.put(ContractConstants.PAGE_CONDITION_COTRACT_NAME, StringUtils.space2Null(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_COTRACT_NAME)));//签订人
        map.put(ContractConstants.PAGE_IS_FUNDS_TRIGGER, StringUtils.space2Null(httpServletRequest.getParameter(ContractConstants.PAGE_IS_FUNDS_TRIGGER)));//查询标识
        String status = StringUtils.space2Null(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_CONTRACT_STATUS));

        //合约状态
        if (null != status) {
            map.put(ContractConstants.PAGE_CONDITION_CONTRACT_STATUS, getStatuList(status));
        } else {
            map.put(ContractConstants.PAGE_CONDITION_CONTRACT_STATUS, ContractConstants.CONTRACT_STATUS_ZC);
        }

        //开始时间
        Date startTime = null;
        try {
            startTime = DateUtils.parseDate(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_START_TIME), DateUtils.DateFormat.DATE_FORMAT_9);
            map.put(ContractConstants.PAGE_CONDITION_START_TIME, startTime);
        } catch (Exception e) {
            map.put(ContractConstants.PAGE_CONDITION_START_TIME, null);
        }

        //结束时间
        Date endTime = null;
        try {
            endTime = DateUtils.parseDate(httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_END_TIME), DateUtils.DateFormat.DATE_FORMAT_9);
            map.put(ContractConstants.PAGE_CONDITION_END_TIME, endTime);
        } catch (Exception e) {
            map.put(ContractConstants.PAGE_CONDITION_END_TIME, null);
        }

        //开始结束时间比对
        if (null != startTime && null != endTime) {
            if (startTime.compareTo(endTime) > 0) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0033);
            }
        }

        pagePo.setConditionParamMap(map);
        return pagePo;
    }

    /**
     * 获取查询状态列表
     *
     * @param status
     * @return
     */
    private List<String> getStatuList(String status) {
        String[] statusArray = status.split(",");
        List<String> statuList = new ArrayList<String>();
        for (String statu : statusArray) {
            if (!CommonConstants.STRING_SPACE.equals(statu)) {
                statuList.add(statu.trim());
            }
        }
        return statuList;
    }
}
