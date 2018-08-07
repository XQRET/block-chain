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
 * @ClassName: UpdateJobCronExpressionController.java
 * @Date: 2018/6/19 17:51
 * @author: create by zhangmingyang
 **/
@Controller
@RequestMapping(value = "/scheduleJob")
public class UpdateJobCronExpressionController extends BaseController {
    @Resource
    private IScheduleJobService scheduleJobService;

    @RequestMapping(value = "/updateCronExpression.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateCronExpression(ScheduleJobConfigBean param) {
        scheduleJobService.updateJobCronExpression(param);
        return retContent(null);
    }
}
