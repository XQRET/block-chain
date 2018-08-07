package cn.inbs.blockchain.controller.verificationcode;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.HttpUtil;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.utils.MobileUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.verificationcode.IVerificationCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 发送验证码
 * @Package:
 * @ClassName:
 * @Date: 2018年04月25-16:08
 * @Author: createBy:zhangmingyang
 **/
@Controller
@RequestMapping("/verificationCode")
public class VerificationCodeCotroller extends BaseController {
    @Resource
    private IVerificationCodeService verificationCodeService;

    /**
     * 发送短信验证码
     *
     * @param mobile  手机号
     * @param request
     */
    @RequestMapping(value = "/sendVerificationCode.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String sendVerificationCode(HttpServletRequest request,
                                       String mobile) {
        mobile = mobile.trim();
        //获取远程客户端IP
        String remoteClientIP = HttpUtil.getRemoteClientIP(request);

        //参数校验
        sendVerificationCodeCheckParam(mobile, remoteClientIP);

        //执行验证码发送
        verificationCodeService.sendVerificationCode(mobile, remoteClientIP);
        return retContent(null);
    }

    /**
     * 发送短信验证码参数校验
     *
     * @param mobile   手机号
     * @param clientIp 客户端IP
     */
    private void sendVerificationCodeCheckParam(String mobile,
                                                String clientIp) {
        //校验手机号
        MobileUtils.checkMobile(mobile);

        //校验客户端IP
        if (StringUtils.isEmpty(clientIp)) {
            throw new BusinessException(BusinessErrorConstants.USER_0001);
        }

        //每个手机号每天只能接收10次该系统发送的验证码
        if (!MobileUtils.checkSend2MobileNumOfTimes(mobile)) {
            throw new BusinessException(BusinessErrorConstants.CODE_0001, mobile);
        }

        //每个IP只能请求10次
        if (!MobileUtils.checkSendIpNumOfTimes(clientIp)) {
            throw new BusinessException(BusinessErrorConstants.CODE_0002, clientIp);
        }
    }
}
