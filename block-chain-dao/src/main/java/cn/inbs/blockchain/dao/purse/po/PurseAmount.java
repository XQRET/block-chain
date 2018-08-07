package cn.inbs.blockchain.dao.purse.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 用户余额bean
 * @Package: cn.inbs.blockchain.dao.purse.po
 * @ClassName: PurseAmount
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 13:15
 * @Version: 1.0
 */
public class PurseAmount extends BaseDaoBean {
    private Long id;
    private Long purseUserId;//钱包用户ID
    private BigDecimal totalAmount;//总金额(单位:RET)
    private BigDecimal fixedAssetsAmount;//固收资产余额(单位:RET)
    private BigDecimal unFixedAssetsAmount;//非固收资产余额(单位:RET)
    private BigDecimal transferAmount;//转让金额(单位:RET)
    private BigDecimal availableAmount;//可用余额(单位:RET)
    private String currency;//币种(单位:RET)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurseUserId() {
        return purseUserId;
    }

    public void setPurseUserId(Long purseUserId) {
        this.purseUserId = purseUserId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFixedAssetsAmount() {
        return fixedAssetsAmount;
    }

    public void setFixedAssetsAmount(BigDecimal fixedAssetsAmount) {
        this.fixedAssetsAmount = fixedAssetsAmount;
    }

    public BigDecimal getUnFixedAssetsAmount() {
        return unFixedAssetsAmount;
    }

    public void setUnFixedAssetsAmount(BigDecimal unFixedAssetsAmount) {
        this.unFixedAssetsAmount = unFixedAssetsAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
