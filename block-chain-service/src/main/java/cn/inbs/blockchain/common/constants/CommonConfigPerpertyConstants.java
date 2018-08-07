package cn.inbs.blockchain.common.constants;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.constants
 * @ClassName:
 * @Date: 2018年05月16-16:29
 * @Author: createBy:zhangmingyang
 **/
public class CommonConfigPerpertyConstants {
    public static final String ALIYUN_PHOTO_FILE_PATH_KEY = "aliyun.photo.file.path";

    public static final String ALIYUN_CONTRACT_FILE_PATH_KEY = "aliyun.contract.file.path";

    public static final String ALIYUN_PRODUCT_FILE_PATH = "aliyun.product.file.path";


    /**
     * 秘钥对
     */
    public static final String LOCAL_BLOCK_CHAIN_PRIVATE_KEY = "local.block.chain.private.key";
    public static final String LOCAL_BLOCK_CHAIN_PUBLIC_KEY = "local.block.chain.public.key";

    /**
     * 合约注册消息key
     */
    public static final String CONTRACT_REGISTER_DESCRIPTION_KEY = "contract.register.description";

    /**
     * 合约触发拒绝
     */
    public static final String CONTRACT_REFUSE_DESCRIPTION = "contract.refuse.description";

    /**
     * 合约触发通过
     */
    public static final String CONTRACT_PASS_DESCRIPTION = "contract.pass.description";

    /**
     * 合约开始执行
     */
    public static final String CONTRACT_EXECUTE_DESCRIPTION = "contract.execute.description";

    /**
     * 合约注销
     */
    public static final String CONTRACT_DESTROY_DESCRIPTION = "contract.destroy.description";

    /**
     * 合约执行中
     */
    public static final String CONTRACT_EXECUTEING_DESCRIPTION = "contract.executeing.description";

    /**
     * 合约逾期执行
     */
    public static final String CONTRACT_OVERDUE_DESCRIPTION = "contract.overdue.description";

    /**
     * 外部请求禁止ip开关
     */
    public static final String BAN_IP_SWITCH_KEY = "ban.ip.switch";
    public static final String ALLOW_IP_LIST_KEY = "allow.ip.list";

    /**
     * 短信模板
     */
    public static final String SEND_VERIFICATION_CODE_MESSAGE_TEMPLATE_KEY = "sms.purseconsume.mobilecode.userRegister";


    /**
     * 短信验证码超时时间
     */
    public static final String VERIFICATION_CODE_CACHE_TIMEOUT = "verification.code.cache.timeout";

    /**
     * 发短信配置参数
     */
    public static final String SMS_253_SWITCH_KEY = "sms.253.switch";
    public static final String SMS_253_SENDURL_KEY = "sms.253.sendUrl";
    public static final String SMS_253_ACCOUNT_KEY = "sms.253.account";
    public static final String SMS_253_PASSWORD_KEY = "sms.253.password";

    /**
     * 短信验证码手机次数限制
     */
    public static final String SEND_MOBILE_NUM_OF_MAX_TIMES = "send.mobile.num.of.max.times";
    public static final String SEND_MOBILE_NUM_OF_ONEDAY_TIMES = "send.mobile.num.of.oneday.times";


    /**
     * 短信验证码IP次数限制
     */
    public static final String CHECK_SEND_IP_NUM_OF_MAX_TIMES = "check.send.ip.num.of.max.times";
    public static final String CHECK_SEND_IP_NUM_OF_ONEDAY_TIMES = "check.send.ip.num.of.oneday.times";

    /**
     * 定时任务线程数配置
     */
    public static final String SCHEDULE_JOB_THREAD_POOL_SIZE = "schedule.job.thread.pool.size";

}
