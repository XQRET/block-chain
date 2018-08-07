package cn.inbs.blockchain.dao.contract;


import cn.inbs.blockchain.dao.po.ContractSerial;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author zhangmingyang
 */
@Repository("contractSerialMapper")
public interface ContractSerialMapper {
    /**
     * 记录合约流水
     *
     * @param contractSerial
     * @return
     */
    int insertContractSerial(ContractSerial contractSerial);

    /**
     * 根据状态及合约ID查询流水列表
     *
     * @param param
     * @return
     */
    List<ContractSerial> selectContractSerialsByStatusAndContractId(Map<String, Object> param);

    /**
     * 根据合约ID,公司区块ID,合约状态查找合约流水
     *
     * @param contractSerial
     * @return
     */
    ContractSerial selectContractSerialsByStatusAndContractIdAndCompanyBlock(ContractSerial contractSerial);

    /**
     * 检查流水是否存在
     *
     * @param contractSerial
     * @return
     */
    ContractSerial selectSerialIsExist(ContractSerial contractSerial);
}