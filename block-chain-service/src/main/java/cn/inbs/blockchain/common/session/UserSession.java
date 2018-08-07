package cn.inbs.blockchain.common.session;

import java.util.Date;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.session
 * @ClassName:
 * @Date: 2018年04月26-20:21
 * @Author: createBy:zhangmingyang
 **/
public class UserSession extends SessionObject {
    private String mobile;//用户注册手机号
    private String passwd;//密码
    private String realName;//用户真实姓名
    private String identityNo;//省份证号码
    private String email;//邮箱
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private String blockId;//区块链Id
    private Long nodeId;//节点ID
    private String privateKey;//私钥
    private Long companyId;//公司ID

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
