package cn.inbs.blockchain.common.schedulejob;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.property.PropertyUtils;
import org.quartz.simpl.SimpleThreadPool;


/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: ScheduleJobThreadPool.java
 * @Date: 2018/6/20 14:08
 * @author: create by zhangmingyang
 **/
public class ScheduleJobThreadPool extends SimpleThreadPool {

    @Override
    public void setThreadCount(int count) {
        super.setThreadCount(PropertyUtils.getIntValue(CommonConfigPerpertyConstants.SCHEDULE_JOB_THREAD_POOL_SIZE, 10));
    }
}
