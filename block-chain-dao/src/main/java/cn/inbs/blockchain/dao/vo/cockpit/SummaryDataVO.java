package cn.inbs.blockchain.dao.vo.cockpit;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 驾驶舱汇总数据VO
 */
public class SummaryDataVO {
    /**
     * 当月新增合约个数
     */
    @JSONField(name = "c")
    private Integer currentNewAdd;

    /**
     * 当月新增合约金额（万元）
     */
    @JSONField(name = "cm")
    private String currentNewMoney;

    /**
     * 资产端用户数（个）
     */
    @JSONField(name = "a")
    private Integer assetNumber;

    /**
     * 资金端用户数（个）
     */
    @JSONField(name = "cn")
    private Integer capitalNumber;

    /**
     * 平台生效合约数（个）
     */
    @JSONField(name = "p")
    private Integer properContractNumber;

    /**
     * 平台生效合约金额（万元）
     */
    @JSONField(name = "pc")
    private String propertContractMoney;

    /**
     * 平台合约总数（个）
     */
    @JSONField(name = "t")
    private Integer contractNumber;

    /**
     * 平台合约总金额（万元）
     */
    @JSONField(name = "m")
    private String contractMoney;


    public Integer getCurrentNewAdd() {
        return currentNewAdd;
    }

    public void setCurrentNewAdd(Integer currentNewAdd) {
        this.currentNewAdd = currentNewAdd;
    }

    public String getCurrentNewMoney() {
        return currentNewMoney;
    }

    public void setCurrentNewMoney(String currentNewMoney) {
        this.currentNewMoney = currentNewMoney;
    }

    public Integer getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(Integer assetNumber) {
        this.assetNumber = assetNumber;
    }

    public Integer getCapitalNumber() {
        return capitalNumber;
    }

    public void setCapitalNumber(Integer capitalNumber) {
        this.capitalNumber = capitalNumber;
    }

    public Integer getProperContractNumber() {
        return properContractNumber;
    }

    public void setProperContractNumber(Integer properContractNumber) {
        this.properContractNumber = properContractNumber;
    }

    public String getPropertContractMoney() {
        return propertContractMoney;
    }

    public void setPropertContractMoney(String propertContractMoney) {
        this.propertContractMoney = propertContractMoney;
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }
}
