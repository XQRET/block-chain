package cn.inbs.blockchain.controller.boss;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.permission.schema.PermissionItem;
import com.cdoframework.cdolib.permission.schema.PermissionList;

import cn.inbs.blockchain.common.utils.Utility;
import cn.inbs.blockchain.web.BusinessService;

public class RoleController extends BaseController
{
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(RoleController.class);
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//查询所有角色列表 带分页
	public CDO findRoleList(HttpServletRequest httpReq){
		String strPageIndex = httpReq.getParameter("pageindex");
		String strPageSize = httpReq.getParameter("strPageSize");

		int nPageIndex = StringUtils.isNotBlank(strPageIndex) ? Integer.parseInt(strPageIndex) : 0;
		int nPageSize = StringUtils.isNotBlank(strPageSize) ? Integer.parseInt(strPageSize) : 10 ;

		CDO cdoRequest = new CDO();
		CDO cdoResponse = new CDO();
		cdoRequest.setStringValue("strServiceName","RoleService");
		cdoRequest.setStringValue("strTransName","findRoleList");
		cdoRequest.setIntegerValue("nPageCount", nPageIndex * nPageSize);
		cdoRequest.setIntegerValue("nPageSize", nPageSize);
		cdoRequest.setStringValue("isDisplay", "0");

		if(StringUtils.isNotBlank(httpReq.getParameter("strRoleName"))){
			cdoRequest.setStringValue("strName", Utility.getLikeText(httpReq.getParameter("strRoleName").toString()));
		}
		Return ret = super.handleTrans(httpReq,null,cdoRequest,cdoResponse);
		if(ret == null){
			logger.error("RoleService.getRoleList The request failed");
			return null;
		}
		if(ret.getCode()!=0)
		{
			return null;
		}
		int nRecordCount = cdoResponse.getIntegerValue("nRecordCount");
		cdoResponse.setIntegerValue("nPageCount", Utility.getPageCount(nRecordCount, nPageSize));
		return cdoResponse;
	}

	/**
	 * 根据角色ID获取角色
	* @param strRoleId
	* @return
	* @author tanglei
	* @date 2017-10-26
	 */
	public CDO getRole(String strRoleId)
	{
		if(StringUtils.isEmpty(strRoleId)){
			return null;
		}
		CDO cdoRequest=new CDO();
		cdoRequest.setStringValue("strServiceName","RoleService");
		cdoRequest.setStringValue("strTransName","getRoleById");
		cdoRequest.setIntegerValue("nId",Integer.parseInt(strRoleId));
		CDO cdoResponse = new CDO();
		Return ret=BusinessService.getInstance().handleTrans(cdoRequest,cdoResponse);
		if(ret.getCode()!=0)
		{
			return null;
		}
		return cdoResponse.getCDOValue("cdoRole");
	}

	/**
	 * permission.xml节点转CDO
	* @param pi
	* @return
	* @author tanglei
	* @date 2017-10-26
	 */
	private CDO permissionItemToCDO(PermissionItem pi)
	{
		CDO cdoItem=new CDO();
		cdoItem.setStringValue("strId",pi.getPermission());
		cdoItem.setStringValue("strName",pi.getName());

		PermissionItem[] pis=pi.getPermissionItem();
		if(pis.length==0)
		{
			return cdoItem;
		}

		//有子节点
		CDO[] cdosItemList=new CDO[pis.length];
		for(int i=0;i<pis.length;i++)
		{
			cdosItemList[i]=permissionItemToCDO(pis[i]);
		}
		cdoItem.setCDOArrayValue("cdosItemList",cdosItemList);

		return cdoItem;
	}

	/**
	 * permission.xml转CDO
	* @param pl
	* @return
	* @author tanglei
	* @date 2017-10-26
	 */
	private CDO permissionListToCDO(PermissionList pl)
	{
		CDO[] cdosItemList=new CDO[pl.getPermissionItemCount()];
		for(int i=0;i<cdosItemList.length;i++)
		{
			cdosItemList[i]=this.permissionItemToCDO(pl.getPermissionItem(i));
		}

		CDO cdoItem=new CDO();
		cdoItem.setCDOArrayValue("cdosItemList",cdosItemList);
		return cdoItem;
	}

	/**
	 * 读取权限列表
	* @return
	* @author tanglei
	* @date 2017-10-26
	 */
	public String getPermissionJSON()
	{
		String strPermission= com.cdoframework.cdolib.base.Utility.readTextResource("Permission.xml","utf-8",null);
		PermissionList pl = PermissionList.fromXML(strPermission);
		CDO cdoPermission = permissionListToCDO(pl);
		String strJSON = cdoPermission.toJSON();
		return strJSON;
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public RoleController()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

}
