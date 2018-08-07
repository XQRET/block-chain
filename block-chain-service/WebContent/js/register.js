var httpClient = new HttpClient("handleTrans.cdo");
//点击注册事件
function onRegister() {
    var strMobile = document.getElementById("idMobile").value;
    var strGraphicVer = document.getElementById("idGraphicVer").value; //图形
    var strMessVer = document.getElementById("idMessVer").value; //手机
    var strPasswd = document.getElementById("idPasswd").value;
    var strMail = document.getElementById("idMail").value;
    var strMechanism = document.getElementById("idMechanism").value;

    if (!($('#idProtocol div').hasClass('layui-form-checked'))) {
        layer.alert("请同意区块链协议", {icon: 0});
        return;
    }
    // 过滤输入值
    if (strMobile.length == 0) {
        document.getElementById("idMobile").focus();
        layer.alert("请输入手机号", {icon: 0});
        return;
    }
    if (strGraphicVer.length == 0) {
        document.getElementById("idGraphicVer").focus();
        layer.alert("请输入图形验证码", {icon: 0});
        return;
    }
    if (strMessVer.length == 0) {
        document.getElementById("idMessVer").focus();
        layer.alert("请输入手机验证码", {icon: 0});
        return;
    }
    if (strPasswd.length == 0) {
        document.getElementById("idPasswd").focus();
        layer.alert("请输入密码", {icon: 0});
        return;
    }
    if (strMail.length == 0) {
        document.getElementById("idMail").focus();
        layer.alert("请输入邮箱", {icon: 0});
        return;
    }
    //校验数据格式
    if (false == isMobile(strMobile)) {
        document.getElementById("idMobile").focus();
        layer.alert("手机号码格式不正确", {icon: 2});
        return;
    }
    if (false == isPasswd(strPasswd)) {
        document.getElementById("idPasswd").focus();
        layer.alert("密码格式不正确", {icon: 2});
        return;
    }
    if (false == isMail(strMail)) {
        document.getElementById("idMail").focus();
        layer.alert("邮箱格式不正确", {icon: 2});
        return;
    }

    // 获取随机信息
    var cdoRequestBeforeLogin = new CDO();
    var cdoResponseBeforeLogin = new CDO();
    cdoRequestBeforeLogin.setStringValue("strServiceName", "UserRegisterService");  //类
    cdoRequestBeforeLogin.setStringValue("strTransName", "userRegister"); //类对应的方法
    cdoRequestBeforeLogin.setStringValue("strMobile", strMobile);
    cdoRequestBeforeLogin.setStringValue("strVerifyCode", strGraphicVer);
    cdoRequestBeforeLogin.setStringValue("strMessVer", strMessVer);
    cdoRequestBeforeLogin.setStringValue("strPasswd", strPasswd);
    cdoRequestBeforeLogin.setStringValue("strMail", strMail);
    cdoRequestBeforeLogin.setStringValue("strMechanism", strMechanism);
    var ret = httpClient.handleTrans(cdoRequestBeforeLogin, cdoResponseBeforeLogin);
    if (ret.nCode == 0) {
        //注册成功
        layer.msg('注册成功！', {
            icon: 0,
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        }, function(){
            //do something
            parent.location.replace( strRootPath +"/index.html");
        });
    } else {
        layer.alert("注册失败：" + ret.strText, {icon: 2});
        changeCheckIMG();
    }
}

//获取手机验证码
function getVer() {
    var strMobile = document.getElementById("idMobile").value;
    if (false == isMobile(strMobile)) {
        document.getElementById("idMobile").focus();
        layer.alert("手机号码格式不正确", {icon: 2});
        return;
    }
    $.ajax({
        type: "POST",
        url:  strRootPath +"/verificationCode/sendVerificationCode.do",
        data: {"mobile": strMobile},
        dataType: "json",
        beforeSend:
            function () {
                sendMessage();
            },
        success: function (data) {
            if (data != null) {
                if (data.resultCode != 0) {
                    layer.alert(data.resultMessage, {icon: 0});
                }else {
                    layer.alert("验证码已发送！", {icon: 0});
                }
            }
        }
    });
}

function isMobile(strMobile) {
    if (strMobile) {
        return (/^1[3578]\d{9}|14[357]\d{8}$/.test(strMobile));
    }
    return false;
}

function isPasswd(strPasswd) {
    if (strPasswd) {
        return (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$/.test(strPasswd));
    }
    return false;
}

function isMail(strMail) {
    if (strMail) {
        return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(strMail));
    }
    return false;
}

//验证码
function changeCheckIMG() {
    $("#loginimg").attr("src", "./servlet/verifyCode.svl?id=" + Math.random());
}
//加载头部
$(document).ready(function(){
	$(".header").load("notLogged/header.html",function(){
		if(sessionStorage.getItem('all_logoimg')==null){
			sessionStorage.setItem('all_logoimg','images/logo.png');
			}
		$(".all_logo").attr("src","./"+sessionStorage.getItem('all_logoimg'));
	$(".alltopright").remove();
	$(".alltop_nav").after("<div class='regright'><a href='login.htm'>立即登录</a></div>");
	/*循环给href加上前缀*/
	$('.alltop_nav li a').each(function(){
		if($(this).attr('href')!=undefined){
			var thehref="notLogged/"+$(this).attr('href');
			$(this).attr('href',thehref);	
		}
	});
	});
});