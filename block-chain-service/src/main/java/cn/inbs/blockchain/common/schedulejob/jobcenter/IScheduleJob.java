package cn.inbs.blockchain.common.schedulejob.jobcenter;

/**
 * @Description: 定时任务接口  (所有定时任务实现该接口即可)
 * @Package: cn.inbs.blockchain.common.schedulejob.jobcenter
 * @ClassName: IScheduleJob.java
 * @Date: 2018/6/14 12:21
 * @author: create by zhangmingyang
 **/
public interface IScheduleJob {
    /**
     * 定时任务执行方法
     */
    void runScheduleJob();
}
