package cn.inbs.blockchain.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.PropertiesExt;
import com.cdoframework.cdolib.base.UUidGenerator;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.security.DES;

public class Utility
{

	private static Logger logger = Logger.getLogger(Utility.class);
	/**
	 * 获取分页页数
	 * @param nRecordCount
	 * @param nPageSize
	 * @return
	 */
	public static int getPageCount(int nRecordCount, int nPageSize)
	{
		if(nPageSize == 0)
			return 0;
		if(nRecordCount == 0)
			return 1;
		if(nRecordCount%nPageSize==0)
			 return nRecordCount/nPageSize;
		return nRecordCount/nPageSize+1;
	}

	/**
	 *
	 * @param strText
	 * @return
	 */
	public static String getLikeText(String strText)
	{
		if(strText==null || strText.length()==0)
		{
			return "%";
		}

		StringBuilder strbOutput=new StringBuilder();
		strbOutput.append('%');
		int nLength=strText.length();
		for(int i=0;i<nLength;i++)
		{
			char ch=strText.charAt(i);
			if(ch==' ')
			{
				if(strbOutput.charAt(strbOutput.length()-1)=='%')
				{
					continue;
				}
				strbOutput.append('%');
			}
			else if(ch=='%')
			{
				strbOutput.append("%%");
			}
			else
			{
				strbOutput.append(ch);
			}
		}
		if(strbOutput.charAt(strbOutput.length()-1)!='%')
		{
			strbOutput.append('%');
		}

		return strbOutput.toString();
	}

	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Real-IP");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取数组中特定下标的当前值
	 * @param naItem
	 * @param nIndex
	 * @return
	 */
	public static int getItem(int[] naItem,int nIndex)
	{
		if(nIndex>=naItem.length)
		{
			return Integer.MIN_VALUE;
		}
		return naItem[nIndex];
	}

	/**
	 * 获取数组中特定下标的当前值
	 * @param naItem
	 * @param nIndex
	 * @return
	 */
	public static String getItem(String[] naItem,int nIndex)
	{
		if(nIndex>=naItem.length)
		{
			return null;
		}
		return naItem[nIndex];
	}

	/**
	 * 获取CDO数组中特定下标的当前值
	 * @param aItem
	 * @param nIndex
	 * @return
	 */
	public static CDO getItem(CDO[] aItem,int nIndex)
	{
		if(nIndex>=aItem.length)
		{
			return null;
		}
		return aItem[nIndex];
	}

	/**
	 * 字符串分隔为数组
	 * @param src
	 * @param split
	 * @return
	 */
	public static String[] sToarr(String src,String split)
	{
		if(src==null)
		{
			return null;
		}
		return src.split("\\"+split);
	}

	/**
	 * 把Objec类型的数据，转为String类型
	 * @param n
	 * @return
	 */
	public static String getLongToString(Object n)
	{
		return String.valueOf(n);
	}

	/**
	 * 把String类型的数据，转为long类型
	 * @param n
	 * @return
	 */
	public static long getStringToLong(String n)
	{
		if(n==null||n.equals(""))
		{
			return Long.MIN_VALUE;
		}
		return Long.parseLong(n);
	}

	/**
	 * 把String类型的数据，转为int类型
	 * @param n
	 * @return
	 */
	public static int getStringToInt(String n)
	{
		if(n==null||n.equals(""))
		{
			return Integer.MIN_VALUE;
		}
		return Integer.parseInt(n);
	}

