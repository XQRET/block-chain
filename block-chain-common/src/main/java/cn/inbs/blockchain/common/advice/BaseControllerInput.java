package cn.inbs.blockchain.common.advice;

import java.io.Serializable;

/**
 * @Description: controller 入参基类
 * @Package: cn.inbs.blockchain.common.advice
 * @ClassName: BaseControllerInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 11:11
 * @Version: 1.0
 */
public class BaseControllerInput implements Serializable {
    private String purseToken;

    public String getPurseToken() {
        return purseToken;
    }

    public void setPurseToken(String purseToken) {
        this.purseToken = purseToken;
    }
}
