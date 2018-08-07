package cn.inbs.blockchain.controller.boss;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.data.cdo.CDO;

import cn.inbs.blockchain.common.utils.Utility;
import cn.inbs.blockchain.web.BusinessService;

public class UtilityController
{

	public static Logger logger = Logger.getLogger(UtilityController.class);
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------


	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//获取数组中特定下标的当前值
	public int getItem(int[] naItem,int nIndex)
	{
		return Utility.getItem(naItem, nIndex);
	}
	//获取数组中特定下标的当前值
	public String getItem(String[] naItem,int nIndex)
	{
		return Utility.getItem(naItem, nIndex);
	}
	//获取CDO数组中特定下标的当前值
	public CDO getItem(CDO[] aItem,int nIndex)
	{
		return Utility.getItem(aItem, nIndex);
	}

	//字符串分隔为数组
	public String[] sToarr(String src,String split)
	{
		return Utility.sToarr(src, split);
	}
	//把Objec类型的数据，转为String类型
	public String getLongToString(Object n)
	{
		return String.valueOf(n);
	}

	//把String类型的数据，转为long类型
	public long getStringToLong(String n)
	{
		return Utility.getStringToLong(n);
	}
	//把String类型的数据，转为int类型
	public int getStringToInt(String n)
	{
		return Utility.getStringToInt(n);
	}
	//获取数组中的第1个元素，如果数组长度为0，返回null
	public CDO getFirstCDO(CDO[] cdos)
	{
		return getFirstCDO(cdos);
	}
	/**
	 * 整型数字是否在intArr中
	 * @param n1
	 * @param intArr
	 * @return
	 */
	public static boolean bExists(int n1,int[] intArr)
	{
		return Utility.bExists(n1, intArr);
	}
	//将URL以utf-8方式编码
	public String getURLEncoder(String s)
	{
		return Utility.getURLEncoder(s);
	}

	/**
	 * 获取前几个字符，如果源字符串的长度超过num，显示num-2个字符连接……,否则显示源字符串
	 * @param src
	 * @param num
	 * @return
	 */
	public String getStringNum(String src,int num)
	{
		return Utility.getStringNum(src,num);
	}

	/**
	 * 相个整除
	 * @param lFirst
	 * @param lSecond
	 * @return
	 */
	public long division(long lFirst,long lSecond)
	{
		return Utility.division(lFirst, lSecond);
	}

	/**
	 * 相个整除
	 * @param lFirst
	 * @param lSecond
	 * @return
	 */
	public int division(int lFirst,int lSecond)
	{
		return Utility.division(lFirst, lSecond);
	}

	/**;
	 * 获取数组长度
	 * @param cdos
	 * @return
	 */
	public int getCDOArrayLength(Object[] cdos)
	{
		return getCDOArrayLength(cdos);
	}

	/**
	 * 字符串比较，使用compareTo比较，返回值与字符串的返回值一致
	 * @param src1
	 * @param src2
	 * @return 0：相等；负数：src1<src2；正数：src1>src2
	 */
	public int compareString(String src1,String src2)
	{
		return compareString(src1, src2);
	}

	//获取FullDomain，即host
	public String getDomainByRequest(HttpServletRequest request)
	{
		return getDomainByRequest(request);
	}


	// 获取配置文件中的每项信息
	public static String getConfigItem(String name)
	{
		return BusinessService.getInstance().getParameter(name);
	}

	public String getStaticCenterURL()
	{
		return getConfigItem("StaticCenter.URL");
	}

	//
	public String getToolCenterURL()
	{
		return getConfigItem("ToolCenter.URL");
	}

	public String getContectCenterURL()
	{
		return getConfigItem("ContestCenter.URL");
	}

	public static String getFileServerUrl()
	{
		return getConfigItem("FileServer.URL");
	}

	public static String getBossSiteURL()
	{
		return getConfigItem("BossSite.URL");
	}

	public static String getDafyURL()
	{
		return getConfigItem("Dafy.URL");
	}

	/**
	 * 取得密钥
	 *
	 * @return String
	 */
	public static String getSecuritykey()
	{
		return getConfigItem("securitykey");
	}

	/**
	 * 取得登陆设置 cookie 的远程访问地址
	 * @return String
	 */
	public static String[] getArrLoginUrl(String respStrCookies)
	{
		// 需要设置 cookie 的远程访问地址
		String[] arrLoginUrl = getConfigItem("arrLoginUrl").split("\\|");
		// 加入参数后的远程访问地址
		String[] strLoginUrls = new String[arrLoginUrl.length];

		for (int i = 0; i < arrLoginUrl.length; i++) {
			strLoginUrls[i] = arrLoginUrl[i].toString() + "?strCookies=" + respStrCookies;
		}

		return strLoginUrls;
	}

	/**
	 * 取得登出清除 cookie 的远程访问地址
	 * @return String
	 */
	public static String[] getArrLogoutUrl(String reqStrCookies)
	{
		// 需要清除 cookie 的远程访问地址
		String[] arrLogoutUrl = getConfigItem("arrLogoutUrl").split("\\|");
		// 加入参数后的远程访问地址
		String[] strLogoutUrls = new String[arrLogoutUrl.length];

		for (int i = 0; i < arrLogoutUrl.length; i++) {
			strLogoutUrls[i] = arrLogoutUrl[i].toString() + "?strCookies=" + reqStrCookies;
		}

		return strLogoutUrls;
	}

	/**
	 * 转换金额，把元转为分
	 * @param strAmount
	 * @return
	 */
	public static long getAmount(String strAmount)
	{
		return Utility.getAmount(strAmount);
	}

	/**
	 * 多个数加操作
	 * @param arrValue
	 * @return
	 */
	public static long plusLong(long...arrValue)
	{
		long lRetValue=0;
		for(long l:arrValue)
		{
			lRetValue+=l;
		}
		return lRetValue;
	}


	/**
	 * 多个数减法操作，第1个参数为减数，第2个参数为被减数
	 * @param lValue
	 * @param arrValue
	 * @return
	 */
	public static long subtractLong(long lValue,long...arrValue)
	{
		for(long l:arrValue)
		{
			lValue=lValue -l;
		}
		return lValue;
	}

	/**
	 * 计算发生额
	 * @param arrValue
	 * @return
	 */
	public static long calcBalanceIncurred(long...arrValue)
	{
		for(long l:arrValue)
		{
			if(Math.abs(l)>0)
			{
				return Math.abs(l);
			}
		}
		return 0;
	}

	/**
	 * 计算数值
	 * @param lValue
	 * @return
	 */
	public static long mathAbs(long lValue)
	{
		return Math.abs(lValue);
	}

	public static String getImgName(HttpServletRequest request)
	{
		String strPreImgName="";
		String strImg="_tit.png";
		String strPath=request.getRequestURI();
		String[] arr=strPath.split("/");
		if(arr.length>2)
		{
			strPreImgName=arr[1];
		}
		else
		{
			strPreImgName="allfinancial";
		}

		return strPreImgName.concat(strImg);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public UtilityController()
	{
		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

	private static String getCookieByName(HttpServletRequest request,String key){
		String strValue=null;
		Cookie cookie=Utility.getCookie(request,key);
		if(cookie != null)
		{
			String strEncValue=cookie.getValue();
			try
			{
				strValue=URLDecoder.decode(strEncValue,"utf-8");

			}
			catch(UnsupportedEncodingException e)
			{
				logger.warn("URLDecoder.decode exception Cookie key="+key, e);
			}
		}
		return strValue;
	}

}
