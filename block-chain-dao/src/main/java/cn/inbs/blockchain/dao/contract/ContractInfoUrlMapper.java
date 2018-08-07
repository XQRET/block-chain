package cn.inbs.blockchain.dao.contract;


import cn.inbs.blockchain.dao.po.ContractInfoUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangmingyang
 */
@Repository("contractInfoUrlMapper")
public interface ContractInfoUrlMapper {
    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertContractInfoUrl(ContractInfoUrl record);

    /**
     * 查询合约详细信息
     *
     * @param record
     * @return
     */
    List<ContractInfoUrl> selectContractInfoUrlListByContractBlockId(ContractInfoUrl record);
}