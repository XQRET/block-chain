package cn.inbs.blockchain.dao.schedulejob;

import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.schedulejob
 * @ClassName: ScheduleJobMapper.java
 * @Date: 2018/6/15 15:41
 * @author: create by zhangmingyang
 **/
@Repository("scheduleJobMapper")
public interface ScheduleJobMapper {
    /**
     * 创建一个定时任务
     *
     * @param scheduleJobConfigBean
     * @return
     */
    int insertScheduleJob(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 定时任务查询
     *
     * @param scheduleJobConfigBean
     * @return
     */
    List<ScheduleJobConfigBean> selectScheduleJob(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    List<ScheduleJobConfigBean> selectPageList(Map<String, Object> param);

    /**
     * 查询总行数
     *
     * @param param
     * @return
     */
    Integer selectTotalCount(Map<String, Object> param);

    /**
     * 修改定时任务信息
     *
     * @param scheduleJobConfigBean
     * @return
     */
    int updateScheduleJobConfigBean(ScheduleJobConfigBean scheduleJobConfigBean);


}
