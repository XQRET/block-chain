package cn.inbs.blockchainpurse.service.user;

import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchainpurse.common.assemblystructure.UserDetailsBean;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserLoginInput;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserRegisterInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.PurseUserLoginOutput;
import cn.inbs.blockchainpurse.controller.user.outputparam.PurseUserRegisterOutput;

/**
 * @Description: 用户服务
 * @Package: cn.inbs.blockchainpurse.service.user
 * @ClassName: IUserService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/23 17:13
 * @Version: 1.0
 */
public interface IUserService {
    /**
     * 用户注册
     *
     * @param input
     * @return
     */
    PurseUserRegisterOutput insertUserByRegister(PurseUserRegisterInput input) throws Exception;

    /**
     * 用户登录操作
     *
     * @param input
     * @return
     */
    PurseUserLoginOutput queryUserByLogin(PurseUserLoginInput input);

    /**
     * 查询用户信息根据用户token
     *
     * @param token
     * @return
     */
    PurseUser queryPurseUserByToken(String token);

    /**
     * 查询用户详细信息
     *
     * @param token
     * @return
     */
    UserDetailsBean queryUserDetailsInfo(String token);
}
