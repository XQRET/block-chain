package cn.inbs.blockchain.service.contract;

import cn.inbs.blockchain.controller.privatechain.inputparam.RegisterContractInput;

import java.util.TreeMap;

/**
 * @Description:私有链合约相关服务
 * @Package: cn.inbs.blockchain.service.contract
 * @ClassName:
 * @Date: 2018年05月31-15:36
 * @Author: createBy:zhangmingyang
 **/
public interface IPrivateChainContractService {

    /**
     * 私有链合约报备
     *
     * @param input
     * @return
     */
    TreeMap<String, String> insertContract(RegisterContractInput input);

    /**
     * 检查合约
     *
     * @param treeMap
     * @return
     */
    TreeMap<String, String> queryByExecuteCheckContract(TreeMap<String, String> treeMap);

    /**
     * 合约执行
     *
     * @param treeMap
     * @return
     */
    TreeMap<String, String> updateContractStatusByExecute(TreeMap<String, String> treeMap);

    /**
     * 合约拒绝执行
     *
     * @param treeMap
     * @return
     */
    TreeMap<String, String> updateContractStatusByRefuseExecute(TreeMap<String, String> treeMap);

    /**
     * 记录合约执行流水
     *
     * @param treeMap
     * @return
     */
    TreeMap<String, String> insertContractExecuteSerial(TreeMap<String, String> treeMap);
}
