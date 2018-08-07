package cn.inbs.blockchainpurse.common.utils;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * @Description: 创蓝短信
 * @Package: cn.inbs.blockchain.common.third
 * @ClassName: block-chain
 * @Author: qinguanmu
 * @CreateDate: 2018/7/27
 * @Version: 1.0
 */
public class ChaungLanSMSUtils {

    private static String sms253Account = PropertyUtils.getStringValue(PurseConfigPropertyConstants.SMS_CHUANGLAN_ACCOUNT, CommonConstants.STRING_SPACE);
    private static String Sms253PassWord = PropertyUtils.getStringValue(PurseConfigPropertyConstants.SMS_CHUANGLAN_PASSWORD, CommonConstants.STRING_SPACE);
    private static String sms253SendUrl = PropertyUtils.getStringValue(PurseConfigPropertyConstants.SMS_CHUANGLAN_SENDURL, CommonConstants.STRING_SPACE);
    private static String sms253Enable = PropertyUtils.getStringValue(PurseConfigPropertyConstants.SMS_CHUANGLAN_ENABLE, CommonConstants.STRING_SPACE);

    /**
     * 组装创蓝平台参数
     *
     * @param mobile
     * @param content
     * @return
     */
    public static String getParamsString(String mobile, String content) {
        JSONObject json = new JSONObject();
        json.put("account", sms253Account);
        json.put("password", Sms253PassWord);
        json.put("msg", content);
        json.put("phone", mobile);
        json.put("report", true);
        return json.toJSONString();
    }

    /**
     * 发送url
     *
     * @return
     */
    public static String getsms253SendUrl() {
        return sms253SendUrl;
    }

    /**
     * 是否发送短信
     *
     * @return
     */
    public static String isSms253Enable() {
        return sms253Enable;
    }

    /**
     * 组装短信参数
     * @param template
     * @param params
     * @return
     */
    public static String buildContent(String template, String... params){
        String content=buildTemplate(template, params);
        return StringUtils.trimToNull(content);
    }
    /**
     * 组装短信参数
     * @param template
     * @param params
     * @return
     */
    private static String buildTemplate(String template, String... params){
        if(params==null){
            return template;
        }
        for(int i=0; i<params.length; i++){
            template=template.replaceAll("\\{"+i+"\\}", params[i]);
        }
        return template;
    }
    /**
     * 生成短信验证码
     * @return
     */
    public static String generateCode() {
        return String.valueOf((new Random()).nextInt(899999) + 100000);
    }
}
