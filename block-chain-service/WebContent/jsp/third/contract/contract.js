//初始化
$(function(){	
	//百度地图
	baidumap();
});

/** 
 * 百度地图 显示函数
 * @param address 传入地址
 * @param mapID   传入地图显示区域ID名称
 * @returns
 */
function mapShow(address, mapID) {
	var place = address;
	var map = new BMap.Map(mapID);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(place, function(point){
		if (point){
			map.centerAndZoom(point, 18);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
			
			var opts = {
			  width : 200,     // 信息窗口宽度
			  height: 100,     // 信息窗口高度
			  title : "地址：" , // 信息窗口标题
			  enableMessage:true,//设置允许信息窗发送短息
			  message:""
			}
			var infoWindow = new BMap.InfoWindow(place, opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(){          
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
		}
	});
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	map.addControl(top_left_control);            
	map.addControl(top_right_navigation);
}

//百度地图
function baidumap(){
	//动态给place赋值
	//var var place = $("p[data-house-address]").attr('data-house-address');
	mapShow('东莞黄江黄江镇陂头巷45号锦绣花园10座7B','b-map');
	mapShow('东莞黄江黄江镇陂头巷45号锦绣花园10座7B','b-map2');
}
$(".nc_outline tr:odd").css("background", "#fafafa")
/* 切换部分 */
$(".nc_nav li").on('click',
function() {
  var navindex = parseInt($(this).index());
  $(this).addClass("nc_liactive").siblings().removeClass("nc_liactive");
  $(".nc_item").eq(navindex).addClass("nc_active").siblings().removeClass("nc_active");
});
layui.use(['layer'],
function() {
  var layer = layui.layer;
  layer.photos({
    photos: '#photo-list',
    shadeClose: true,
    closeBtn: 0,
    shift: 5
  });
  layer.photos({
    photos: '#photo-list-leaseContract',
    shadeClose: true,
    closeBtn: 0,
    shift: 5
  });
  layer.photos({
    photos: '#photo-list-purchase',
    shadeClose: true,
    closeBtn: 0,
    shift: 5
  });
  layer.photos({
    photos: '#photo-list-property',
    shadeClose: true,
    closeBtn: 0,
    shift: 5
  });
  layer.photos({
    photos: '#rentVoucherUrl-list',
    shadeClose: true,
    closeBtn: 0,
    shift: 5
  });
  layer.photos({
      photos: '#screenshot',
      shadeClose: true,
      closeBtn: 0,
      shift: 5
    });
  layer.photos({
      photos: '#leaseContract-list',
      shadeClose: true,
      closeBtn: 0,
      shift: 5
    });
});
