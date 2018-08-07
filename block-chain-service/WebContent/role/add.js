var httpClient=new HttpClient("handleTrans.cdo");

var addRole = function()
{
	var strRoleName = _public.getVal("roleName");
	var strRoleCode = _public.getVal("roleCode");
	
	if(strRoleName.length==0)
	{
        layer.alert("角色名称不正确");
		return;
	}
	var cdoRequest=new CDO();
	var cdoResponse=new CDO();

    var arrSelectedNodeIds=new Array();
	var arrSelectedNodeTitles=new Array();
	var arrSelectedNodePaths=new Array();
	
	mtree.getSelectedNode(arrSelectedNodeIds,arrSelectedNodeTitles,arrSelectedNodePaths);
	var rolePermission = "";
	for(var i=0;i<arrSelectedNodeIds.length;i++){
	     if (i >0 && i < arrSelectedNodeIds.length) {
                         rolePermission += "|";
          } 
	     rolePermission+=arrSelectedNodeIds[i];
	}
	cdoRequest.setStringValue("strServiceName","RoleService");
	cdoRequest.setStringValue("strTransName","addRole");
	cdoRequest.setStringValue("strName",strRoleName);
	cdoRequest.setStringValue("strRoleCode",strRoleCode);
	cdoRequest.setStringValue("strPermission",rolePermission);
	var ret=httpClient.handleTrans(cdoRequest,cdoResponse);
	if(ret.nCode!=0)
	{
        layer.alert("操作失败："+ret.strText);
	}
	else
	{
		window.location.replace("list.htm");
	}
}

/**
 * 验证格式
 * @param num
 * @returns {Boolean}
 */
function checkRegisterInfo(num){
	var text;
	switch(num){
	   //1. 校验角色名称
	case 0:
		text = document.getElementById("roleName").value.trim();
		//验证是否为空  
         if (text == "") {  
             return false;
         }   
        //验证格式  
       else if (text.search(/^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,20}$/g) == -1) {
             layer.alert("角色名称格式错误");
        	 return false;
        }
        break;
	case 1:
		text = document.getElementById("roleCode").value.trim();
		if(text == ""){
			return false;
		}
		else if (text.search(/^[0-9a-zA-Z]+$/g) == -1) {
            layer.alert("角色名称格式错误");
       		return false;
        } 
		break;
	}
}