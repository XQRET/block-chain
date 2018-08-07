package cn.inbs.blockchain;

import cn.inbs.blockchain.common.schedulejob.IScheduleJobControlCenterService;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestScheduleJob.java
 * @Date: 2018/6/13 18:26
 * @author: create by zhangmingyang
 **/
public class TestScheduleJob {
    private static Logger logger = LoggerFactory.getLogger(TestScheduleJob.class);

    public static void main(String[] args) {
//        createAndExecuteJob();
        refreshJobTriggerCron();
//        stopJobTrigger();
//        executeOldJobTrigger();
    }


    private static void createAndExecuteJob() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            IScheduleJobControlCenterService scheduleJobControlCenterService = (IScheduleJobControlCenterService) context.getBean("scheduleJobControlCenterService");
            //添加一个新的定时任务
            ScheduleJobConfigBean addjob = new ScheduleJobConfigBean();
            addjob.setJobGroup("测试组");
            addjob.setJobName("测试test1");
            addjob.setDescription("测试test1");
            addjob.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            addjob.setCronExpression("*/3 * * * * ?");
            addjob.setIsConcurrent(ScheduleJobConfigBean.CONCURRENT_IS);
            addjob.setSpringId("demoScheduleJobService");
            scheduleJobControlCenterService.addAndExecuteNewJob(addjob);

            //休眠20秒
            Thread.sleep(20000);

            try {
                //添加相同的任务
                scheduleJobControlCenterService.addAndExecuteNewJob(addjob);
            } catch (Exception e) {
                logger.error("添加失败:", e);
            }

            //休眠20秒
            Thread.sleep(20000);

            //再添加一个新的任务
            ScheduleJobConfigBean addNewJOb = new ScheduleJobConfigBean();
            addNewJOb.setJobGroup("测试组");
            addNewJOb.setJobName("测试test2");
            addNewJOb.setDescription("测试test2");
            addNewJOb.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            addNewJOb.setCronExpression("*/5 * * * * ?");
            addNewJOb.setIsConcurrent(ScheduleJobConfigBean.CONCURRENT_IS);
            addNewJOb.setSpringId("newDemoScheduleJobService");
            scheduleJobControlCenterService.addAndExecuteNewJob(addNewJOb);

            System.in.read();
        } catch (Throwable e) {
            logger.error("错误:", e);
        }
    }

    private static void refreshJobTriggerCron() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            IScheduleJobControlCenterService scheduleJobControlCenterService = (IScheduleJobControlCenterService) context.getBean("scheduleJobControlCenterService");
            //添加一个新的定时任务
            ScheduleJobConfigBean addjob = new ScheduleJobConfigBean();
            addjob.setJobGroup("测试组");
            addjob.setJobName("测试test1");
            addjob.setDescription("测试test1");
            addjob.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            addjob.setCronExpression("*/4 * * * * ?");
            addjob.setIsConcurrent(ScheduleJobConfigBean.CONCURRENT_IS);
            addjob.setSpringId("demoScheduleJobService");
            scheduleJobControlCenterService.addAndExecuteNewJob(addjob);

            //休眠20秒
            Thread.sleep(20000);

            addjob.setCronExpression("*/1 * * * * ?");
            scheduleJobControlCenterService.refreshJobTriggerCron(addjob);

            System.in.read();
        } catch (Throwable e) {
            logger.error("错误:", e);
        }
    }

    private static void stopJobTrigger() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            IScheduleJobControlCenterService scheduleJobControlCenterService = (IScheduleJobControlCenterService) context.getBean("scheduleJobControlCenterService");
            //添加一个新的定时任务
            ScheduleJobConfigBean addjob = new ScheduleJobConfigBean();
            addjob.setJobGroup("测试组");
            addjob.setJobName("测试test1");
            addjob.setDescription("测试test1");
            addjob.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            addjob.setCronExpression("*/4 * * * * ?");
            addjob.setIsConcurrent(ScheduleJobConfigBean.CONCURRENT_IS);
            addjob.setSpringId("demoScheduleJobService");
            scheduleJobControlCenterService.addAndExecuteNewJob(addjob);

            //休眠20秒
            Thread.sleep(20000);

            scheduleJobControlCenterService.stopJobTrigger(addjob);


            System.in.read();
        } catch (Throwable e) {
            logger.error("错误:", e);
        }
    }

    private static void executeOldJobTrigger() {

        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            IScheduleJobControlCenterService scheduleJobControlCenterService = (IScheduleJobControlCenterService) context.getBean("scheduleJobControlCenterService");
            //添加一个新的定时任务并执行触发器
            ScheduleJobConfigBean addjob = new ScheduleJobConfigBean();
            addjob.setJobGroup("测试组");
            addjob.setJobName("测试test1");
            addjob.setDescription("测试test1");
            addjob.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            addjob.setCronExpression("*/3 * * * * ?");
            addjob.setIsConcurrent(ScheduleJobConfigBean.CONCURRENT_IS);
            addjob.setSpringId("demoScheduleJobService");
            scheduleJobControlCenterService.addAndExecuteNewJob(addjob);

            //休眠10秒 停止该触发器
            Thread.sleep(10000);
            scheduleJobControlCenterService.stopJobTrigger(addjob);

            //休眠10秒 修改触发器表达式
            Thread.sleep(10000);
            addjob.setCronExpression("*/1 * * * * ?");
            scheduleJobControlCenterService.refreshJobTriggerCron(addjob);

            //休眠10秒 拉起该触发器
            Thread.sleep(10000);
            scheduleJobControlCenterService.executeOldJobTrigger(addjob);


            System.in.read();
        } catch (Throwable e) {
            logger.error("错误:", e);
        }
    }


}
