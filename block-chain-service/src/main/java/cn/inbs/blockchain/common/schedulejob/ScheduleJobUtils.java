package cn.inbs.blockchain.common.schedulejob;

import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 定时任务工具类
 * @Package: cn.inbs.blockchain.common.schedulejob
 * @ClassName: ScheduleJobUtils.java
 * @Date: 2018/6/15 10:24
 * @author: create by zhangmingyang
 **/
public class ScheduleJobUtils {
    /**
     * 获取任务状态
     *
     * @param status
     * @return
     */
    public static String getScheduleJobStatus(Trigger.TriggerState status) {
        Map<Trigger.TriggerState, String> statusMap = new HashMap<TriggerState, String>();
        statusMap.put(Trigger.TriggerState.NONE, "无");
        statusMap.put(Trigger.TriggerState.NORMAL, "正常状态");
        statusMap.put(Trigger.TriggerState.PAUSED, "暂停状态");
        statusMap.put(Trigger.TriggerState.COMPLETE, "完成");
        statusMap.put(Trigger.TriggerState.ERROR, "错误");
        statusMap.put(Trigger.TriggerState.BLOCKED, "堵塞");
        return statusMap.get(status);
    }
}
