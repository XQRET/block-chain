package cn.inbs.blockchainpurse.service.authentication.impl;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchain.dao.purse.user.PurseUserMapper;
import cn.inbs.blockchainpurse.common.cache.MessageCodeCache;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.common.utils.valuecode.PurseValueCodeArrays;
import cn.inbs.blockchainpurse.controller.authentication.inputparam.MobileAuthInput;
import cn.inbs.blockchainpurse.service.authentication.IPurseMobileAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 手机认证
 * @Package: cn.inbs.blockchainpurse.service.authentication.impl
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/31
 * @Version: 1.0
 */
@Service("purseMobileAuthService")
public class PurseMobileAuthServiceImpl implements IPurseMobileAuthService {
    private static Logger logger = LoggerFactory.getLogger(PurseMobileAuthServiceImpl.class);
    @Resource
    private PurseUserMapper purseUserMapper;

    @Override
    public void updateMobileAuth(MobileAuthInput input) {

        try {
            String stype = PurseValueCodeArrays.MESSAGE_CODE_STYPES[3];
            String code = MessageCodeCache.getMessageCodeInCache(input.getMobile(), stype);
            if (!input.getCode().equals(code)) {
                if (logger.isWarnEnabled()) {
                    logger.warn("短信验证码不匹配");
                }
                throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_MESSAGECODE_0001);
            }
            /**
             *从token获取用户名
             */
            String[] PurseTokens = input.getPurseToken().split(CommonConstants.SPLIT_MARK);
            String purseName = PurseTokens[0];
            /**
             *根据用户名更新认证手机号
             */
            PurseUser purseUser = new PurseUser();
            purseUser.setPurseUserMobile(input.getMobile());
            purseUser.setPurseUserName(purseName);
            int i = purseUserMapper.updatePurseUser(purseUser);
            if (i <= 0) {
                if (logger.isWarnEnabled()) {
                    logger.warn("更新数据库状态失败");
                }
                throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_MOBILEAUTH_0002);
            }
            if (logger.isInfoEnabled()){
                logger.info("手机认证成功");
            }
        } catch (PurseBusinessException e) {
            if(logger.isErrorEnabled()){
                logger.error("手机认证异常");
            }
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_MOBILEAUTH_0001);
        }
    }
}
