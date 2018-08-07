var httpClient=new HttpClient("handleTrans.cdo");

var deleteRole=function(nId)
{
    layer.confirm('确定要删除吗?', {
    	  btn: ['确定','取消'],icon:3 //按钮
    	}, function(index){
    		var cdoRequest=new CDO();
    		var cdoResponse=new CDO();
    		cdoRequest.setStringValue("strServiceName","RoleService");
    		cdoRequest.setStringValue("strTransName","deleteRole");
    		cdoRequest.setIntegerValue("nId",Number(nId));
    		var ret=httpClient.handleTrans(cdoRequest,cdoResponse);
    		if(ret.nCode!=0)
    		{
    			layer.alert("操作失败："+ret.strText,{icon: 2});
    		}
    		else
    		{
    			window.location.reload(true);
    		}
    	}, function(index){
    		layer.close(index);
    	});
	
	
}

//查询
var doSearch = function(){
	var roleName = $("#strRoleName").val();//角色名称
	var url = "list.htm?strRoleName="+roleName;
    url = encodeURI(url).replace(/#/g, "%23");
    window.location.href = url;
}

