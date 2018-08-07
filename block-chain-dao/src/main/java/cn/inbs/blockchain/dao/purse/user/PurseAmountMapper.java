package cn.inbs.blockchain.dao.purse.user;

import cn.inbs.blockchain.dao.purse.po.PurseAmount;
import org.springframework.stereotype.Repository;

/**
 * @Description: 钱包余额dao
 * @Package: cn.inbs.blockchain.dao.purse.user
 * @ClassName: PurseAmountMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 14:20
 * @Version: 1.0
 */
@Repository("purseAmountMapper")
public interface PurseAmountMapper {
    /**
     * 添加账户余额信息
     *
     * @param purseAmount
     * @return
     */
    int insertPurseAmount(PurseAmount purseAmount);

    /**
     * 查询账户余额
     *
     * @param query
     * @return
     */
    PurseAmount queryPurseAmountByUserId(PurseAmount query);
}
