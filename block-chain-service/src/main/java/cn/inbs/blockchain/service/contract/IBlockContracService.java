package cn.inbs.blockchain.service.contract;

import cn.inbs.blockchain.common.commonbean.ContractDetail;
import cn.inbs.blockchain.common.commonbean.ContractTriggerInfo;
import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.controller.contract.inputparam.QueryDetailContractInput;

import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.contract
 * @ClassName:
 * @Date: 2018年05月15-11:25
 * @Author: createBy:zhangmingyang
 **/
public interface IBlockContracService {
    /**
     * 查询合约详细信息
     *
     * @param input
     * @return
     */
    DetailContractInfo queryDetailContractInfo(QueryDetailContractInput input);

    /**
     * 查询触发列表
     *
     * @param id
     * @param userId
     * @return
     */
    Map<String, List<ContractTriggerInfo>> queryContractTriggering(Long id, Long userId);

    /**
     * 触发合约状态
     *
     * @param contractId 合约自增ID
     * @param status     触发合约状态
     * @param userId     用户ID
     * @param remark     触发备注
     */
    void updateContractStatus(Long contractId, String status, Long userId, String remark);

    /**
     * 公有链资产端销毁合约
     *
     * @param contractId
     * @param companyBlockId
     */
    void updateContractByPublicChainDestroy(Long contractId, String companyBlockId);


    /**
     * 合约注销查看详情
     *
     * @param contractId 合约id
     * @param status     状态
     * @return 公司名字
     */
    String queryLogoutDetailContract(String contractId, String status);

    /**
     * 合约待注销确认
     *
     * @param contractId
     * @param status
     * @param userId
     */
    void updateWaitLogoutDetail(Long contractId, String status, Long userId);

    /**
     * 首页
     * 查询 当前进件数 用户总数 交易笔数
     *
     * @return
     */
    Map showIndexInfo();

    /**
     * 根据合约状态查询最新合约
     *
     * @param map
     * @param rootPath
     * @return
     */
    List<ContractDetail> selectContractByContractType(Map<String, Object> map, String rootPath);

}
