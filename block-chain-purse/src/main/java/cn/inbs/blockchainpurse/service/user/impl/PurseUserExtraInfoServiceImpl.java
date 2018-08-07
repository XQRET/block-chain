package cn.inbs.blockchainpurse.service.user.impl;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchain.dao.purse.po.PurseUserExtraInfo;
import cn.inbs.blockchain.dao.purse.user.PurseUserExtraInfoMapper;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.controller.user.inputparam.SetPurseTransactionPasswordInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.CheckUserTransactionPasswordOutput;
import cn.inbs.blockchainpurse.service.user.IPurseUserExtraInfoService;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: //TODO
 * @Package: cn.inbs.blockchainpurse.service.user.impl
 * @ClassName: PurseUserExtraInfoServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/26 17:18
 * @Version: 1.0
 */
@Service("purseUserExtraInfoService")
public class PurseUserExtraInfoServiceImpl implements IPurseUserExtraInfoService {
    private static Logger logger = LoggerFactory.getLogger(PurseUserExtraInfoServiceImpl.class);

    @Resource
    private PurseUserExtraInfoMapper purseUserExtraInfoMapper;

    @Resource
    private IUserService userService;

    @Override
    public CheckUserTransactionPasswordOutput queryByCheckTransactionPasswordIsExist(BaseControllerInput input) {
        CheckUserTransactionPasswordOutput output = new CheckUserTransactionPasswordOutput();

        //查询用户基本信息
        PurseUser queryPurseUser = userService.queryPurseUserByToken(input.getPurseToken());

        //查询用户附加信息
        PurseUserExtraInfo queryPurseUserExtraInfo = new PurseUserExtraInfo();
        queryPurseUserExtraInfo.setPurseUserId(queryPurseUser.getId());
        queryPurseUserExtraInfo = purseUserExtraInfoMapper.selectPurseUserExtraInfoByUserId(queryPurseUserExtraInfo);

        output.setHave(StringUtils.isNotEmpty(queryPurseUserExtraInfo.getPurseTransactionPassword()));

        return output;
    }

    @Override
    public void updateBySetPurseTransactionPassword(SetPurseTransactionPasswordInput input) {
        //查询用户基本信息
        PurseUser queryPurseUser = userService.queryPurseUserByToken(input.getPurseToken());

        //查询用户附加信息
        PurseUserExtraInfo queryPurseUserExtraInfo = new PurseUserExtraInfo();
        queryPurseUserExtraInfo.setPurseUserId(queryPurseUser.getId());
        queryPurseUserExtraInfo = purseUserExtraInfoMapper.selectPurseUserExtraInfoByUserId(queryPurseUserExtraInfo);

        if (StringUtils.isNotEmpty(queryPurseUserExtraInfo.getPurseTransactionPassword())) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0008);
        } else {
            PurseUserExtraInfo update = new PurseUserExtraInfo();
            update.setId(queryPurseUserExtraInfo.getId());
            update.setPurseTransactionPassword(input.getPurseTransactionPassword());
            update.setUpdateTime(new Date());
            int count = purseUserExtraInfoMapper.updatePurseUserExtraInfoById(update);

            if (count != 1) {
                throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0009);
            }

            if (logger.isInfoEnabled()) {
                logger.info("设置用户:[{}]交易密码成功", queryPurseUser.getPurseUserName());
            }
        }
    }
}
