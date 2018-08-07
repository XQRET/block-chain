package cn.inbs.blockchain.service.contract.impl;

import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.company.CompanyRelationMapper;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractSerialMapper;
import cn.inbs.blockchain.dao.po.*;
import cn.inbs.blockchain.service.contract.IContractPageService;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.contract.impl
 * @ClassName:
 * @Date: 2018年05月25-14:16
 * @Author: createBy:zhangmingyang
 **/
@Service("contractPageService")
public class ContractPageServiceImpl implements IContractPageService {
    private static Logger logger = LoggerFactory.getLogger(ContractPageServiceImpl.class);

    @Resource
    private BlockContractMapper blockContractMapper;

    @Resource
    private BlockCompanyMapper blockCompanyMapper;

    @Resource
    private ContractSerialMapper contractSerialMapper;

    @Resource
    private CompanyRelationMapper companyRelationMapper;

    @Override
    public PagePo queryContractPage(PagePo pagePo) {
        //输出分页请求参数
        if (logger.isDebugEnabled()) {
            logger.debug("合约列表查询请求参数:{}", pagePo.toString());
        }

        //获取请求参数用户ID
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        String userId = (String) conditionParamMap.get(CompanyConstants.USER_ID);

        //根据用ID查询公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(Long.valueOf(userId));
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        if (null != blockCompany) {
            //公司信息存在

            //设置信贷机构ID
            List<String> companyBlockIds = new ArrayList<String>();
            if (CompanyConstants.COMPANY_TYPE_ZC.equals(blockCompany.getCompanyType())) {
                companyBlockIds.add(blockCompany.getCompanyBlockId());
            } else {
                //资金端获取当前公司关联机构
                CompanyRelation companyRelation = new CompanyRelation();
                companyRelation.setZjCompanyBlockId(blockCompany.getCompanyBlockId());//资金端公司区块ID
                companyRelation.setRelationStatus(CompanyConstants.COMPANY_RELATION_STATUS_1);//关联通过状态
                List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationByIndex(companyRelation);
                for (CompanyRelation relation : companyRelations) {
                    companyBlockIds.add(relation.getZcCompanyBlockId());
                }

                //合约触发机构
                List<String> triggerCompanyBlockIds = new ArrayList<String>();
                triggerCompanyBlockIds.add(blockCompany.getCompanyBlockId());
                conditionParamMap.put(CompanyConstants.TRIGGER_COMPANY_BLOCK_ID, triggerCompanyBlockIds);

                //时候包含资金触发机构为空
                List<String> contractStatus = (List<String>) conditionParamMap.get(ContractConstants.PAGE_CONDITION_CONTRACT_STATUS);
                List<String> zjTriggerStatsus = new ArrayList<String>();
                zjTriggerStatsus.add(ContractConstants.CONTRACT_STATUS_ZC);
                zjTriggerStatsus.add(ContractConstants.CONTRACT_STATUS_CF_PASS);
                if (contractStatus.containsAll(zjTriggerStatsus)) {
                    conditionParamMap.put("isInclude", "1");
                } else {
                    conditionParamMap.put("isInclude", "2");
                }
            }

            if (CollectionUtils.isEmpty(companyBlockIds)) {
                return pagePo;
            }

            conditionParamMap.put(ContractConstants.COMPANY_BLOCK_ID, companyBlockIds);

            //查询分页信息
            List<BlockContract> blockContracts = blockContractMapper.selectPageList(conditionParamMap);

            //机构类型为资金端,设置该机构操作合约状态
            if (CompanyConstants.COMPANY_TYPE_ZJ.equals(blockCompany.getCompanyType())) {
                setContractEndStatus(blockContracts, blockCompany);
            }
            pagePo.setPageInfoList(blockContracts);

            //查询总条数
            pagePo.setTotalCount(blockContractMapper.selectTotalCount(conditionParamMap));

            //记录日志值返回
            if (logger.isDebugEnabled()) {
                logger.debug("查询合约分页信息结果:{}", pagePo.toString());
            }
            return pagePo;
        } else {
            //公司信息不存在
            if (logger.isWarnEnabled()) {
                logger.warn("用户:{}不存在公司信息", userId);
            }
            return pagePo;
        }
    }

