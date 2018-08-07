package cn.inbs.blockchain.dao.contract;

import cn.inbs.blockchain.dao.po.BlockContractSale;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 合约预售表
 * @Package: cn.inbs.blockchain.dao.contract
 * @ClassName: BlockContractSaleMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 11:35
 * @Version: 1.0
 */
@Repository("blockContractSaleMapper")
public interface BlockContractSaleMapper {
    /**
     * 天剑合约
     *
     * @param insert
     * @return
     */
    int insertBlockContractSale(BlockContractSale insert);

    /**
     * 查询合约预售详细信息
     *
     * @param query
     * @return
     */
    BlockContractSale selectBlockContractSaleByIndex(BlockContractSale query);

    /**
     * 查询分页列表
     *
     * @param paramMap
     * @return
     */
    List<BlockContractSale> selectBlockContractSalePageList(Map<String, Object> paramMap);

    /**
     * 查询总行数
     *
     * @param paramMap
     * @return
     */
    int selectBlockContractSaleTotalCount(Map<String, Object> paramMap);

    /**
     * 修改合约信息
     *
     * @param update
     * @return
     */
    int updateBlockContractSaleByIndex(BlockContractSale update);
}
