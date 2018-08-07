package cn.inbs.blockchain.common.schedulejob.demo;

import cn.inbs.blockchain.common.schedulejob.jobcenter.IScheduleJob;
import cn.inbs.blockchain.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.schedulejob.demo
 * @ClassName: BBBNewDemoScheduleJobService.java
 * @Date: 2018/6/14 20:36
 * @author: create by zhangmingyang
 **/
@Service("bBBNewDemoScheduleJobService")
public class BBBNewDemoScheduleJobService implements IScheduleJob {
    private static Logger logger = LoggerFactory.getLogger(BBBNewDemoScheduleJobService.class);

    @Override
    public void runScheduleJob() {
        logger.info("定时任务:[{}]执行时间:[{}]", "bBBNewDemoScheduleJobService",
                DateUtils.formatDate(new Date(), DateUtils.DateFormat.DATE_FORMAT_4));
    }
}
