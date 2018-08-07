//页面加载
$(function () {
    getContractImportParam();
    getRelationContract();
})



//等待遮罩
var layerload;

//页码
var pageIndex = 1;

var u = navigator.userAgent, app = navigator.appVersion;
var theie = u.indexOf('Trident') > -1// IE内核
if (theie) {
	$(".con-down span").find("a").removeAttr("target");
	$(".con-down span").find("a").click(function(){
		layer.alert('该浏览器暂不支持预览，请直接下载或者换个浏览器', {
				icon : 2
			});
	});
}

//请求查询导入参数
function getContractImportParam() {
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/contract/queryImportParam.do",
        success: function (data) {
            if (data != null) {
                var pageHtml = "";
                if (data.resultCode == 0 && data.resultDate != null) {
                    loadImportParamData(data.resultDate);
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
}
//加载导入参数数据
function loadImportParamData(resultDate) {
	$("#contentThead").html("");
	$("#contentBody").html("");
	var colunm = resultDate.colunm;
	var mapping = JSON.parse(resultDate.mapping);
	var htmlThead = '<tr><td>数据字段名称</td>';
	var htmlBody = '<tr><td>execl字段对应列</td>';
	for(var p in mapping){
		
		htmlThead += '<td>' + mapping[p] + '</td>';
		htmlBody += '<td id="'+p+'">' + colunm[p] + '</td>';
	}
	htmlThead+='<td>操作</td></tr>'
	htmlBody+= '<td><a onclick="" class="bluebtn dataEdit">编辑</a>'+
    '<a onclick="" class="bluebtn dataSave hidden">保存</a></td>' +
    '</tr>';
	$("#contentThead").append(htmlThead);
    $("#contentBody").append(htmlBody);
    //点编辑之后把td改成input
    $(".dataEdit").on('click',function(){
    	$(this).closest('td').siblings().html(function(i,html) {
    		if($(this).attr("id")!= null){
        		return '<input type="text" name=' + $(this).attr("id") + ' value=' + html + ' />';
    		}
    	});
    	$(this).hide();
    	//显示保存按钮
    	$(this).next('a').removeClass("hidden");
    });
    
    $(".dataSave").on('click',function(){
	   $.ajax({
	        type: "POST",
	        data:$("#form").serialize(),
	        dataType: 'json',
	        url: strRootPath + "/contract/insertImportParam.do",
	        success: function (data) {
	            if (data != null) {
	                var pageHtml = "";
	                if (data.resultCode == 0 && data.resultDate != null) {
	                	loadImportParamData(data.resultDate);
	                } 
	                layer.alert(data.resultMessage, {icon: 0});
	            }
	        }
	   });
    })
}

//请求查询待关联合约列表
function getRelationContract(pageIndex) {
	pageIndex == null ? 1 : pageIndex;
    $.ajax({
        type: "POST",
        dataType: 'json',
        data: {
            "pageIndex": pageIndex,
            "importUniqueSign":$("#importUniqueSignSearch").val()
        },
        url: strRootPath + "/contract/queryRelationContract.do",
        success: function (data) {
            if (data != null) {
                var pageHtml = "";
                if (data.resultCode == 0 && data.resultDate != null) {
                	pageIndex = data.resultDate.pageIndex;
                    var pageInfoList = data.resultDate.pageInfoList;//数据
                    var totalCount = data.resultDate.totalCount;
                    var pageCount = data.resultDate.pageCount;
                    var totalPage = data.resultDate.totalPage
                    loadContractData(pageInfoList);
                    pageData(totalCount, pageCount, pageIndex);
                    pageHtml = '<span class="layui-laypage-count" style="color: #333;font-size:12px;margin-left: 5px;">' + "共" + totalCount + "记录，共" + totalPage + "页" + '</span>';
                    $("#pageTool").append(pageHtml);
                } 
            }
        }
    })
}

//加载合约列表数据
function loadContractData(resultDate) {
	 $("#contractContentBody").html("");
  var html = "";
  for(var p in resultDate){
	  var registDate = new  Date(resultDate[p].registDate).format("yyyy-MM-dd hh:mm:ss");
	   html += '<tr>' +
	   '<td >' + resultDate[p].importUniqueSign + '</td>' +
      '<td >' + resultDate[p].contractSigner + '</td>' +
      '<td >' + resultDate[p].houseCode + '</td>' +
      '<td >' + resultDate[p].amount + '元</td>' +
      '<td >' + resultDate[p].interestRate + '%</td>' +
      '<td >' + resultDate[p].term + '月</td>' +
      '<td >' + registDate + '</td>' +
      '<td><a onclick="showRelation(\''+resultDate[p].contractId+'\',\''+resultDate[p].houseCode+'\',\''+resultDate[p].contractSigner+'\',\''+registDate+'\',\''+resultDate[p].importUniqueSign+'\')" class="the_blue" style="margin-right:10px;">关联</a>'+
      '<a onclick="deleteContract(\''+resultDate[p].contractId+'\')" class="the_false">删除</a></td>' +
      '</tr>';
  }
  $("#contractContentBody").append(html);
}

//生成分页
function pageData(totalCount, pageCount, pageIndex) {
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'pageTool'
            , count: totalCount
            , limit: pageCount
            , curr: pageIndex
            , first: '首页'
            , last: '尾页'
            // , layout: ['page', 'count']
            , jump: function (obj, first) {
                pageIndex = obj.curr;  //这里是后台返回给前端的当前页数
                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr  ajax 再次请求
                	getRelationContract(pageIndex);
                }
            }
        });
    })
}

