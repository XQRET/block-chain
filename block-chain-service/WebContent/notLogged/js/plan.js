$(function() {
	if(sessionStorage.getItem('planChart')==null){
		sessionStorage.setItem('planChart','../images/notlogged/plan_pic.png');
	}
	$(".planChart").attr("src",sessionStorage.getItem('planChart'));
	$(".plan_advantage li").hover(function() {
		$(this).addClass("plancheck");
		$(".plancheck h1").eq(1).hide();
		$(".plancheck").fadeTo("slow",0.8);
	}, function() {
		$(this).removeClass("plancheck");
		$(this).find('h1').eq(1).show();
		$(this).fadeTo("slow",1);
	});
	/*核心业务流程的切换*/
	$(".open_flow li").click(function(){
		$(".plan_flow_item li").eq($(this).index()/2).addClass("pf_check").siblings("li").removeClass("pf_check");
	})
});