package cn.inbs.blockchain.service.contract.impl;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.BlockUtils;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.controller.privatechain.ExecuteContractController;
import cn.inbs.blockchain.controller.privatechain.RecordingContractExecuteSerial;
import cn.inbs.blockchain.controller.privatechain.RefuseExecuteContractController;
import cn.inbs.blockchain.controller.privatechain.inputparam.RegisterContractInput;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractFileUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractInfoUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractSerialMapper;
import cn.inbs.blockchain.dao.po.*;
import cn.inbs.blockchain.service.contract.IPrivateChainContractService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.contract.impl
 * @ClassName:
 * @Date: 2018年05月31-15:37
 * @Author: createBy:zhangmingyang
 **/
@Service("privateChainContractService")
public class PrivateChainContractServiceImpl implements IPrivateChainContractService {
    private static Logger logger = LoggerFactory.getLogger(PrivateChainContractServiceImpl.class);

    @Resource
    private BlockCompanyMapper blockCompanyMapper;//公司

    @Resource
    private BlockContractMapper blockContractMapper;//合约

    @Resource
    private ContractSerialMapper contractSerialMapper;//流水

    @Resource
    private ContractInfoUrlMapper contractInfoUrlMapper;//关联信息

    @Resource
    private ContractFileUrlMapper contractFileUrlMapper;

