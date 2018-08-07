package cn.inbs.blockchain.common.schedulejob.jobcenter;

import cn.inbs.blockchain.common.utils.SpringContextUtil;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import cn.inbs.blockchain.dao.schedulejob.ScheduleJobMapper;
import org.quartz.Job;


/**
 * @Description: 定时任务基类
 * @Package: cn.inbs.blockchain.common.schedulejob.jobcenter
 * @ClassName: AbstractConcurrentJob.java
 * @Date: 2018/6/14 12:10
 * @author: create by zhangmingyang
 **/
abstract class AbstractConcurrentJob implements Job {
    /**
     * 执行定时任务方法
     *
     * @param jobConfigBean
     */
    void runScheduleJob(ScheduleJobConfigBean jobConfigBean) {
        //获取定时任务bean并执行
        String springId = jobConfigBean.getSpringId();
        Object beanObject = SpringContextUtil.getBean(springId);
        if (null != beanObject) {
            IScheduleJob executeJob = (IScheduleJob) beanObject;
            executeJob.runScheduleJob();

            //记录执行次数
            Object scheduleJobMapperObject = SpringContextUtil.getBean("scheduleJobMapper");
            if (null != scheduleJobMapperObject) {
                ScheduleJobMapper scheduleJobMapper = (ScheduleJobMapper) scheduleJobMapperObject;

                //查询上次执行次数
                ScheduleJobConfigBean query = new ScheduleJobConfigBean();
                query.setJobId(jobConfigBean.getJobId());
                query = scheduleJobMapper.selectScheduleJob(query).get(0);

                //+1更新次数
                ScheduleJobConfigBean update = new ScheduleJobConfigBean();
                update.setJobId(query.getJobId());
                update.setCount(query.getCount() + 1);
                scheduleJobMapper.updateScheduleJobConfigBean(update);
            }

        }
    }
}
