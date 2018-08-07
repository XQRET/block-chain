package cn.inbs.blockchain.controller.privatechain;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.contract.IPrivateChainContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.TreeMap;

/**
 * @Description: 资产端拒绝合约执行放款
 * @Package: cn.inbs.blockchain.controller.privatechain
 * @ClassName:
 * @Date: 2018年06月06-17:25
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping("/contract")
public class RefuseExecuteContractController extends BaseController {
    public static final String REQ_COMPANY_BLOCK_ID = "companyBlockId";//公司区块链ID
    public static final String REQ_CONTRACT_BLOCK_ID = "contractBlockId";//合约区块ID
    public static final String REQ_CONTRACT_ID = "contractId";//合约ID

    public static final String RES_EXECUTE_REMARK = "remark";//

    @Resource
    private IPrivateChainContractService privateChainContractService;


    @RequestMapping(value = "/refuseExecuteContract.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String refuseExecuteContract(HttpServletRequest httpServletRequest) throws Exception {
        //参数校验
        TreeMap<String, String> treeMap = checkParam(httpServletRequest);

        //执行拒绝放款销毁合约
        TreeMap<String, String> returnMap = privateChainContractService.updateContractStatusByRefuseExecute(treeMap);

        //生成签名数据
        String resultSign = RSAUtils.sign(returnMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
        returnMap.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, resultSign);

        //返回参数
        return retContent(returnMap);
    }

    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @return
     */
    private TreeMap<String, String> checkParam(HttpServletRequest httpServletRequest) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        //签名数据
        String sign = httpServletRequest.getParameter(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
        if (StringUtils.isEmpty(sign)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
        }

        //合约区块ID
        String contractBlockId = httpServletRequest.getParameter(REQ_CONTRACT_BLOCK_ID);
        if (StringUtils.isEmpty(contractBlockId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, REQ_CONTRACT_BLOCK_ID);
        }

        //公司区块ID
        String companyBlockId = httpServletRequest.getParameter(REQ_COMPANY_BLOCK_ID);
        if (StringUtils.isEmpty(companyBlockId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, REQ_COMPANY_BLOCK_ID);
        }

        //合约ID
        String contractId = httpServletRequest.getParameter(REQ_CONTRACT_ID);
        if (StringUtils.isEmpty(contractId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, REQ_CONTRACT_ID);
        }

        treeMap.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, sign);
        treeMap.put(REQ_COMPANY_BLOCK_ID, companyBlockId);
        treeMap.put(REQ_CONTRACT_BLOCK_ID, contractBlockId);
        treeMap.put(REQ_CONTRACT_ID, contractId);

        if (logger.isDebugEnabled()) {
            logger.debug("拒绝放款请求参数:{}", treeMap);
        }

        return treeMap;
    }
}
