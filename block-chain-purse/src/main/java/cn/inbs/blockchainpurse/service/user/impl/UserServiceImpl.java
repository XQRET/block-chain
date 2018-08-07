package cn.inbs.blockchainpurse.service.user.impl;

import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.dao.purse.po.PurseAmount;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchain.dao.purse.po.PurseUserExtraInfo;
import cn.inbs.blockchain.dao.purse.user.PurseAmountMapper;
import cn.inbs.blockchain.dao.purse.user.PurseUserExtraInfoMapper;
import cn.inbs.blockchain.dao.purse.user.PurseUserMapper;
import cn.inbs.blockchainpurse.common.assemblystructure.UserDetailsBean;
import cn.inbs.blockchainpurse.common.cache.UserTokenCache;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.common.utils.PurseUtils;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserLoginInput;
import cn.inbs.blockchainpurse.controller.user.inputparam.PurseUserRegisterInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.PurseUserLoginOutput;
import cn.inbs.blockchainpurse.controller.user.outputparam.PurseUserRegisterOutput;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchainpurse.service.user.impl
 * @ClassName: UserServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:09
 * @Version: 1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final BigDecimal AMOUNT_ZERO = new BigDecimal(0);

    @Resource
    private PurseUserMapper purseUserMapper;

    @Resource
    private PurseAmountMapper purseAmountMapper;

    @Resource
    private PurseUserExtraInfoMapper purseUserExtraInfoMapper;

    @Override
    public PurseUserRegisterOutput insertUserByRegister(PurseUserRegisterInput input) throws Exception {
        PurseUserRegisterOutput output = new PurseUserRegisterOutput();
        //查询用户信息是否存在
        PurseUser query = new PurseUser();
        String purseName = input.getPurseName().trim();
        query.setPurseUserName(purseName);
        query = purseUserMapper.selectPurseUser(query);
        if (null != query) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0001, purseName);
        } else {
            //生成密钥对
            Map<String, Object> stringObjectMap = RSAUtils.genKeyPair();
            String publicKey = RSAUtils.getPublicKey(stringObjectMap);//公钥
            String privateKey = RSAUtils.getPrivateKey(stringObjectMap);//私钥
            String purseAddressByPublicKey = PurseUtils.getPurseAddressByPublicKey(publicKey);//钱包地址

            //创建用户基本信息
            PurseUser purseUser = createPurseUser(input, publicKey, privateKey, purseAddressByPublicKey);

            //添加余额信息
            createPurseAomunt(purseUser);

            //添加用户附属信息
            createUserExtraInfo(purseUser);

            //组装返回参数
            output.setPrivateKey(privateKey);
            output.setPurseName(purseName);
            output.setPurseAddress(purseAddressByPublicKey);

            if (logger.isInfoEnabled()) {
                logger.info("用户:[{}]注册成功", purseName);
            }
        }
        return output;
    }

    /**
     * 创建用户基本信息
     *
     * @param input
     * @param publicKey
     * @param privateKey
     * @param purseAddressByPublicKey
     * @return
     */
    private PurseUser createPurseUser(PurseUserRegisterInput input, String publicKey, String privateKey, String purseAddressByPublicKey) {
        //组装插入参数
        PurseUser insert = new PurseUser();
        Date insertDate = new Date();
        insert.setPurseUserName(input.getPurseName());
        insert.setPurseUserPassword(input.getPursePassword());
        insert.setPursePrivateKey(privateKey);
        insert.setPursePublicKey(publicKey);
        insert.setPurseAddress(purseAddressByPublicKey);
        insert.setInvitationCode(input.getInvitationCode());
        insert.setRegisterImei(input.getImei());
        insert.setCreateTime(insertDate);
        insert.setUpdateTime(insertDate);

        int count = purseUserMapper.insertPurseUser(insert);
        if (count != 1) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0002, input.getPurseName());
        }
        return insert;
    }

    /**
     * 创建用户余额信息
     *
     * @param purseUser
     */
    private void createPurseAomunt(PurseUser purseUser) {
        PurseAmount insert = new PurseAmount();
        insert.setPurseUserId(purseUser.getId());
        insert.setTotalAmount(AMOUNT_ZERO);
        insert.setFixedAssetsAmount(AMOUNT_ZERO);
        insert.setUnFixedAssetsAmount(AMOUNT_ZERO);
        insert.setTransferAmount(AMOUNT_ZERO);
        insert.setAvailableAmount(AMOUNT_ZERO);
        insert.setCreateTime(purseUser.getCreateTime());
        insert.setUpdateTime(purseUser.getCreateTime());
        int count = purseAmountMapper.insertPurseAmount(insert);
        if (count != 1) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0002, purseUser.getPurseUserName());
        }
    }

    /**
     * 创建用户附加信息
     *
     * @param purseUser
     */
    private void createUserExtraInfo(PurseUser purseUser) {
        PurseUserExtraInfo insert = new PurseUserExtraInfo();
        insert.setPurseUserId(purseUser.getId());
        insert.setCreateTime(purseUser.getCreateTime());
        insert.setUpdateTime(purseUser.getCreateTime());
        insert.setPurseMnemonic(PurseUtils.getPurseUserMnemonicByPrivateKey(purseUser.getPursePrivateKey()));
        int count = purseUserExtraInfoMapper.insertPurseUserExtraInfo(insert);
        if (count != 1) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0002, purseUser.getPurseUserName());
        }
    }

    @Override
    public PurseUserLoginOutput queryUserByLogin(PurseUserLoginInput input) {
        PurseUserLoginOutput output = new PurseUserLoginOutput();
        //查询用户信息是否存在
        PurseUser query = new PurseUser();
        String purseName = input.getPurseName().trim();
        query.setPurseUserName(purseName);
        query = purseUserMapper.selectPurseUser(query);
        if (null == query) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0004, purseName);
        } else {
            //密码校验
            if (!input.getPursePassword().equals(query.getPurseUserPassword())) {
                throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0005);
            }

            String tokenValue = purseName + "-" + DateUtils.formatDate(new Date(), DateUtils.DateFormat.DATE_FORMAT_6);//钱包名称+时间戳

            //记录token
            UserTokenCache.createTokenCache(purseName, tokenValue);

            output.setPurseToken(tokenValue);

            if (logger.isInfoEnabled()) {
                logger.info("用户:[{}]登录成功", purseName);
            }
        }
        return output;
    }

    @Override
    public PurseUser queryPurseUserByToken(String token) {
        String purseName = PurseUtils.getPurseNameInToken(token);
        PurseUser query = new PurseUser();
        query.setPurseUserName(purseName);
        query = purseUserMapper.selectPurseUser(query);
        if (null == query) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0004, purseName);
        }
        return query;
    }

    @Override
    public UserDetailsBean queryUserDetailsInfo(String token) {
        UserDetailsBean output = new UserDetailsBean();

        //用户基本信息
        PurseUser purseUser = this.queryPurseUserByToken(token);

        //用户附加信息
        PurseUserExtraInfo queryPurseUserExtraInfo = new PurseUserExtraInfo();
        queryPurseUserExtraInfo.setPurseUserId(purseUser.getId());
        queryPurseUserExtraInfo = purseUserExtraInfoMapper.selectPurseUserExtraInfoByUserId(queryPurseUserExtraInfo);

        //组装返回参数
        output.setPurseUser(purseUser);
        output.setPurseUserExtraInfo(queryPurseUserExtraInfo);

        if (logger.isInfoEnabled()) {
            logger.info("用户详细信息:[{}]", output.toString());
        }

        return output;
    }
}
