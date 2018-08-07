$(function() {
	/*中间合作模式下面三个端的切换*/
	$(".news_nav li").on("click",function(){
		var parindex=$(this).index();
		$(this).addClass("bgff").siblings().removeClass("bgff");
		$(".news_item").eq(parindex).removeClass("dn").siblings(".news_item").addClass("dn");
	});
});