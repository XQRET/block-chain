package cn.inbs.blockchainpurse.controller.user;

import cn.inbs.blockchainpurse.common.web.BaseController;
import cn.inbs.blockchainpurse.controller.user.inputparam.UploadUserPhotoInput;
import cn.inbs.blockchainpurse.service.photo.IPursePhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 上传用户图像
 * @Package: cn.inbs.blockchainpurse.controller.user
 * @ClassName: UploadUserPhotoController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 11:26
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/purseUser")
public class UploadUserPhotoController extends BaseController {
    @Resource
    private IPursePhotoService pursePhotoService;

    @RequestMapping(value = "/uploadUserPhoto.dx", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadUserPhoto(UploadUserPhotoInput input) {
        return retContent(pursePhotoService.updateByUploadUserPhoto(input));
    }
}
