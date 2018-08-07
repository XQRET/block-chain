package cn.inbs.blockchain.service.contract.impl;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.controller.contract.inputparam.AddContract2PresaleListInput;
import cn.inbs.blockchain.controller.contract.inputparam.QueryContractPresaleBasicInfoInput;
import cn.inbs.blockchain.controller.contract.inputparam.QueryPresalePageListInput;
import cn.inbs.blockchain.controller.contract.inputparam.UpdateContractRaiseInput;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.BlockContractSaleMapper;
import cn.inbs.blockchain.dao.contract.ContractSerialMapper;
import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.dao.po.BlockContractSale;
import cn.inbs.blockchain.dao.po.ContractSerial;
import cn.inbs.blockchain.service.contract.IContractSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.contract.impl
 * @ClassName: ContractSaleServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 14:16
 * @Version: 1.0
 */
@Service("contractSaleService")
public class ContractSaleServiceImpl implements IContractSaleService {
    private static Logger logger = LoggerFactory.getLogger(ContractSaleServiceImpl.class);

    @Resource
    private BlockContractMapper blockContractMapper;

    @Resource
    private BlockContractSaleMapper blockContractSaleMapper;

    @Resource
    private ContractSerialMapper contractSerialMapper;

    @Override
    public void insertByAddContractToPresaleList(AddContract2PresaleListInput input) {
        //根据合约ID检查待预售的合约是否存在
        BlockContract queryBlockContract = new BlockContract();
        queryBlockContract.setContractId(input.getContractId());
        queryBlockContract = blockContractMapper.selectBlockContractByIndex(queryBlockContract);
        if (null == queryBlockContract) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0017, input.getContractId());
        }

        //检查合约状态
        if (!ContractConstants.CONTRACT_STATUS_ZC.equals(queryBlockContract.getContractStatus())) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0002, input.getContractId());
        }

        //检查合约是否已经加入预售列表
        BlockContractSale queryBlockContractSale = new BlockContractSale();
        queryBlockContractSale.setContractId(input.getContractId());
        queryBlockContractSale = blockContractSaleMapper.selectBlockContractSaleByIndex(queryBlockContractSale);
        if (null != queryBlockContractSale) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0001, input.getContractId());
        }

        Date insertDate = new Date();
        //合约摘要
        String contractRemark = StringUtils.
                assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_PASS_DESCRIPTION, null),
                        DateUtils.formatDate(insertDate, DateUtils.DateFormat.DATE_FORMAT_8),
                        CompanyConstants.SYSTEM_COMPANY_NAME);

        //记录触发流水
        ContractSerial contractSerial = new ContractSerial();
        contractSerial.setContractId(input.getContractId());//合约id
        contractSerial.setContractBlockId(queryBlockContract.getContractBlockId());//合约区块ID
        contractSerial.setCompanyBlockId(CompanyConstants.SYSTEM_COMPANY_ID);//公司区块ID(平台戳发记录平台管理员手机号)
        contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_PASS);//触发合约状态
        contractSerial.setExecuteRemark(contractRemark);//备注
        contractSerial.setCreateTime(insertDate);
        contractSerial.setUpdateTime(insertDate);
        contractSerial.setContractRemark(contractRemark);//概要
        int count = contractSerialMapper.insertContractSerial(contractSerial);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0003);
        }

        //将合约改为戳发状态
        BlockContract update = new BlockContract();
        update.setId(queryBlockContract.getId());//合约ID
        update.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_PASS);//合约状态
        update.setTriggerCompanyBlockId(CompanyConstants.SYSTEM_COMPANY_ID);//触发通过机构
        update.setUpdateTime(insertDate);//修改时间
        update.setRemark(contractRemark);
        int updateCount = blockContractMapper.updateStatusAndRefuseTimesById(update);
        if (1 != updateCount) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0003);
        }

        //加入预售列表
        BlockContractSale insertBlockContractSale = new BlockContractSale();
        insertBlockContractSale.setContractId(input.getContractId());//合约ID
        insertBlockContractSale.setContractProductType(input.getContractProductType());//合约产品类型
        insertBlockContractSale.setContractServings(input.getContractServings());//合约拆分份数
        insertBlockContractSale.setContractAmount(queryBlockContract.getAmount());//合约金额
        insertBlockContractSale.setYearOfRate(input.getYearOfRate());//合约年化利率
        insertBlockContractSale.setContractTerm(queryBlockContract.getTerm());//合约期数
        insertBlockContractSale.setReleaseTime(insertDate);//合约发布时间
        insertBlockContractSale.setRaiseStatus(BlockContractSale.RAISE_STATUS_1);//合约募集状态
        insertBlockContractSale.setContractCurrentProgress(0);//合约募集份数
        insertBlockContractSale.setCreateTime(insertDate);//创建时间
        insertBlockContractSale.setUpdateTime(insertDate);//修改时间
        int insertBlockContractSaleCount = blockContractSaleMapper.insertBlockContractSale(insertBlockContractSale);
        if (1 != insertBlockContractSaleCount) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0003);
        }

        if (logger.isInfoEnabled()) {
            logger.info("添加合约:[{}] 到预售列表成功", input.getContractId());
        }
    }

    @Override
    public BlockContract queryContractPresaleBasicInfo(QueryContractPresaleBasicInfoInput input) {
        BlockContract queryBlockContract = new BlockContract();
        queryBlockContract.setContractId(input.getContractId());
        queryBlockContract = blockContractMapper.selectBlockContractByIndex(queryBlockContract);
        if (null == queryBlockContract) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0017, input.getContractId());
        } else {
            return queryBlockContract;
        }
    }

    @Override
    public QueryPresalePageListInput queryPresalePageList(QueryPresalePageListInput input) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("raiseStatus", input.getRaiseStatus());
        paramMap.put("contractProductType", input.getContractProductType());
        paramMap.put("startIndex", input.getPageStartCountIndex());
        paramMap.put("pageCount", input.getPageCount());
        input.setPageInfoList(blockContractSaleMapper.selectBlockContractSalePageList(paramMap));
        input.setTotalCount(blockContractSaleMapper.selectBlockContractSaleTotalCount(paramMap));

        if (logger.isInfoEnabled()) {
            logger.info("查询预售列表分页信息:[{}]", input.toString());
        }
        return input;
    }

    @Override
    public void updateContractRaiseStatus(UpdateContractRaiseInput input) {
        //检查当前合约是否存在
        BlockContractSale queryBlockContractSale = new BlockContractSale();
        queryBlockContractSale.setContractId(input.getContractId());
        queryBlockContractSale = blockContractSaleMapper.selectBlockContractSaleByIndex(queryBlockContractSale);
        if (null == queryBlockContractSale) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0017, input.getContractId());
        }

        //检查合约状态
        if (input.getRaiseStatus().equals(queryBlockContractSale.getRaiseStatus())) {
            //合约募集状态,1-待募集,2-募集中,3-募集完成
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0004, getRaiseStatusMapper().get(input.getRaiseStatus()));
        }

        //执行状态修改
        BlockContractSale update = new BlockContractSale();
        update.setId(queryBlockContractSale.getId());
        update.setRaiseStatus(input.getRaiseStatus());
        update.setUpdateTime(new Date());
        int count = blockContractSaleMapper.updateBlockContractSaleByIndex(update);
        if (count != 1) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_SALE_0005);
        }

        if (logger.isInfoEnabled()) {
            logger.info("修改合约:[{}]状态成功", input.getContractId());
        }
    }

    /**
     * 募集状态映射关系
     *
     * @return
     */
    private static Map<String, String> getRaiseStatusMapper() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("1", "待募集");
        keyMap.put("2", "募集中");
        keyMap.put("3", "募集完成");
        return keyMap;
    }
}
