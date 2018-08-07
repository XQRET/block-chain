var httpClient = new HttpClient("handleTrans.cdo");

$(function () {
    skipPage();
})

//跳转页面判断
function skipPage() {
    var lEmployeeId = $("#lEmployeeId").val();
    $.ajax({
        type: "POST",
        url: strRootPath + "/company/queryCertifiedInfo.do",
        data: {"lEmployeeId": lEmployeeId},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    var status = data.resultDate.companyStatus;
                    if (status == 2) {
                        topContent();
                    }
                    if (status == 4) {
                        topContent();
                        $(".basic_info p:gt(1)").css("display", "none");
                    }
                }
                $(".basic_info").css("display","block");
            }
        }
    });
}

function topContent() {
    $(".auth_tips").remove();
    var html="";
    html+='<div class="alltitle">'+
        '<p>基本信息</p>'+
        '</div>';
    $(".body-content").prepend(html);
}

function onShowKey() {
    $("#idPasswd").val("");
    showDialog('prKeyDialog');
}

function idComfirm() {
    var strMobile = $("#strMobile").val();
    var strPasswd = $("#idPasswd").val();
    strPasswd = hex_md5(strPasswd);
    if (strPasswd.length == 0) {
        $("#idPasswd").focus();
        layer.alert("请输入密码", {icon: 0});
        return;
    }
    // 获取随机信息
    var cdoRequestPasswd = new CDO();
    var cdoResponsePasswd = new CDO();
    cdoRequestPasswd.setStringValue("strServiceName", "UserCenterService");  //类
    cdoRequestPasswd.setStringValue("strTransName", "checkPasswd"); //类对应的方法
    cdoRequestPasswd.setStringValue("strPasswd", strPasswd);
    cdoRequestPasswd.setStringValue("strMobile", strMobile);
    var ret = httpClient.handleTrans(cdoRequestPasswd, cdoResponsePasswd);
    if (ret.nCode != 0) {
        layer.alert(ret.strText, {icon: 2});
    } else {
        //密码验证通过
        showDialog('showPrKeyDialog');
    }
}


