package cn.inbs.blockchainpurse.controller.user.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 上传用户图像接口入参
 * @Package: cn.inbs.blockchainpurse.controller.user.inputparam
 * @ClassName: UploadUserPhotoInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 11:34
 * @Version: 1.0
 */
public class UploadUserPhotoInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "用户图像")
    private MultipartFile userPhoto;//用户图像

    public MultipartFile getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(MultipartFile userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
