var httpClient = new HttpClient("handleTrans.cdo");
//验证码
function changeCheckIMG() {
    $("#loginimg").attr("src", "../servlet/verifyCode.svl?id=" + Math.random());
}


function onRetrievePwd() {
    var strMobile = document.getElementById("idMobile").value;
    var strGraphicVer = document.getElementById("idGraphicVer").value; //图形
    var strMessVer = document.getElementById("idMessVer").value; //手机
    var newPwd = document.getElementById("newPwd").value;
    var firmPwd = document.getElementById("firmPwd").value;

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
    if (newPwd.length == 0) {
        document.getElementById("newPwd").focus();
        layer.alert("请输入密码", {icon: 0});
        return;
    }
    if (firmPwd.length == 0) {
        document.getElementById("firmPwd").focus();
        layer.alert("请确认密码", {icon: 0});
        return;
    }
    if(newPwd!=firmPwd){
        layer.alert("密码输入不一致！", {icon: 0});
        return;
    }
    //校验数据格式
    if (false == isMobile(strMobile)) {
        document.getElementById("idMobile").focus();
        layer.alert("手机号码格式不正确", {icon: 2});
        return;
    }
    if (false == isPasswd(newPwd) || false==isPasswd(firmPwd)) {
        layer.alert("密码格式不正确", {icon: 2});
        return;
    }
    // 获取随机信息
    var cdoRequestBeforeLogin = new CDO();
    var cdoResponseBeforeLogin = new CDO();
    cdoRequestBeforeLogin.setStringValue("strServiceName", "UserPasswordService");  //类
    cdoRequestBeforeLogin.setStringValue("strTransName", "userResetPassword"); //类对应的方法
    cdoRequestBeforeLogin.setStringValue("strMobile", strMobile);
    cdoRequestBeforeLogin.setStringValue("strVerifyCode", strGraphicVer);
    cdoRequestBeforeLogin.setStringValue("strSmsCode", strMessVer);
    cdoRequestBeforeLogin.setStringValue("strPassword", newPwd);
    var ret = httpClient.handleTrans(cdoRequestBeforeLogin, cdoResponseBeforeLogin);
    if (ret.nCode == 0) {
        //注册成功
        layer.msg('更改密码成功！', {
            icon: 0,
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        }, function(){
            //do something
            parent.location.replace( strRootPath +"/index.html");
        });
    } else {
        layer.alert("找回密码失败：" + ret.strText, {icon: 2});
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
        url: strRootPath + "/verificationCode/sendVerificationCode.do",
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
                } else {
                    layer.alert("验证码已发送！", {icon: 0});
                    sendMessage();
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