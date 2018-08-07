package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.CompanyRelation;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description:审核关联
 * @Package: cn.inbs.blockchain.controller.company
 * @ClassName:
 * @Date: 2018年05月29-10:51
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class ReviewRelationCompanyController extends BaseController {
    private static final String USER_ID = "userId";
    @Resource
    private IBlockCompanyService blockCompanyService;

    private static Logger logger = LoggerFactory.getLogger(RelatedCompanyController.class);

    @RequestMapping(value = "/reviewRelationCompany.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String reviewRelationCompany(HttpServletRequest httpServletRequest) {
        CompanyRelation companyRelation = checkParam(httpServletRequest);
        String userId = httpServletRequest.getParameter(USER_ID);

        blockCompanyService.updateRelatedStatus(companyRelation, Long.valueOf(userId));

        return retContent(null);
    }

    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @return
     */
    private CompanyRelation checkParam(HttpServletRequest httpServletRequest) {
        String relationStatus = httpServletRequest.getParameter(CompanyConstants.RELATION_STATUS);
        CheckValueCodeUtils.checkValueCode(false, relationStatus, ValueCodeEnums.RELATION_STATUS);

        String zcCompanyBlockId = httpServletRequest.getParameter(CompanyConstants.ZC_COMPANY_BLOCK_ID);
        if (null == zcCompanyBlockId) {
            throw new BusinessException(BusinessErrorConstants.COMPANY_RELATION_0003);
        }

        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setRelationStatus(relationStatus);
        companyRelation.setZcCompanyBlockId(zcCompanyBlockId);
        companyRelation.setUpdateTime(new Date());
        return companyRelation;
    }

}
