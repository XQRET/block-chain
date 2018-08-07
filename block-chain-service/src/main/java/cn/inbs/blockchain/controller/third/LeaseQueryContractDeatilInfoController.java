package cn.inbs.blockchain.controller.third;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.HttpUtil;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.ContractFileUrl;
import cn.inbs.blockchain.dao.po.ContractSerial;
import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.common.commonbean.ThirdQueryContractDetailInfo;
import cn.inbs.blockchain.service.third.IThirdContractService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @AUTHOR leijian
 * @Date Created in 2018/7/17
 **/
@Controller
@RequestMapping(value = "/contract")
public class LeaseQueryContractDeatilInfoController extends BaseController {
    /**
     * 返回h5页面
     * 01长租
     * 02澳洲
     */
    private static final String RETURN_VIEW_PAGE_00 = "/jsp/third/contract/leaseContract.jsp";
    private static final String RETURN_VIEW_PAGE_01 = "/jsp/third/contract/australiaContract.jsp";

    @Resource
    private IThirdContractService thirdContractService;

    /**
     * 第三方查询合约详细信息返回h5页面
     *
     * @param httpServletRequest
     * @param thirdQueryContractDetailInfo 请求基本参数
     * @return
     */
    @RequestMapping(value = "/leaseQueryContractDetailInfo.do", produces = "application/json; charset=utf-8")
    public ModelAndView leaseQueryContractDetailInfo(HttpServletRequest httpServletRequest, ThirdQueryContractDetailInfo thirdQueryContractDetailInfo) throws Exception {
        thirdQueryContractDetailInfo.setValidate(ThirdQueryContractDetailInfo.validate_1);
        //参数校验
        checkParam(thirdQueryContractDetailInfo);

        //日志记录请求方记录
        if (logger.isInfoEnabled()) {
            logger.info("调用方Ip:[{}],请求数据:{}",
                    HttpUtil.getRemoteClientIP(httpServletRequest),
                    thirdQueryContractDetailInfo.toString());
        }

        //查询合约详细信息
        DetailContractInfo detailContractInfo = thirdContractService.queryContractDetailInfoByThird(thirdQueryContractDetailInfo);
        List<ContractSerial> contractSerials = detailContractInfo.getContractSerials();
        List<ContractFileUrl> contractFileUrls = detailContractInfo.getContractFileUrls();
        logger.debug("查询合约信息:{}", detailContractInfo.toString());
        //返回页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("detailContractInfo", detailContractInfo);
        modelAndView.addObject("strRootPath", getRootPath(httpServletRequest));
        modelAndView.addObject("contractSerials", JSON.toJSONString(contractSerials));
        modelAndView.addObject("contractFileUrls", JSON.toJSONString(contractFileUrls));
        if(detailContractInfo.getBlockContract().getContractType().equals(ContractConstants.CONTRACT_LEASE_TYPE_0)){
            modelAndView.setViewName(RETURN_VIEW_PAGE_00);
        }else if(detailContractInfo.getBlockContract().getContractType().equals(ContractConstants.CONTRACT_LEASE_TYPE_1)){
            modelAndView.setViewName(RETURN_VIEW_PAGE_01);
        }
        return modelAndView;
    }

    /**
     * 参数校验
     *
     * @param thirdQueryContractDetailInfo
     */
    private void checkParam(ThirdQueryContractDetailInfo thirdQueryContractDetailInfo) {
        if (null == thirdQueryContractDetailInfo) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, "请求参数");
        } else {
            //合约ID
            if (StringUtils.isEmpty(thirdQueryContractDetailInfo.getContractId())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, "合约Id");
            }
        }
    }
}
