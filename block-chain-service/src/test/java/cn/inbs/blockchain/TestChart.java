package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.SpringContextUtil;
import cn.inbs.blockchain.service.cockpit.INewCockpitService;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestChart.java
 * @Date: 2018/7/10 16:41
 * @author: create by zhangmingyang
 **/
public class TestChart {
    private static Logger logger = LoggerFactory.getLogger(TestChart.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

            //启动加载所有定时任务
            Object scheduleJobServiceObject = SpringContextUtil.getBean("scheduleJobService");
            if (null != scheduleJobServiceObject) {
                IScheduleJobService scheduleJobService = (IScheduleJobService) scheduleJobServiceObject;
                scheduleJobService.updateSeverStartedInitStartJobs();

                if (logger.isInfoEnabled()) {
                    logger.info("block-chain-service start scheduleJob successed .");
                }
            } else {
                if (logger.isErrorEnabled()) {
                    logger.error("block-chain-service start scheduleJob failed . cause by : context get bean :[scheduleJobService] is null .");
                }
                return;
            }

            INewCockpitService newCockpitService = (INewCockpitService) context.getBean("newCockpitServiceImpl");
            for (int i = 0; i < 5; i++) {
                if (i != 0) {
                    Thread.sleep(1000 * 5);
                }
                testChart(newCockpitService);
            }
        } catch (InterruptedException e) {
            logger.error("错误 :", e);
        }
    }

    private static void testChart(INewCockpitService newCockpitService) {
        if (logger.isInfoEnabled()) {
            logger.info("数据统计:\n头部基本数据:[{}]\n近12个月统计:[{}]\ntop+all:[{}]\n省份统计:[{}]\n收入统计:[{}]",
                    newCockpitService.queryChartHeaderBaseData(),
                    newCockpitService.queryRecentSomeMonthData(),
                    newCockpitService.queryChartCompanyTopArray(),
                    newCockpitService.queryByCountContractByProvince(),
                    newCockpitService.queryBySomeMonthIncome());
        }
    }
}
