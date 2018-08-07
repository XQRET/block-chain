package cn.inbs.blockchain.dao.vo.cockpit;

/**
 * @Description:柱状图数据对象
 * @Package: cn.inbs.blockchain.dao.vo.cockpit
 * @ClassName:
 * @Date: 2018年07月05-10:44
 * @Author: createBy:chenhao
 **/
public class BarDO {
    /**
     * 月份
     */
    private String currentMonth;
    /**
     * 新增数量
     */
    private Integer newAdd;
    /**
     * 新增金额
     */
    private String newAddAmount;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 省份名称
     */
    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Integer getNewAdd() {
        return newAdd;
    }

    public void setNewAdd(Integer newAdd) {
        this.newAdd = newAdd;
    }

    public String getNewAddAmount() {
        return newAddAmount;
    }

    public void setNewAddAmount(String newAddAmount) {
        this.newAddAmount = newAddAmount;
    }
}
