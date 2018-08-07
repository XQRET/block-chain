package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 查询公司认证信息
 * @Package: cn.inbs.blockchain.controller.usercenter
 * @ClassName:
 * @Date: 2018年05月17-16:02
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class QueryCompanyCertifiedInfoController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryCompanyCertifiedInfoController.class);
    @Resource
    private IBlockCompanyService blockCompanyService;


    @RequestMapping(value = "/queryCertifiedInfo.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryCertifiedInfo(HttpServletRequest httpServletRequest) {
        String userId = httpServletRequest.getParameter(CompanyConstants.USER_ID);
        if (logger.isDebugEnabled()) {
            logger.debug("用户:{}执行查询认证信息", userId);
        }

        return retContent(blockCompanyService.queryCertifiedInfo(userId));
    }
}
