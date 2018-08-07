package cn.inbs.blockchain.common.schedulejob;


import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;

/**
 * @Description: 定时任务管理中心
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: IScheduleJobControlCenterService.java
 * @Date: 2018/6/14 10:38
 * @author: create by zhangmingyang
 **/
public interface IScheduleJobControlCenterService {
    /**
     * 添加一个定时任务到定时任务工厂,并为该任务添加触发器,执行触发器(发起定时任务)
     *
     * @param scheduleJobConfigBean
     */
    void addAndExecuteNewJob(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 停止一个定时任务并将该定时任务从定时任务工厂删除
     *
     * @param scheduleJobConfigBean
     */
    void stopAndDeleteJobInFactory(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 修改一个触发器表达式
     *
     * @param scheduleJobConfigBean
     */
    void refreshJobTriggerCron(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 停止一个定时任务触发器
     *
     * @param scheduleJobConfigBean
     */
    void stopJobTrigger(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 执行一个停止的触发器
     *
     * @param scheduleJobConfigBean
     */
    void executeOldJobTrigger(ScheduleJobConfigBean scheduleJobConfigBean);

    /**
     * 获取运行任务状态
     *
     * @param scheduleJobConfigBean
     * @return
     */
    String getRunningJobStatus(ScheduleJobConfigBean scheduleJobConfigBean);


}
