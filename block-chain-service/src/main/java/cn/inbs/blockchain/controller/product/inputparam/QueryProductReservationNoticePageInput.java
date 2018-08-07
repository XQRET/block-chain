package cn.inbs.blockchain.controller.product.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 查询预售通知列表入参
 * @Package: cn.inbs.blockchain.controller.product.inputparam
 * @ClassName: QueryProductReservationNoticePageInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 14:52
 * @Version: 1.0
 */
public class QueryProductReservationNoticePageInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "产品ID")
    private Long productId;//产品ID

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
