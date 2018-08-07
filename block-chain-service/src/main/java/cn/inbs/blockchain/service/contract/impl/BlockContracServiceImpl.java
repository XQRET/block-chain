package cn.inbs.blockchain.service.contract.impl;

import cn.inbs.blockchain.common.commonbean.ContractDetail;
import cn.inbs.blockchain.common.commonbean.ContractTriggerInfo;
import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.*;
import cn.inbs.blockchain.controller.contract.inputparam.QueryDetailContractInput;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.company.CompanyRelationMapper;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractFileUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractInfoUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractSerialMapper;
import cn.inbs.blockchain.dao.po.*;
import cn.inbs.blockchain.service.contract.IBlockContracService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.contract.impl
 * @ClassName:
 * @Date: 2018年05月15-11:28
 * @Author: createBy:zhangmingyang
 **/
@Service("blockContracService")
public class BlockContracServiceImpl implements IBlockContracService {
    private static Logger logger = LoggerFactory.getLogger(BlockContracServiceImpl.class);

    @Resource
    private BlockContractMapper blockContractMapper;

    @Resource
    private ContractSerialMapper contractSerialMapper;

    @Resource
    private BlockCompanyMapper blockCompanyMapper;

    @Resource
    private ContractInfoUrlMapper contractInfoUrlMapper;

    @Resource
    private CompanyRelationMapper companyRelationMapper;

    @Resource
    private ContractFileUrlMapper contractFileUrlMapper;

    /**
     * 查询合约详细信息
     *
     * @param input
     * @return
     */
    @Override
    public DetailContractInfo queryDetailContractInfo(QueryDetailContractInput input) {
        //查询合约基本信息
        BlockContract blockContract = new BlockContract();
        if (StringUtils.isNotEmpty(input.getId())) {
            blockContract.setId(Long.valueOf(input.getId()));
        }
        blockContract.setContractId(input.getContractId());
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        if (null != blockContract) {
            //设置合约概要
            List<ContractSerial> contractSerials = assemblyContractRemark(blockContract);
            blockContract.setRemark(getEndContractRemark(contractSerials));

            //查询合约附属信息
            ContractInfoUrl contractInfoUrl = new ContractInfoUrl();
            contractInfoUrl.setContractId(blockContract.getContractId());
            List<ContractInfoUrl> contractInfoUrls = contractInfoUrlMapper.selectContractInfoUrlListByContractBlockId(contractInfoUrl);

            //RUL加密
//            signContractInfoUrl(contractInfoUrls);

            //组装返回参数
            DetailContractInfo detailContractInfo = new DetailContractInfo();
            detailContractInfo.setContractSerials(contractSerials);
            detailContractInfo.setBlockContract(blockContract);
            detailContractInfo.setContractInfoUrlList(contractInfoUrls);
            return detailContractInfo;
        } else {
            return null;
        }
    }


