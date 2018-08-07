package cn.inbs.blockchain.dao.contract;


import cn.inbs.blockchain.dao.po.ContractFileUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contractFileUrlMapper")
public interface ContractFileUrlMapper {

    /**
     * 插入
     *
     * @param contractFileUrl
     * @return
     */
    int insertSelective(ContractFileUrl contractFileUrl);

    /**
     * 查询
     *
     * @param contractFileUrl
     * @return
     */
    List<ContractFileUrl> selectContractFileUrlList(ContractFileUrl contractFileUrl);


}