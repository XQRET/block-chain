$(".ht_c1 li").click(function(){
	$(".ht_c1 li").removeClass("rowcheck");
	$(this).addClass("rowcheck");
	$("body").animate({scrollTop:0});
	/*获取点击的菜单的链接切换右侧内容，不绑定数据只切换的的写法*/
	var _url = $(this).attr("data-tpl"); 
	$("#data_view").load(_url,function(data){ 
	$(this).html(data); 
	}) 
	/*数据绑定的js代码写在common.js里面了 下面这句是调用*/
	// bindingDataWithParam($(this).attr("data-url"), $(this).attr("data-tpl"), "data_view", $("#pre-loan-report-form").serialize());
})
/*右侧默认显示内容设置*/
bindingData(1,'./template/develop-doc-list1.tpl','data_view');

$(window).scroll(function (){
	//固定leftcontent
	if ($(document).scrollTop() > '65') {
		$('.leftcontent').offset({top:$(document).scrollTop()});
	}else if($(document).scrollTop() <= '65') {
		$('.leftcontent').offset({top:65});
	};

});