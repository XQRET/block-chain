package cn.inbs.blockchain.controller.contract.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeArrays;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 预售产品分页列表入参
 * @Package: cn.inbs.blockchain.controller.contract.inputparam
 * @ClassName: QueryPresalePageListInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 19:09
 * @Version: 1.0
 */
public class QueryPresalePageListInput extends BaseControllerInput {
    @ValueCodeCheck(fieldDescription = "产品类型", codeValue = ValueCodeArrays.CONTRACT_PRODUCT_TYPE_STR)
    private String contractProductType;//产品类型

    @ValueCodeCheck(fieldDescription = "募集状态", codeValue = ValueCodeArrays.RAISE_STATUS_STR)
    private String raiseStatus;//募集状态

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

    public String getContractProductType() {
        return contractProductType;
    }

    public void setContractProductType(String contractProductType) {
        this.contractProductType = contractProductType;
    }

    public String getRaiseStatus() {
        return raiseStatus;
    }

    public void setRaiseStatus(String raiseStatus) {
        this.raiseStatus = raiseStatus;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