    /**
     * 暴露合约在资金端下的状态
     *
     * @param blockContracts
     * @param blockCompany
     */
    private void setContractEndStatus(List<BlockContract> blockContracts, BlockCompany blockCompany) {
        for (BlockContract blockContract : blockContracts) {
            //查询当前机构是否存在拒绝该合约流水
            ContractSerial contractSerial = new ContractSerial();
            contractSerial.setCompanyBlockId(blockCompany.getCompanyBlockId());//公司区块ID
            contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);//状态:拒绝
            contractSerial.setContractId(blockContract.getContractId());//合约ID
            ContractSerial contractSerial1 = contractSerialMapper.selectContractSerialsByStatusAndContractIdAndCompanyBlock(contractSerial);
            if (null != contractSerial1) {
                blockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);
                blockContract.setUpdateTime(contractSerial1.getCreateTime());
            }
        }
    }

    @Override
    public PagePo queryContractPageByFundsTrigger(PagePo pagePo) {
        //输出分页请求参数
        if (logger.isDebugEnabled()) {
            logger.debug("合约列表查询请求参数:{}", pagePo.toString());
        }

        //获取请求参数用户ID
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        String userId = (String) conditionParamMap.get(CompanyConstants.USER_ID);

        //根据用ID查询公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(Long.valueOf(userId));
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        if (null != blockCompany) {
            //查询当前公司关联机构(关联成功状态)
            CompanyRelation queryRelation = new CompanyRelation();
            queryRelation.setZjCompanyBlockId(blockCompany.getCompanyBlockId());
            queryRelation.setRelationStatus(CompanyConstants.COMPANY_RELATION_STATUS_1);
            List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationByIndex(queryRelation);
            if (CollectionUtils.isEmpty(companyRelations)) {
                return pagePo;
            } else {
                conditionParamMap.put("relations", doReationDate(companyRelations, conditionParamMap));
                //合约触发机构
                List<String> triggerCompanyBlockIds = new ArrayList<String>();
                triggerCompanyBlockIds.add(blockCompany.getCompanyBlockId());
                conditionParamMap.put(CompanyConstants.TRIGGER_COMPANY_BLOCK_ID, triggerCompanyBlockIds);

                //查询数据列表
                List<BlockContract> blockContracts = blockContractMapper.selectPageListByFundsTrigger(conditionParamMap);
                setContractEndStatus(blockContracts, blockCompany);
                pagePo.setPageInfoList(blockContracts);

                //查询总行数
                pagePo.setTotalCount(blockContractMapper.selectTotalCountByFundsTrigger(conditionParamMap));
                return pagePo;
            }
        } else {
            //公司信息不存在
            if (logger.isWarnEnabled()) {
                logger.warn("用户:{}不存在公司信息", userId);
            }
            return pagePo;
        }
    }

    /**
     * 设置查询时间
     *
     * @param companyRelations
     * @param conditionParamMap
     * @return
     */
    private List<CompanyRelation> doReationDate(List<CompanyRelation> companyRelations, HashMap<String, Object> conditionParamMap) {
        Object startTimeObj = conditionParamMap.get(ContractConstants.PAGE_CONDITION_START_TIME);//查询开始时间
        if (null == startTimeObj) {
            return companyRelations;
        } else {
            //请求开始时间
            Date startTime = (Date) startTimeObj;
            for (CompanyRelation companyRelation : companyRelations) {
                //关联时间
                Date relationTime = companyRelation.getUpdateTime();

                //开始时间大于关联时间时,取请求开始时间
                if (startTime.compareTo(companyRelation.getUpdateTime()) > 0) {
                    relationTime = startTime;
                }

                //结束时间大于关联时间,
                Object endTimeObj = conditionParamMap.get(ContractConstants.PAGE_CONDITION_END_TIME);
                if (null != endTimeObj) {
                    Date endTime = (Date) endTimeObj;
                    if (relationTime.compareTo(endTime) > 0) {
                        relationTime = null;
                        conditionParamMap.put(ContractConstants.PAGE_CONDITION_END_TIME, new Date(0));
                    }
                }
                companyRelation.setUpdateTime(relationTime);
            }
            return companyRelations;
        }
    }
}
