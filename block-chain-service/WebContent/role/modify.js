var httpClient=new HttpClient("handleTrans.cdo");
var modifyRole=function()
{
	var strRoleName = _public.getVal("roleName");
	var strRoleCode = _public.getVal("roleCode");
	var nId = _public.getVal("nId");
	if(strRoleName.length==0)
	{
		layer.alert("角色名称不正确");
		return;
	}
	if(!nId){
        layer.alert("角色ID不存在");
		return;
	}
	var cdoRequest=new CDO();
	var cdoResponse=new CDO();
	var arrSelectedNodeIds=new Array();
	var arrSelectedNodeTitles=new Array();
	var arrSelectedNodePaths=new Array();
    tree.getSelectedNode(arrSelectedNodeIds,arrSelectedNodeTitles,arrSelectedNodePaths);
	var rolePermission = "";
	for(var i=0;i<arrSelectedNodeIds.length;i++){
	     if (i >0 && i < arrSelectedNodeIds.length) {
               rolePermission += "|";
          } 
	     rolePermission+=arrSelectedNodeIds[i];
	 }
	cdoRequest.setStringValue("strServiceName","RoleService");
	cdoRequest.setStringValue("strTransName","modifyRole");
	cdoRequest.setIntegerValue("nId",Number(nId));
	cdoRequest.setStringValue("strName",strRoleName);
	cdoRequest.setStringValue("strRoleCode",strRoleCode);
	cdoRequest.setStringValue("strPermission",rolePermission);
	var ret=httpClient.handleTrans(cdoRequest,cdoResponse);
	if(ret!=null){
		if(ret.nCode!=0){
            layer.alert("操作失败："+ret.strText);
		}
		else{
			window.location.replace("list.htm");
		}
	}else{
        layer.alert("执行异常");
	}
}


function checkRegisterInfo(num){
	var text;
	switch(num){
	   //1. 校验角色名称
	case 0:
		text = document.getElementById("roleName").value.trim();
		//验证是否为空  
         if (text == "") {
             layer.alert("角色名称不为空");
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