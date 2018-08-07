package cn.inbs.blockchainpurse.controller.user.outputparam;

import com.alibaba.fastjson.JSON;

/**
 * @Description:
 * @Package: cn.inbs.blockchainpurse.controller.user.outputparam
 * @ClassName: CheckUserTransactionPasswordOutput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:35
 * @Version: 1.0
 */
public class CheckUserTransactionPasswordOutput {

    private boolean have;

    public boolean isHave() {
        return have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
