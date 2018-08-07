$(function(){
	setTimeout(function(){
		$(".header .w80b,.changess").removeClass("dn");
		$(".index_slogan,.news_nav,.about_nav").show();
		}, 1000);
	var loadsparam = $("#loads").val();
	if (self != top) { 
	if(parseInt(loadsparam)!=1){
	var all_index =layer.load(2, {
		shade: [1,'#F5F5F5'] //1透明度的白色背景
	});  
	setTimeout(function(){
		$(".layui-layer-shade").hide();
		$(".layui-layer-loading").hide();
		}, 1000);	
	$(".body-content").show();
	}
	}
	
})

    //js获取项目根路径，如： http://localhost:8083/uimcardprj
    function getRootPath() {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    }
$(document).on('click','.changess',function(){
	translate();
	parent.location.reload();
	});
var _public = {};

	/**
	 * 验证是否是手机号
	 */
	_public.isMobile = function(strMobile){
		if(strMobile)
		{
			return (/^1[3578]\d{9}|14[357]\d{8}$/.test(strMobile));
		}
		return false;
	};

	/**
 	 * 获取dom对象
 	 * @param {} id
 	 * @return {}
 	 */
 	_public.getDom=function(id){
		return document.getElementById(id);
	};
	
	/**
 	 * 获取dom对象的value
 	 * @param {} id
 	 */
	_public.getVal = function(id){
		var val = document.getElementById(id).value || "";
		return val.toString().trim();
	}
	
 	/**
	 * 显示div
	 * @param {} dom  dom对象或者id
	 * @param {} flag true-显示;false-隐藏
	 */
	_public.show = function(dom,flag){
		if((typeof dom === 'string') && dom.constructor==String){dom = document.getElementById(dom)}
		if(dom){
			var _style = flag == true ? "block" : "none";
			dom.style.display = _style;
		}
	};
	
	/**
	 * 判断是否为数组
	 * @param {} o
	 * @return {}
	 */
	_public.isArray=function(o){
	    return Object.prototype.toString.call(o) === '[object Array]';
	}
	
	
	/*******************************************以下是给原生的对象添加新的方法****************************************************/
	
	/**
	 * 去除前后空格
	 * @return {}
	 */
	String.prototype.trim = function(){ 
	    return this.replace(/(^\s*)|(\s*$)/g, ""); 
	};
	
	/**
	 * 时间格式化
	 * @param {} format
	 * @return {}
	 * 
		var now = new Date();
		var nowStr = now.format("yyyy-MM-dd hh:mm:ss");
		
		var testDate = new Date();
		var testStr = testDate.format("YYYY年MM月dd日hh小时mm分ss秒");
	 * 
	 */
	Date.prototype.format =function(format){
	    var o = {
	    "M+" : this.getMonth()+1, //month
	    "d+" : this.getDate(), //day
	    "h+" : this.getHours(), //hour
	    "m+" : this.getMinutes(), //minute
	    "s+" : this.getSeconds(), //second
	    "q+" : Math.floor((this.getMonth()+3)/3), //quarter
	    "S" : this.getMilliseconds() //millisecond
	    }
	 
	    if(/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	    }
	 
	    for(var k in o) {
	        if(new RegExp("("+ k +")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	        }
	    }
	    return format;
	};


	/*全屏弹出层*/
	function openView(url,title){
		var _index = layer.open({
			  type: 2,
			  content: url,
			  area: ['100%', '100%'],
			  anim: 2,
			  title:[title,'font-size:18px;'],
			  cancel: function(index, layero){ 
				 
				}   
			});
		layer.full(_index);
	}
	/*遮罩层*/
	function showDialog(id) {
		var bh = document.body.clientHeight;
		if($("body").height()>=bh){
			bh=$("body").height();
		}
		var bw = $("body").width();
		$("#e_st_fullbg").css({
			height:bh,
			width:bw,
			display:"block"
		});
		$("#"+id).show();
		var navH = $(".close").offset().top;
	}

	/*关闭全屏弹出层后清空弹出的内容,firefox的iframe中引用会有没清空的问题*/
	$(document).on('click','.layui-layer-setwin',function(){
		$(".layer-anim-close").remove();
	});

	//点击灰色部分关闭弹窗
	function closefbE(){
		$("#plays").html("");
		closeBgE();
	}
	function closeBgE(){
		$(".e_st_fullbg").hide();
		$(".e_st_dialog").hide();
	};
	/*加载底部页面*/
	$(document).ready(function(){
		$(".footer").load( strRootPath +"/notLogged/footer.html",function(){
			$(".f_left img").attr('src',strRootPath +"/images/reg/footer_left.png");
			$(".reg_rqcode img").attr('src',strRootPath +"/images/reg/reg_rqcode.png");
			if(sessionStorage.getItem('all_logoimg')==null){
				sessionStorage.setItem('all_logoimg','./images/logo.png');
			}
			$(".f_slogan img").attr("src",strRootPath+"/"+sessionStorage.getItem('all_logoimg'));
			/*循环给href加上前缀*/
			var fc_number=0;
			$('.f_center div a').each(function(){
				fc_number++;
				if($(this).attr('href')!=undefined&&fc_number<5){
					var thehref="notLogged/"+$(this).attr('href');
					$(this).attr('href',thehref);	
				}
			});
			$("head").prepend("<meta http-equiv ='proma' content = 'no-cache'/>"
					+"<meta http-equiv='cache-control' content='no cache' />"
					+"<meta http-equiv='expires' content='0' />");
		});
	});
	
	//查看大图
	//给缩略图添加点击事件，点击之后放大
		$(document).on('click','.thumbnail',function(){
			if($(this).attr('src') != ""){
				$('.mask').css('display','block');
				var img_src = $(this).attr('src');
				$('.big-img').css('display','block').children('img').attr('src',img_src);
			}
		//给遮罩层添加点击事件,点击之后退出放大图模式
		$('.big-img').bind('click', function() {
			$('.mask').css('display','none');
			$('.big-img').css('display','none');
		})
		$('.big-img img').bind('click', function() {
			event.stopPropagation();
		})
	});