var temp_contractId = null;

var temp_houseCode = null;

var temp_contractSigner = null;

var temp_confirmJson = null;

var temp_importUniqueSign = null;

//展示导入关联数据页面
function showRelation(contractId,houseCode,contractSigner,registDate,importUniqueSign){
	temp_contractId = contractId;
	temp_houseCode = houseCode;
	temp_contractSigner = contractSigner;
	temp_importUniqueSign = importUniqueSign;
	$("#relationContractSigner").html(contractSigner);
	$("#relationRegistDate").html(registDate);
	$("#fileFolder").siblings("span").remove();
	showDialog('importDialog');
}


//导入文件夹
function selectFolder(){
	$("#fileFolder")[0].value="";
	$("#fileFolder").siblings("span").remove();
	$("#fileFolder")[0].click();	
}

function showSelectFolder(){
	var file=$("#fileFolder");
	var files = file[0].files;
	var length = files.length;
	if(length > 0){
		var parentPath = files[0].webkitRelativePath.split("/")[0];
		file.before('<span>导入目录'+parentPath+'下'+length+'个文件</span>');
	}

}

function uploadFolder(){
	layerload = layer.load();
	var file=$("#fileFolder");
	var files = file[0].files;
	var length = files.length;
	var formData = new FormData();
	formData.append("houseCode",temp_houseCode);
	if(length > 0){
		var childrenPath = files[0].webkitRelativePath;
		var parentPath = childrenPath.split("/")[0];
		//校验是否符合要求
        for(var i=0; i<length;i++){
            formData.append('files', files[i]);
        }
		//符合要求上传文件
        if(temp_importUniqueSign != parentPath){
        	layer.close(layerload);
        	layer.alert("文件目录不匹配", {icon: 0});
        	return false;
        }
	}
	$.ajax({
	    url: strRootPath + "/contract/uploadFolder.do",
	    type: 'POST',
	    cache: false,
	    dataType:"json",
	    data: formData,
	    processData: false,
	    contentType: false
	}).done(function(data) {
		layer.close(layerload);
		 if (data.resultCode == 0 && data.resultDate != null) {
			 temp_confirmJson = data.resultDate;
			 console.log(temp_confirmJson);
			 openView('importconfirm.htm','确认页面');
			 closefbE();
         } 
	}).fail(function(res) {
		layer.close(layerload);
		layer.alert("上传失败", {icon: 0});
	});
	
}

//删除合约
function deleteContract(contractId){
	layer.confirm('是否确认删除？', {
		  btn: ['确认','取消'] //按钮
	}, function(){
	   $.ajax({
	        type: "POST",
	        data:{"contractId":contractId},
	        dataType: 'json',
	        url: strRootPath + "/contract/deleteImportContract.do",
	        success: function (data) {
	            if (data != null) {
	            	if (data.resultCode == 0 ) {
	            		getRelationContract();
	                }
	                layer.alert(data.resultMessage, {icon: 0});
	            }
	        }
	   });
	});
}

//下载模板
function downloadTemplate(){
	$("#form").attr("action",strRootPath+"/contract/downloadContractTemplate.do")	
	$("#form").submit();
}

//上传execl
layui.use('upload', function(){
	  var upload = layui.upload;
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#upload' //绑定元素
	    ,accept:"file"
	    ,ext: 'xls|xlsx'
	    ,url: strRootPath + "/contract/importContract.do" //上传接口
	    ,before: function(input){
	    	layerload = layer.load(); //上传loading
	    }
	    ,done: function(res){
	    	layer.close(layerload);
	        getRelationContract();
	    	var message = res.resultCode>=0?res.resultDate:res.resultMessage
	    	
			layer.open({
				content:(res.resultCode>=0?'操作完成:':'操作失败:')+message
				,yes: function(index, layero){
					layer.close(index);
				}
				,end:function(){
				}
			}); 
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	  });
	});