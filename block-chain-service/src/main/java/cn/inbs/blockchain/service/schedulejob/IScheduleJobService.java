package cn.inbs.blockchain.service.schedulejob;

import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;


/**
 * @Description: 定时任务服务层
 * @Package: cn.inbs.blockchain.service.schedulejob
 * @ClassName: IScheduleJobService.java
 * @Date: 2018/6/15 16:25
 * @author: create by zhangmingyang
 **/
public interface IScheduleJobService {
    /**
     * 添加一个新的定时任务
     *
     * @param scheduleJobConfigBean
     */
    void insertNewScheduleJob(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 查询定时任务分页数据
     *
     * @param pagePo
     * @return
     */
    PagePo queryScheduleJobPage(PagePo pagePo);

    /**
     * 发起定时任务
     *
     * @param scheduleJobConfigBean
     */
    void updateJobByStart(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 停止一个正在运行的任务
     *
     * @param scheduleJobConfigBean
     */
    void updateJobByStop(ScheduleJobConfigBean scheduleJobConfigBean);


    /**
     * 修改定时任务时间表达式
     *
     * @param scheduleJobConfigBean
     */
    void updateJobCronExpression(ScheduleJobConfigBean scheduleJobConfigBean);


    /**
     * 容器启动拉起定时任务
     */
    void updateSeverStartedInitStartJobs();

    /**
     * 容器销毁
     */
    void updateSeverDestroyStopJobs();
}
