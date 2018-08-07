package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.CacheIndex;
import cn.inbs.blockchain.common.cache.RedisCache;
import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @Description: 手机短信验证码工具类
 * @Package: cn.inbs.blockchain.common.utils.mobile
 * @ClassName:
 * @Date: 2018年04月25-17:18
 * @Author: createBy:zhangmingyang
 **/
public class MobileUtils {
    private static Logger logger = LoggerFactory.getLogger(MobileUtils.class);

    private static int timeout;//缓存超时时间
    private static RedisTemplate<String, String> redisTemplate;//redisTemplate

    /**
     * 常量定义
     */
    private static final String VERIFICATION_CODE_MOBILE_CACHE_START_INDEX_KEY = "massageMobile:";//记录手机号次数缓存索引
    private static final String SEND_VERIFICATION_CODE_TIMES_INIT_VALUE = "0";//短信验证码发送次数初始化值
    private static final long ONE_DAY_BY_SECONDS = 24 * 60 * 60;//一天时间(以秒为单位)
    private static final String VERIFICATION_CODE_IP_CACHE_START_INDEX_KEY = "massageIp:";//记录手机IP数缓存索引
    private static final String SEND_IP_TIMES_INIT_VALUE = "0";//短信验证码发送次数初始化值
    private static final String VERIFICATION_CODESTART_INDEX = "mobileVerificationCode:";

    private static BaseCache<String> mobileVerificationCache;//手机短信验证码缓存


    public MobileUtils(RedisTemplate<String, String> redisTemplate, int timeout) {
        MobileUtils.timeout = timeout;
        MobileUtils.redisTemplate = redisTemplate;
        mobileVerificationCache = new RedisCache<String>(String.class, redisTemplate, CacheIndex.MOBILE_CACHE_INDEX.ordinal());
    }

    private static final String PARAM_PLACEHOLDER_SYM = "\\{}";//参数占位符

    /**
     * 创建短信内容
     *
     * @param templateKey
     * @param params
     * @return
     */
    public static String buildSMSContent(String templateKey, String... params) {
        //获取短信模板
        String smsTemplate = PropertyUtils.getStringValue(templateKey, null);

        if (StringUtils.isEmpty(smsTemplate)) {
            if (logger.isErrorEnabled()) {
                logger.error("短信模板:[{}]不存在", templateKey);
            }
            throw new BusinessException(BusinessErrorConstants.CODE_0007, templateKey);
        } else {
            return StringUtils.assemblyStringMessageByParam(smsTemplate, params);
        }
    }


    /**
     * 检查向某个手机号发送短信验证码次数
     *
     * @param mobile 手机号
     * @return
     */
    public static synchronized boolean checkSend2MobileNumOfTimes(String mobile) {
        String cacheKey = VERIFICATION_CODE_MOBILE_CACHE_START_INDEX_KEY + mobile;//缓存索引

        if (!redisTemplate.hasKey(cacheKey)) {
            redisTemplate.opsForValue().set(
                    cacheKey,
                    SEND_VERIFICATION_CODE_TIMES_INIT_VALUE,
                    ONE_DAY_BY_SECONDS,
                    TimeUnit.SECONDS);// 向redis里存入数据和设置缓存时间
        } else {
            // 一天超过oneDayMaxTimes次下周再试
            if (Integer.parseInt(redisTemplate.opsForValue().get(cacheKey)) >= PropertyUtils.getIntValue(CommonConfigPerpertyConstants.SEND_MOBILE_NUM_OF_MAX_TIMES, 20)) {
                redisTemplate.expire(cacheKey, 7 * ONE_DAY_BY_SECONDS, TimeUnit.SECONDS);
                if (logger.isWarnEnabled()) {
                    logger.warn("手机号:[{}]一天请求发送验证码:[{}]次,一周后再试", mobile, redisTemplate.opsForValue().get(cacheKey));
                }
                return false;
            }

            // 一天超过oneDayTimes次让他第二天再试
            if (Integer.parseInt(redisTemplate.opsForValue().get(cacheKey)) >= PropertyUtils.getIntValue(CommonConfigPerpertyConstants.SEND_MOBILE_NUM_OF_ONEDAY_TIMES, 10)) {
                if (logger.isWarnEnabled()) {
                    logger.warn("手机号:[{}]当天发送验证码超过:[{}]次", mobile, redisTemplate.opsForValue().get(cacheKey));
                }
                return false;
            }

            redisTemplate.opsForValue().increment(cacheKey, 1);
        }
        return true;
    }

