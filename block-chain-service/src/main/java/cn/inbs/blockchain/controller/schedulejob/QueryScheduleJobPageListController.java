package cn.inbs.blockchain.controller.schedulejob;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 定时任务列表查询
 * @Package: cn.inbs.blockchain.controller.schedulejob
 * @ClassName: QueryScheduleJobPageListController.java
 * @Date: 2018/6/19 11:20
 * @author: create by zhangmingyang
 **/
@Controller
@RequestMapping(value = "/scheduleJob")
public class QueryScheduleJobPageListController extends BaseController {

    @Resource
    private IScheduleJobService scheduleJobService;

    @RequestMapping(value = "/queryScheduleJobPageList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryScheduleJobPageList(PagePo pagePo) {
        PagePo resultPagePo = scheduleJobService.queryScheduleJobPage(pagePo);
        if (logger.isDebugEnabled()) {
            logger.debug("定时任务列表数据:[{}]", resultPagePo.toString());
        }
        return retContent(resultPagePo);
    }
}
