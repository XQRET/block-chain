package cn.inbs.blockchain.dao.purse.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @Description: 钱包用户附属信息
 * @Package: cn.inbs.blockchain.dao.purse.po
 * @ClassName: PurseUserExtraInfo
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 16:02
 * @Version: 1.0
 */
public class PurseUserExtraInfo extends BaseDaoBean {
    private Long id;//钱包附属信息ID
    private Long purseUserId;//钱包用户ID
    private String purseTransactionPassword;//钱包交易密码
    private String purseMnemonic;//钱包助记词
    private String purseDigitalCertificate;//钱包数字证书
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

    public String getPurseTransactionPassword() {
        return purseTransactionPassword;
    }

    public void setPurseTransactionPassword(String purseTransactionPassword) {
        this.purseTransactionPassword = purseTransactionPassword;
    }

    public String getPurseMnemonic() {
        return purseMnemonic;
    }

    public void setPurseMnemonic(String purseMnemonic) {
        this.purseMnemonic = purseMnemonic;
    }

    public String getPurseDigitalCertificate() {
        return purseDigitalCertificate;
    }

    public void setPurseDigitalCertificate(String purseDigitalCertificate) {
        this.purseDigitalCertificate = purseDigitalCertificate;
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