    /**
     * 限制发送短信验证IP发送次数
     *
     * @param clientIP
     * @return
     */
    public static synchronized boolean checkSendIpNumOfTimes(String clientIP) {
        String cacheKey = VERIFICATION_CODE_IP_CACHE_START_INDEX_KEY + clientIP;
        if (!redisTemplate.hasKey(cacheKey)) {
            redisTemplate.opsForValue().set(
                    cacheKey,
                    SEND_IP_TIMES_INIT_VALUE,
                    ONE_DAY_BY_SECONDS,
                    TimeUnit.SECONDS);// 向redis里存入数据和设置缓存时间
        } else {
            // 一天超过oneDayMaxTimes次不正常，禁止他ip时间加大
            if (Integer.parseInt(redisTemplate.opsForValue().get(cacheKey)) >= PropertyUtils.getIntValue(CommonConfigPerpertyConstants.CHECK_SEND_IP_NUM_OF_MAX_TIMES, 20)) {
                redisTemplate.expire(cacheKey, 7 * ONE_DAY_BY_SECONDS, TimeUnit.SECONDS);
                if (logger.isWarnEnabled()) {
                    logger.warn("IP:[{}]一天请求发送验证码:[{}]次,一周后再试", clientIP, redisTemplate.opsForValue().get(cacheKey));
                }
                return false;
            }

            // 一天超过oneDayTimes次让他第二天再试
            if (Integer.parseInt(redisTemplate.opsForValue().get(cacheKey)) >= PropertyUtils.getIntValue(CommonConfigPerpertyConstants.CHECK_SEND_IP_NUM_OF_ONEDAY_TIMES, 10)) {
                if (logger.isWarnEnabled()) {
                    logger.warn("IP:[{}]当天发送验证码超过:[{}]次", clientIP, redisTemplate.opsForValue().get(cacheKey));
                }
                return false;
            }

            redisTemplate.opsForValue().increment(cacheKey, 1);
        }
        return true;
    }

    /**
     * 获取随机验证码
     *
     * @return
     */
    public static String getRandomVerificationCode() {
        return String.valueOf((new Random()).nextInt(899999) + 100000);
    }

    private static final String MOBILE_REGEX = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";//手机号正则表达式
    private static final int MOBILE_LENGTH = 11;//手机号长度

    /**
     * 校验手机号
     *
     * @param moile
     * @return
     */
    public static void checkMobile(String moile) {
        if (StringUtils.isEmpty(moile)) {
            //非空校验
            throw new BusinessException(BusinessErrorConstants.MOBILE_0001);
        } else {
            //长度校验
            if (moile.length() != MOBILE_LENGTH) {
                throw new BusinessException(BusinessErrorConstants.MOBILE_0002,
                        String.valueOf(MOBILE_LENGTH),
                        String.valueOf(moile.length()));
            } else {
                //正则校验
                if (!Pattern.matches(MOBILE_REGEX, moile)) {
                    throw new BusinessException(BusinessErrorConstants.MOBILE_0003, moile);
                }
            }
        }
    }

    /**
     * 记录验证码到缓存
     *
     * @param mobile
     * @param verificationCode
     * @param templateKey
     */
    public static void saveVerificationCode2Cache(String mobile,
                                                  String verificationCode,
                                                  String templateKey) {
        mobileVerificationCache.put(VERIFICATION_CODESTART_INDEX + mobile + templateKey, verificationCode,
                PropertyUtils.getLongValue(CommonConfigPerpertyConstants.VERIFICATION_CODE_CACHE_TIMEOUT, 60));
        if (logger.isDebugEnabled()) {
            logger.debug("记录短信验证码:[{}]到缓存成功,超时时间为:[{}]秒",
                    mobileVerificationCache.get(VERIFICATION_CODESTART_INDEX + mobile + templateKey),
                    PropertyUtils.getLongValue(CommonConfigPerpertyConstants.VERIFICATION_CODE_CACHE_TIMEOUT, 60));
        }
    }

    /**
     * 用户注册验证码校验
     *
     * @param mobile
     * @param verificationCode
     * @param templateKey
     */
    public static void checkVerificationCode2Cache(String mobile,
                                                   String verificationCode,
                                                   String templateKey) {
        if (StringUtils.isEmpty(verificationCode)) {
            //验证码非空校验
            throw new BusinessException(BusinessErrorConstants.CODE_0006);
        } else {
            String cacheVerificationCode = mobileVerificationCache.get(VERIFICATION_CODESTART_INDEX + mobile + templateKey);
            if (StringUtils.isEmpty(cacheVerificationCode)) {
                //超时
                throw new BusinessException(BusinessErrorConstants.CODE_0004, verificationCode);
            } else {
                //匹配
                if (!cacheVerificationCode.equals(verificationCode)) {
                    throw new BusinessException(BusinessErrorConstants.CODE_0005, verificationCode);
                } else {
                    //一个验证码只允许使用一次
                    mobileVerificationCache.del(VERIFICATION_CODESTART_INDEX + mobile + templateKey);
                }
            }
        }
    }
}
