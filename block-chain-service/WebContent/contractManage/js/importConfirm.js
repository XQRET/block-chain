$(function () {    
	$("#confirmContractSigner").html("合约导入-"+window.parent.temp_contractSigner);
	createHtml(window.parent.temp_confirmJson);
})

//input 生成计数
var index = 0;

var params = null;

function createHtml(jsonResult){
	var urls= jsonResult.urls;
	params = jsonResult.params;
	if(urls != null){
		var html = '<input type="hidden" name="contractId" value="'+window.parent.temp_contractId+'" />';
		for(var p in urls){
			var imgstr = (urls[p].type != "img")?'<img class="normal_img" src="../images/demopdf.png" />':'<img class="thumbnail" src="'+urls[p].url+'" />';
			html+= '<li>'+
		    '<p>'+urls[p].title+'</p>'+ imgstr+
		    '<input type="hidden" name="files['+index+'].fileUrl" value="'+urls[p].url+'" />'+
		    '<div class="layui-input-block">'+createSelect(params,urls[p].paramKey)+'</div>'+
		    '</li>'
		    index ++;
		}
		$("#showImg").html(html);
		layform = layui.form;
		layform.render();
	}
}

function createSelect(params,paramKey){
	var selectHtml = '<select name="files['+index+'].fileType" lay-verify="required"><option value="">请选择</option>';
	for(var p in params){
		if(params[p].paramKey == paramKey){
			selectHtml+='<option value="'+params[p].paramKey+'" selected>'+params[p].title+'</option>';
		}else{
			selectHtml+='<option value="'+params[p].paramKey+'">'+params[p].title+'</option>';
		}
	}
	selectHtml += '<option value="none">不显示</option></select>'
	return selectHtml;
}


var fileKey = null;

var fileValue = null;

var fileType = null;

var fileTitle = null;

function showSupplementaryData(){
	$("#add_apply").html("");
	getRelationParam(window.parent.temp_contractId,
			window.parent.temp_houseCode,window.parent.temp_contractSigner);
	
	fileKey = new Array(); 
	fileValue = new Array();
	fileType = new Array();
	fileTitle = new Array();
	showDialog('relationDialog');
}

//提交关联
function relation(){
	
	var selecteds = $("select option:selected");
	var flat = true;
	
	selecteds.each(function(){
		  if(this.value == ''){
			  flat = false;
		  }
	 });
	if(flat){
		$.ajax({
	        type: "POST",
	        data:$("#confirmForm").serialize(),
	        dataType: 'json',
	        url: strRootPath + "/contract/relationContract.do",
	        success: function (data) {
	            if (data != null) {
	            	if (data.resultCode == 0 ) {
	                	 layer.alert(data.resultMessage, {icon: 0},function(){
		                 		window.parent.getRelationContract();
			            		closefbE();
			            		var _index = parent.layer.getFrameIndex(window.name);
			            		parent.layer.close(_index);
		                });
	                }
	            }
	        }
	   });
	}else{
		layer.alert("存在文件未关联，请选择关联关系", {icon: 0});
	}
}


//查询关联导入参数
function getRelationParam(contractId,houseCode,contractSigner) {
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/contract/queryRelationParam.do",
        success: function (data) {
            if (data != null) {
                var pageHtml = "";
                if (data.resultCode == 0 && data.resultDate != null) {
                	loadUpdateParamData(contractId,houseCode,contractSigner,data.resultDate);
                } 
            }
        }
    })
}

//加载关联导入参数
function loadUpdateParamData(contractId,houseCode,contractSigner,resultDate){
	var html = "";
	html +='<input type="hidden" id="relationhouseCode" value="'+houseCode+'" /><input type="hidden" name="contractId" id="contractId" value="'+contractId+'" /><div class="add_group"><label>姓名：</label><span>'+contractSigner+'</span></div>';
	   for(var p in resultDate){
		   html += '<div class="add_group" id="'+resultDate[p].paramKey+'"><label>'+resultDate[p].title+'：</label><a class="remind"   onclick="uploadFile(this,\''+resultDate[p].paramKey+'\',\''+resultDate[p].multiple+'\',\''+resultDate[p].type+'\',\''+resultDate[p].title+'\')">上传</a></div>';
	   }
	$("#add_apply").html(html);
}

//上传文件
function uploadFile(obj,paramKey,multiple,type,title){
	$(obj).siblings("input[type='file']").remove();
	
	if(multiple == "false" && $(obj).next().size()>0){
		layer.alert("该栏只能上传一次", {icon: 0});
	}else{
	    var inputEle=document.createElement("input"); //创建input 
	    inputEle.type="file";
	    if(type == "img"){
	        inputEle.accept="image/*";
	        inputEle.onchange = function(){
	        	processFile(paramKey,type,inputEle,title);
	        };
	    }else{
		   if(type == "doc"){
		        inputEle.accept="application/msword";
		    }else if(type == "execl"){
		        inputEle.accept="application/vnd.ms-excel";
		    }else if(type== "pdf"){
		        inputEle.accept="application/pdf";
		    }
	       inputEle.onchange = function(){
	    	   processFile(paramKey,type,inputEle,title);
	       };
	    }
	    //"<input type='file'  id='fileFolder' name='fileFolder' webkitdirectory />"
	    inputEle.style="display:none;";
	    $(obj).parent().append(inputEle);
	    inputEle.click();
	}
}

