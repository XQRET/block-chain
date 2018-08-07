package cn.inbs.blockchain.common.schedulejob.jobcenter;

import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import org.quartz.JobExecutionContext;

/**
 * @Description: 有状态任务(等待正在执行的任务)
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: ConcurrentJob.java
 * @Date: 2018/6/14 12:06
 * @author: create by zhangmingyang
 **/
public class ConcurrentJob extends AbstractConcurrentJob {
    @Override
    public void execute(JobExecutionContext context) {
        ScheduleJobConfigBean scheduleJobConfigBean = (ScheduleJobConfigBean) context.getMergedJobDataMap().get(ScheduleJobConfigBean.SCHEDULEJOB_KEY);
        runScheduleJob(scheduleJobConfigBean);
    }
}
