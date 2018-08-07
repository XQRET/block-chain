package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:查询公司列表(公司类型及状态)
 * @Package: cn.inbs.blockchain.controller.company
 * @ClassName:
 * @Date: 2018年05月28-13:04
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class QueryUnRelationCompanyListController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QueryUnRelationCompanyListController.class);

    private static final String USER_ID = "userId";

    @Resource
    private IBlockCompanyService blockCompanyService;

    @RequestMapping(value = "/queryUnRelation.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryUnRelation(HttpServletRequest httpServletRequest)   {
        BlockCompany blockCompany = checkParam(httpServletRequest);

        if (logger.isDebugEnabled()) {
            logger.debug("根据状态及类型查询公司列表请求参数:{}", blockCompany.toString());
        }

        List<BlockCompany> blockCompanies = blockCompanyService.queryUnRelation(blockCompany, Long.valueOf(httpServletRequest.getParameter(USER_ID)));

        if (logger.isDebugEnabled()) {
            logger.debug("根据状态及类型查询公司列表返回信息:{}", blockCompanies.toString());
        }

        return retContent(blockCompanies);
    }

    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @return
     */
    private BlockCompany checkParam(HttpServletRequest httpServletRequest) {
        BlockCompany blockCompany = new BlockCompany();

        //校验公司状态
        String companyStatus = httpServletRequest.getParameter(CompanyConstants.COMPANY_STATUS);
        CheckValueCodeUtils.checkValueCode(false, companyStatus, ValueCodeEnums.COMPANY_STATUS);

        //校验公司类型
        String companyType = httpServletRequest.getParameter(CompanyConstants.COMPANY_TYPE);
        CheckValueCodeUtils.checkValueCode(false, companyType, ValueCodeEnums.COMPANY_TYPE);

        //组织返回参数
        blockCompany.setCompanyStatus(companyStatus);
        blockCompany.setCompanyType(companyType);
        return blockCompany;
    }

}
