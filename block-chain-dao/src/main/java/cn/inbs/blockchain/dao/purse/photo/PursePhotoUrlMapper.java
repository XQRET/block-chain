package cn.inbs.blockchain.dao.purse.photo;

import cn.inbs.blockchain.dao.purse.po.PursePhotoUrl;
import org.springframework.stereotype.Repository;

/**
 * @Description: //TODO
 * @Package: cn.inbs.blockchain.dao.purse.photo
 * @ClassName: PursePhotoUrlMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 13:24
 * @Version: 1.0
 */
@Repository("pursePhotoUrlMapper")
public interface PursePhotoUrlMapper {
    /**
     * 插入
     *
     * @param insert
     * @return
     */
    int insertPursePhotoUrl(PursePhotoUrl insert);

    /**
     * 查询
     *
     * @param query
     * @return
     */
    PursePhotoUrl selectPursePhotoUrlByIndex(PursePhotoUrl query);
}
