package cn.inbs.blockchain.controller.product.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 查询产品预售列表入参
 * @Package: cn.inbs.blockchain.controller.product.inputparam
 * @ClassName: QueryProductSalePageListInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 16:46
 * @Version: 1.0
 */
public class QueryProductSalePageListInput extends BaseControllerInput {
    @ValueCodeCheck(fieldDescription = "产品类型", codeValue = ValueCodeArrays.CONTRACT_PRODUCT_TYPE_STR)
    private String productType;//产品类型

    @ValueCodeCheck(fieldDescription = "产品状态", codeValue = ValueCodeArrays.PRODUCT_STATUS_STR)
    private String productStatus;//产品状态

    private Integer totalCount = 0;//行数

    private Integer totalPage;//总页数

    private Integer pageCount = 10;//每页条数

    @IsNotNull(fieldDescription = "页数")
    private Integer pageIndex = 1;//页数

    private Integer pageStartCountIndex;//每页开始索引

    private Integer pageEndCountIndex;//每页结束索引

    private Object pageInfoList;//每页信息列表


    public Integer getTotalPage() {
        if (null == totalCount || 0 == totalCount) {
            return 0;
        } else if (totalCount % pageCount == 0) {
            return totalCount / pageCount;
        } else {
            return (totalCount / pageCount) + 1;
        }
    }

    /**
     * 分页开始索引
     *
     * @return
     */
    public Integer getPageStartCountIndex() {
        return ((pageIndex - 1) * pageCount);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageStartCountIndex(Integer pageStartCountIndex) {
        this.pageStartCountIndex = pageStartCountIndex;
    }

    public Integer getPageEndCountIndex() {
        return pageEndCountIndex;
    }

    public void setPageEndCountIndex(Integer pageEndCountIndex) {
        this.pageEndCountIndex = pageEndCountIndex;
    }

    public Object getPageInfoList() {
        return pageInfoList;
    }

    public void setPageInfoList(Object pageInfoList) {
        this.pageInfoList = pageInfoList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
