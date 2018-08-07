package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:机构关联
 * @Package: cn.inbs.blockchain.controller.company
 * @ClassName:
 * @Date: 2018年05月28-13:48
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class RelatedCompanyController extends BaseController {

    @Resource
    private IBlockCompanyService blockCompanyService;

    private static Logger logger = LoggerFactory.getLogger(RelatedCompanyController.class);
    private static final String USER_ID = "userId";

    @RequestMapping(value = "/relatedCompanys.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String relatedCompanys(HttpServletRequest httpServletRequest,
                                  @RequestParam("relations[]") List<String> relations) {
        if (CollectionUtils.isNotEmpty(relations)) {
            blockCompanyService.updateRelatedCompanys(relations, Long.valueOf(httpServletRequest.getParameter(USER_ID)));
        }
        return retContent(null);
    }


}
