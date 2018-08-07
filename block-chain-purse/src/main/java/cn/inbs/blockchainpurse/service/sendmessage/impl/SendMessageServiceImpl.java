package cn.inbs.blockchainpurse.service.sendmessage.impl;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.ThirdSendSMS;
import cn.inbs.blockchainpurse.common.cache.MessageCodeCache;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.common.utils.ChaungLanSMSUtils;
import cn.inbs.blockchainpurse.controller.sendmessage.inputparam.SendMessageCodeInput;
import cn.inbs.blockchainpurse.service.sendmessage.ISendMessageService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 75952
 * @Package: cn.inbs.blockchainpurse.service.sendmessage.impl
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
@Service("sendMessageService")
public class SendMessageServiceImpl implements ISendMessageService {
    private static Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);

    @Override
    public void getMessageCode(SendMessageCodeInput input) {
        if (String.valueOf(Boolean.TRUE).equals(ChaungLanSMSUtils.isSms253Enable())) {
            try {
                String code = ChaungLanSMSUtils.generateCode();
                String messageTemplate  =PropertyUtils.getStringValue(PurseConfigPropertyConstants.MESSAGE_MOBILECODE_TEMPLATE+input.getStype(), CommonConstants.STRING_SPACE);
                String content = ChaungLanSMSUtils.buildContent(messageTemplate, code);
                String smsResult = ThirdSendSMS.sendPost(ChaungLanSMSUtils.getsms253SendUrl(),
                        ChaungLanSMSUtils.getParamsString(input.getMobile(), content));
                if (!JSON.parseObject(smsResult).getString("code").equals("0")) {
                    if (logger.isWarnEnabled()){
                        logger.warn("第三方发送失败");
                    }
                    throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_MESSAGECODE_0002,JSON.parseObject(smsResult).getString("errorMsg"));
                }
                MessageCodeCache.createMessageCodeCache(input.getMobile(), input.getStype(), code);
                if(logger.isInfoEnabled()){
                    logger.info("发送短信成功");
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("发送短信失败：", e);
                }
                throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_MESSAGECODE_0003);
            }
        }
    }
}
