package cn.inbs.blockchain.controller.boss;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.Utility;
import cn.inbs.blockchain.web.BusinessService;

public class BaseController implements Serializable
{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=-165335617382826979L;

	public Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse)
	{

		return BusinessService.getInstance().handleTrans(cdoRequest,cdoResponse);
	}

	public CDO getLoginer(HttpServletRequest request)
	{
		CDO cdoLoginer=(CDO)request.getAttribute(Constant.Key_cdoBossUser);
		if(cdoLoginer!=null)
		{
			return cdoLoginer;
		}
		return null;
	}

	/**
	 * 获取根目录，如果有项目名，会带上项目名
	 * @param request
	 * @return
	 */
	public String getRootPath(HttpServletRequest request)
	{
		StringBuffer url = request.getRequestURL();
		String rootPath = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).toString();
		return rootPath;
	}

	/**
	 * 获取配置信息Json对象
	 * @param nId
	 * @return
	 */
	public String getJsonCDOConfig(int nId)
	{
		CDO cdoConfig=getCDOConfig(nId);
		if(cdoConfig==null)
		{
			return "";
		}
		return cdoConfig.toJSON().replace("\"", "\\\"");
	}

	/**
	 * 获取配置信息CDO对象
	 * @param nId
	 * @return
	 */
	public CDO getCDOConfig(int nId)
	{
		String strConfig=this.getStringConfig(nId);
		CDO cdoConfig=null;
		if(strConfig!=null)
		{
			cdoConfig=CDO.fromXML(strConfig);
		}

		return cdoConfig;
	}

	/**
	 * 获取配置信息字符串
	 * @param nId
	 * @return
	 */
	public String getStringConfig(int nId)
	{
		CDO cdoRequest=new CDO();
		cdoRequest.setStringValue("strServiceName","ConfigService");
		cdoRequest.setStringValue("strTransName","GetConfig");
		cdoRequest.setIntegerValue("nId",nId);

		CDO cdoResponse=new CDO();
		Return ret=BusinessService.getInstance().handleTrans(cdoRequest,cdoResponse);
		if(ret.getCode()!=0)
		{
			return null;
		}
		if(cdoResponse.exists("strConfig"))
			return cdoResponse.getStringValue("strConfig");
		else
			return null;
	}
	public static String longToString(long lValue,int nDigitCount)
	{
		return com.cdoframework.cdolib.base.Utility.integerToNumberText(lValue,nDigitCount);
	}

	/**
	 * 获取cookieDomain
	 * @param request
	 * @return
	 */
	public String getCookieDomain(HttpServletRequest request)
	{
		return Utility.getCookieDomain(request);
	}
}
