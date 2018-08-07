package cn.inbs.blockchain.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servlet.CDOServlet;

import cn.inbs.blockchain.common.utils.Utility;

public class BusinessServlet extends CDOServlet
{
	private static final long serialVersionUID=1L;
	private Logger logger = Logger.getLogger(BusinessServlet.class);

	public Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse)
	{
		Return ret=null;
		if(cdoRequest.exists("strServiceName")==false||cdoRequest.exists("strTransName")==false)
		{
			logger.error("StrServiceName or strTransName does not exist");
	   		return ret;
		}

		//从reuqest中取出cdoLoginer,设置到cdoRequest对象中
		Utility.setLoginer(request,cdoRequest);

		//设置IP,主要是为了以后的分析
		String strTransName = cdoRequest.getStringValue("strTransName");
		if(cdoRequest.getStringValue("strServiceName").equals("EmployeeService")&&(strTransName.equals("bossBeforeLogin") || strTransName.equals("doBossLogin")))
		{
			StringBuffer sb=new StringBuffer();
			Enumeration e = request.getHeaderNames();
			while (e.hasMoreElements()) {
				String name = (String)e.nextElement();
				String value = request.getHeader(name);
				sb.append(name);
				sb.append("=");
				sb.append(value);
				sb.append(";");
			}
			String strIp=Utility.getIpAddr(request);
			logger.info("登录IP添加的header日志："+sb.toString()+"========IP:"+strIp);
			cdoRequest.setStringValue("strClientIp",strIp);
		}
		ret = BusinessService.getInstance().handleTrans(cdoRequest,cdoResponse);
		if(ret == null || ret.getCode() != 0)
		{
			logger.error("ret is null or ret.getCode() != 0");
			return ret;
		}
		return ret;
	}

	@Override
	public Return checkToDo(HttpServletRequest request,CDO cdoRequest)
	{
		return null;
	}

	@Override
	public void handleEvent(CDO cdoEvent)
	{
	}

	@Override
	protected Return checkTrans(HttpServletRequest request,CDO cdoRequest)
	{
		return Return.OK;
	}

	@Override
	protected void onTransChecked(HttpServletRequest request,CDO cdoRequest,boolean bAllowed)
	{
	}

}
