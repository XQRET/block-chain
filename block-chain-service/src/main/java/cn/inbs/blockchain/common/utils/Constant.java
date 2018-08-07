package cn.inbs.blockchain.common.utils;

/**
 * 系统原则上所有常量都在这里定义
* @author tanglei
* @date 2017-10-30
 */
public class Constant
{
	public static final String UTF_8 = "utf-8";
	public static final int PAGE_SIZE = 10; // 每页记录条数
	public static final int PAGE_INDEX = 0;

	/* **************************************系统配置项Id定义区**********************************************/
	/**验证加密的DES key*/
	public static final String VERIFYCODE_DES_KEY = "verifycode_des_key";
	/**cookie中存在的验证码的Key*/
	public static final String COOKIE_VERIFYCODE_NAME = "verifycode";

	public static final String CDO_strServiceName = "strServiceName";
	public static final String CDO_strTransName = "strTransName";
	public static final String CDO_Request = "$$CDORequest$$";

	/* ************************************** Cookie定义区	**********************************************/
	public static final String CLEAR_COOKIE_VALUE = "";
	public static final int CLEAR_COOKIE_MAXAGE = 0;

	public static final String Key_cdoUser = "cdoLoginer";
	public static final String Key_cdoBossUser = "cdoBossLoginer";

	public static final int COOKIE_MAXAGE = 60 * 60 * 24; // cookie生命周期,以秒为单位。参数为负数代表关闭浏览器时清除cookie,参数为0时代表删除cookie,参数为正数时代表cookie存在多少秒。
	public static final String COOKIE_PATH = "/";			// 设置路径,只有设置该cookie路径及其子路径可以访问
	public static final String COOKIE_NAME_EMPLOYEEID = "employeeId";
	public static final String COOKIE_NAME_TOKEN = "token";
	public static final String COOKIE_NAME_LASTVISITTIME = "lastVisitTime";
	public static final String COOKIE_NAME_BOSSTOKEN = "bossToken";
	public static final String COOKIE_NAME_APPTOKEN = "appToken";
	public static final String COOKIE_DES_KEY = "cookie_xxxx_des_";

	/* **************************************cdoBossLoginer定义**********************************************/
	public static final String Key_dtLastLoginTime = "dtLastLoginTime";

	public static final String Key_Boss_lEmployeeId = "lEmployeeId";
	public static final String Key_Boss_strName = "strName";
	public static final String Key_Boss_strMobile = "strMobile";

	public static final String Key_Boss_nRoleId = "nRoleId";
	public static final String Key_Boss_strRoleName = "strRoleName";
	public static final String Key_Boss_strPermission = "strPermission";

	/* **************************************  redis定义区****************************************************/
	/**存储在Redis里面的随机数，主要是用于boos后台登陆*/
	public static final String REDIS_bossRandom = "bossLogin:strBossRandom:";
	/**boss后台 登陆中redis存活时间  60 秒*/
	public static final int REDIS_nBossExpireTime = 60;
	/**boss后台用于踢人的*/
	public static final String REDIS_bossJsessionid = "bossLogin:jsessionid:";
	/**redis存储的短信验证码次数的时效  24小时*/
	public static final int REDIS_nCodeCountExpireTime = 60 * 60 *24;

	public static final String MSG_VERIFYCODE_ERROR = "图形验证码输入错误！";


}
