package cn.inbs.blockchain.controller.boss;

import javax.servlet.http.HttpServletRequest;

public class PageController implements java.io.Serializable
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=1L;

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public String getText()
	{
		return ";asdlfja;djfa;dfkj";
	}

	public int n(int nPageIndex)
	{
		return  nPageIndex;
	}
	public int getPageIndex(HttpServletRequest request)
	{
		String strPageIndex=request.getParameter("pageindex");
		if(strPageIndex==null)
		{
			return 0;
		}
		else
		{
			try
			{
				return Integer.parseInt(strPageIndex);
			}
			catch(Exception e)
			{
				return 0;
			}
		}
	}
	public String getPageURL(HttpServletRequest request,int nPageIndex)
	{
		String strParater=request.getQueryString();
		if(strParater==null){
			strParater="";
		}
		int loc=strParater.indexOf("pageindex");
		if(loc!=-1)
		{
			strParater=strParater.substring(0,loc);
			strParater=strParater+"pageindex="+nPageIndex;
		}
		else
		{
			if("".equals(strParater)){
				strParater=strParater+"pageindex="+nPageIndex;
			} else {
				strParater=strParater+"&pageindex="+nPageIndex;
			}
		}
		StringBuilder strbURL=new StringBuilder();
		if(strbURL.length()>0)
		{
			strbURL.append('&');
		}
		strbURL.insert(0,request.getRequestURL().toString()+'?');
		strbURL.append(strParater);
		return strbURL.toString();
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public PageController()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

}