    /**
     * 根绝合约状态组装合约概要
     *
     * @param blockContract
     * @return
     */
    private List<ContractSerial> assemblyContractRemark(BlockContract blockContract) {
        //查询当前合约流水
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("contractId", blockContract.getContractId());
        List<ContractSerial> contractSerials = contractSerialMapper.selectContractSerialsByStatusAndContractId(paramMap);

        //记录合约概要
        List<ContractSerial> remarkSerials = new ArrayList<>();

        //根据状态组装合约概要
        String contractStatus = blockContract.getContractStatus();
        if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractStatus)) {
            //注册状态
            for (ContractSerial contractSerial : contractSerials) {
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                    break;
                }
            }
        } else if (ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractStatus)) {
            //合约触发通过
            for (ContractSerial contractSerial : contractSerials) {
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                }
            }
        } else if (ContractConstants.CONTRACT_STATUS_CF_UN_PASS.equals(contractStatus)) {
            //合约触发拒绝
            List<ContractSerial> refuseSers = new ArrayList<>();
            for (ContractSerial contractSerial : contractSerials) {
                //注册流水
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                }
                //拒绝流水
                if (ContractConstants.CONTRACT_STATUS_CF_UN_PASS.equals(contractSerial.getContractStatus())) {
                    refuseSers.add(contractSerial);
                }
            }
            if (CollectionUtils.isNotEmpty(refuseSers)) {
                refuseSers = ContractSerial.orderSerialsByCreateTime(refuseSers);
                remarkSerials.add(refuseSers.get(0));
            }
        } else if (ContractConstants.CONTRACT_STATUS_ZX.equals(contractStatus)) {
            //合约执行中
            for (ContractSerial contractSerial : contractSerials) {
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_ZX.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                }
            }
        } else if (ContractConstants.CONTRACT_STATUS_ZX_FINISHED.equals(contractStatus)) {
            //合约执行完成
            for (ContractSerial contractSerial : contractSerials) {
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_ZX.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_ZX_FINISHED.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                }
            }
        } else if (ContractConstants.CONTRACT_STATUS_XH.equals(contractStatus)) {
            //合约销毁
            for (ContractSerial contractSerial : contractSerials) {
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_ZX.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_ZX_FINISHED.equals(contractSerial.getContractStatus()) ||
                        ContractConstants.CONTRACT_STATUS_XH.equals(contractSerial.getContractStatus())) {
                    remarkSerials.add(contractSerial);
                }
            }
        }


        return remarkSerials;
    }

    /**
     * 组装合约概要
     *
     * @param contractSerials
     * @return
     */
    private String getEndContractRemark(List<ContractSerial> contractSerials) {
        //流水时间倒叙排
        contractSerials = ContractSerial.orderSerialsByCreateTime(contractSerials);

        //组装概要
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < contractSerials.size(); i++) {
            stringBuilder.append(contractSerials.get(i).getContractRemark());
            if (i != contractSerials.size() - 1) {
                stringBuilder.append(CommonConstants.NEW_LINE);
            }
        }
        return stringBuilder.toString();
    }


    @Override
    public Map<String, List<ContractTriggerInfo>> queryContractTriggering(Long blockId, Long userId) {
        //根据用户ID查询用户机构信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //查询该公司关联成功机构
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setZcCompanyBlockId(blockCompany.getCompanyBlockId());//资金端公司区块ID
        companyRelation.setRelationStatus(CompanyConstants.COMPANY_RELATION_STATUS_1);//关联通过状态
        List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationByIndex(companyRelation);

        //获取合约相关信息
        BlockContract blockContract = new BlockContract();
        blockContract.setId(blockId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        //查询当前合约流水(状态:拒绝,通过)
        Map<String, Object> paramMap = new HashMap<>();
        List<String> status = new ArrayList<>();
        status.add(ContractConstants.CONTRACT_STATUS_CF_PASS);
        status.add(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);
        paramMap.put("contractStatus", status);
        paramMap.put("contractId", blockContract.getContractId());
        List<ContractSerial> contractSerials = contractSerialMapper.selectContractSerialsByStatusAndContractId(paramMap);

        //通过,拒绝,待审核分离
        List<ContractSerial> refuse = new ArrayList<>();
        List<ContractSerial> pass = new ArrayList<>();
        for (ContractSerial contractSerial : contractSerials) {
            if (ContractConstants.CONTRACT_STATUS_CF_PASS.equals(contractSerial.getContractStatus())) {
                //合约通过机构
                pass.add(contractSerial);
            } else {
                //合约拒绝机构
                refuse.add(contractSerial);
            }

            //合约待触发机构
            for (int i = 0; i < companyRelations.size(); i++) {
                if (contractSerial.getCompanyBlockId().equals(companyRelations.get(i).getZjCompanyBlockId())) {
                    companyRelations.remove(i);
                    break;
                }
            }
        }

        //查询公司信息
        //通过
        List<ContractTriggerInfo> passInfos = new ArrayList<>();
        for (ContractSerial contractSerial : pass) {
            //查询当前流水执行机构
            queryCompanyByContractSer(passInfos, contractSerial);
        }

        //拒绝
        List<ContractTriggerInfo> refuseInfos = new ArrayList<>();
        for (ContractSerial contractSerial : refuse) {
            //查询当前流水执行机构
            queryCompanyByContractSer(refuseInfos, contractSerial);
        }

        //待审核
        List<ContractTriggerInfo> triggeringInfos = new ArrayList<>();
        for (CompanyRelation relation : companyRelations) {
            //查询公司信息
            BlockCompany query = new BlockCompany();
            query.setCompanyBlockId(relation.getZjCompanyBlockId());
            query = blockCompanyMapper.selectBlockCompanyByIndex(query);

            //赋值
            ContractTriggerInfo contractTriggerInfo = new ContractTriggerInfo();
            contractTriggerInfo.setBlockCompany(query);
            triggeringInfos.add(contractTriggerInfo);
        }

        Map<String, List<ContractTriggerInfo>> returnMap = new HashMap<>();
        returnMap.put("refuseCompanys", refuseInfos);//拒绝
        returnMap.put("triggeringCompanys", triggeringInfos);//待审核
        returnMap.put("triggeredCompanys", passInfos);//通过
        return returnMap;
    }

    /**
     * 根据流水查询公司信息并组装
     *
     * @param contractTriggerInfos
     * @param contractSerial
     */
    private void queryCompanyByContractSer(List<ContractTriggerInfo> contractTriggerInfos,
                                           ContractSerial contractSerial) {
        BlockCompany query = new BlockCompany();
        query.setCompanyBlockId(contractSerial.getCompanyBlockId());
        query = blockCompanyMapper.selectBlockCompanyByIndex(query);

        ContractTriggerInfo contractTriggerInfo = new ContractTriggerInfo();
        contractTriggerInfo.setBlockCompany(query);
        contractTriggerInfo.setContractSerial(contractSerial);
        contractTriggerInfos.add(contractTriggerInfo);
    }

    @Override
    public void updateContractStatus(Long contractId, String status, Long userId, String remark) {
        //查询触发合约的公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //查询合约信息
        BlockContract blockContract = new BlockContract();
        blockContract.setId(contractId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        //记录触发流水
        ContractSerial contractSerial = new ContractSerial();
        Date insertDate = new Date();
        contractSerial.setContractId(blockContract.getContractId());//合约id
        contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块ID
        contractSerial.setCompanyBlockId(blockCompany.getCompanyBlockId());//公司区块ID
        contractSerial.setContractStatus(status);//触发合约状态
        contractSerial.setExecuteRemark(remark);//备注
        contractSerial.setCreateTime(insertDate);
        contractSerial.setUpdateTime(insertDate);


        //修改合约最终状态
        if (status.equals(ContractConstants.CONTRACT_STATUS_CF_PASS)) {
            //通过直接把状态记为通过
            BlockContract update = new BlockContract();
            update.setId(contractId);//合约ID
            update.setContractStatus(status);//合约状态
            update.setTriggerCompanyBlockId(blockCompany.getCompanyBlockId());//触发通过机构
            update.setUpdateTime(insertDate);//修改时间

            //合约摘要
            String contractRemark = StringUtils.
                    assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_PASS_DESCRIPTION, null),
                            DateUtils.formatDate(insertDate, DateUtils.DateFormat.DATE_FORMAT_8),
                            blockCompany.getCompanyName());

            update.setRemark(contractRemark);
            contractSerial.setContractRemark(contractRemark);//概要
            int updateCount = blockContractMapper.updateStatusAndRefuseTimesById(update);
            if (1 != updateCount) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0008);
            }
        } else {
            //拒绝时判断是否是最后一个拒绝的,是的话状态改为触发拒绝

            //查询当前机构关联资金机构(关联成功)
            CompanyRelation companyRelation = new CompanyRelation();
            companyRelation.setZcCompanyBlockId(blockContract.getRegistCompanyBlockId());
            companyRelation.setRelationStatus(CompanyConstants.COMPANY_RELATION_STATUS_1);
            List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationByIndex(companyRelation);

            BlockContract update = new BlockContract();
            update.setId(contractId);//合约ID
            update.setRefuseTimes(blockContract.getRefuseTimes() + 1);//拒绝次数
            update.setUpdateTime(insertDate);

            //合约摘要
            String contractRemark = StringUtils.
                    assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_REFUSE_DESCRIPTION, null),
                            DateUtils.formatDate(insertDate, DateUtils.DateFormat.DATE_FORMAT_8));
            contractSerial.setContractRemark(contractRemark);//概要


            if (blockContract.getRefuseTimes() == companyRelations.size() - 1) {
                //如果为最后一个,更新状态为21
                update.setContractStatus(status);//合约状态
                update.setTriggerCompanyBlockId(blockCompany.getCompanyBlockId());//触发拒绝机构
                update.setRemark(contractRemark);
            }
            int updateCount = blockContractMapper.updateStatusAndRefuseTimesById(update);
            if (1 != updateCount) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0008);
            }
        }

        int count = contractSerialMapper.insertContractSerial(contractSerial);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0008);
        }
    }

    @Override
    public void updateContractByPublicChainDestroy(Long contractId, String companyBlockId) {
        //查询当前合约信息
        BlockContract blockContract = new BlockContract();
        blockContract.setId(contractId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        if (null == blockContract) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0017);
        } else {
            //判断合约状态
            String contractStatus = blockContract.getContractStatus();
            if (!(ContractConstants.CONTRACT_STATUS_ZC.equals(contractStatus) ||
                    ContractConstants.CONTRACT_STATUS_CF_UN_PASS.equals(contractStatus))) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0018);
            } else {
                //初始化流水参数
                ContractSerial contractSerial = new ContractSerial();
                contractSerial.setCompanyBlockId(companyBlockId);//公司区块id
                contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块ID
                contractSerial.setContractId(blockContract.getContractId());//合约Id

                //初始化修改合约状态参数
                BlockContract update = new BlockContract();

                //合约状态为注册状态记录合约触发流水,及合约触发机构
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(contractStatus)) {
                    //合约触发日期
                    Date dateCf = new Date();

                    //组装触发概要
                    String refuseRemark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_REFUSE_DESCRIPTION, null), DateUtils.formatDate(dateCf, DateUtils.DateFormat.DATE_FORMAT_8));

                    //触发拒绝流水参数设置
                    contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);//合约状态
                    contractSerial.setContractRemark(refuseRemark);//合约概要
                    contractSerial.setExecuteRemark(refuseRemark);//合约执行概要
                    contractSerial.setCreateTime(dateCf);//创建时间
                    contractSerial.setUpdateTime(dateCf);//修改时间
                    int insertCountSer = contractSerialMapper.insertContractSerial(contractSerial);
                    if (1 != insertCountSer) {
                        throw new BusinessException(BusinessErrorConstants.CONTRACT_0019);
                    }

                    update.setTriggerCompanyBlockId(companyBlockId);//设置合约触发机构
                }

                //合约销毁日期
                Date destroyDate = new Date();

                //组装合约注销概要
                String remark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_DESTROY_DESCRIPTION, null), DateUtils.formatDate(destroyDate, DateUtils.DateFormat.DATE_FORMAT_8));

                //执行合约注销,修改合约状态
                update.setId(contractId);//合约Id
                update.setUpdateTime(destroyDate);//合约修改时间
                update.setContractStatus(ContractConstants.CONTRACT_STATUS_XH);//合约状态
                update.setRemark(remark);//合约当前概要
                int count = blockContractMapper.updateStatusAndRefuseTimesById(update);
                if (1 != count) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0019);
                }

                //记录合约销毁流水
                contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_XH);//合约状态
                contractSerial.setContractRemark(remark);//合约概要
                contractSerial.setExecuteRemark(remark);//合约执行概要
                contractSerial.setCreateTime(destroyDate);//创建时间
                contractSerial.setUpdateTime(destroyDate);//修改时间
                int insertCount = contractSerialMapper.insertContractSerial(contractSerial);
                if (1 != insertCount) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0019);
                }
            }
        }
    }

    @Override
    public String queryLogoutDetailContract(String contractId, String status) {
        //根据合约和状态查询流水表
        ContractSerial query = new ContractSerial();
        query.setContractStatus(status);
        query.setContractId(contractId);
        query = contractSerialMapper.selectContractSerialsByStatusAndContractIdAndCompanyBlock(query);
        //根据blockid查询公司名称
        String companyBlockId = query.getCompanyBlockId();
        BlockCompany queryCompany = new BlockCompany();
        queryCompany.setCompanyBlockId(companyBlockId);
        queryCompany = blockCompanyMapper.selectBlockCompanyByIndex(queryCompany);
        return queryCompany.getCompanyName();
    }

    @Override
    public void updateWaitLogoutDetail(Long contractId, String status, Long userId) {
        //查询触发合约的公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //查询合约信息
        BlockContract blockContract = new BlockContract();
        blockContract.setId(contractId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        //记录注销流水
        ContractSerial contractSerial = new ContractSerial();
        Date insertDate = new Date();
        contractSerial.setContractId(blockContract.getContractId());//合约id
        contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块ID
        contractSerial.setCompanyBlockId(blockCompany.getCompanyBlockId());//公司区块ID
        contractSerial.setContractStatus(status);//注销合约状态
        contractSerial.setCreateTime(insertDate);
        contractSerial.setUpdateTime(insertDate);
        //修改合约最终状态
        if (status.equals(ContractConstants.CONTRACT_STATUS_XH)) {
            //通过直接把状态记为通过
            BlockContract update = new BlockContract();
            update.setId(contractId);//合约ID
            update.setContractStatus(status);//合约状态
            update.setTriggerCompanyBlockId(blockCompany.getCompanyBlockId());//注销机构
            update.setUpdateTime(insertDate);//修改时间

            //合约摘要
            String contractRemark = StringUtils.
                    assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_DESTROY_DESCRIPTION, null),
                            DateUtils.formatDate(insertDate, DateUtils.DateFormat.DATE_FORMAT_8),
                            blockCompany.getCompanyName());

            update.setRemark(contractRemark);
            contractSerial.setContractRemark(contractRemark);//概要
            int updateCount = blockContractMapper.updateStatusAndRefuseTimesById(update);
            if (1 != updateCount) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0008);
            }
        }
        int count = contractSerialMapper.insertContractSerial(contractSerial);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0008);
        }
    }

    @Override
    public Map showIndexInfo() {
        //格式化double
        DecimalFormat df = new DecimalFormat("#0");
        //用户总数
        Integer userSums = blockContractMapper.selectTotalCount(null);
        //当前进件数
        String curSums = df.format(userSums / 0.3);
        //总交易笔数
        String transactionSums = df.format(userSums * 0.8);
        Map map = new HashMap(4);
        //合约总金额
        map.put("amountSums", blockContractMapper.selectTotalAmount());
        map.put("userSums", userSums);
        map.put("curSums", curSums);
        map.put("transactionSums", transactionSums);
        return map;
    }

    @Override
    public List<ContractDetail> selectContractByContractType(Map<String, Object> map, String rootPath) {
        //合约详情带房产照片
        List<ContractDetail> contractDetailList = new ArrayList<>();
        //根据条件查询合约
        List<BlockContract> blockContracts = blockContractMapper.selectContractByContractType(map);
        if (blockContracts.size() > 0) {
            for (BlockContract blockContract : blockContracts) {
                //组装url
                ContractDetail contractDetail = new ContractDetail();
                //查询房源信息URL
                ContractFileUrl contractFileUrl = new ContractFileUrl();
                contractFileUrl.setContractId(blockContract.getContractId());
                contractFileUrl.setFileMode("1");
                contractFileUrl.setFileType("1");
                List<ContractFileUrl> contractFileUrls = contractFileUrlMapper.selectContractFileUrlList(contractFileUrl);
                //组装合约详情
                if (contractFileUrls.size() > 0) {
                    contractDetail.setContractFileUrl(contractFileUrls.get(0));
                }

                contractDetail.setContractUrl(rootPath + "/contract/leaseQueryContractDetailInfo.do?ContractId=" + blockContract.getContractId());
                contractDetail.setBlockContract(blockContract);
                contractDetailList.add(contractDetail);
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("Query ContractDetail List:" + contractDetailList.toString());
        }
        return contractDetailList;
    }

}
