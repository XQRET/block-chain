package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.contract.inputparam.QueryDetailContractInput;
import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.dao.po.ContractInfoUrl;
import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description: 资金机构查询合约触发详细信息
 * @Package: cn.inbs.blockchain.controller.contract
 * @ClassName:
 * @Date: 2018年05月23-19:06
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/contract")
public class QueryDetailContractController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryDetailContractController.class);

    @Resource
    private IBlockContracService blockContracService;

    @RequestMapping(value = "/queryDetailContract.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryDetail(QueryDetailContractInput input) throws UnsupportedEncodingException {
        //查询合约详情
        DetailContractInfo detailContractInfo = blockContracService.queryDetailContractInfo(input);

        //数据URL加密
        detailContractInfo.setContractInfoUrlList(signContractInfoUrl(detailContractInfo.getContractInfoUrlList(), detailContractInfo.getBlockContract()));

        //房屋信息拆分
        detailContractInfo.setBlockContract(splitContractHouseinfo(detailContractInfo.getBlockContract()));

        //日志记录房屋详细信息
        if (logger.isDebugEnabled()) {
            logger.debug("合约:[{}]详细信息:[{}]", detailContractInfo.getBlockContract().getId(), detailContractInfo.toString());
        }

        return retContent(detailContractInfo);
    }

    /**
     * 实现加密组装RUL
     *
     * @param contractInfoUrls
     * @param blockContract
     */
    private List<ContractInfoUrl> signContractInfoUrl(List<ContractInfoUrl> contractInfoUrls, BlockContract blockContract) throws UnsupportedEncodingException {
        ContractInfoUrl newContractInfoUrl = null;
        for (ContractInfoUrl contractInfoUrl : contractInfoUrls) {
            if (ContractConstants.CONTRACT_INFO_PERSON_INFO.equals(contractInfoUrl.getUrlType()) ||
                    ContractConstants.CONTRACT_INFO_HOUSE_INFO.equals(contractInfoUrl.getUrlType())) {
                if (ContractConstants.CONTRACT_TYPE_0.equals(blockContract.getImportType())) {
                    String url = contractInfoUrl.getUrl();
                    TreeMap<String, String> treeMap = new TreeMap<>();
                    treeMap.put(ContractConstants.H5_PARAM_KEY_REQUEST_URL, url);

                    String sign;
                    try {
                        sign = RSAUtils.sign(treeMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
                        sign = URLEncoder.encode(sign, CommonConstants.COMMON_ENCODING);
                    } catch (Exception e) {
                        throw new BusinessException(e, BusinessErrorConstants.CONTRACT_0007);
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder
                            .append(url)
                            .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                            .append(ContractConstants.REGISTER_CONTRACT_SIGN_KEY).append(ContractConstants.SYM_EQUAL).append(sign)
                            .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                            .append(ContractConstants.H5_PARAM_KEY_REQUEST_URL).append(ContractConstants.SYM_EQUAL).append(URLEncoder.encode(url, CommonConstants.COMMON_ENCODING));

                    contractInfoUrl.setUrl(stringBuilder.toString());
                }
            } else if (ContractConstants.CONTRACT_INFO_FILE_INFO.equals(contractInfoUrl.getUrlType())) {
                if (ContractConstants.CONTRACT_TYPE_0.equals(blockContract.getImportType())) {
                    newContractInfoUrl = new ContractInfoUrl();
                    newContractInfoUrl.setUrl(contractInfoUrl.getUrl());
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                        .append("/contract/downloadContractInfo.do")
                        .append("?")
                        .append("contractId").append(ContractConstants.SYM_EQUAL).append(contractInfoUrl.getContractId())
                        .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                        .append(ContractConstants.H5_REQUEST_IS_THIRD).append(ContractConstants.SYM_EQUAL).append(String.valueOf(Boolean.TRUE));
                contractInfoUrl.setUrl(stringBuilder.toString());
            }
        }

        if (null != newContractInfoUrl) {
            String sign;
            try {
                TreeMap<String, String> treeMap = new TreeMap<>();
                treeMap.put(ContractConstants.H5_PARAM_KEY_REQUEST_URL, newContractInfoUrl.getUrl());
                sign = RSAUtils.sign(treeMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
                sign = URLEncoder.encode(sign, CommonConstants.COMMON_ENCODING);
            } catch (Exception e) {
                throw new BusinessException(e, BusinessErrorConstants.CONTRACT_0007);
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(newContractInfoUrl.getUrl())
                    .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                    .append(ContractConstants.REGISTER_CONTRACT_SIGN_KEY).append(ContractConstants.SYM_EQUAL).append(sign)
                    .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                    .append(ContractConstants.H5_PARAM_KEY_REQUEST_URL).append(ContractConstants.SYM_EQUAL).append(URLEncoder.encode(newContractInfoUrl.getUrl(), CommonConstants.COMMON_ENCODING));

            newContractInfoUrl.setUrlType("4");
            newContractInfoUrl.setUrl(stringBuilder.toString());
            contractInfoUrls.add(newContractInfoUrl);
        }

        return contractInfoUrls;
    }

    /**
     * 处理房屋信息
     *
     * @param blockContract
     */
    private BlockContract splitContractHouseinfo(BlockContract blockContract) {
        String houseInfo = blockContract.getHouseInfo();
        if (null != houseInfo) {
            if (logger.isDebugEnabled()) {
                logger.debug("房屋信息(houseInfo):[{}]", houseInfo);
            }

            //分隔房源信息
            if (houseInfo.contains("|")) {
                String[] splitHouseInfo = houseInfo.split(ContractConstants.HOUSE_INFO_SPLIT_SYM);
                blockContract.setHouseInfo(splitHouseInfo[0]);
            }
        } else {
            blockContract.setHouseInfo(CommonConstants.STRING_SPACE);
        }

        return blockContract;
    }
}
