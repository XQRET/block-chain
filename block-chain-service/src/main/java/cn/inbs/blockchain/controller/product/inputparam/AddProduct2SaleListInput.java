package cn.inbs.blockchain.controller.product.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 添加预售产品入参
 * @Package: cn.inbs.blockchain.controller.product.inputparam
 * @ClassName: AddProduct2SaleListInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:36
 * @Version: 1.0
 */
public class AddProduct2SaleListInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "产品类型")
    @ValueCodeCheck(fieldDescription = "产品类型", codeValue = ValueCodeArrays.CONTRACT_PRODUCT_TYPE_STR)
    private String productType;//产品类型a

    @IsNotNull(fieldDescription = "项目名称")
    private String productName;//项目名称

    @IsNotNull(fieldDescription = "产品Banner")
    private MultipartFile productBanner;//产品Banner

    @IsNotNull(fieldDescription = "产品详情")
    private MultipartFile productDetails;//产品详情

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
