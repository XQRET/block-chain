package cn.inbs.blockchain.controller.company;

import cn.inbs.blockchain.common.utils.CookieUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description: 上传公司logo
 * @Package: cn.inbs.blockchain.controller.company
 * @ClassName: UploadCompanyLogoController.java
 * @Date: 2018/7/3 16:39
 * @author: create by zhangmingyang
 **/
@Controller
@RequestMapping(value = "/company")
public class UploadCompanyLogoController extends BaseController {
    @Resource
    private IBlockCompanyService blockCompanyService;

    @RequestMapping(value = "/uploadCompanyLogo.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        String companyBlockId = CookieUtils.getCompanyBlockIdInCookie(multipartHttpServletRequest);//公司区块ID
        MultipartFile companyLogo = multipartHttpServletRequest.getFile("companyLogo");
        blockCompanyService.updateCompanyByUploadCompanyLogo(companyLogo, companyBlockId);
        return retContent(null);
    }
}