//处理文件
function processFile(paramKey,type,file,title){
	layerload = layer.load();
    var fileName =file.files[0].name;
    //读取后辍,判断是否允许的文件
    var strSpilt =  fileName.split(".");
    var fileSuffix = strSpilt[strSpilt.length-1];
    var allowFile = "";
	if(type == "img"){
	    allowFile = "jpeg|jpg|png|gif";
	    if(allowFile.indexOf(fileSuffix.toLowerCase())==-1) {
	    	layer.alert("请使用"+allowFile+"后辍的文件", {icon: 0});
	        return false;
	    }
	    //如果传了显示控件的id,显示本地预览
	    var reader = new FileReader()
	    reader.readAsDataURL(file.files[0]);
	    reader.onload = function(e){
		    var imgBase64Data = e.target.result;
		    //显示预览
		    //对图片进行缩小处理,然后再上传
		    compressImg(imgBase64Data,1000,1000,function(imgBase64DataBack){                               
		        saveFile(imgBase64DataBack,null,fileName,paramKey,type,file,title);
		    }); 
	    } 
	}else{
		if(type == "doc"){
			allowFile = "doc|docx";
		    if(allowFile.indexOf(fileSuffix.toLowerCase())==-1) {
		    	layer.alert("请使用"+allowFile+"后辍的文件", {icon: 0});
		        return false;
		    }
	    }else if(type == "execl"){
			allowFile = "xls|xlsx";
		    if(allowFile.indexOf(fileSuffix.toLowerCase())==-1) {
		    	layer.alert("请使用"+allowFile+"后辍的文件", {icon: 0});
		        return false;
		    }
	    }else if(type== "pdf"){
			allowFile = "pdf";
		    if(allowFile.indexOf(fileSuffix.toLowerCase())==-1) {
		    	layer.alert("请使用"+allowFile+"后辍的文件", {icon: 0});
		        return false;
		    }
	    } 
		saveFile(null,file,fileName,paramKey,type,file,title);
	}     
}   

//压缩图片
function compressImg(imgBase64Data,maxWidth,maxHeight,fun){
    var  img = new Image();
    img.src = imgBase64Data;
    // 缩放图片需要的canvas
    var canvas = document.createElement('canvas');
    var context = canvas.getContext('2d');
    // base64地址图片加载完毕后
    img.onload = function () {
        // 图片原始尺寸
        var originWidth = this.width;
        var originHeight = this.height;           
        // 目标尺寸
        var targetWidth = originWidth, targetHeight = originHeight;
        // 图片尺寸超过400x400的限制
        if (originWidth > maxWidth || originHeight > maxHeight) {
            if (originWidth / originHeight > maxWidth / maxHeight) {
                // 更宽，按照宽度限定尺寸
                targetWidth = maxWidth;
                targetHeight = Math.round(maxWidth * (originHeight / originWidth));
            } else {
                targetHeight = maxHeight;
                targetWidth = Math.round(maxHeight * (originWidth / originHeight));
            }
        }
        // canvas对图片进行缩放
        canvas.width = targetWidth;
        canvas.height = targetHeight;
        // 清除画布
        context.clearRect(0, 0, targetWidth, targetHeight);
        // 图片压缩
        context.drawImage(img, 0, 0, targetWidth, targetHeight);
        var base=canvas.toDataURL("image/jpeg",0.7);//canvas转码为base64               
        fun(base);//返回处理
    };
}


//提交文件到服务器
function saveFile(base64Data,file,fileName,paramKey,type,obj,title){
    var formData = new FormData();
    formData.append("houseCode",$("#relationhouseCode").val());
    formData.append("fileName",fileName);
    if(file != null){
        formData.append("file",file.files[0]);
    }
    if(base64Data != null){
        formData.append("base64Data",encodeURIComponent(base64Data));
    }
	//ajax上传
	$.ajax({
	    url: strRootPath + "/contract/uploadFile.do",
	    type: 'POST',
	    cache: false,
	    dataType:"json",
	    data: formData,
	    processData: false,
	    contentType: false
	}).done(function(data) {
		layer.close(layerload);
		 if (data.resultCode == 0 && data.resultDate != null) {
			 fileKey.push(paramKey);
			 fileType.push(type);
			 fileValue.push(data.resultDate);
			 fileTitle.push(title);
			 createContractFileUrl(obj,type,data.resultDate,fileName);
         } 
         layer.alert(data.resultMessage, {icon: 0});		
	}).fail(function(res) {
		layer.close(layerload);
		layer.alert("上传失败", {icon: 0});
	});
}

//创建关联提交数据
function createContractFileUrl(obj,type,fileUrl,fileName){
	 if(type == "img"){
		 $(obj).parent().append('<img class="thumbnail" src="'+fileUrl+'"/>')
	 }else{
		 $(obj).parent().append('<span>'+fileName+'</span>');
	 }	 
	 $(obj).parent().append('<input type="hidden">');
	 $(obj).remove();
}

function copyFile(){
	var html = '';
	for(var p in fileKey){
		var imgstr = (fileType[p] != "img")?'<img class="normal_img" src="../images/demopdf.png" />':'<img class="thumbnail" src="'+fileValue[p]+'" />';
		html+= '<li>'+
	    '<p>'+fileTitle[p]+'</p>'+ imgstr+
	    '<input type="hidden" name="files['+index+'].fileUrl" value="'+fileValue[p]+'" />'+
	    '<div class="layui-input-block">'+createSelect(params,fileKey[p])+'</div>'+
	    '</li>'
	    index ++;
	}
	$("#showImg").append(html);
	layform = layui.form;
	layform.render();
	closefbE();
}



