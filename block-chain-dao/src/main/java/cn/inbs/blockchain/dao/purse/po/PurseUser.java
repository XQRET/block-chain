package cn.inbs.blockchain.dao.purse.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.util.Date;

public class PurseUser extends BaseDaoBean {
    private Long id;
    private String purseUserName;
    private String purseUserPassword;
    private String pursePrivateKey;
    private String pursePublicKey;
    private String purseAddress;
    private String purseUserMobile;
    private String purseUserType;
    private String invitationCode;
    private String registerImei;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurseUserName() {
        return purseUserName;
    }

    public void setPurseUserName(String purseUserName) {
        this.purseUserName = purseUserName;
    }

    public String getPurseUserPassword() {
        return purseUserPassword;
    }

    public void setPurseUserPassword(String purseUserPassword) {
        this.purseUserPassword = purseUserPassword;
    }

    public String getPursePrivateKey() {
        return pursePrivateKey;
    }

    public void setPursePrivateKey(String pursePrivateKey) {
        this.pursePrivateKey = pursePrivateKey;
    }

    public String getPursePublicKey() {
        return pursePublicKey;
    }

    public void setPursePublicKey(String pursePublicKey) {
        this.pursePublicKey = pursePublicKey;
    }

    public String getPurseAddress() {
        return purseAddress;
    }

    public void setPurseAddress(String purseAddress) {
        this.purseAddress = purseAddress;
    }

    public String getPurseUserMobile() {
        return purseUserMobile;
    }

    public void setPurseUserMobile(String purseUserMobile) {
        this.purseUserMobile = purseUserMobile;
    }

    public String getPurseUserType() {
        return purseUserType;
    }

    public void setPurseUserType(String purseUserType) {
        this.purseUserType = purseUserType;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getRegisterImei() {
        return registerImei;
    }

    public void setRegisterImei(String registerImei) {
        this.registerImei = registerImei;
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
