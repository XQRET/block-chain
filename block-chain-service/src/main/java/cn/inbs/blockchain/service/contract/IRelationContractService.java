/**
 * Project Name:trunk
 * File Name:IRelationContractService.java
 * Package Name:cn.inbs.blockchain.service.contract
 * Date:2018年6月5日上午10:37:47
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package cn.inbs.blockchain.service.contract;

import java.util.Map;

import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.common.commonbean.PagePo;

/**
 * ClassName: IRelationContractService <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu
 * Date:2018年6月5日上午10:37:47  
 */
public interface IRelationContractService {

    /**
     * insertRelationContract:新增关联合约底层数据. <br/>
     * @author anxiaoyu
     * @param contractId
     * @param files
     */
    void insertRelationContract(String companyBlockId, BlockContract blockContract, Map<String, String> paramMap);

    /**
     * deleteImportContract:删除合约. <br/>
     * @author anxiaoyu
     * @param contractId
     */
    void deleteImportContract(String companyBlockId, String contractId);

    /**
     * queryContractPage:查询待关联合约. <br/>
     * @author anxiaoyu
     * @param pagePo
     * @return
     */
    PagePo queryContractPage(PagePo pagePo);

}
  
