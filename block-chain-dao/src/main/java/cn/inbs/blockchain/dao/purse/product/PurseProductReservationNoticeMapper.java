package cn.inbs.blockchain.dao.purse.product;

import cn.inbs.blockchain.dao.purse.po.PurseProductReservationNotice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 产品预售通知用户
 * @Package: cn.inbs.blockchain.dao.purse.product
 * @ClassName: PurseProductReservationNoticeMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 14:21
 * @Version: 1.0
 */
@Repository("purseProductReservationNoticeMapper")
public interface PurseProductReservationNoticeMapper {
    /**
     * 添加预售通知信息
     *
     * @param insert
     * @return
     */
    int insertPurseProductReservationNotice(PurseProductReservationNotice insert);

    /**
     * 查询
     *
     * @param query
     * @return
     */
    PurseProductReservationNotice selectPurseProductReservationNoticeByIndex(PurseProductReservationNotice query);

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    List<PurseProductReservationNotice> selectPurseProductReservationNoticePageList(Map<String, Object> param);

    /**
     * 查询总行数
     *
     * @param param
     * @return
     */
    int selectPurseProductReservationNoticeTotalCount(Map<String, Object> param);
}
