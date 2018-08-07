$(".pd_tab p").click(function() {
	var thisnum = $(this).index();
	$(this).addClass("pdcheck").siblings().removeClass("pdcheck");
	$(".tabinfo").eq(thisnum).show().siblings(".tabinfo").hide();
});


$(function() {
	// 根据身份证号判断年龄 性别
	initInfo();
	// 初始化图片
	initImg();
	// 初始化期限
	initTerm();

});

/**
 * 检查身份证号
 * @param {Object} pId
 */
function checkID(pId) {
	if (!pId) {
		return;
	}
	//检查身份证号码   
	var arrVerifyCode = [1, 0, "x", 9, 8, 7, 6, 5, 4, 3, 2];
	var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
	var Checker = [1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1];
	if (pId.length != 15 && pId.length != 18) return "身份证号共有15位或18位";
	var Ai = pId.length == 18 ? pId.substring(0, 17) : pId.slice(0, 6) + "19" + pId.slice(6, 16);
	if (!/^\d+$/.test(Ai)) return "身份证除最后一位外，必须为数字！";
	var yyyy = Ai.slice(6, 10),
		mm = Ai.slice(10, 12) - 1,
		dd = Ai.slice(12, 14);
	var d = new Date(yyyy, mm, dd),
		now = new Date();
	var year = d.getFullYear(),
		mon = d.getMonth(),
		day = d.getDate();
	if (year != yyyy || mon != mm || day != dd || d > now || year < 1800) return "身份证输入错误！";
	for (var i = 0, ret = 0; i < 17; i++) ret += Ai.charAt(i) * Wi[i];
	Ai += arrVerifyCode[ret %= 11];
	return pId.length == 18 && pId != Ai ? "身份证输入错误！" : Ai;
};

//根据身份证取 省份,生日，性别  
function initInfo() {
	//获取输入身份证号码  
	var ic = $("#hdIDNo").val();
	ic = checkID(ic);
	if (isNaN(ic)){
		$('#lblSex').html("暂无");
		$("#bAge").after("暂无");
		return;
	}
	var ic = String(ic);
	//获取出生日期  
	var birth = ic.substring(6, 10) + "-" + ic.substring(10, 12) + "-" + ic.substring(12, 14);
	//获取性别  
	// 1代表男性，2代表女性  
	var gender = ic.slice(14, 17) % 2 ? "男" : "女";
	$('#lblSex').html(gender);

	//获取年龄  
	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();
	var age = myDate.getFullYear() - ic.substring(6, 10) - 1;
	if (ic.substring(10, 12) < month || ic.substring(10, 12) == month && ic.substring(12, 14) <= day) {
		age++;
	}
	$("#bAge").html(age);
	$("#bAge").after("岁");
}

function initImg() {
	var $img = $("#hdImg").val();
	if (!$img) {
		return;
	}

	var imgs = $img.split(",");
	for (var i = 0; i < imgs.length; i++) {
		switch (imgs[i]) {
			//数据库表t_system_param
			case CONTRACT_CONSTANT.PERSON_CREDIT:
				// 1.判断浏览器类型 如果不是IE
				var browser_version=IEVersion();
				if (browser_version==-1) {
					// 使用iframe
					var iframe_pdf = $("<iframe width='800' height='460' src='" + imgs[i + 1] + "'></iframe>");
					$("#divPDF").append(iframe_pdf);
				}
				// 2.如果是IE
				else{
					switch (browser_version) {
					case 9:
					case 10:	
					case 11:
					case "edge":
						// 也使用iframe 不过src不同
						var iframe_pdf = $("<iframe width='800' height='460' src=''></iframe>");
						// 传递文件
						iframe_pdf.attr("src","../pdf/web/viewer.html?file="+encodeURIComponent(strRootPath+"/contract/displayPDF.do?id="+imgs[i+1]));
						$("#divPDF").append(iframe_pdf);
						break;
					default:
						$("#divPDF").append("<span>浏览器版本不支持,请更换浏览器</span>");
						break;
					}
				}
				
				break;
			case CONTRACT_CONSTANT.ID_CARD:
				var id_image = $("<div class='pd_idcard'><img class='thumbnail' src='" + imgs[i + 1] + "'/></div>");
				$("#divID").append(id_image);
				break;
			case CONTRACT_CONSTANT.PHOTO_INFO:
				var phone_image = $("<img class='thumbnail hauto w350' src='" + imgs[i + 1] + "'/>");
				$("#divPhone").append(phone_image);
				break;
			default:
				break;
		}
	}
}

/**
 * 初始化还款期限
 */
function initTerm() {
	var term = $("#hdTerm").val();
	if (!term) {
		$("#bTerm").text("暂无");
		return;
	}
	$("#bTerm").after("个月");
}

/**
 * 判断浏览器是不是IE
 * @returns
 */
function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }   
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11  
    }else{
        return -1;//不是ie浏览器
    }
}