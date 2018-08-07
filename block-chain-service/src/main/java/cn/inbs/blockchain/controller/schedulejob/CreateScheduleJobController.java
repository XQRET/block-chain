package cn.inbs.blockchain.controller.schedulejob;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 配置创建一个新的定时任务(任务状态默认为停止状态)
 * @Package: cn.inbs.blockchain.controller.schedulejob
 * @ClassName: CreateScheduleJobController.java
 * @Date: 2018/6/19 10:18
 * @author: create by zhangmingyang
 **/
@Controller
@RequestMapping(value = "/scheduleJob")
public class CreateScheduleJobController extends BaseController {

    @Resource
    private IScheduleJobService scheduleJobService;

    @RequestMapping(value = "/createScheduleJob.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String createScheduleJob(ScheduleJobConfigBean param) {
        //参数校验
        checkParam(param);

        //配置创建新的定时任务
        scheduleJobService.insertNewScheduleJob(param);

        return retContent(null);
    }

    /**
     * 参数校验
     *
     * @param param
     * @return
     */
    private void checkParam(ScheduleJobConfigBean param) {
        //日志记录参数
        if (logger.isDebugEnabled()) {
            logger.debug("创建配置定时任务参数:[{}]", param.toString());
        }

        //组名
        if (StringUtils.isEmpty(param.getJobGroup())) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0011, "组名");
        }

        //任务名
        if (StringUtils.isEmpty(param.getJobName())) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0011, "任务名");
        }

        //cron表达式
        if (StringUtils.isEmpty(param.getCronExpression())) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0011, "cron表达式");
        }

        //beanId
        if (StringUtils.isEmpty(param.getSpringId())) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0011, "beanId");
        }

        //设置任务状态默认为停止状态
        param.setJobStatus(ScheduleJobConfigBean.STATUS_NOT_RUNNING);

        //设置时间
        Date createDate = new Date();
        param.setCreateTime(createDate);
        param.setUpdateTime(createDate);
    }
}
