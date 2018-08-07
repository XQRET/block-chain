package cn.inbs.blockchain.dao.purse.user;

import cn.inbs.blockchain.dao.purse.po.PurseUserExtraInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户附属信息dao
 * @Package: cn.inbs.blockchain.dao.purse.user
 * @ClassName: PurseUserExtraInfoMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 16:04
 * @Version: 1.0
 */
@Repository("purseUserExtraInfoMapper")
public interface PurseUserExtraInfoMapper {
    /**
     * 添加用户附属信息
     *
     * @param insert
     * @return
     */
    int insertPurseUserExtraInfo(PurseUserExtraInfo insert);

    /**
     * 查询用户附加信息
     *
     * @param query
     * @return
     */
    PurseUserExtraInfo selectPurseUserExtraInfoByUserId(PurseUserExtraInfo query);


    /**
     * 修改用户附属信息
     *
     * @param update
     * @return
     */
    int updatePurseUserExtraInfoById(PurseUserExtraInfo update);
}
