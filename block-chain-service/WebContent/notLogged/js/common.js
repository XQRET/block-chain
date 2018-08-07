var laypage = layui.laypage,layer = layui.layer,laydate = layui.laydate,layform = layui.form;

/*加载头部底部页面*/
$(document).ready(function(){
	$("head").prepend("<meta http-equiv ='proma' content = 'no-cache'/>"
			+"<meta http-equiv='cache-control' content='no cache' />"
			+"<meta http-equiv='expires' content='0' />");
	$(".header").load("header.html",function(){
		$(".all_logo").attr('src',"../images/logo.png");
		$(".alltopright a").each(function(){
			if($(this).attr('href')!=undefined){
			var thehref="."+$(this).attr('href');
			$(this).attr('href',thehref);	
		}
		})
		var checkparam=$("#check").val();
		$(".alltop_nav li").eq(parseInt(checkparam)).addClass("check").siblings().removeClass("check");
		if(sessionStorage.getItem('all_logoimg')==null){
		sessionStorage.setItem('all_logoimg','images/logo.png');
		}
		$(".all_logo").attr("src","../"+sessionStorage.getItem('all_logoimg'));
	});
	$(".footer").load("footer.html",function(){
		if(sessionStorage.getItem('all_logoimg')==null){
			sessionStorage.setItem('all_logoimg','images/logo.png');
		}
		$(".footer img").each(function(){
			if($(this).attr('src')!=undefined){
			var thesrc="."+$(this).attr('src');
			$(this).attr('src',thesrc);	
		}
		});
		$(".f_slogan img").attr("src","../"+sessionStorage.getItem('all_logoimg'));
	});
});

/**
 * 数据绑定
 * @param dataUrl 数据接口地址
 * @param tplUrl  模板地址
 * @param viewId  数据展示容器id
 * @param param   数据接口请求参数
 */
function bindingDataWithParam(dataUrl,tplUrl,viewId,param) {
	//正在查詢，请求到数据之后用layer.close(index); 关闭这个层
	var index = layer.load(0, {shade: [0.7,'#fff']}); 
	
	$.post(dataUrl,param,function(data){
		var res = eval('(' + data + ')');
		if (null == res || '' == res || undefined == res || -1 == res.code || res.res == undefined || null == res.res) {
			$("#"+viewId).html("未查询到相应信息");
			return ;
		}
		bindingData(res.res,tplUrl,viewId);
	});
}

/**
 * 模板数据绑定通用方法
 * @param tplData
 * @param tplUrl
 * @param viewId
 */
function bindingData(tplData,tplUrl,viewId) {
    $.post(tplUrl,function(data){
		var view = document.getElementById(viewId);
		layui.use('laytpl', function(){
			var laytpl = layui.laytpl;
			laytpl(data).render(tplData, function(html){
				view.innerHTML = '';
				view.innerHTML = html;
			});
		});
	});
}


/* 数据显示检查是否为空*/
layui.empty = function(data){
    if (null == data || undefined == data) {
        return '';
    }
    return data;
}

layui.toJsonStr = function(data){
    if (null == data || undefined == data) {
        return '';
    }
    return JSON.stringify(data);
}
layui.returnFloat = function(data){
    if (null == data || undefined == data) {
        return '';
    }
    return data.toFixed(2);
}



/* 银联数据排序月份*/
layui.unionpaySortMonth = function(data){
    var monthArray = new Array();
    var i = 0;
    for (var key in data) {
        monthArray[i] = key;
        i++;
    }
    monthArray.sort();
    return monthArray;
}


/*时间戳的处理*/
layui.toDateString = function(d, format){
    var date = new Date(d || new Date())
        ,ymd = [
        this.digit(date.getFullYear(), 4)
        ,this.digit(date.getMonth() + 1)
        ,this.digit(date.getDate())
    ]
        ,hms = [
        this.digit(date.getHours())
        ,this.digit(date.getMinutes())
        ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
        .replace(/MM/g, ymd[1])
        .replace(/dd/g, ymd[2])
        .replace(/HH/g, hms[0])
        .replace(/mm/g, hms[1])
        .replace(/ss/g, hms[2]);
};

/*时间戳的处理空显示空*/
layui.toDateStringNull = function(d, format){
    if(d==null||d==""||d==undefined){
        return "";
    }
    var date = new Date(d || new Date())
        ,ymd = [
        this.digit(date.getFullYear(), 4)
        ,this.digit(date.getMonth() + 1)
        ,this.digit(date.getDate())
    ]
        ,hms = [
        this.digit(date.getHours())
        ,this.digit(date.getMinutes())
        ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
        .replace(/MM/g, ymd[1])
        .replace(/dd/g, ymd[2])
        .replace(/HH/g, hms[0])
        .replace(/mm/g, hms[1])
        .replace(/ss/g, hms[2]);
};


/*转字符串保留两位小数*/
layui.toString = function(value){
    var value=Math.round(parseFloat(value)*100)/100;
    var xsd=value.toString().split(".");
    if(xsd.length==1){
        value=value.toString()+".00";
        return value;
    }
    if(xsd.length>1){
        if(xsd[1].length<2){
            value=value.toString()+"0";
        }
        return value;
    }
};

/*数字前置补零*/
layui.digit = function(num, length, end){
    var str = '';
    num = String(num);
    length = length || 2;
    for(var i = num.length; i < length; i++){
        str += '0';
    }
    return num < Math.pow(10, length) ? str + (num|0) : num;
};

//字符串格式化
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}


/*全屏弹出层*/
function openView(url,title){
    var _index = layer.open({
        type: 2,
        content: url,
        area: ['100%', '100%'],
        anim: 2,
        title:[title,'font-size:18px;'],
        cancel: function(index, layero){
            reviewHouseInfoCB();
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
//日期转换
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return Y + M + D;
}