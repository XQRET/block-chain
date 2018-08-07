var layform = layui.form;

/* 文件下载和预览 */
var u = navigator.userAgent,
	app = navigator.appVersion;
var theie = u.indexOf('Trident') > -1 // IE内核
if (theie) {
	$(".con-down span").find("a").removeAttr("target");
	$(".con-down span").find("a").click(function() {
		layer.alert('该浏览器暂不支持预览，请直接下载或者换个浏览器', {
			icon: 2
		});
	});
}
/* 文件下载和预览--end-- */

/*全选*/
var $ = layui.jquery,
	form = layui.form;
form.on('checkbox(allChoose)', function(data) {
	var child = $(data.elem).parent().parent().next().next().find('input[type="checkbox"]');
	child.each(function(index, item) {
		item.checked = data.elem.checked;
	});
	form.render('checkbox');
});
/*全选 end*/

$(function() {
	initImg();
	initDownload();
});

function initImg() {
	var $img_person = $("#hdImgPerson").val();
	var $img_house = $("#hdImgHouse").val();
	// 禁用数据全选
	if (!$img_person) {
		var $checkbox = $("input:checkbox:first");
		$checkbox.attr("disabled", "disabled");
	}
	if (!$img_house) {
		var $checkbox = $("input:checkbox:last");
		$checkbox.attr("disabled", "disabled");
	}
	if (!$img_person && !$img_house) {
		return;
	}
	var $img = $img_person + "," + $img_house;
	var imgs = $img.split(",");
	for (var i = 0; i < imgs.length; i++) {
		switch (imgs[i]) {
			//数据库表t_system_param
			case CONTRACT_CONSTANT.ID_CARD:
				imgHtml("#dtPerson", imgs[i + 1], "身份证信息");
				break;
				//手机
			case CONTRACT_CONSTANT.PHOTO_INFO:
				imgHtml("#dtPerson", imgs[i + 1], "手机实名");
				break;
				//查档单
			case CONTRACT_CONSTANT.PERSON_CREDIT:
				imgHtml("#dtPerson", imgs[i + 1], "个人征信报告");
				break;
			case CONTRACT_CONSTANT.HOUSE_CARD:
				imgHtml("#dtHouse", imgs[i + 1], "房产证信息");
				break;
				//按揭合同
			case CONTRACT_CONSTANT.MORTAGE_CONTRACT:
				imgHtml("#dtHouse", imgs[i + 1], "按揭合同");
				break;
				//查档单
			case CONTRACT_CONSTANT.CONSULT_ORDER:
				imgHtml("#dtHouse", imgs[i + 1], "查档单");
				break;
			default:
				break;
		}
	}
}

/**
 * 绑定下载事件
 */
function initDownload() {
	$(".condown_btn").bind("click", function() {
		layer.confirm("您确定下载吗?", {
			btn: ['确定', '取消']
				// 按钮确定事件
		}, function(index) {
			// 截取使用 
			// 不需要阿里云前缀 https://blockoss.oss-cn-shenzhen.aliyuncs.com/后面是它的fileID
			var prexIndex = 46;
			// url字符串
			var urls;
			// 存储url 获取所有的选中标签后面的a标签的href做下载使用
			var arr = [];
			var checked = $(".layui-form-checked");
			$.each(checked, function(i, e) {
				var a = $(e).next();
				var href = a.attr("href");
				if (href) {
					// 只要文件的fileID 类似于uri
					arr.push(href.substring(prexIndex));
				}
			});

			// 获取url,url的形式
			urls = arr.join(",");
			// 发送ajax请求
			var host = strRootPath + "/contract/download.do";

			var params = {
				urls: urls
			};

			$.ajax({
				type: "POST",
				url: host,
				data: params,
				success: function(response, status, request) {
					var disp = request.getResponseHeader('Content-Disposition');
					//判断是否为文件
					if (disp && disp.search('attachment') != -1) {
						var form = $('<form method="POST" action="' + host + '">');
						$.each(params, function(k, v) {
							form.append($('<input type="hidden" name="' + k +
								'" value="' + v + '">'));
						});
						$('body').append(form);
						//自动提交
						form.submit();
					}
				}
			});
			layer.close(index);
		}, function(index) {
			layer.close(index);
		});
	});
}

/**
 * 在指定id后面拼接图片
 * @param {Object} id 需要拼接的id
 * @param {Object} img 拼接的内容
 * @param {Object} message 消息
 */
function imgHtml(id, img, message) {
	var img_html = $("<span><input type='checkbox' lay-skin='primary'><a href='" + img + "' target='_blank'>" + message + "</a></span>");
	$(id).append(img_html);
	layform.render();
}