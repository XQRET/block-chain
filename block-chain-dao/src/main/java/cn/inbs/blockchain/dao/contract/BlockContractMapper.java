package cn.inbs.blockchain.dao.contract;


import cn.inbs.blockchain.dao.po.BlockContract;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("blockContractMapper")
public interface BlockContractMapper {
    /**
     * 根据索引查询合约信息
     *
     * @param blockContract
     * @return
     */
    BlockContract selectBlockContractByIndex(BlockContract blockContract);

    /**
     * 插入合约信息
     *
     * @param record
     * @return
     */
    int insertBlockContract(BlockContract record);

    /**
     * 修改合约状态
     *
     * @param blockContract
     * @return
     */
    int updateStatusAndRefuseTimesById(BlockContract blockContract);


    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<BlockContract> selectPageList(Map<String, Object> map);

    /**
     * 总条数查询
     *
     * @param map
     * @return
     */
    int selectTotalCount(Map<String, Object> map);

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<BlockContract> selectPageListByFundsTrigger(Map<String, Object> map);

    /**
     * 总条数查询
     *
     * @param map
     * @return
     */
    int selectTotalCountByFundsTrigger(Map<String, Object> map);
    /**
     * 总h合约金额查询
     * @return
     */
    String selectTotalAmount();
    /**
     *
     * selectDistinctHouseCode:查询非注销的房屋编号. <br/>
     * @author anxiaoyu
     * @return
     */
    List<String> selectDistinctHouseCode();
    
    List<String> selectDistinctImportUniqueSign(Long companyId);

    int updateImportTypeSuccessBycontractId(@Param("companyBlockId") String companyBlockId,
                                            @Param("contractId") String contractId, @Param("importType") String importType);

	/**
	 * deleteImportContract:(这里用一句话描述这个方法的作用). <br/>
	 * @author anxiaoyu
	 * @param companyBlockId
	 * @param contractId
	 */
	int deleteImportContract(@Param("companyBlockId") String companyBlockId,
                             @Param("contractId") String contractId);

    /**
     * 根据合约状态查询最新3条合约
     * @return
     */
    List<BlockContract> selectContractByContractType(Map<String, Object> map);
}