    /**
     * 合约注册
     *
     * @param input
     * @return
     */
    @Override
    public TreeMap<String, String> insertContract(RegisterContractInput input) {
        //根据公司区块ID查询当前公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(input.getCompanyBlockId());
        BlockCompany resultCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //公司校验
        checkCompanyInfo(input.getCompanyBlockId(), resultCompany);

        //签名校验
        insertContractCheckSign(input, resultCompany.getCompanyPublicKey());

        //根据房产证编号查询当前房屋是否已经注册
        BlockContract checkResult = checkContractIsExist(input);

        if (null == checkResult) {
            //合约不存在注册新的合约
            return registContract(input, resultCompany);
        } else {
            //合约存在执行以下操作
            if (ContractConstants.CONTRACT_STATUS_ZC.equals(checkResult.getContractStatus())) {
                //状态为合约注册状态(10)返回注册信息
                if (!resultCompany.getCompanyBlockId().equals(checkResult.getRegistCompanyBlockId())) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0035);

                }
                return assemblyResultMap(checkResult);
            } else if (ContractConstants.CONTRACT_STATUS_XH.equals(checkResult.getContractStatus())) {
                //状态为注销(40)可执行报备
                return registContract(input, resultCompany);
            } else {
                //其余状态下是不允许报备
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0013, checkResult.getHouseCode());
            }
        }
    }

    /**
     * 合约报备数据签名校验
     *
     * @param input
     * @param publicKey
     */
    private void insertContractCheckSign(RegisterContractInput input, String publicKey) {
        TreeMap<String, String> signMap = RSAUtils.getSignMap(input);
        signMap.remove("sign");
        try {
            if (!RSAUtils.verify(signMap, publicKey, input.getSign())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0004);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0004);
        }
    }


    /**
     * 合约执行检查
     *
     * @param treeMap
     * @return
     */
    @Override
    public TreeMap<String, String> queryByExecuteCheckContract(TreeMap<String, String> treeMap) {
        TreeMap<String, String> returnMap = new TreeMap<String, String>();

        //根据公司区块ID查询当前公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(treeMap.get(ExecuteContractController.COMPANY_BLOCK_ID));
        BlockCompany resultCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //公司校验
        checkCompanyInfo(treeMap.get(ExecuteContractController.COMPANY_BLOCK_ID), resultCompany);

        //签名校验
        checkSign(treeMap, resultCompany.getCompanyPublicKey());

        //查询当前合约信息
        BlockContract blockContract = new BlockContract();
        String contractId = treeMap.get(ExecuteContractController.CONTRACT_ID);
        blockContract.setContractId(contractId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);

        //检查合约信息
        if (null == blockContract) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0014, contractId);
        } else {
            //检查当前合约是否属于该公司注册
            if (!resultCompany.getCompanyBlockId().equals(blockContract.getRegistCompanyBlockId())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0020);
            }
            if (!(ContractConstants.CONTRACT_STATUS_ZC.equals(blockContract.getContractStatus()) ||
                    ContractConstants.CONTRACT_STATUS_CF_PASS.equals(blockContract.getContractStatus()))) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0015, contractId);
            } else {
                //获取合约关联资金方
                String triggerCompanyBlockId = blockContract.getTriggerCompanyBlockId();
                if (null == triggerCompanyBlockId) {
                    returnMap.put(ExecuteContractController.CHECK_RESULT_IS_RELATION_ZJ, "1");
                    returnMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_BLOCK_ID, resultCompany.getCompanyBlockId());
                    returnMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_NAME, resultCompany.getCompanyName());
                } else {
                    returnMap.put(ExecuteContractController.CHECK_RESULT_IS_RELATION_ZJ, "0");
                    returnMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_BLOCK_ID, triggerCompanyBlockId);
                    BlockCompany query = new BlockCompany();
                    query.setCompanyBlockId(triggerCompanyBlockId);
                    query = blockCompanyMapper.selectBlockCompanyByIndex(query);
                    returnMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_NAME, query.getCompanyName());
                }
            }
        }
        return returnMap;
    }

    /**
     * 合约执行
     *
     * @param treeMap
     * @return
     */
    @Override
    public TreeMap<String, String> updateContractStatusByExecute(TreeMap<String, String> treeMap) {
        TreeMap<String, String> returnMap = new TreeMap<String, String>();

        //根据公司区块ID查询当前公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(treeMap.get(ExecuteContractController.COMPANY_BLOCK_ID));
        BlockCompany resultCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //公司校验
        checkCompanyInfo(treeMap.get(ExecuteContractController.COMPANY_BLOCK_ID), resultCompany);

        //签名校验
        checkSign(treeMap, resultCompany.getCompanyPublicKey());

        //查询当前合约信息
        BlockContract blockContract = new BlockContract();
        String contractId = treeMap.get(ExecuteContractController.CONTRACT_ID);
        blockContract.setContractId(contractId);
        blockContract = blockContractMapper.selectBlockContractByIndex(blockContract);
        if (null == blockContract) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0014, contractId);
        } else {
            //检查当前合约是否属于该公司注册
            if (!resultCompany.getCompanyBlockId().equals(blockContract.getRegistCompanyBlockId())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0020);
            }
            if (ContractConstants.CONTRACT_STATUS_ZC.equals(blockContract.getContractStatus()) ||
                    ContractConstants.CONTRACT_STATUS_CF_PASS.equals(blockContract.getContractStatus())) {
                //只有刚注册以及刚触发通过的合约才能执行

                //初始化流水参数
                ContractSerial contractSerial = new ContractSerial();
                contractSerial.setCompanyBlockId(resultCompany.getCompanyBlockId());//操作机构
                contractSerial.setContractId(blockContract.getContractId());//合约ID
                contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块链ID

                //初始化修改合约参数
                BlockContract updateBlockContract = new BlockContract();

                //如果当前合约状态为注册(10)表示当前合约为自由资金放款执行,记录合约触发流水
                if (ContractConstants.CONTRACT_STATUS_ZC.equals(blockContract.getContractStatus())) {
                    Date dateCf = new Date();

                    //组装合约触发流水概要
                    String serRemark = StringUtils.
                            assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_PASS_DESCRIPTION, null), DateUtils.formatDate(dateCf, DateUtils.DateFormat.DATE_FORMAT_8), resultCompany.getCompanyName());

                    contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_PASS);//触发状态
                    contractSerial.setContractRemark(serRemark);//触发概要
                    contractSerial.setExecuteRemark(serRemark);//执行概要
                    contractSerial.setCreateTime(dateCf);//创建时间
                    contractSerial.setUpdateTime(dateCf);//修改时间
                    int countCf = contractSerialMapper.insertContractSerial(contractSerial);
                    if (1 != countCf) {
                        throw new BusinessException(BusinessErrorConstants.CONTRACT_0016);
                    }

                    //记录合约触发机构
                    updateBlockContract.setTriggerCompanyBlockId(resultCompany.getCompanyBlockId());
                }

                //合约执行时间
                Date dateZx = new Date();

                //组装执行概要
                String executeRemark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_EXECUTE_DESCRIPTION, null), DateUtils.formatDate(dateZx, DateUtils.DateFormat.DATE_FORMAT_8));

                //修改合约状态为执行状态
                updateBlockContract.setId(blockContract.getId());//合约ID
                updateBlockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);//合约状态改为执行中
                updateBlockContract.setUpdateTime(dateZx);//合约修改时间
                updateBlockContract.setRemark(executeRemark);//合约概要
                updateBlockContract.setContractToDate(DateUtils.getDateAfterOrBeforeMonth(dateZx, blockContract.getTerm()));//设置合约到期日期
                int count = blockContractMapper.updateStatusAndRefuseTimesById(updateBlockContract);
                if (1 != count) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0016);
                }

                //记录合约执行流水
                contractSerial.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);//触发状态
                contractSerial.setContractRemark(executeRemark);//触发概要
                contractSerial.setExecuteRemark(executeRemark);//执行概要
                contractSerial.setCreateTime(dateZx);//创建时间
                contractSerial.setUpdateTime(dateZx);//修改时间
                int countZx = contractSerialMapper.insertContractSerial(contractSerial);
                if (1 != countZx) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0016);
                }

                //组装返回值
                returnMap.put(ExecuteContractController.EXECUTE_REMARK, executeRemark);
            } else {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0015, contractId);
            }
        }
        return returnMap;
    }

    /**
     * 拒绝合约执行
     *
     * @param treeMap
     * @return
     */
    @Override
    public TreeMap<String, String> updateContractStatusByRefuseExecute(TreeMap<String, String> treeMap) {
        TreeMap<String, String> returnMap = new TreeMap<String, String>();

        //根据公司区块ID查询当前公司信息
        String companyBlockId = treeMap.get(RefuseExecuteContractController.REQ_COMPANY_BLOCK_ID);
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(companyBlockId);
        BlockCompany resultCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //校验公司信息
        checkCompanyInfo(companyBlockId, resultCompany);

        //签名校验
        checkSign(treeMap, resultCompany.getCompanyPublicKey());

        //查询当前合约信息
        BlockContract query = new BlockContract();
        String contractId = treeMap.get(RefuseExecuteContractController.REQ_CONTRACT_ID);
        query.setContractId(contractId);
        query = blockContractMapper.selectBlockContractByIndex(query);

        if (null == query) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0014, contractId);
        } else {
            //检查当前合约是否属于该公司注册
            if (!resultCompany.getCompanyBlockId().equals(query.getRegistCompanyBlockId())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0020);
            }

            //执行合约检查
            if (!ContractConstants.CONTRACT_STATUS_ZC.equals(query.getContractStatus())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0021);
            } else {
                //执行拒绝放款(合约注销)

                //触发时间
                Date dateCf = new Date();

                //组装合约触发流水概要
                String refuseRemark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_REFUSE_DESCRIPTION, null), DateUtils.formatDate(dateCf, DateUtils.DateFormat.DATE_FORMAT_8));

                //记录合约触发流水
                ContractSerial contractSerialCf = new ContractSerial();
                contractSerialCf.setUpdateTime(dateCf);//修改时间
                contractSerialCf.setCreateTime(dateCf);//创建时间
                contractSerialCf.setContractId(query.getContractId());//合约ID
                contractSerialCf.setContractBlockId(query.getContractBlockId());//合约区块ID
                contractSerialCf.setCompanyBlockId(companyBlockId);//操作机构
                contractSerialCf.setContractStatus(ContractConstants.CONTRACT_STATUS_CF_UN_PASS);//状态为合约触发拒绝
                contractSerialCf.setContractRemark(refuseRemark);//概要
                contractSerialCf.setExecuteRemark(refuseRemark);//操作描述
                int insertCountCf = contractSerialMapper.insertContractSerial(contractSerialCf);
                if (1 != insertCountCf) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0022);
                }

                //注销时间
                Date dateZx = new Date();

                //组装合约注销描述
                String remark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_DESTROY_DESCRIPTION, null), DateUtils.formatDate(dateZx, DateUtils.DateFormat.DATE_FORMAT_8));

                //记录合约注销流水
                ContractSerial contractSerialXh = new ContractSerial();
                contractSerialXh.setUpdateTime(dateZx);//修改时间
                contractSerialXh.setCreateTime(dateZx);//创建时间
                contractSerialXh.setContractId(query.getContractId());//合约ID
                contractSerialXh.setContractBlockId(query.getContractBlockId());//合约区块ID
                contractSerialXh.setCompanyBlockId(companyBlockId);//操作机构
                contractSerialXh.setContractStatus(ContractConstants.CONTRACT_STATUS_XH);//状态为合约触发拒绝
                contractSerialXh.setContractRemark(remark);//概要
                contractSerialXh.setExecuteRemark(remark);//操作描述
                int insertCountXh = contractSerialMapper.insertContractSerial(contractSerialXh);
                if (1 != insertCountXh) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0022);
                }

                //修改合约状态为注销状态
                BlockContract update = new BlockContract();
                update.setRemark(remark);//概要
                update.setId(query.getId());//合约ID
                update.setContractStatus(ContractConstants.CONTRACT_STATUS_XH);//销毁状态
                update.setUpdateTime(dateZx);//注销时间
                update.setTriggerCompanyBlockId(companyBlockId);//合约触发机构
                int count = blockContractMapper.updateStatusAndRefuseTimesById(update);
                if (1 != count) {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0022);
                }

                //组装返回参数
                returnMap.put(RefuseExecuteContractController.RES_EXECUTE_REMARK, remark);
            }
        }
        return returnMap;
    }

    /**
     * 记录合约执行流水
     *
     * @param treeMap
     * @return
     */
    @Override
    public TreeMap<String, String> insertContractExecuteSerial(TreeMap<String, String> treeMap) {
        TreeMap<String, String> returnMap = new TreeMap<String, String>();

        //根据公司区块ID查询当前公司信息
        String companyBlockId = treeMap.get(RecordingContractExecuteSerial.REQ_COMPANY_BLOCK_ID);
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(companyBlockId);
        BlockCompany resultCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //校验公司信息
        checkCompanyInfo(companyBlockId, resultCompany);

        //签名校验
        checkSign(treeMap, resultCompany.getCompanyPublicKey());

        //查询当前合约信息
        BlockContract query = new BlockContract();
        String contractId = treeMap.get(RecordingContractExecuteSerial.REQ_CONTRACT_ID);
        query.setContractId(contractId);
        query = blockContractMapper.selectBlockContractByIndex(query);

        if (null == query) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0014, contractId);
        } else {
            //检查当前合约是否属于该公司注册
            if (!resultCompany.getCompanyBlockId().equals(query.getRegistCompanyBlockId())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0020);
            }

            //合约期数校验
            Integer contractTerm = query.getTerm();
            Integer reqTerm = Integer.valueOf(treeMap.get(RecordingContractExecuteSerial.REQ_CONTRACT_TERM_NUM));
            if (reqTerm > contractTerm) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0025, String.valueOf(contractTerm), String.valueOf(reqTerm));
            }

            //检查当前合约是否正在执行中
            if (!ContractConstants.CONTRACT_STATUS_ZX.equals(query.getContractStatus())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0026);
            } else {
                //检查是否已经登记该期流水
                ContractSerial querySerial = new ContractSerial();
                querySerial.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);
                querySerial.setContractId(query.getContractId());
                querySerial.setRepaymentTrem(reqTerm);
                querySerial = contractSerialMapper.selectSerialIsExist(querySerial);
                if (null == querySerial) {
                    //查询是否存在上一期流水
                    if (reqTerm > 1) {
                        ContractSerial lastSerical = new ContractSerial();
                        lastSerical.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);
                        lastSerical.setContractId(query.getContractId());
                        lastSerical.setRepaymentTrem(reqTerm - 1);
                        lastSerical = contractSerialMapper.selectSerialIsExist(lastSerical);
                        if (null == lastSerical) {
                            throw new BusinessException(BusinessErrorConstants.CONTRACT_0028);
                        }
                    }

                    //流水登记日期
                    Date date = new Date();

                    //获取还款状态,组装合约执行概要
                    String remark;
                    String contractSerialStatus = treeMap.get(RecordingContractExecuteSerial.REQ_CONTRACT_SERIAL_STATUS);
                    String dateStr = DateUtils.formatDate(date, DateUtils.DateFormat.DATE_FORMAT_8);
                    if (ContractConstants.CONTRACT_SERIAL_STATUS_ZC.equals(contractSerialStatus)) {
                        remark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_EXECUTEING_DESCRIPTION, null), dateStr, reqTerm.toString());
                    } else {
                        remark = StringUtils.assemblyStringMessageByParam(PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_OVERDUE_DESCRIPTION, null), dateStr, reqTerm.toString());
                    }

                    //执行还款流水登记
                    ContractSerial insert = new ContractSerial();
                    insert.setContractId(query.getContractId());//合约ID
                    insert.setContractBlockId(query.getContractBlockId());//合约区块ID
                    insert.setCompanyBlockId(companyBlockId);//合约操作公司ID
                    insert.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);//合约状态
                    insert.setContractRemark(remark);//合约概要
                    insert.setExecuteRemark(remark);//操作描述
                    insert.setCreateTime(date);//创建时间
                    insert.setUpdateTime(date);//修改时间
                    insert.setRepaymentTrem(reqTerm);//还款期数
                    insert.setRepaymentStatus(contractSerialStatus);//还款状态(逾期或者正常)
                    int count = contractSerialMapper.insertContractSerial(insert);
                    if (1 != count) {
                        throw new BusinessException(BusinessErrorConstants.CONTRACT_0029);
                    }

                    //组装返回参数
                    returnMap.put(RecordingContractExecuteSerial.RES_CONTRACT_REMARK, remark);

                } else {
                    throw new BusinessException(BusinessErrorConstants.CONTRACT_0027, String.valueOf(reqTerm));
                }
            }
        }
        return returnMap;
    }

    /**
     * 校验公司信息
     *
     * @param reqCompanyBlockId
     * @param resultCompany
     */
    private void checkCompanyInfo(String reqCompanyBlockId, BlockCompany resultCompany) {
        //检查公司信息
        if (null == resultCompany) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0002,
                    ContractConstants.COMPANY_BLOCK_ID,
                    reqCompanyBlockId);
        } else {
            //日志记录报备合约公司信息
            if (logger.isDebugEnabled()) {
                logger.debug("报备合约资产端信息:{}", resultCompany.toString());
            }

            //认证未通过不允许注册
            if (!CompanyConstants.COMPANY_STATUS_2.equals(resultCompany.getCompanyStatus())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0003,
                        resultCompany.getCompanyBlockId());
            }

            //只有资产端能够导入数据
            if (!CompanyConstants.COMPANY_TYPE_ZC.equals(resultCompany.getCompanyType())) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0012);
            }
        }
    }

    /**
     * 签名校验
     *
     * @param treeMap
     * @param publicKey
     */
    private void checkSign(TreeMap<String, String> treeMap, String publicKey) {
        String sign = treeMap.get(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
        treeMap.remove(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);

        try {
            if (!RSAUtils.verify(treeMap, publicKey, sign)) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0004);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("验签未通过", e);
            }
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0004);
        }
    }


    /**
     * 检查合约是否已经存在
     *
     * @param input
     */
    private BlockContract checkContractIsExist(RegisterContractInput input) {
        String houseCode = input.getHouseCode();
        String houseBlockId = BlockUtils.createContractBlockId(houseCode);
        BlockContract blockContract = new BlockContract();
        blockContract.setContractBlockId(houseBlockId);
        return blockContractMapper.selectBlockContractByIndex(blockContract);

    }

    /**
     * 创建合约
     *
     * @param input
     * @param resultCompany
     * @return
     */
    private TreeMap<String, String> registContract(RegisterContractInput input, BlockCompany resultCompany) {
        //不存在执行合约注册
        BlockContract blockContract = createContract(input);
        blockContract.setCompanyId(resultCompany.getId());//公司ID
        blockContractMapper.insertBlockContract(blockContract);

        //记录合约流水
        recordingoCntractSerial(blockContract, input.getCompanyBlockId());

        //记录获取信息URL
        recordingoContractInfoUrl(blockContract, input);

        //记录房源图片信息
        recordingoContractHouseInfo(blockContract, input);

        if (logger.isInfoEnabled()) {
            logger.info("合约:{} 注册成功", blockContract.getContractId());
        }
        return assemblyResultMap(blockContract);
    }

    /**
     * 记录合约房源图片信息
     *
     * @param blockContract
     * @param input
     */
    private void recordingoContractHouseInfo(BlockContract blockContract,
                                             RegisterContractInput input) {
        String houseImgUrls = input.getHouseImgUrls();
        if (StringUtils.isNotEmpty(houseImgUrls)) {
            String[] split = houseImgUrls.split(",");
            if (split.length > 0) {
                for (String houseInfoUrl : split) {
                    ContractFileUrl contractFileUrl = new ContractFileUrl();
                    contractFileUrl.setContractId(blockContract.getContractId());
                    contractFileUrl.setFileMode("1");
                    contractFileUrl.setFileType("1");
                    contractFileUrl.setFileUrl(houseInfoUrl);
                    contractFileUrl.setCreateTime(blockContract.getRegistDate());
                    contractFileUrl.setUpdateTime(blockContract.getRegistDate());
                    int count = contractFileUrlMapper.insertSelective(contractFileUrl);
                    if (1 != count) {
                        throw new BusinessException(BusinessErrorConstants.CONTRACT_0006);
                    }
                }
            }
        }
    }

    /**
     * 记录合约注册流水
     *
     * @param blockContract
     * @param companyBlockId
     */
    private void recordingoCntractSerial(BlockContract blockContract, String companyBlockId) {
        ContractSerial contractSerial = new ContractSerial();
        contractSerial.setCompanyBlockId(companyBlockId);//操作机构
        contractSerial.setContractId(blockContract.getContractId());//合约ID
        contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块链ID
        contractSerial.setContractRemark(getContractRemark(blockContract.getRegistDate()));//概要
        contractSerial.setContractStatus(blockContract.getContractStatus());//合约状态
        contractSerial.setCreateTime(blockContract.getRegistDate());//注册时间
        contractSerial.setUpdateTime(blockContract.getRegistDate());//修改时间
        contractSerial.setExecuteRemark(getContractRemark(blockContract.getRegistDate()));//操作描述
        contractSerialMapper.insertContractSerial(contractSerial);
    }

    /**
     * 组装remark
     *
     * @param registerDate
     * @return
     */
    private String getContractRemark(Date registerDate) {
        String messsgeTemplate = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.CONTRACT_REGISTER_DESCRIPTION_KEY, null);
        return StringUtils.assemblyStringMessageByParam(messsgeTemplate,
                DateUtils.formatDate(registerDate, DateUtils.DateFormat.DATE_FORMAT_8));
    }


    /**
     * 组装注册合约信息
     *
     * @param input
     * @return
     */
    private BlockContract createContract(RegisterContractInput input) {
        BlockContract blockContract = new BlockContract();
        Date registerDate = new Date();
        String houseCode = input.getHouseCode();
        blockContract.setContractName(input.getSigner() + "分期租赁合约");//合约名称
        blockContract.setContractId(BlockUtils.createContractId(houseCode));//合约ID
        blockContract.setContractBlockId(BlockUtils.createContractBlockId(houseCode));//合约区块ID
        blockContract.setAmount(new BigDecimal(input.getAmount()));//合约金额
        blockContract.setBank(input.getBank());//资金方银行
        blockContract.setRegistCompanyBlockId(input.getCompanyBlockId());//注册方公司区块ID
        blockContract.setHouseCode(houseCode);//房产证编号
        blockContract.setHouseInfo(input.getHouseInfo());//房屋信息
        blockContract.setInterestRate(new BigDecimal(input.getInterestRate()));//lilv
        blockContract.setRegistDate(registerDate);//注册时间
        blockContract.setUpdateTime(registerDate);
        blockContract.setContractRegister(input.getRegister());//登记人
        blockContract.setRemark(getContractRemark(registerDate));//合约概要
        blockContract.setRepaymentWay(input.getRepaymentWay());//还款方式
        blockContract.setContractSigner(input.getSigner());
        blockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_ZC);//合约状态
        blockContract.setTerm(new Integer(input.getTerm()));//合约期数
        blockContract.setImportType(ContractConstants.CONTRACT_TYPE_0);//合约注册类型
        blockContract.setRefuseTimes(0);//初始化合约拒绝次数

        blockContract.setIdNo(input.getIdNo());//身份证号码

        //平台逾期金额
        if (StringUtils.isNotEmpty(input.getOverdueAmount())) {
            blockContract.setOverdueAmount(new BigDecimal(input.getOverdueAmount()));
        }

        //平台逾期次数
        if (StringUtils.isNotEmpty(input.getOverdueNum())) {
            blockContract.setOverdueNum(Integer.valueOf(input.getOverdueNum()));
        }

        //征信逾期次数
        if (StringUtils.isNotEmpty(input.getCreditOverdueNum())) {
            blockContract.setCreditOverdueNum(Integer.valueOf(input.getCreditOverdueNum()));
        }

        //征信借款次数
        if (StringUtils.isNotEmpty(input.getCreditLoanNum())) {
            blockContract.setCreditLoanNum(Integer.valueOf(input.getCreditLoanNum()));
        }

        //合类型
        blockContract.setContractType(input.getContractType());

        //jsondata
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put(ContractConstants.SIGNER_HOUSE_ADDRESS, input.getHouseAddress());
        jsonMap.put(ContractConstants.SIGNER_HOUSE_APARTMENTS, input.getHouseApartments());
        jsonMap.put(ContractConstants.SIGNER_HOUSE_TYPE, input.getHouseType());
        jsonMap.put(ContractConstants.SIGNER_HOUSE_SIZE, input.getHouseSize());
        jsonMap.put(ContractConstants.SIGNER_HOUSE_TOTAL_AMOUNT, input.getHouseTotalAmount());
        jsonMap.put(ContractConstants.SIGNER_NUMBER_OF_HAIRSTYLES, input.getNumberOfHairstyles());
        blockContract.setAttachInfoJson(JSON.toJSONString(jsonMap));

        return blockContract;
    }

    /**
     * 记录Url信息
     *
     * @param blockContract
     * @param input
     */
    private void recordingoContractInfoUrl(BlockContract blockContract,
                                           RegisterContractInput input) {
        //出事滑参数
        ContractInfoUrl contractInfoUrl = new ContractInfoUrl();
        contractInfoUrl.setCreateTime(blockContract.getRegistDate());//创建时间
        contractInfoUrl.setUpdateTime(blockContract.getRegistDate());//修改时间
        contractInfoUrl.setContractId(blockContract.getContractId());//合约ID

        int count;
        //插入个人报告
        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_PERSON_INFO);
        contractInfoUrl.setUrl(input.getPersonInfoUrl());
        count = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
        if (1 != count) {
            if (logger.isErrorEnabled()) {
                logger.error("插入获取个人信息URL失败");
            }
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0006);
        }

        //插入房屋
        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_HOUSE_INFO);
        contractInfoUrl.setUrl(input.getHouseInfoUrl());
        count = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
        if (1 != count) {
            if (logger.isErrorEnabled()) {
                logger.error("插入获取房屋信息URL失败");
            }
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0006);
        }

        //插入下载URL
        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_FILE_INFO);
        contractInfoUrl.setUrl(input.getDownloadFileInfoUrl());
        count = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
        if (1 != count) {
            if (logger.isErrorEnabled()) {
                logger.error("插入下载URL失败");
            }
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0006);
        }
    }

    /**
     * 组装返回参数
     *
     * @param blockContract
     * @return
     */
    private TreeMap<String, String> assemblyResultMap(BlockContract blockContract) {
        TreeMap<String, String> resultMap = new TreeMap<String, String>();
        resultMap.put(ContractConstants.CONTRACT_BLOCK_ID, blockContract.getContractBlockId());
        resultMap.put(ContractConstants.COTRACT_ID, blockContract.getContractId());
        resultMap.put(ContractConstants.COTRACT_REMARK, blockContract.getRemark());
        return resultMap;
    }
}
