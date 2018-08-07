package cn.inbs.blockchainpurse.common.assemblystructure;

import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchain.dao.purse.po.PurseUserExtraInfo;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 用户详细信息
 * @Package: cn.inbs.blockchainpurse.common.assemblystructure
 * @ClassName: UserDetailsBean
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/2 11:07
 * @Version: 1.0
 */
public class UserDetailsBean {
    private PurseUser purseUser;//用户基本信息
    private PurseUserExtraInfo purseUserExtraInfo;//用户附加信息

    public PurseUser getPurseUser() {
        return purseUser;
    }

    public void setPurseUser(PurseUser purseUser) {
        this.purseUser = purseUser;
    }

    public PurseUserExtraInfo getPurseUserExtraInfo() {
        return purseUserExtraInfo;
    }

    public void setPurseUserExtraInfo(PurseUserExtraInfo purseUserExtraInfo) {
        this.purseUserExtraInfo = purseUserExtraInfo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
