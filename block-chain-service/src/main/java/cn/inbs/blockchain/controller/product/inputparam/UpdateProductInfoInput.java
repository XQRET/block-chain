package cn.inbs.blockchain.controller.product.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 修改预售产品信息入参
 * @Package: cn.inbs.blockchain.controller.product.inputparam
 * @ClassName: UpdateProductInfoInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 15:56
 * @Version: 1.0
 */
public class UpdateProductInfoInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "产品ID")
    private Long id;//产品ID

    private String productName;//产品名称

    private MultipartFile productBanner;//banner

    private MultipartFile productDetails;//详情

    @ValueCodeCheck(fieldDescription = "状态", codeValue = ValueCodeArrays.PRODUCT_STATUS_STR)
    private String productStatus;//状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public MultipartFile getProductBanner() {
        return productBanner;
    }

    public void setProductBanner(MultipartFile productBanner) {
        this.productBanner = productBanner;
    }

    public MultipartFile getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(MultipartFile productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
