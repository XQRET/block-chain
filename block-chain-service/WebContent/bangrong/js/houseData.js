$(function() {
	initImg();
});

function initImg() {
	var $img = $("#hdImgs").val();
	if (!$img) {
		$("#divHouseCard").text("");
		$("#divMortgageContract").text("");
		$("#divConsultOrder").text("");
		var span_text=$("<span style='color:red'>暂无图片</span>");
		span_text.insertBefore($("#divHouseCard"));
		return;
	}

	var imgs = $img.split(",");
	for (var i = 0; i < imgs.length; i++) {
		switch (imgs[i]) {
			//数据库表t_system_param
			case CONTRACT_CONSTANT.HOUSE_CARD:
				var img = imgs[i + 1];
				var house_image = $("<img class='thumbnail' src='" + img + "'/>");
				house_image.insertAfter($("#divHouseCard"));
				break;
				//按揭合同
			case CONTRACT_CONSTANT.MORTAGE_CONTRACT:
				var img = imgs[i + 1];
				var mortgage_image = $("<img class='thumbnail' src='" + img + "'/>");
				mortgage_image.insertAfter($("#divMortgageContract"));
				break;
				//查档单
			case CONTRACT_CONSTANT.CONSULT_ORDER:
				var img = imgs[i + 1];
				var consult_image = $("<img class='thumbnail' src='" + img + "'/>");
				consult_image.insertAfter($("#divConsultOrder"));
				break;
			default:
				break;
		}
	}
}