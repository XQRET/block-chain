var httpClient = new HttpClient("handleTrans.cdo");

if (self != top) { 
	//检测到被踢下线的询问框
	layer.confirm("检测到账号已掉线,请您重新进入系统",{icon:0}, 
	function(){
		parent.location.reload();
	});
}

var onLogin = function() {
	// 保存输入值
	var strMobile = document.getElementById("idMobile").value;
	var strPassword = document.getElementById("idPassword").value;
	var strVerifyCode = document.getElementById("verifyCode").value;

	// 过滤输入值
	if (strMobile.length == 0) {
		document.getElementById("idMobile").focus();
		layer.alert("请输入用户名",{icon:0});
		return;
	}
	if (strPassword.length == 0) {
		document.getElementById("idPassword").focus();
		layer.alert("请输入密码",{icon:0});
		return;
	}
	if(strVerifyCode.length == 0){
		document.getElementById("verifyCode").focus();
		layer.alert("请输入验证码",{icon:0});
		return;
	}

	if(false == _public.isMobile(strMobile)){
		document.getElementById("idMobile").focus();
		layer.alert("手机号码格式不正确",{icon:2});
		return ;
	}

	// 获取随机信息
	var cdoRequestBeforeLogin = new CDO();
	var cdoResponseBeforeLogin = new CDO();

	cdoRequestBeforeLogin.setStringValue("strServiceName", "EmployeeService");
	cdoRequestBeforeLogin.setStringValue("strTransName", "bossBeforeLogin");
	cdoRequestBeforeLogin.setStringValue("strMobile", strMobile);
	cdoRequestBeforeLogin.setStringValue("strVerifyCode",strVerifyCode);
	var ret = httpClient.handleTrans(cdoRequestBeforeLogin, cdoResponseBeforeLogin);

	if (ret.nCode != 0)
	{// 获取获取随机信息失败
		layer.alert("登录失败：" + ret.strText,{icon:2});
		changeCheckIMG();
		document.getElementById("verifyCode").focus();
		return;
	}

	var strRandom = cdoResponseBeforeLogin.getStringValue("strRandom");
	var strSalt = cdoResponseBeforeLogin.getStringValue("strSalt");

	// 登录过程
	if(strRandom && strSalt)
	{
		// 密码混淆
		var strHashPass = hex_md5(strSalt + '' + hex_md5(strPassword));

		// 请求令牌
		var strLoginToken = hex_md5(hex_md5(strRandom) + '' + strHashPass);

		// 执行登录事物
		var cdoRequest = new CDO();
		var cdoResponse = new CDO();

		cdoRequest.setStringValue("strServiceName", "EmployeeService");
		cdoRequest.setStringValue("strTransName", "doBossLogin");
		cdoRequest.setStringValue("strMobile", strMobile);
		cdoRequest.setStringValue("strLoginToken", strLoginToken);

		var ret = httpClient.handleTrans(cdoRequest, cdoResponse);
		if (ret.nCode == 0)
		{
			// 登录成功，跳转
			//window.location.replace("main.htm");
			parent.location.replace("main.htm");
		}
		else
		{
			layer.alert("登录失败：" + ret.strText,{icon:2});
			changeCheckIMG();
			document.getElementById("verifyCode").focus();
		}
	}// 结束登录过程
}

function isMobile(strMobile)
{
	if(strMobile)
	{
		if((/^0?1[3|4|5|8][0-9]\d{8}$/.test(strMobile)))
		{
			return true;
		}
	}
	return false;
}

/*window.onload = function() {
	document.getElementById("idMobile").focus();
}*/

//回车
document.onkeydown = function(e) {
	if (e == null) {
		return;
	}
	if (e.keyCode == 13) {
		//1、检测到是否有验证提示框弹出，如果弹出则关闭提示框
		if($(".layui-layer-shade").length > 0){
			layer.closeAll();
		}else{
			onLogin();
		}
	}
}

//验证码
function changeCheckIMG(){
	$("#loginimg").attr("src","./servlet/verifyCode.svl?id=" + Math.random());
}

$(document).ready(function(){
	//加载头部
	$(".header").load("notLogged/header.html",function(){
		$(".all_logo").attr("src",sessionStorage.getItem('all_logoimg'));
	/*循环给href加上前缀*/
	$('.alltop_nav li a').each(function(){
		if($(this).attr('href')!=undefined){
			var thehref="notLogged/"+$(this).attr('href');
			$(this).attr('href',thehref);	
		}
	});
});
	 //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
	var logId = parseInt(getUrlParam('logId'));
	/*根据参数显示内容 0为资产端  1为资金端 2为背书端*/
	if(logId>=0){
	/*背景图片切换*/
	var logidadd=logId+1;
	$(".login_center").addClass("bg"+logidadd);
	//登录的标题切换
	$(".login_title").eq(0).hide();
	$(".login_title").eq(logidadd).show();
	/*左边标语切换*/
	$(".login_slogan").eq(logId).show();
	/*流程切换*/
	$(".login_flow").eq(logId).show();
	}
});