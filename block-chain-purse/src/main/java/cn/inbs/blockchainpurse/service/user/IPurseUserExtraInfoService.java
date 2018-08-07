package cn.inbs.blockchainpurse.service.user;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchainpurse.controller.user.inputparam.SetPurseTransactionPasswordInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.CheckUserTransactionPasswordOutput;

/**
 * @Description: 用户附属信息
 * @Package: cn.inbs.blockchainpurse.service.user
 * @ClassName: IPurseUserExtraInfoService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:16
 * @Version: 1.0
 */
public interface IPurseUserExtraInfoService {
    /**
     * 检查用户是否存在交易密码
     *
     * @param input
     * @return
     */
    CheckUserTransactionPasswordOutput queryByCheckTransactionPasswordIsExist(BaseControllerInput input);

    /**
     * 设置用户交易密码
     *
     * @param input
     */
    void updateBySetPurseTransactionPassword(SetPurseTransactionPasswordInput input);

}
