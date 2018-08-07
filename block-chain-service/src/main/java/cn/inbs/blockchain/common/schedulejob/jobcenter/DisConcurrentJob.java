package cn.inbs.blockchain.common.schedulejob.jobcenter;

import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;


/**
 * @Description: 无状态任务 (终止正在执行的任务)
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: DisConcurrentJob.java
 * @Date: 2018/6/14 12:07
 * @author: create by zhangmingyang
 **/
@DisallowConcurrentExecution
public class DisConcurrentJob extends AbstractConcurrentJob {
    @Override
    public void execute(JobExecutionContext context) {
        ScheduleJobConfigBean scheduleJobConfigBean = (ScheduleJobConfigBean) context.getMergedJobDataMap().get(ScheduleJobConfigBean.SCHEDULEJOB_KEY);
        runScheduleJob(scheduleJobConfigBean);
    }
}
