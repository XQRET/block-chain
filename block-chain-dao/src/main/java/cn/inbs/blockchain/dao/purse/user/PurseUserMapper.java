package cn.inbs.blockchain.dao.purse.user;

import cn.inbs.blockchain.dao.purse.po.PurseUser;
import org.springframework.stereotype.Repository;

@Repository("purseUserMapper")
public interface PurseUserMapper {
    /**
     * 添加钱包用户
     *
     * @param purseUser
     * @return
     */
    int insertPurseUser(PurseUser purseUser);

    /**
     * 查询用户信息
     *
     * @param purseUser
     * @return
     */
    PurseUser selectPurseUser(PurseUser purseUser);

    /**
     * 更新用戶信息
     * @param purseUser
     * @return
     */
    int updatePurseUser(PurseUser purseUser);
}
