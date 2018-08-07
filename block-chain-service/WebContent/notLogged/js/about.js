$(function() {
	if(sessionStorage.getItem('profile')==null){
		sessionStorage.setItem('profile','../images/notlogged/profile.png');
	}
	$(".profile").attr("src",sessionStorage.getItem('profile'));
	//获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
	var aboutId = parseInt(getUrlParam('aboutId'));
	$(".about_nav li").eq(aboutId).addClass("bgff").siblings().removeClass("bgff");
	$(".about_item").eq(aboutId).removeClass("dn").siblings(".about_item").addClass("dn");
	/*中间合作模式下面三个端的切换*/
	$(".about_nav li").on("click",function(){
		var parindex=$(this).index();
		$(this).addClass("bgff").siblings().removeClass("bgff");
		$(".about_item").eq(parindex).removeClass("dn").siblings(".about_item").addClass("dn");
	});
});