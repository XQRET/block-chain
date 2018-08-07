package cn.inbs.blockchain.service.verificationcode;

/**
 * @Description: 发送短信验证码
 * @Package: cn.inbs.blockchain.service.user
 * @ClassName:
 * @Date: 2018年04月25-16:40
 * @Author: createBy:zhangmingyang
 **/
public interface IVerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param mobile   接收方手机号
     * @param clientIp 接收方IP
     */
    void sendVerificationCode(String mobile, String clientIp);
}
