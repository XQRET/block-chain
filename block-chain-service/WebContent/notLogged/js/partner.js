$(function() {
	/*中间合作模式下面三个端的切换*/
	$(".partner_nav li").on("click",function(){
		var parindex=$(this).index();
		$(this).addClass("par_check").siblings().removeClass("par_check");
		$(".partner_item").eq(parindex).removeClass("dn").siblings(".partner_item").addClass("dn");
	});
	$(".partner_list li").on("click",function(){
		var listindex=$(this).index();
		$(this).addClass("parlist_check").siblings().removeClass("parlist_check");
		$(".parlist_item").eq(listindex).removeClass("dn").siblings(".parlist_item").addClass("dn");
	});
});