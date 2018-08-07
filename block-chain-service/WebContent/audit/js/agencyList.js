$(function() {
	init();
	bindSearch();
});

// 绑定查询事件
function bindSearch() {
	$("#doSearch").click(function(event) {
		var timestamp = Date.parse(new Date());
		var url = "agencyList.htm?t=" + timestamp;
		var target = event.currentTarget;
		var pid = $(target).attr("pid");
		var pDom = $("#" + pid);
		$("#agencyForm").find("select").each(function(index, dom) {
			var that = $(dom);
			var key = that.attr("id");
			var val = that.val().trim();
			if (val && val.length > 0) {
				url += "&" + key + "=" + val;
			}
		});
		url = encodeURI(url).replace(/#/g, "%23");
		window.location.href = url;
	});
}

// 初始化赋值
function init() {
	var type = _public.getVal("hdCompanyType") || 0;
	var status = _public.getVal("hdCompanyStatus") || 0;
	$("#selOrgType").find("option[value=" + type + "]").attr("selected",
			"selected");
	$("#selAuditState").find("option[value=" + status + "]").attr("selected",
			"selected");
	layui.form.render('select');
}
