package cn.inbs.blockchain.common.commonbean;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:分页po
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName:
 * @Date: 2018年05月18-10:42
 * @Author: createBy:zhangmingyang
 **/
public class PagePo {

    public static final String PAGE_CONDITION_PAGE_COUNT = "pageCount";//每页显示条数
    public static final String PAGE_CONDITION_PAGE_INDEX = "pageIndex";////第几页
    public static final String PAGE_CONDITION_START_INDEX = "startIndex";////第几页

    private Integer totalCount = 0;//行数
    private Integer totalPage;//总页数
    private Integer pageCount = 10;//每页条数
    private Integer pageIndex = 1;//页数
    private Integer pageStartCountIndex;//每页开始索引
    private Integer pageEndCountIndex;//每页结束索引
    private HashMap<String, Object> conditionParamMap;
    private Object pageInfoList;//每页信息列表

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public Integer getTotalPage() {
        if (null == totalCount || 0 == totalCount) {
            return 0;
        } else if (totalCount % pageCount == 0) {
            return totalCount / pageCount;
        } else {
            return (totalCount / pageCount) + 1;
        }
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

    /**
     * 分页开始索引
     *
     * @return
     */
    public Integer getPageStartCountIndex() {
        return ((pageIndex - 1) * pageCount);
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


    public HashMap<String, Object> getConditionParamMap() {
        return conditionParamMap;
    }

    public void setConditionParamMap(HashMap<String, Object> conditionParamMap) {
        this.conditionParamMap = conditionParamMap;
    }

    public Object getPageInfoList() {
        return pageInfoList;
    }

    public void setPageInfoList(Object pageInfoList) {
        this.pageInfoList = pageInfoList;
    }

    @Override
    public String toString() {
        return "PagePo{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageCount=" + pageCount +
                ", pageIndex=" + pageIndex +
                ", pageStartCountIndex=" + pageStartCountIndex +
                ", pageEndCountIndex=" + pageEndCountIndex +
                ", conditionParamMap=" + conditionParamMap +
                ", pageInfoList=" + pageInfoList +
                '}';
    }
}
