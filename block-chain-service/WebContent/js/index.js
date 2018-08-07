 //加载头部
    $(document).ready(function () {
    	if(sessionStorage.getItem('banner2')==null){
    		sessionStorage.setItem('banner2','./images/notlogged/banner_bg_02.png');
    	}
    	if(sessionStorage.getItem('index_hy')==null){
    		sessionStorage.setItem('index_hy','./images/notlogged/index_hy_left.png');
    	}
    	$(".banner2").attr("src",sessionStorage.getItem('banner2'));
    	$(".index_hy").attr("src",sessionStorage.getItem('index_hy'));
        //其他用户金额信息
        $.ajax({
            type: "POST",
            url: getRootPath() + "/cockpit/showIndexInfo.do",
            dataType: "json",
            success: function (data) {
                if (data.resultCode == 0) {
                    var moneySums = data.resultDate.amountSums;
                    var curSums = data.resultDate.curSums;
                    var userSums = data.resultDate.userSums;
                    $("#statisticsInfo li:eq(0) label").text(moneySums);
                    $("#statisticsInfo li:eq(1) label").text(curSums);
                    $("#statisticsInfo li:eq(2) label").text(userSums);
                    setInterval(function () {
                        $.ajax({
                            type: "POST",
                            url: getRootPath() + "/cockpit/showIndexInfo.do",
                            dataType: "json",
                            success: function (data) {
                                var moneySums = data.resultDate.amountSums;
                                var curSums = data.resultDate.curSums;
                                var userSums = data.resultDate.userSums;
                                $("#statisticsInfo li:eq(0) label").text("");
                                $("#statisticsInfo li:eq(1) label").text("");
                                $("#statisticsInfo li:eq(2) label").text("");
                                $("#statisticsInfo li:eq(0) label").text(moneySums);
                                $("#statisticsInfo li:eq(0) label").countUp();
                                $("#statisticsInfo li:eq(1) label").text(curSums);
                                $("#statisticsInfo li:eq(1) label").countUp();
                                $("#statisticsInfo li:eq(2) label").text(userSums);
                                $("#statisticsInfo li:eq(2) label").countUp();
                            }
                        });
                    }, 10000);
                }
            }
        })
        //节点数
        $.ajax({
            type: "POST",
            url: getRootPath() + "/cockpit/showNodeInfo.do",
            dataType: "json",
            success: function (data) {
                if (data.resultCode == 0) {
                    var resultDate = eval(data.resultDate);
                    $("#statisticsInfo li:eq(3) label").text(resultDate.length);
                    setInterval(function () {
                        $.ajax({
                            type: "POST",
                            url: getRootPath() + "/cockpit/showNodeInfo.do",
                            dataType: "json",
                            success: function (data) {
                                var resultDate = eval(data.resultDate);
                                $("#statisticsInfo li:eq(3) label").text("");
                                $("#statisticsInfo li:eq(3) label").text(resultDate.length);
                                $("#statisticsInfo li:eq(3) label").countUp();
                            }
                        });
                    }, 10000);
                }
            }
        })

        $(".header").load("notLogged/header.html", function () {
        	if(sessionStorage.getItem('all_logoimg')==null){
        		sessionStorage.setItem('all_logoimg','images/logo.png');
        		}
        		$(".all_logo").attr("src","./"+sessionStorage.getItem('all_logoimg'));
            var checkparam = $("#check").val();
            $(".alltop_nav li").eq(parseInt(checkparam)).addClass("check").siblings().removeClass("check");
            /*循环给href加上前缀*/
            $('.alltop_nav li a').each(function () {
                if ($(this).attr('href') != undefined) {
                    var thehref = "notLogged/" + $(this).attr('href');
                    $(this).attr('href', thehref);
                }
            });
        });
            $(".footer").load("./notLogged/footer.html",function(){
            	if(sessionStorage.getItem('all_logoimg')==null){
        			sessionStorage.setItem('all_logoimg','images/logo.png');
        		}
            	/*循环给href加上前缀*/
    			var fc_number=0;
    			$('.f_center div a').each(function(){
    				fc_number++;
    				if($(this).attr('href')!=undefined&&fc_number<5){
    					var thehref="notLogged/"+$(this).attr('href');
    					$(this).attr('href',thehref);	
    				}
    			});
    			$(".f_slogan img").attr("src","./"+sessionStorage.getItem('all_logoimg'));
            });
            $(".index_house li").hover(function() {
        		$(this).addClass("incheck");
        		$(".incheck").fadeTo("slow",0.8);
        	}, function() {
        		$(this).removeClass("incheck");
        		$(this).fadeTo("slow",1);
        	});
    });
