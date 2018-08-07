package cn.inbs.blockchain.common.schedulejob;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.schedulejob.jobcenter.ConcurrentJob;
import cn.inbs.blockchain.common.schedulejob.jobcenter.DisConcurrentJob;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;


/**
 * @Description: 定时任务管理中心实现
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: ScheduleJobControlCenterServiceImpl.java
 * @Date: 2018/6/14 10:54
 * @author: create by zhangmingyang
 **/
@Service("scheduleJobControlCenterService")
public class ScheduleJobControlCenterServiceImpl implements IScheduleJobControlCenterService {
    public static Logger logger = LoggerFactory.getLogger(ScheduleJobControlCenterServiceImpl.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;//定时任务工厂

    /**
     * 获取定时任务调度器
     *
     * @return
     */
    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 添加一个定时任务到任务工厂并触发执行
     *
     * @param scheduleJobConfigBean
     * @return
     */
    @Override
    public void addAndExecuteNewJob(ScheduleJobConfigBean scheduleJobConfigBean) {
        if (null == scheduleJobConfigBean || !ScheduleJobConfigBean.STATUS_RUNNING.equals(scheduleJobConfigBean.getJobStatus())) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0001,
                    scheduleJobConfigBean == null ? CommonConstants.STRING_SPACE : scheduleJobConfigBean.getJobName(),
                    scheduleJobConfigBean == null ? CommonConstants.STRING_SPACE : scheduleJobConfigBean.getJobStatus());
        } else {
            try {
                //获取调度器
                Scheduler scheduler = getScheduler();

                //获取当前任务控制触发器
                CronTrigger cronTrigger;
                TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup());
                cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                //添加定时任务
                if (null == cronTrigger) {
                    //创建job
                    Class<? extends Job> clazz = ScheduleJobConfigBean.CONCURRENT_IS.equals(scheduleJobConfigBean.getIsConcurrent()) ? ConcurrentJob.class : DisConcurrentJob.class;
                    JobBuilder jobBuilder = JobBuilder.newJob(clazz);
                    JobDetail jobDetail = jobBuilder
                            .withIdentity(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup())
                            .build();
                    jobDetail.getJobDataMap().put(ScheduleJobConfigBean.SCHEDULEJOB_KEY, scheduleJobConfigBean);

                    //创建触发器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobConfigBean.getCronExpression());
                    cronTrigger = TriggerBuilder
                            .newTrigger()
                            .withIdentity(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup())
                            .withSchedule(scheduleBuilder)
                            .build();

                    //添加触发器以及任务
                    scheduler.scheduleJob(jobDetail, cronTrigger);

                    if (logger.isInfoEnabled()) {
                        logger.info("add and execute new job:[{}] successed", scheduleJobConfigBean.getName());
                    }
                } else {
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(cronTrigger.getKey());
                    String scheduleJobStatus = ScheduleJobUtils.getScheduleJobStatus(triggerState);
                    throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0002, scheduleJobConfigBean.getName(), scheduleJobStatus);
                }
            } catch (SchedulerException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("add and execute new job:[{}] failed,cause by:", scheduleJobConfigBean.getJobName(), e);
                }
                throw new BusinessException(e, BusinessErrorConstants.SCHEDULE_JOB_0001,
                        scheduleJobConfigBean.getJobName(),
                        scheduleJobConfigBean.getJobStatus());
            }
        }
    }

    @Override
    public void stopAndDeleteJobInFactory(ScheduleJobConfigBean scheduleJobConfigBean) {
        try {
            //获取定时任务调度器
            Scheduler scheduler = getScheduler();

            //获取定时任务身份
            JobKey jobKey = JobKey.jobKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup());

            //停止定时任务
            scheduler.pauseJob(jobKey);

            //从任务工厂删除该定时任务
            scheduler.deleteJob(jobKey);

            if (logger.isInfoEnabled()) {
                logger.info("stop and delete job:[{}] in factory successed . ", scheduleJobConfigBean.getName());
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("stop and delete job:[{}] in factory failed , cause by: ", scheduleJobConfigBean.getName(), e);
            }

            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0012, scheduleJobConfigBean.getName());
        }
    }

    @Override
    public void refreshJobTriggerCron(ScheduleJobConfigBean scheduleJobConfigBean) {
        try {
            //获取定时任务调度器
            Scheduler scheduler = getScheduler();

            //获取当前定时任务触发器
            TriggerKey triggerKey = new TriggerKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //修改定时任务表达式
            if (null == cronTrigger) {
                throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0003, scheduleJobConfigBean.getName());
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobConfigBean.getCronExpression());
                cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, cronTrigger);
                if (logger.isInfoEnabled()) {
                    logger.info("refresh job trigger cron:[{}] successed", scheduleJobConfigBean.getName());
                }
            }
        } catch (SchedulerException e) {
            if (logger.isErrorEnabled()) {
                logger.error("refresh job trigger cron:[{}] failed , cause by: ", scheduleJobConfigBean.getName(), e);
            }
            throw new BusinessException(e, BusinessErrorConstants.SCHEDULE_JOB_0004, scheduleJobConfigBean.getName());
        }
    }

    @Override
    public void stopJobTrigger(ScheduleJobConfigBean scheduleJobConfigBean) {
        try {
            //获取定时任务调度器
            Scheduler scheduler = getScheduler();

            //获取当前定时任务触发器
            TriggerKey triggerKey = new TriggerKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (null == cronTrigger) {
                throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0003, scheduleJobConfigBean.getName());
            } else {
                scheduler.pauseTrigger(triggerKey);
                if (logger.isInfoEnabled()) {
                    logger.info("stop job trigger:[{}] successed", scheduleJobConfigBean.getName());
                }
            }
        } catch (SchedulerException e) {
            throw new BusinessException(e, BusinessErrorConstants.SCHEDULE_JOB_0005, scheduleJobConfigBean.getName());
        }
    }

    @Override
    public void executeOldJobTrigger(ScheduleJobConfigBean scheduleJobConfigBean) {
        try {
            //获取定时任务调度器
            Scheduler scheduler = getScheduler();
            //获取当前定时任务触发器
            TriggerKey triggerKey = new TriggerKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (null == cronTrigger) {
                throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0003, scheduleJobConfigBean.getName());
            } else {
                scheduler.resumeTrigger(triggerKey);
                if (logger.isInfoEnabled()) {
                    logger.info("execute old job trigger:[{}] successed", scheduleJobConfigBean.getName());
                }
            }
        } catch (SchedulerException e) {
            throw new BusinessException(e, BusinessErrorConstants.SCHEDULE_JOB_0006, scheduleJobConfigBean.getName());
        }
    }

    @Override
    public String getRunningJobStatus(ScheduleJobConfigBean scheduleJobConfigBean) {
        try {
            //获取调度器
            Scheduler scheduler = getScheduler();

            //获取当前任务控制触发器
            Trigger.TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey(scheduleJobConfigBean.getJobName(), scheduleJobConfigBean.getJobGroup()));
            return ScheduleJobUtils.getScheduleJobStatus(triggerState);
        } catch (SchedulerException e) {
            return null;
        }
    }
}
