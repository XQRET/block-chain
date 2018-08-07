package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @Description: 产品预售
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName: ProductSale
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:12
 * @Version: 1.0
 */
public class ProductSale extends BaseDaoBean {
    //销售状态,1-待预售,2-预售中,3-预售完成
    public static final String PRODUCT_STATUS_1 = "1";
    public static final String PRODUCT_STATUS_2 = "2";
    public static final String PRODUCT_STATUS_3 = "3";

    private Long id;//
    private String productType;//0-固收,1-非固收
    private String productName;//产品项目名称
    private String productBanner;//产品Banner
    private String productDetails;//产品详情
    private String productStatus;//销售状态,1-待预售,2-预售中,3-预售完成
    private Date releaseTime;//发布时间
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProductBanner() {
        return productBanner;
    }

    public void setProductBanner(String productBanner) {
        this.productBanner = productBanner;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
