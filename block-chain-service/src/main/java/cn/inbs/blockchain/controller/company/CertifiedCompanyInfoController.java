package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.utils.CookieUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.company.inputparam.CertifiedCompanyInfoInput;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @Description: 认证公司信息
 * @Package: cn.inbs.blockchain.controller.usercenter
 * @ClassName:
 * @Date: 2018年05月17-16:02
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class CertifiedCompanyInfoController extends BaseController {

    @Resource
    private IBlockCompanyService blockCompanyService;

    @RequestMapping(value = "/certifiedCompanyInfo.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(CertifiedCompanyInfoInput input, HttpServletRequest request) throws IOException {
        //执行认证修改
        blockCompanyService.updateCompanyInfo(input, CookieUtils.getUserIdInCookie(request));

        return retContent(null);
    }
}
