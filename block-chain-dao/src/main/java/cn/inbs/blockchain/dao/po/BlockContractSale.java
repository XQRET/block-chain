package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 合约预售po
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName: BlockContractSale
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 11:29
 * @Version: 1.0
 */
public class BlockContractSale extends BaseDaoBean {
    /**
     * 合约募集状态 :1-待募集,2-募集中,3-募集完成
     */
    public static final String RAISE_STATUS_1 = "1";
    public static final String RAISE_STATUS_2 = "2";
    public static final String RAISE_STATUS_3 = "3";


    private Long id;//
    private String contractId;//合约ID(唯一)
    private String contractProductType;//0-固收,1-非固收
    private Integer contractServings;//合约拆分份数
    private BigDecimal contractAmount;//合约总金额
    private BigDecimal yearOfRate;//合约年化利率
    private Integer contractTerm;//合约期限(月)
    private Date releaseTime;//发布时间
    private String raiseStatus;//合约募集状态,1-待募集,2-募集中,3-募集完成
    private Integer contractCurrentProgress;//合约募集进度,共募集多少份
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractProductType() {
        return contractProductType;
    }

    public void setContractProductType(String contractProductType) {
        this.contractProductType = contractProductType;
    }

    public Integer getContractServings() {
        return contractServings;
    }

    public void setContractServings(Integer contractServings) {
        this.contractServings = contractServings;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getYearOfRate() {
        return yearOfRate;
    }

    public void setYearOfRate(BigDecimal yearOfRate) {
        this.yearOfRate = yearOfRate;
    }

    public Integer getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Integer contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRaiseStatus() {
        return raiseStatus;
    }

    public void setRaiseStatus(String raiseStatus) {
        this.raiseStatus = raiseStatus;
    }

    public Integer getContractCurrentProgress() {
        return contractCurrentProgress;
    }

    public void setContractCurrentProgress(Integer contractCurrentProgress) {
        this.contractCurrentProgress = contractCurrentProgress;
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
}
