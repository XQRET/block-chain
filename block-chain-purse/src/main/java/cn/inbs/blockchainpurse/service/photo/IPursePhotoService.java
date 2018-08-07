package cn.inbs.blockchainpurse.service.photo;

import cn.inbs.blockchainpurse.controller.user.inputparam.UploadUserPhotoInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.UploadUserPhotoOutput;

/**
 * @Description: 钱包图片服务
 * @Package: cn.inbs.blockchainpurse.service.photo
 * @ClassName: IPursePhotoService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 12:19
 * @Version: 1.0
 */
public interface IPursePhotoService {
    /**
     * 上传钱包用户图像
     *
     * @param input
     * @return
     */
    UploadUserPhotoOutput updateByUploadUserPhoto(UploadUserPhotoInput input);
}
