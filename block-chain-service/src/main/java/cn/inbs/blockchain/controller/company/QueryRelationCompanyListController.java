package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.controller.company
 * @ClassName:
 * @Date: 2018年05月28-15:00
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class QueryRelationCompanyListController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryRelationCompanyListController.class);

    private static final String USER_ID = "userId";

    @Resource
    private IBlockCompanyService blockCompanyService;

    @RequestMapping(value = "/queryRelation.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryRelation(HttpServletRequest httpServletRequest) {
        PagePo pagePo = blockCompanyService.queryRelationPage(checkParam(httpServletRequest), Long.valueOf(httpServletRequest.getParameter(USER_ID)));

        return retContent(pagePo);
    }

    private PagePo checkParam(HttpServletRequest httpServletRequest) {
        PagePo pagePo = new PagePo();
        String pageCountStr = httpServletRequest.getParameter(PagePo.PAGE_CONDITION_PAGE_COUNT);//每页条数
        if (null != pageCountStr) {
            try {
                Integer pageCount = Integer.valueOf(pageCountStr);
                pagePo.setPageCount(pageCount);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取每页条数值:{}不合法", pageCountStr, e);
                }
            }
        }

        String pageIndexStr = httpServletRequest.getParameter(PagePo.PAGE_CONDITION_PAGE_INDEX);//页数
        if (null != pageIndexStr) {
            try {
                Integer pageIndex = Integer.valueOf(pageIndexStr);
                pagePo.setPageIndex(pageIndex);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取页数值:{}不合法", pageIndexStr, e);
                }
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PagePo.PAGE_CONDITION_START_INDEX, pagePo.getPageStartCountIndex());//也开始行数
        map.put(PagePo.PAGE_CONDITION_PAGE_COUNT, pagePo.getPageCount());//查询行数
        pagePo.setConditionParamMap(map);
        return pagePo;
    }
}