	/**
	 * 获取数组中的第1个元素，如果数组长度为0，返回null
	 * @param cdos
	 * @return
	 */
	public static CDO getFirstCDO(CDO[] cdos)
	{
		if(cdos!=null&&cdos.length!=0)
		{
			return cdos[0];
		}
		return null;
	}
	/**
	 * 整型数字是否在intArr中
	 * @param n1
	 * @param intArr
	 * @return
	 */
	public static boolean bExists(int n1,int[] intArr)
	{
		for(int n2:intArr)
		{
			if(n2==n1)
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 将URL以utf-8方式编码
	 * @param s
	 * @return
	 */
	public static String getURLEncoder(String s)
	{
		try {
			return URLEncoder.encode(s,Constant.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * 获取前几个字符，如果源字符串的长度超过num，显示num-2个字符连接……,否则显示源字符串
	 * @param src
	 * @param num
	 * @return
	 */
	public static String getStringNum(String src,int num)
	{
		if(src==null)
			return "";
		if(src.length()>num&&num>=3)
			return src.substring(0, num-1)+"...";
		return src;
	}

	/**
	 * 相个整除
	 * @param lFirst
	 * @param lSecond
	 * @return
	 */
	public static long division(long lFirst,long lSecond)
	{
		if(lSecond==0)
		{
			return 0;
		}
		return lFirst/lSecond;
	}

	/**
	 * 相个整除
	 * @param lFirst
	 * @param lSecond
	 * @return
	 */
	public static int division(int lFirst,int lSecond)
	{
		if(lSecond==0)
		{
			return 0;
		}
		return lFirst/lSecond;
	}

	/**
	 * 获取数组长度
	 * @param cdos
	 * @return
	 */
	public static int getCDOArrayLength(Object[] cdos)
	{
		if(cdos==null)
			return 0;
		return cdos.length;
	}

	/**
	 * 字符串比较，使用compareTo比较，返回值与字符串的返回值一致
	 * @param src1
	 * @param src2
	 * @return 0：相等；负数：src1<src2；正数：src1>src2
	 */
	public static int compareString(String src1,String src2)
	{
		if(src1==null||src2==null)
		{
			return 0;
		}

		return src1.compareTo(src2);
	}

	//获取FullDomain，即host
	public static String getDomainByRequest(HttpServletRequest request)
	{
		String domain=request.getHeader("host");
		return domain;
	}

	/**
	 * 根据key值获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request,String name)
	{
		if(name==null)
		{
			return null;
		}
		Cookie cookies[]=request.getCookies();
		if(cookies==null)
		{
			return null;
		}
		int length=cookies.length;
		for(int i=0;i<length;i++)
		{
			if(name.equals(cookies[i].getName()))
			{
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 验证码校验
	 * @param application
	 * @param request
	 * @param code
	 * @return
	 */
	public static boolean isValidCode(ServletContext application, HttpServletRequest request, String code)
	{
		if(code==null||code.trim().length()==0)
		{
			return false;
		}
		DES des = getDES(application, request.getSession(),Constant.VERIFYCODE_DES_KEY);
		Cookie cVerifycode=getCookie(request,Constant.COOKIE_VERIFYCODE_NAME);
		if(cVerifycode!=null)
		{
			String strCode = cVerifycode.getValue();
			strCode = DESDecrypt(strCode,des);
			if(strCode.equalsIgnoreCase(code))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 退出登录，清除cookie
	 * @param response
	 * @param request
	 * @param strCookieName
	 * @param strDomain
	 */
	public static void clearCookie(HttpServletResponse response,HttpServletRequest request, String strCookieName,String strDomain)
	{
		Cookie cToken=new Cookie(strCookieName, null);

		cToken.setDomain(strDomain);
		cToken.setPath("/");
		cToken.setMaxAge(0);
		response.addCookie(cToken);
	}

	/**
	 * 退出登录，清除cookie
	 * @param response
	 * @param request
	 * @param strCookieName
	 * @param strDomain
	 */
	public static void clearCookie(HttpServletResponse response,HttpServletRequest request, String strCookieName)
	{
		Cookie cToken=new Cookie(strCookieName, null);
		cToken.setPath("/");
		cToken.setMaxAge(0);
		response.addCookie(cToken);
	}
	/**
	 * des加密
	 * @param text
	 * @param des
	 * @return
	 */
	public static String DESEncrypt(String text,DES des)
	{
		byte[] ret = null;
		try {
			ret = des.encrypt(text.getBytes(Constant.UTF_8));
			return String.valueOf(Hex.encodeHex(ret));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * des解密
	 * @param text
	 * @param des
	 * @return
	 */
	public static String DESDecrypt(String text,DES des)
	{
		String ret = "";
		try {
			ret = new String(des.decrypt(Hex.decodeHex(text.toCharArray())),Constant.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获取des key
	 * @param application
	 * @param session
	 * @param strDesKey
	 * @return
	 */
	public static DES getDES(ServletContext application,HttpSession session,String strDesKey)
	{
		if(strDesKey==null)
		{
			return null;
		}
		String sessionKey = "DES_key" + strDesKey;
		String appHashTableKey = "DES_HASHTABLE";

		//从session中取des
		DES des=(DES)session.getAttribute(sessionKey);
		if(des==null)
		{
			//从application中取hashTable
			Hashtable hashTable =(Hashtable)application.getAttribute(appHashTableKey);
			if(hashTable==null)
			{
				hashTable = new Hashtable();
				application.setAttribute(appHashTableKey, hashTable);
			}

			des = (DES)hashTable.get(strDesKey);
			if(des==null)
			{
				des = new DES();
				try {
					des.setKey(strDesKey.getBytes(Constant.UTF_8));
				} catch (Exception e) {
					e.printStackTrace();
				}
				hashTable.put(strDesKey, des);
			}

			session.setAttribute(sessionKey, des);
		}
		return des;
	}

	/**
	 * 计算ModeCode
	 * @param strLoginId
	 * @return
	 */
	public static int getModeCode(String strLoginId)
	{
		return Math.abs(strLoginId.trim().toLowerCase().hashCode()%500);
	}

	/**
	 * 取用户名小写
	 * @param strLoginId
	 * @return
	 */
	public static String getLowerCase(String strLoginId)
	{
		return strLoginId.trim().toLowerCase();
	}

	/**
	 * 更新cookie的lasttime：最后一次访问服务器的时间
	 * @param request
	 * @param response
	 * @param strDomain
	 * @return
	 */
	public static boolean updateVisitLasttime(HttpServletRequest request, HttpServletResponse response,String strDomain)
	{
		Cookie cookie = getCookie(request,Constant.COOKIE_NAME_LASTVISITTIME);
		if(cookie!=null)
		{
			String strValue = cookie.getValue();
			if(System.currentTimeMillis() - Long.parseLong(strValue) > Constant.COOKIE_MAXAGE * 1000 )
			{//超时时间过
				clearAllCookie(request,response,strDomain);
				return false;
			}
			cookie.setValue(String.valueOf(DateTime.now().getTime()));
		}
		else
		{
			cookie=new Cookie(Constant.COOKIE_NAME_LASTVISITTIME,String.valueOf(DateTime.now().getTime()));
		}
		cookie.setDomain(strDomain);
		cookie.setPath(Constant.COOKIE_PATH);
		response.addCookie(cookie);
		return true;
	}

	public static void clearAllCookie(HttpServletRequest request, HttpServletResponse response,String strDomain){
		clearCookie(response,request,Constant.COOKIE_NAME_EMPLOYEEID,strDomain);
		clearCookie(response,request,Constant.COOKIE_NAME_BOSSTOKEN,strDomain);
		clearCookie(response,request,Constant.COOKIE_NAME_TOKEN,strDomain);
		clearCookie(response,request,Constant.COOKIE_NAME_LASTVISITTIME,strDomain);
	}

	//cdo[]添加cdo
	public static CDO[] add(CDO[] cdos, CDO cdo)
	{
		int nLength=cdos.length;
		CDO[] cdosList=new CDO[nLength+1];
		System.arraycopy(cdos, 0, cdosList, 0, nLength);
		cdosList[nLength]=cdo;
		return cdosList;
	}

	public static boolean isExist(int[] arr,int n)
	{
		int nCount=arr.length;
		for(int i=0;i<nCount;i++)
		{
			if(n==arr[i])
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否有移动终端
	 * @param request
	 * @return true：是移动终端；false:不是移动终端
	 */
	public static boolean judgeIsMobile(HttpServletRequest request)
	{
        boolean isMobile = false;
        String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null)
        {
            for (String mobileAgent : mobileAgents)
            {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0)
                {
                    isMobile = true;
                    break;
                }
            }
        }
        return isMobile;
    }

	/**
	 * 取得域名
	 * @param request
	 * @return String
	 */
	public static String getDomain(HttpServletRequest request)
	{
		return request.getServerName();
	}

	/**
	 * 取得需要设置cookie的域
	 * @return String
	 */
	public static String getDomain(String servletPath) {
		String strDomains = "";
		servletPath = servletPath.replaceAll("http://", "");
		strDomains = servletPath.substring(0, servletPath.indexOf("/"));
		return strDomains;
	}

	/**
	 * 获取cookieDomain
	 * @param request
	 * @return
	 */
	public static String getCookieDomain(HttpServletRequest request)
	{
		String strDomain=request.getServerName();
		String strCookie=strDomain.substring(strDomain.indexOf(".")+1);
		return strCookie;
	}

	public static String getCommonCookieDomain(HttpServletRequest request){
		String strDomain=request.getServerName();
		if("localhost".equals(strDomain)){
			return strDomain;
		}

		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
	    Matcher matcher = pattern.matcher(strDomain);
	    if(matcher.matches()){//ip地址
	    	return strDomain;
	    }else{ //域名,只用一级域名
	    	return strDomain.substring(strDomain.indexOf(".")+1);
	    }
	}

	/**
	 * 清除cookie
	 * @param request
	 * @param response
	 * @param strCookieDomain
	 */
	public static void clearAllCookies(HttpServletRequest request,HttpServletResponse response, String strCookieDomain) {
		Utility.clearCookie(response,request, Constant.COOKIE_NAME_EMPLOYEEID, strCookieDomain);
		Utility.clearCookie(response,request, Constant.COOKIE_NAME_TOKEN, strCookieDomain);
		Utility.clearCookie(response,request, Constant.COOKIE_NAME_LASTVISITTIME, strCookieDomain);
		Utility.clearCookie(response,request, Constant.COOKIE_NAME_BOSSTOKEN, strCookieDomain);
	}

	/**
	 * 接收客户端的输入流转化成字符串
	 * @param request
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String reciveStreamToString(HttpServletRequest request)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			ServletInputStream sis = request.getInputStream();
			byte[] b = new byte[1024];
			for(int n;(n=sis.read(b))>0;){
				sb.append(new String(b,0,n,"utf-8"));
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			return sb.toString();
		}
	}
	/**
	 * 判断是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str)
	{
		Pattern pattern = Pattern.compile("^[-//+]?[0-9]+$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * 判断是否为浮点数
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str)
	{
		Pattern pattern = Pattern.compile("^[-//+]?[0-9]+\\.{0,1}[0-9]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 把json对象以流的方式输出
	 * @param response
	 * @param json对象
	 */
	public static void writeResponseStream(HttpServletResponse response, JSONObject json)
	{
		response.setContentType("text/json; charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(json.toString());
			writer.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e1) {
			logger.error(e1.getMessage(),e1);
		}
	}

	/**
	 * 产生概率数，是否被命中
	 * @param p 概率，取值范为：0<= p <=100
	 * @return true:命中,false:未命中
	 */
	public static boolean bHit(double p)
	{
		double random=Math.random()*100;
		if(p<random)
		{
			return false;
		}
		return true;
	}

	/**
	 *
	 * Description: 三方回调金额换算<br/>
	 *
	 * String 金额转换成 long 型<br/>
	 * 如: getAmount("106.04") == 10604L
	 *
	 * @param strAmount
	 * @return	lMoney
	 *
	 * @Author JungLone_Cui
	 * @Create Date: 2014-12-30 下午6:53:30
	 */
	public static long getAmount(String strAmount)
	{
		if(strAmount == null){
			logger.error("invoke getAmount error strAmount is null");
			return 0;
		}
		try {
			BigDecimal bigAmount=new BigDecimal(strAmount);
			BigDecimal bigFactor=new BigDecimal("100");
			BigDecimal bigResult=bigAmount.multiply(bigFactor).setScale(2);
			return bigResult.longValue();
		} catch (NumberFormatException e) {
			logger.error("invoke getAmount error strAmount is invalidate strAmount="+strAmount);
			return 0;
		}
	}


	/**
	 * 从reuqest中取出cdoLoginer,设置到cdoRequest对象中
	 * @param request
	 * @param cdoRequest
	 */
	public static void setLoginer(HttpServletRequest request,CDO cdoRequest)
	{
		CDO cdoLoginer=(CDO)request.getAttribute(Constant.Key_cdoBossUser);
		if(cdoLoginer!=null)
		{
			cdoRequest.setCDOValue(Constant.Key_cdoBossUser,cdoLoginer);
		}
	}

	public static String getRandomCode6()
	{
		Random r = new Random();
		String randomCode =  String.valueOf(r.nextInt(899999)+100000);//生成6位随机数
		return randomCode;
	}

	public static String getRandomCode4()
	{
		Random r = new Random();
		String randomCode =  String.valueOf(r.nextInt(8999)+1000);//生成4位随机数
		return randomCode;
	}


	public static CDO a2e(CDO cdoRequest,PropertiesExt proper)
	{
		CDO cdoReq = new CDO();
		String[] fields = cdoRequest.getFieldIds();
		for(String s:fields)
		{
			String toFieldName = proper.getParameter(s,"");
			//没有该字段，忽略
			if("".equals(toFieldName))continue;
			//该字段的传入值
			String valueFrom = cdoRequest.getStringValue(s);
			//尝试获取该传入职的转换值，没有，则使用原值
			String valueTo = proper.getParameter(s+"."+valueFrom, "");
			if("".equals(valueTo)==false)
			{
				valueFrom=valueTo.split("\\.")[1];//使用转换值
			}
			else
			{
				if(toFieldName.indexOf("+")>0)
				{
					String[] ss = toFieldName.split("\\+");
					toFieldName = ss[0];
					String oper = ss[1];
					valueFrom =String.valueOf(Long.valueOf(valueFrom)+Integer.valueOf(oper));
				}
				else if(toFieldName.indexOf("-")>0)
				{
					String[] ss = toFieldName.split("\\-");
					toFieldName = ss[0];
					String oper = ss[1];
					valueFrom =String.valueOf(Long.valueOf(valueFrom)-Integer.valueOf(oper));
				}
				else if(toFieldName.indexOf("*")>0)
				{
					String[] ss = toFieldName.split("\\*");
					toFieldName = ss[0];
					String oper = ss[1];
					valueFrom =String.valueOf(Long.valueOf(valueFrom)*Integer.valueOf(oper));
				}
				else if(toFieldName.indexOf("/")>0)
				{
					String[] ss = toFieldName.split("/");
					toFieldName = ss[0];
					String oper = ss[1];
					valueFrom =String.valueOf(Long.valueOf(valueFrom)/Integer.valueOf(oper));
				}
			}

			cdoReq.setStringValue(toFieldName,valueFrom);//使用原值
		}
		return cdoReq;
	}

	public static CDO[] setDataPermissionArray(String strDataPermission){
		CDO[] resultArray = null;
		if (!"".equals(strDataPermission) && !"none".equals(strDataPermission)) {
			String[] dataPerArray = strDataPermission.split("\\|");
			resultArray = new CDO[dataPerArray.length];
			String[] deptInfo = new String[2];
			for (int i = 0; i < dataPerArray.length; i++) {
				CDO dataPer = new CDO();
				deptInfo = dataPerArray[i].split("@");
				dataPer.setStringValue("strDeptCode", deptInfo[0]);
				dataPer.setStringValue("nCurrentLevel", deptInfo[1]);
				resultArray[i] = dataPer;
			}
		}else{
			resultArray = new CDO[0];
		}
		return resultArray;
	}


	/**
	 * 生成唯一的订单号
	 * @return
	 */
	public static String generateOrderCode()
	{
		int code=Math.abs(UUidGenerator.genenator().hashCode());
		String strOrderCode=System.nanoTime()+""+code;
		return strOrderCode;
	}

	/**
	 	* Description:获取本机ip号码
	 	* @return
	 	* @Author chenxg
	 	* @param @return
	 	* @return String
	 	* @Create 2015-8-1 上午11:07:00
	 */
	@SuppressWarnings("finally")
	public static String getSelfIp()
	{
		String ip = "";
		InetAddress addr;
		try{
			addr=InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//获得本机IP
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}finally{
			return ip;
		}
	}

}
