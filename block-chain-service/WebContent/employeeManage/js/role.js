var _role = {};
	_role.lEmployeeId = 0; //员工Id
	_role.leftSelect = "";
	_role.rightSelect = "";

	//绑定选中事件
	_role.init = function(){
		_role.bindSelectRow();
	}

	_role.open = function(lEmployeeId){
		_role.leftSelect = "";
		_role.rightSelect = "";
		$("#role_main_id li").removeClass("active");
		_role.lEmployeeId = lEmployeeId;
		_role.setRoleData();
	}

	//设定角色数据
	_role.setRoleData = function(){
		if(!_role.lEmployeeId || _role.lEmployeeId == 0){
			return ;
		}

		var cdoReq = new CDO();
	    var cdoRep = new CDO();
	    cdoReq.setStringValue("strServiceName","EmployeeRoleService");
	    cdoReq.setStringValue("strTransName","queryRoleList");
	    cdoReq.setLongValue("lEmployeeId",Number(_role.lEmployeeId));
	    var ret = httpClient.handleTrans(cdoReq,cdoRep);
		if(ret!=null){
			if(ret.nCode == 0){
	        	var leftList = cdoRep.getCDOArrayValue("cdoLeftList"); //获取选定用户可选择角色
	            var rightList = cdoRep.getCDOArrayValue("cdoRightList"); //获取选定用户已选择角色

	            var strLeftData = "";
	            var strRightData = "";
	            //左边
				for(var i=0;i<leftList.length;i++){
					var leftCDO = leftList[i];
					strLeftData += "<li id="+leftCDO.getIntegerValue("nId")+" class=\"clearfix\">"+leftCDO.getStringValue("strName")+" <span></span></li>";
				}
				$("#leftData_id").html('');
	            $("#leftData_id").append(strLeftData);

	            //右边
				for(var i=0;i<rightList.length;i++){
					var rightCDO = rightList[i];
					strRightData += "<li id="+rightCDO.getIntegerValue("nId")+" class=\"clearfix\">"+rightCDO.getStringValue("strName")+" <span></span></li>";
				}
				$("#rightData_id").html('');
				$("#rightData_id").append(strRightData);

				//显示
				$("#roleDialog_shade").show();
				$("#roleDialog").show();
			}
		}
	}

	//右移
	_role.rightMove = function(){
		if(_role.rightSelect.length == 0){
			layer.alert("请选中后再右移");
			return ;
		}

		var rightArr = _role.rightSelect.split(",");
		var strRightData = "";
		for(var i=0;i<rightArr.length;i++){
			var tempRight = rightArr[i].split("|");
			var liId = tempRight[0];
			//清除
			$("#"+liId).remove();

			strRightData += "<li id="+liId+" class=\"clearfix\">"+tempRight[1]+" <span></span></li>";
		}

		$("#rightData_id").append(strRightData);
		_role.rightSelect = "";
	}

	//左移
	_role.leftMove = function(){
		if(_role.leftSelect.length == 0){
			layer.alert("请选中后再左移");
			return ;
		}

		var leftArr = _role.leftSelect.split(",");
		var strLeftData = "";
		for(var i=0;i<leftArr.length;i++){
			var tempLeft = leftArr[i].split("|");
			var liId = tempLeft[0];
			//清除
			$("#"+liId).remove();

			strLeftData += "<li id="+liId+" class=\"clearfix\">"+tempLeft[1]+" <span></span></li>";
		}

		$("#leftData_id").append(strLeftData);
		_role.leftSelect = "";
	}

	//绑定选中事件
	_role.bindSelectRow = function(){
		$("#leftData_id").on("click","li",function(){
			var that = $(this);
			var id = that.attr("id");
			var name = that.text();
			if(that.hasClass("active")){
				that.removeClass("active");
				var newStr = _role.removeSelectContent(id,_role.rightSelect);
				_role.rightSelect = newStr;
			}else{
				that.addClass("active");
				var newStr = _role.addSelectContent(id,name,_role.rightSelect);
				_role.rightSelect = newStr;
			}
		});

		$("#rightData_id").on("click","li",function(){
			var that = $(this);
			var id = that.attr("id");
			var name = that.text();
			if(that.hasClass("active")){
				that.removeClass("active");
				var newStr = _role.removeSelectContent(id,_role.leftSelect);
				_role.leftSelect = newStr;
			}else{
				that.addClass("active");
				var newStr = _role.addSelectContent(id,name,_role.leftSelect);
				_role.leftSelect = newStr;
			}
		});
	}

	//添加到选中
	_role.addSelectContent = function(id,name,str){
		if(str.length == 0){
			str += id+"|"+name;
		}else{
			str += ","+id+"|"+name;
		}
		return str;
	}

	//移除选中
	_role.removeSelectContent = function(id,str){
		var tempStr = "";
		var strArr = str.split(",");
		for(var j=0;j<strArr.length;j++){
			var tempArr = strArr[j].split("|");
			if(tempArr[0]!=id){
				tempStr += id+"|"+name+","
			}
		}

		if(tempStr.length > 0){
			tempStr = tempStr.substring(0,tempStr-1);
		}
		return tempStr;
	}

	//取消
	_role.cancel = function(){
		$("#roleDialog").hide();
		$("#roleDialog_shade").hide();
	}

	//保存
	_role.save = function(){
		if(_role.lEmployeeId == 0 || !_role.lEmployeeId){
			layer.alert("lEmployeeId不存在");
			return ;
		}

		var roleArr = [];
		$("#rightData_id li").each(function(){
			var id = this.id;
			roleArr.push(id);
		});

		var cdoReq = new CDO();
	    var cdoRep = new CDO();
	    cdoReq.setStringValue("strServiceName","EmployeeRoleService");
	    cdoReq.setStringValue("strTransName","inserttbEmployeeRole");
	    cdoReq.setLongValue("lEmployeeId",Number(_role.lEmployeeId));
	    cdoReq.setLongValue("nTotalLen",roleArr.length);
	    cdoReq.setLongArrayValue("arRoleList",roleArr);
	    var ret = httpClient.handleTrans(cdoReq,cdoRep);
	    if(ret!=null){
	        if(ret.getCode()==0){
	        	_role.cancel();
	        	window.location.replace("list.htm");
	        }
	    }
	}

