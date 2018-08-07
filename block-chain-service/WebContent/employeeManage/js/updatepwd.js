var httpClient=new HttpClient("handleTrans.cdo");

function onModifyPwd()
{
	//输入检查

	var strOldPwd = _public.getVal("idoldpwd");
	var strNewPassword = _public.getVal("idnewpwd");
	var strPassword2 = _public.getVal("idnewpwd2");

	if(!strOldPwd)
	{
		layer.alert("请输入原始密码！",{icon: 2});
		document.getElementById("idoldpwd").focus();
		return;
	}

	if(!strNewPassword)
	{
		layer.alert("请输入新密码！",{icon: 2});
		document.getElementById("idnewpwd").focus();
		return;
	}

	if(!strPassword2)
	{
		layer.alert("请输入确认密码！",{icon: 2});
		document.getElementById("idnewpwd2").focus();
		return;
	}

	if(strNewPassword!=strPassword2)
	{
		layer.alert("新密码和确认密码不一致！",{icon: 2});
		return;
	}

	if(strOldPwd == strNewPassword){
		layer.alert("新密码与原密码一致，不用改了吧！",{icon: 2});
		return;
	}

	strOldPwd = hex_md5(strOldPwd);
	strNewPassword = hex_md5(strNewPassword);

	//执行事务
	var cdoRequest=new CDO();
	cdoRequest.setStringValue("strServiceName","EmployeeService");
	cdoRequest.setStringValue("strTransName","updatePassword");
	cdoRequest.setStringValue("strOldPwd",strOldPwd);
	cdoRequest.setStringValue("strNewPwd",strNewPassword);
	var cdoResponse=new CDO();
	var ret=httpClient.handleTrans(cdoRequest,cdoResponse);
	if(ret){
        layer.alert(ret.strText,{icon:0});
		if(ret.getCode() == 0){
			var url = strRootPath+"/main.htm";
			parent.location=url;
		}
	}else{
		layer.alert("系统异常",{icon: 2});
	}
}

function cancel(){
	var url = strRootPath+"/main.htm";
	parent.location = url;
}