var httpClient = new HttpClient("handleTrans.cdo");

window.onload = function(){
	_empinfo.init(); //初始化员工
	_role.init(); //初始化角色
}

//添加员工
$("#aDomAddEmp").click(function(){
	//resetRadio();
	_empinfo.openAdd();
});

//设定角色
$("#aDomSetRole").click(function(event) {
	var lEmployeeId = getChooselId();
	if(!lEmployeeId){return ;}
	_role.open(lEmployeeId);
});

//编辑员工
$("#aDomModEmp").click(function(event) {
	var lId = getChooselId();
	if(!lId){return ;}
	_empinfo.openEdit(lId);
	$('#e_st_fullbg').show();
});

//重置密码
$("#aDomResetPwd").click(function(){
	var lId = getChooselId();
	if(!lId){return ;}

    layer.confirm('确定要将密码重新设定为手机号后6位吗？', {
    	  btn: ['确定','取消'] //按钮
    	}, function(index){

    		var cdoReq = new CDO();
		    var cdoRes = new CDO();
		    cdoReq.setStringValue("strServiceName","EmployeeService");
		    cdoReq.setStringValue("strTransName","resetPassword");
		    cdoReq.setLongValue("lId",Number(lId));
		    var ret = httpClient.handleTrans(cdoReq,cdoRes);
		    if(ret!=null){
		        layer.alert(ret.getText());
		        if(ret.getCode()==0){
		        }
		    }else{
		    	layer.alert("执行异常");
		    }

    		layer.close(index);
    	}, function(index){
    		layer.close(index);
    	});
});

//删除
$("#aDelEmployee").click(function() {
	var lId = getChooselId();
	if(!lId){return ;}

	var isAdmin = $("#list_"+lId).attr("isAdmin");
	if(isAdmin && isAdmin == 1){
		layer.alert("超级管理员,不能删除");
		return ;
	}

    layer.confirm('确定要删除吗?', {
  	  btn: ['确定','取消'] //按钮
  	}, function(index){
  		var cdoReq = new CDO();
	    var cdoRes = new CDO();
	    cdoReq.setStringValue("strServiceName","EmployeeService");
	    cdoReq.setStringValue("strTransName","delEmployee");
	    cdoReq.setLongValue("lId",Number(lId));
	    var ret = httpClient.handleTrans(cdoReq,cdoRes);
	    if(ret!=null){
	        if(ret.getCode()==0){
	            window.location.replace("list.htm");
	        }else{
	        	layer.alert(ret.getText());
	        }
	    }else{
	    	layer.alert("执行异常");
	    }
  		layer.close(index);
  	}, function(index){
  		layer.close(index);
  	});

});

//员工信息管理 搜索按钮
$("#doSearch").click(function(event) {
	var timestamp = Date.parse(new Date());
    var url = "list.htm?t="+timestamp;
    var target = event.currentTarget;
	var pid = $(target).attr("pid");
	var pDom = $("#"+pid);
	pDom.find("input").each(function(index,dom){
		var that = $(dom);
		var key = that.attr("id");
		var val = that.val().trim();
		if(val && val.length > 0){
			url += "&"+key+"="+val;
		}
	});
    url = encodeURI(url).replace(/#/g, "%23");
	window.location.href = url;
});

//重置搜索
$("#doReset").click(function(){
	$("#search_id").find("input[type=text]").val('');
});

/**
 * 获取选中的某条记录
 * @returns
 */
function getChooselId(){
	var radios = $("#empDataTable").find("input:radio:checked");
	if(radios.length == 0){
		layer.alert("请选择一个员工");
		return false;
	}
    var lId = $(radios[0]).attr("value");
    return lId;
}

//重置radio
function resetRadio(){
$("#empDataTable").find("input:radio").each(function(){
		/*
		var that = $(this);
		that.attr("checked",false);
		var $div = that.siblings("div")
		$div.removeClass("layui-form-radioed");
		$div.children("i").removeClass("layui-anim-scaleSpring");
		*/
	});
}

