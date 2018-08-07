package cn.inbs.blockchainpurse.service.authentication;

import cn.inbs.blockchainpurse.controller.authentication.inputparam.MobileAuthInput; /**
 * @Description: 75952
 * @Package: cn.inbs.blockchainpurse.service.authentication
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/31
 * @Version: 1.0
 */
public interface IPurseMobileAuthService {
    void updateMobileAuth(MobileAuthInput input);
}
