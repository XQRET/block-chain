package cn.inbs.blockchain.controller.company.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.controller.company.inputparam
 * @ClassName: CertifiedCompanyInfoInput.java
 * @Date: 2018/7/12 15:03
 * @author: create by zhangmingyang
 **/
public class CertifiedCompanyInfoInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "公司名称")
    private String name;//公司名称

    @IsNotNull(fieldDescription = "省名称")
    private String provinceName;//省名称

    @IsNotNull(fieldDescription = "城市名称")
    private String cityName;//城市名称

    @IsNotNull(fieldDescription = "联系人姓名")
    private String linkman;//联系人姓名

    @IsNotNull(fieldDescription = "联系人手机号")
    private String phone;//联系人手机号

    @IsNotNull(fieldDescription = "公司营业执照图片")
    private MultipartFile companyPhotoFile;//公司营业执照图片

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getCompanyPhotoFile() {
        return companyPhotoFile;
    }

    public void setCompanyPhotoFile(MultipartFile companyPhotoFile) {
        this.companyPhotoFile = companyPhotoFile;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
