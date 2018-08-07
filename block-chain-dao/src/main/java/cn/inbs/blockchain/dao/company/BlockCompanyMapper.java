package cn.inbs.blockchain.dao.company;


import cn.inbs.blockchain.dao.po.BlockCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("blockCompanyMapper")
public interface BlockCompanyMapper {
    /**
     * 根据索引查询公司信息
     *
     * @param blockCompany
     * @return
     */
    BlockCompany selectBlockCompanyByIndex(BlockCompany blockCompany);

    /**
     * 根据索引修改用户信息
     *
     * @param record
     * @return
     */
    int updateBlockCompanyByIndex(BlockCompany record);

    /**
     * 根据公司类型查询公司列表
     *
     * @param blockCompany
     * @return
     */
    List<BlockCompany> selectBlockCompanysByStatusAndType(BlockCompany blockCompany);
    
}