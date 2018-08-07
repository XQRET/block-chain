var httpClient = new HttpClient("handleTrans.cdo");
var statusDB;
var nameDB;
var linkmanDB;
var phoneDB;
var photoFileDB;
var provinceNameDB;
var cityNameDB;
//默认选中城市
$("#interview").citySelect({prov: "广东", city: "深圳", dist: "福田区"});
/*点击切换公司类型*/
$(".auth_corp span").click(function () {
    $(this).addClass("ac_check").siblings().removeClass("ac_check");
});
$(function () {
    //进入页面查询状态
    var lEmployeeId = $("#lEmployeeId").val();
    $.ajax({
        type: "POST",
        data: {"lEmployeeId": lEmployeeId},
        dataType: 'json',
        url: strRootPath + "/company/queryCertifiedInfo.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    statusDB = data.resultDate.companyStatus;
                    nameDB = data.resultDate.name;
                    linkmanDB = data.resultDate.linkman;
                    phoneDB = data.resultDate.phone;
                    photoFileDB = data.resultDate.companyPhotoFile;
                    provinceNameDB = data.resultDate.provinceName;
                    cityNameDB = data.resultDate.cityName;
                    if (statusDB == 0) {
                        layer.alert("请认证公司信息！", {icon: 0});
                    } else if (statusDB == 1) {
                        $(".auth_icon").text("待审核");
                        $("#companyName").val(nameDB);
                        $("#linkman").val(linkmanDB);
                        $("#phone").val(phoneDB);
                        $("#interview").citySelect({prov: provinceNameDB, city: cityNameDB, dist: ""});
                        $("#busLicenseImg").attr('src', photoFileDB);
                        $("#uploadBusLicenseImg").css("display", "none");
                        $("#busLicenseImg").css('display', "block");
                        $(".auth_btn").text("更新数据");
                    } else if (statusDB == 2) {
                        $(".auth_icon").text("审核通过");
                        $("#companyName").val(nameDB);
                        $("#linkman").val(linkmanDB);
                        $("#phone").val(phoneDB);

                        $("#companyName").attr("readonly", true);
                        $("#linkman").attr("readonly", true);
                        $("#phone").attr("readonly", true);
                        $(".auth_btn").css("display", "none");

                        $("#busLicenseImg").attr('src', photoFileDB);
                        $("#uploadBusLicenseImg").css("display", "none");
                        $("#busLicense").remove();
                        $("#busLicenseImg").css('display', "block");
                        //地址
                        $("#interview").empty();
                        var html = "<label>公司地址：</label>" +
                            "<input value=" + provinceNameDB + "-" + cityNameDB + " readonly>" + "<br/>";
                        $("#interview").append(html);
                    } else if (statusDB == 3) {
                        layer.alert("审核未通过请重新认证！", {icon: 0});
                        $(".auth_icon").text("审核拒绝");
                        $("#companyName").val(nameDB);
                        $("#linkman").val(linkmanDB);
                        $("#phone").val(phoneDB);
                        $("#interview").citySelect({prov: provinceNameDB, city: cityNameDB, dist: ""});
                        $("#busLicenseImg").attr('src', photoFileDB);
                        $("#uploadBusLicenseImg").css("display", "none");
                        $("#busLicenseImg").css('display', "block");
                        $(".auth_btn").text("重新认证");
                    }
                }
            }
        }
    });

    //照片上传
    $("#addBusLicenseImg").change(function () {
        var file = $("#imgBusLicenseForm").find("input")[0].files[0];
        var img = $("#busLicenseImg");
        loadImg(file, img);
        $("#uploadBusLicenseImg").css("display", "none");
    });


})

//照片回显
function busLicense() {
    $("#addBusLicenseImg").click();
}


function loadImg(file, img) {
    //创建读取文件的对象
    var reader = new FileReader();
    //创建文件读取相关的变量
    var imgFile;
    //为文件读取成功设置事件
    reader.onload = function (e) {
        imgFile = e.target.result;
        console.log(imgFile);
        img.attr('src', imgFile);
        img.css('display', "block");
    };
    //正式读取文件
    reader.readAsDataURL(file);
}

//提交认证
function submitAuth() {
    var lEmployeeId = $("#lEmployeeId").val();
    var name = $("#companyName").val();
    var provinceName = $(".prov").val();
    var cityName = $(".city").val();
    var linkman = $("#linkman").val();
    var phone = $("#phone").val();
    var formData = new FormData();
    var companyPhotoFile = $("#imgBusLicenseForm").find("input")[0].files[0];
    formData.append("companyPhotoFile", companyPhotoFile);
    formData.append("lEmployeeId", lEmployeeId);
    formData.append("name", name);
    formData.append("provinceName", provinceName);
    formData.append("cityName", cityName);
    formData.append("linkman", linkman);
    formData.append("phone", phone);
    //过滤输入值
    if (name.length == 0) {
        $("#companyName").focus();
        layer.alert("请输入公司名称", {icon: 0});
        return;
    }
    if (linkman.length == 0) {
        $("#linkman").focus();
        layer.alert("请输入联系人", {icon: 0});
        return;
    }
    if (phone.length == 0) {
        $("#phone").focus();
        layer.alert("请输入电话", {icon: 0});
        return;
    }
    //校验数据格式
    if (false == isMobile(phone)) {
        $("#phone").focus();
        layer.alert("手机号码格式不正确", {icon: 2});
        return;
    }
    if (name.length > 100) {
        $("#name").focus();
        layer.alert("公司名称格式不正确", {icon: 2});
        return;
    }
    if (false == isStr(linkman)) {
        $("#linkman").focus();
        layer.alert("联系人格式不正确", {icon: 2});
        return;
    }

    $.ajax({
        type: "POST",
        data: formData,
        url: strRootPath + "/company/certifiedCompanyInfo.do",
        async: false, // 同步上传，默认(true)异步
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            if (data != null) {
                if (data.resultCode != 0) {
                    layer.alert(data.resultMessage, {icon: 0});
                } else {
                    layer.msg('提交认证成功！', {
                        icon: 0,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        //do something
                        location.reload();
                    });
                }
            }
        }
    });
}

//电话格式
function isMobile(strMobile) {
    if (strMobile) {
        return (/^((\+?86)|(\+86))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[012356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/.test(strMobile));
    }
    return false;
}

//中文
function isStr(str) {
    if (str) {
        return (/^[a-zA-Z\u4e00-\u9fa5]+$/.test(str));
    }
    return false;
}

