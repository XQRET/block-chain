package cn.inbs.blockchain.controller.schedulejob;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.controller.schedulejob
 * @ClassName: StopJobController.java
 * @Date: 2018/6/19 17:00
 * @author: create by zhangmingyang
 **/
@Controller
@RequestMapping(value = "/scheduleJob")
public class StopJobController extends BaseController {

    @Resource
    private IScheduleJobService scheduleJobService;

    @RequestMapping(value = "/stopJob.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String stopJob(ScheduleJobConfigBean param) {
        scheduleJobService.updateJobByStop(param);
        return retContent(null);
    }
}
