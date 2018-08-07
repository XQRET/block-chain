package cn.inbs.blockchain.service.verificationcode.impl;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.ThirdSendSMS;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.utils.MobileUtils;
import cn.inbs.blockchain.service.verificationcode.IVerificationCodeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.user.impl
 * @ClassName:
 * @Date: 2018年04月25-16:43
 * @Author: createBy:zhangmingyang
 **/
@Service("verificationCodeService")
public class VerificationCodeServiceImpl implements IVerificationCodeService {
    private static Logger logger = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @Override
    public void sendVerificationCode(String mobile, String clientIp) {
        //获取随机验证码
        String randomVerificationCode = MobileUtils.getRandomVerificationCode();

        //组装短信内容
        String content = MobileUtils.buildSMSContent(CommonConfigPerpertyConstants.SEND_VERIFICATION_CODE_MESSAGE_TEMPLATE_KEY, randomVerificationCode);

        //发送短信验证码
        send(mobile, content);

        //记录验证码到缓存
        MobileUtils.saveVerificationCode2Cache(mobile, randomVerificationCode, CommonConfigPerpertyConstants.SEND_VERIFICATION_CODE_MESSAGE_TEMPLATE_KEY);
    }

    private static final String SMS_253_SWITCH_ON = "true";
    private static final String SEND_SMS_RESULT_CODE_KEY = "code";
    private static final String SEND_SMS_RESULT_SUCCESS = "0";

    /**
     * 发送短信验证码方法
     *
     * @param mobile
     * @param content
     */
    private void send(String mobile, String content) {
        //获取第三方相关配置(common-config.properties)
        String smsSwitch = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.SMS_253_SWITCH_KEY, null);
        String smsURL = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.SMS_253_SENDURL_KEY, null);
        String accountNo = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.SMS_253_ACCOUNT_KEY, null);
        String password = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.SMS_253_PASSWORD_KEY, null);
        if (StringUtils.isEmpty(smsSwitch) ||
                StringUtils.isEmpty(smsURL) ||
                StringUtils.isEmpty(accountNo) ||
                StringUtils.isEmpty(password)) {
            if (logger.isErrorEnabled()) {
                logger.warn("获取第三方短信配置失败,开关:[{}],URL:[{}],账号:[{}]",
                        smsSwitch, smsURL, accountNo);
            }

            throw new BusinessException(BusinessErrorConstants.CODE_0003, mobile);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("获取第三方短信配置成功,开关:[{}],URL:[{}],账号:[{}],密码:[{}]",
                        smsSwitch, smsURL, accountNo, "******");
            }

            if (SMS_253_SWITCH_ON.equals(smsSwitch.trim())) {
                //组装第三方参数
                String smsParam = initSendSMSParam(mobile, content, accountNo.trim(), password.trim());

                //发送短信
                try {
                    String sendPostResult = ThirdSendSMS.sendPost(smsURL.trim(), smsParam);
                    String sendSMSResultCode = JSON.parseObject(sendPostResult).getString(SEND_SMS_RESULT_CODE_KEY);
                    if (SEND_SMS_RESULT_SUCCESS.equals(sendSMSResultCode)) {
                        if (logger.isInfoEnabled()) {
                            logger.info("发送验证码到用户:[{}]成功,[{}]", mobile, content);
                        }
                    } else {
                        throw new BusinessException(BusinessErrorConstants.CODE_0003, mobile);
                    }
                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("调用第三方发送短信失败", e);
                    }
                    throw new BusinessException(BusinessErrorConstants.CODE_0003, mobile);
                }

            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("短信开关已关闭,暂不支持发送短信");
                }
                throw new BusinessException(BusinessErrorConstants.CODE_0003, mobile);
            }
        }
    }

    private static final String ACCOUNT_KEY = "account";
    private static final String PASSWORD_KEY = "password";
    private static final String MSG_KEY = "msg";
    private static final String PHONE_KEY = "phone";
    private static final String REPORT_KEY = "report";

    /**
     * 初始化调用第三方参数
     *
     * @param mobile
     * @param content
     * @param accountNo
     * @param password
     * @return
     */
    private String initSendSMSParam(String mobile,
                                    String content,
                                    String accountNo,
                                    String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ACCOUNT_KEY, accountNo);
        jsonObject.put(PASSWORD_KEY, password);
        jsonObject.put(MSG_KEY, content);
        jsonObject.put(PHONE_KEY, mobile);
        jsonObject.put(REPORT_KEY, Boolean.TRUE);
        return jsonObject.toJSONString();
    }
}
