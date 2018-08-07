var InterValObj; //timer变量，控制时间 
var count = 60; //间隔函数，1秒执行
var curCount; //当前剩余秒数 
function sendMessage() {
    curCount = count;
    //设置button效果，开始计时 
    $("#btnSendCode").removeAttr("onclick");//移除点击事件
    $("#btnSendCode").addClass("bgc1").removeClass("bg1f");//更改背景颜色
    $("#btnSendCode").removeAttr("style");//移除点击事件
    $("#btnSendCode").html(curCount + "s后可发送");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次 
}
//timer处理函数 
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj); //停止计时器 
        //启用按钮 
        $("#btnSendCode").addClass("bg1f").removeClass("bgc1");
        $("#btnSendCode").css("background", "#1fa6f3");
        $("#btnSendCode").attr("onclick", "getVer();");
        $("#btnSendCode").html("重新发送");
    } else {
        curCount--;
        $("#btnSendCode").html(curCount + "s");
    }
}