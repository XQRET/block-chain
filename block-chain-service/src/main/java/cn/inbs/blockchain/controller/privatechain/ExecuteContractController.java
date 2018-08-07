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
 * @Description: 私有链合约执行
 * @Package: cn.inbs.blockchain.controller.privatechain
 * @ClassName:
 * @Date: 2018年06月01-14:28
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping("/contract")
public class ExecuteContractController extends BaseController {
    @Resource
    private IPrivateChainContractService privateChainContractService;

    public static final String COMPANY_BLOCK_ID = "companyBlockId";//公司区块链ID
    public static final String CONTRACT_BLOCK_ID = "contractBlockId";//合约区块ID
    public static final String CONTRACT_ID = "contractId";//合约ID


    public static final String CHECK_RESULT_IS_RELATION_ZJ = "relationCheck";//
    public static final String CHECK_RESULT_COMPANY_BLOCK_ID = "companyBlockId";//
    public static final String CHECK_RESULT_COMPANY_NAME = "companyName";//

    public static final String EXECUTE_REMARK = "remark";//


    @RequestMapping(value = "/executeCheckContract.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String executeCheckContract(HttpServletRequest httpServletRequest) throws Exception {
        //IP拦截
//        HttpUtil.banIP(httpServletRequest);

        //参数校验
        TreeMap<String, String> treeMap = checkParam(httpServletRequest);


        //执行检查
        TreeMap<String, String> returnMap = privateChainContractService.queryByExecuteCheckContract(treeMap);

        //生成签约数据
        String resultSign = RSAUtils.sign(returnMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
        returnMap.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, resultSign);

        return retContent(returnMap);
    }

    @RequestMapping(value = "/executeContract.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String executeContract(HttpServletRequest httpServletRequest) throws Exception {
        //IP拦截
//        HttpUtil.banIP(httpServletRequest);

        //参数校验
        TreeMap<String, String> treeMap = checkParam(httpServletRequest);

        //调用合约执行
        TreeMap<String, String> returnMap = privateChainContractService.updateContractStatusByExecute(treeMap);

        //生成签约数据
        String resultSign = RSAUtils.sign(returnMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
        returnMap.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, resultSign);

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

        String sign = httpServletRequest.getParameter(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
        if (StringUtils.isEmpty(sign)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
        }

        String contractBlockId = httpServletRequest.getParameter(CONTRACT_BLOCK_ID);
        if (StringUtils.isEmpty(contractBlockId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, CONTRACT_BLOCK_ID);
        }

        String companyBlockId = httpServletRequest.getParameter(COMPANY_BLOCK_ID);
        if (StringUtils.isEmpty(companyBlockId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, COMPANY_BLOCK_ID);
        }

        String contractId = httpServletRequest.getParameter(CONTRACT_ID);
        if (StringUtils.isEmpty(contractId)) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0001, CONTRACT_ID);
        }

        treeMap.put(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, sign);
        treeMap.put(CONTRACT_BLOCK_ID, contractBlockId);
        treeMap.put(COMPANY_BLOCK_ID, companyBlockId);
        treeMap.put(CONTRACT_ID, contractId);

        if (logger.isDebugEnabled()) {
            logger.debug("修改合约执行请求参数:{}", treeMap.toString());
        }
        return treeMap;
    }
}
