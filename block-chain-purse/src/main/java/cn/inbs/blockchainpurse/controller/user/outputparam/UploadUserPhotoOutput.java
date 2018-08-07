package cn.inbs.blockchainpurse.controller.user.outputparam;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 用户上传图片出参结构体
 * @Package: cn.inbs.blockchainpurse.controller.user.outputparam
 * @ClassName: UploadUserPhotoOutput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 14:41
 * @Version: 1.0
 */
public class UploadUserPhotoOutput {
    private String userPhotoUrl;

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
