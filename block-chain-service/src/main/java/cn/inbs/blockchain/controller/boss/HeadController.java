package cn.inbs.blockchain.controller.boss;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.navigator.Navigator;
import com.cdoframework.cdolib.navigator.schema.Node;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.web.BusinessService;

public class HeadController
{
	private Navigator navigator;

	public HeadController()
	{
		this.navigator = new Navigator();
		String strXML=Utility.readTextResource("Navigator.xml","utf-8",null);
		this.navigator.init(strXML);
	}

	public String getNavigatorJSON(HttpServletRequest request)
	{
		//获取登录者角色信息
		int nRoleId=(Integer)request.getAttribute("nRoleId");
		CDO cdoRequest = CDO.newRequest("RoleService", "getRole");
		cdoRequest.setIntegerValue("nId",nRoleId);

		CDO cdoResponse = new CDO();
		Return ret = BusinessService.getInstance().handleTrans(cdoRequest, cdoResponse);
		if(ret.getCode()!=0)
		{
			return null;
		}
		CDO cdoRole=cdoResponse.getCDOValue("cdoRole");
		String strPermission=cdoRole.getStringValue("strPermission");
		String[] strsPermissionItem=Utility.splitString(strPermission,'|');
		HashSet<String> hsPermissionItem=new HashSet<String>();
		for(int i=0;i<strsPermissionItem.length;i++)
		{
			hsPermissionItem.add(strsPermissionItem[i]);
		}
		return this.navigator.toJSON(hsPermissionItem);
	}

	public com.cdoframework.cdolib.navigator.schema.Navigator getNavigatorMenu(HttpServletRequest request)
	{
		//获取登录者角色信息
		CDO cdoLoginer = (CDO)request.getAttribute(Constant.Key_cdoBossUser);
		String strPermission = cdoLoginer.getStringValue("strPermission");
		String[] strsPermissionItem=Utility.splitString(strPermission,'|');
		HashSet<String> hsPermissionItem=new HashSet<String>();
		for(int i=0;i<strsPermissionItem.length;i++)
		{
			hsPermissionItem.add(strsPermissionItem[i]);
		}
		com.cdoframework.cdolib.navigator.schema.Navigator tempNavigator = this.navigator.getHasPermissionNavigator(hsPermissionItem);
		Node[] nodes = tempNavigator.getNode();
		for(int j=0;j<nodes.length;j++){
			Node[] subNodes = nodes[j].getNode();

			//二级URL
			for(int k=0;k<subNodes.length;k++){
				Node node = subNodes[k];
				String targetURL = node.getTargetURL();

				//1.替换#前面的占位
				if(targetURL.indexOf("#")>0){ //替换#前面的
					String[] urlSplit = targetURL.split("#");
					String newPrefix = BusinessService.getInstance().getParameter(urlSplit[0]);
					node.setTargetURL(newPrefix+urlSplit[1]);
				}
			}
		}
		return tempNavigator;
	}

	public String getSelectedId(HttpServletRequest request)
	{
		String strPath=request.getRequestURI();
		return this.navigator.getNodeId(strPath);
	}

}
