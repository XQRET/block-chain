package cn.inbs.blockchain.service.third.impl;

import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.common.commonbean.ThirdQueryContractDetailInfo;
import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.controller.contract.inputparam.QueryDetailContractInput;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractFileUrlMapper;
import cn.inbs.blockchain.dao.po.*;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import cn.inbs.blockchain.service.third.IThirdContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.third
 * @ClassName:
 * @Date: 2018年06月07-19:23
 * @Author: createBy:zhangmingyang
 **/
@Service("thirdContractService")
public class ThirdContractServiceImpl implements IThirdContractService {
    private static Logger logger = LoggerFactory.getLogger(ThirdContractServiceImpl.class);

    @Resource
    private IBlockContracService blockContracService;

    @Resource
    private BlockContractMapper blockContractMapper;

    @Resource
    private BlockCompanyMapper blockCompanyMapper;

    @Resource
    private ContractFileUrlMapper contractFileUrlMapper;

    /**
     * 第三方查询合约详细信息
     *
     * @param thirdQueryContractDetailInfo 请求参数
     * @return
     * @throws Exception
     */
    @Override
    public DetailContractInfo queryContractDetailInfoByThird(ThirdQueryContractDetailInfo thirdQueryContractDetailInfo) throws Exception {
        DetailContractInfo detailContractInfo;
        //查询合约基本信息
        BlockContract blockContract = new BlockContract();
        blockContract.setContractId(thirdQueryContractDetailInfo.getContractId());
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        if (null == blockContract) {
            //合约信息不存在报错
            throw new BusinessException(BusinessErrorConstants.TQCDI_0001, thirdQueryContractDetailInfo.getContractId());
        } else {
            //只有http数据可以访问
            if (!ContractConstants.CONTRACT_TYPE_0.equals(blockContract.getImportType())) {
                if (logger.isWarnEnabled()) {
                    logger.warn("当前合约：[{}]为 导入类型不为 '0'", thirdQueryContractDetailInfo.getContractId());
                }
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0017, thirdQueryContractDetailInfo.getContractId());
            }

            //数据签名
            if (thirdQueryContractDetailInfo.getValidate().equals(ThirdQueryContractDetailInfo.validate_0)) {
                checkSign(thirdQueryContractDetailInfo, blockContract);
            }
            //查询合约基本信息
            QueryDetailContractInput queryDetailContractInput = new QueryDetailContractInput();
            queryDetailContractInput.setContractId(blockContract.getContractId());
            detailContractInfo = blockContracService.queryDetailContractInfo(queryDetailContractInput);
            if (logger.isInfoEnabled()) {
                logger.info("托米前数据:{}", detailContractInfo.toString());
            }

            //合约信息托米
            detailContractInfo.setBlockContract(tommyBlockContractDate(detailContractInfo.getBlockContract()));

            //组装URl
            detailContractInfo.setContractInfoUrlList(assemblyContractInfoUrl(detailContractInfo.getContractInfoUrlList(), blockContract));

            //查询房源信息URL
            ContractFileUrl contractFileUrl = new ContractFileUrl();
            contractFileUrl.setContractId(blockContract.getContractId());
            contractFileUrl.setFileMode("1");
            contractFileUrl.setFileType("1");
            List<ContractFileUrl> contractFileUrls = contractFileUrlMapper.selectContractFileUrlList(contractFileUrl);
            detailContractInfo.setContractFileUrls(contractFileUrls);

        }
        return detailContractInfo;
    }

    /**
     * 数据签名校验
     *
     * @param thirdQueryContractDetailInfo
     * @param blockContract
     */
    private void checkSign(ThirdQueryContractDetailInfo thirdQueryContractDetailInfo, BlockContract blockContract) throws Exception {
        //查询资金端公司信息
        BlockCompany zjBlockCompany = new BlockCompany();
        zjBlockCompany.setCompanyBlockId(thirdQueryContractDetailInfo.getFundsCompanyBlockId());
        zjBlockCompany = blockCompanyMapper.selectBlockCompanyByIndex(zjBlockCompany);
        if (null == zjBlockCompany) {
            throw new BusinessException(BusinessErrorConstants.TQCDI_0003);
        }

        //执行数据验签
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put(ThirdQueryContractDetailInfo.ASSETS_COMPANY_PUBLICKEY, thirdQueryContractDetailInfo.getAssetsCompanyPublicKey());//资产端公钥
        treeMap.put(ThirdQueryContractDetailInfo.FUNDS_COMPANY_BLOCK_ID, thirdQueryContractDetailInfo.getFundsCompanyBlockId());//资金端公司区块ID
        treeMap.put(ThirdQueryContractDetailInfo.CONTRACT_ID, thirdQueryContractDetailInfo.getContractId());//合约ID
        boolean verify = RSAUtils.verify(treeMap, zjBlockCompany.getCompanyPublicKey(), thirdQueryContractDetailInfo.getSign());
        if (!verify) {
            throw new BusinessException(BusinessErrorConstants.TQCDI_0004);
        }

        //匹配资产端公钥
        String registCompanyBlockId = blockContract.getRegistCompanyBlockId();
        BlockCompany zcBlockCompany = new BlockCompany();
        zcBlockCompany.setCompanyBlockId(registCompanyBlockId);
        zcBlockCompany = blockCompanyMapper.selectBlockCompanyByIndex(zcBlockCompany);
        if (!zcBlockCompany.getCompanyPublicKey().equals(thirdQueryContractDetailInfo.getAssetsCompanyPublicKey())) {
            throw new BusinessException(BusinessErrorConstants.TQCDI_0005);
        }
    }

    /**
     * 合约数据托米
     *
     * @param blockContract
     * @return
     */
    private BlockContract tommyBlockContractDate(BlockContract blockContract) {
        //托米合约名称
        blockContract.setContractName(tommyContractName(blockContract));

        //托米房产编号
        blockContract.setHouseCode(tommyHouseInfo(blockContract.getHouseCode()));

        //托米房产信息
        if (blockContract.getContractType().equals(ContractConstants.CONTRACT_LEASE_TYPE_0)) {
            blockContract.setHouseInfo(tommyHouseInfo(blockContract.getHouseInfo()));
        }

        //托米签订人
        blockContract.setContractSigner(blockContract.getContractSigner().substring(0, 1) + "**");

        return blockContract;
    }


    /**
     * 托米合约名称
     *
     * @param blockContract
     * @return
     */
    private String tommyContractName(BlockContract blockContract) {
        String contractSigner = blockContract.getContractSigner();
        return contractSigner.substring(0, 1) + "**" + "分期租赁合约";
    }

    /**
     * 托米字母和数字信息
     *
     * @param hoseInfo 房屋信息
     * @return
     */
    private String tommyHouseInfo(String hoseInfo) {
        String[] split = hoseInfo.split("", -1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            if (s.matches("^[A-Za-z0-9]+$")) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 组装url
     *
     * @param contractSerialList
     * @return
     */
    private List<ContractInfoUrl> assemblyContractInfoUrl(List<ContractInfoUrl> contractSerialList, BlockContract blockContract) throws Exception {
        for (ContractInfoUrl contractInfoUrl : contractSerialList) {
            if (ContractConstants.CONTRACT_INFO_PERSON_INFO.equals(contractInfoUrl.getUrlType()) ||
                    ContractConstants.CONTRACT_INFO_HOUSE_INFO.equals(contractInfoUrl.getUrlType())) {
                if (ContractConstants.CONTRACT_TYPE_0.equals(blockContract.getImportType())) {

                    String url = contractInfoUrl.getUrl();
                    url = url +
                            ContractConstants.SYM_PARAM_APPEND_SYM +
                            ContractConstants.H5_REQUEST_IS_THIRD + ContractConstants.SYM_EQUAL + String.valueOf(Boolean.TRUE);

                    //生成签名数据
                    TreeMap<String, String> treeMap = new TreeMap<>();
                    treeMap.put(ContractConstants.H5_PARAM_KEY_REQUEST_URL, url);
                    String sign;
                    try {
                        sign = RSAUtils.sign(treeMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
                        sign = URLEncoder.encode(sign, CommonConstants.COMMON_ENCODING);
                    } catch (Exception e) {
                        throw new BusinessException(e, BusinessErrorConstants.CONTRACT_0007);
                    }

                    //组装访问路径
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer
                            .append(url)
                            .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                            .append(ContractConstants.REGISTER_CONTRACT_SIGN_KEY).append(ContractConstants.SYM_EQUAL).append(sign)
                            .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                            .append(ContractConstants.H5_PARAM_KEY_REQUEST_URL).append(ContractConstants.SYM_EQUAL).append(URLEncoder.encode(url, CommonConstants.COMMON_ENCODING));

                    contractInfoUrl.setUrl(stringBuffer.toString());
                }
            } else if (ContractConstants.CONTRACT_INFO_FILE_INFO.equals(contractInfoUrl.getUrlType())) {
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
        return contractSerialList;
    }
}
