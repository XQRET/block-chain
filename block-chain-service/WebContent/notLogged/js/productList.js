$(function () {
    //初始化长租
    initLongRentedApartment(getRootPath());
    // 初始化海外房产列表
    initOverseas(getRootPath());
})

//初始化长租
function initLongRentedApartment(rootPath) {
    $.ajax({
        type: "POST",
        url: getRootPath() + "/contract/queryApartment.do",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                $("#LongRentedApartment").empty();
                var html = "";
                if (data.resultCode == 0) {
                    var dataArray = eval(data.resultDate);
                    layui.each(dataArray, function (index, item) {
                        var registDate = timestampToTime(item.blockContract.registDate);
                        var houseinfoArray = item.blockContract.houseInfo.split("|");
                        var amount = formatNum(item.blockContract.amount+"");
                        var houseInfo; //房屋信息
                        if (houseinfoArray.length > 1) {
                            houseInfo = houseinfoArray[1];
                        } else {
                            houseInfo = item.blockContract.houseInfo;
                        }
                        html += '<li><a target="_blank" href=' + item.contractUrl + '>';
                        if (item.contractFileUrl == null || item.contractFileUrl.fileUrl == null) {
                            html += '<img src="' + rootPath + '/images/notlogged/short_1.png" />';
                        } else {
                            html += '<img src=' + item.contractFileUrl.fileUrl + ' />';
                        }
                        html += '<p class=\"hl_1\">' +
                            '<label>' + houseInfo + '</label>' +
                            '<span class=\"cf8\">' + amount + '元</span>' +
                            '<span>' + item.blockContract.term + '个月</span></p>' +
                            '<p class=\"hl_2\">' +
                            '<label><span>登记日期：</span>' + registDate + '</label>' +
                            '<span>合约价格</span>' +
                            '<span>期限</span></p>' +
                            '</a>' +
                            '</li>';
                    })
                    $("#LongRentedApartment").append(html);
                }
            }
        }
    })
}

/**
 * 初始化海外房产列表
 */
function initOverseas(rootPath) {
    $.ajax({
        type: "post",
        url: getRootPath() + "/contract/queryOverseas.do",
        success: function (data) {
            if (data) {
                // 清空
                $("#ulOverseas").empty();
                var html = "";
                if (data.resultCode == 0) {
                    layui.each(data.resultDate, function (index, item) {
                        // 页面取值对象
                        var jsonObj = eval('(' + item.blockContract.attachInfoJson + ')');
                        // 发行价格
                        var house_total_amount = jsonObj.houseTotalAmount;
                        var totalAmount = formatNum(house_total_amount);
                        // 单价
                        var price = Number(house_total_amount / 2000);
                        // 募集时间开始时间
                        var beginTime = timestampToTime(item.blockContract.registDate);
                        var endTime = timestampToTime(item.blockContract.contractExpire);
                        // 募集进度
                        var collect_progress = collectProgress();
                        html += '<li><a target="_blank" href="' + item.contractUrl + '"><div class="hlist_left">';
                        if (item.contractFileUrl == null || item.contractFileUrl.fileUrl == null) {
                            html += '<img src="' + rootPath + '/images/notlogged/house_sz_3.png" />';
                        } else {
                            html += '<img src=' + item.contractFileUrl.fileUrl + ' />';
                        }
                        html += '<em>在售</em><h2>' + jsonObj.houseAddress + '</h2>' +
                            '<p><label>房产类型：</label>' + jsonObj.houseApartments + '</p>' +
                            '<p><label>户<b></b>型：</label>' + jsonObj.houseType + '</p>' +
                            '<p><label>面<b></b>积：</label>' + jsonObj.houseSize + '</p>' +
                            '<span>租金+房产增值 预期年化15%</span></div>' +
                            '<div class="hlist_right"><div class="bbe8 plr25"><p>' +
                            '<label>募集时间：</label>' + beginTime + "至" + endTime + '</p>' +
                            '<div>募集进度</label><span>' + collect_progress + '%</span></div>' +
                            '<div class="layui-progress"><div class="layui-progress-bar" lay-percent="60%" style=width:' + collect_progress + '%;></div>' +
                            '</div></div><dl><dt><pre class="cf8">' + totalAmount + '</pre><pre>发行价格(元)</pre></dt>' +
                            '<dt><pre>' + jsonObj.numberOfHairstyles + '</pre><pre>发行数量(份)</pre></dt>' +
                            '<dt><pre class="cf8">' + price + '</pre><pre>单价(元/份)</pre></dt>' +
                            '<dt><pre>0.1㎡</pre><pre>最小单位</pre></dt>' +
                            '</dl></div></a></li>';
                    });
                    $("#ulOverseas").append(html);
                }
            }
        }
    });
}

/**
 * 生成募集进度 前期先随机 后期使用第三方对接数据
 */
function collectProgress() {
    return Math.ceil(Math.random() * 100);
}

//数字每三位逗号隔开
function formatNum(str) {
    str=str.split(".")[0];
    var newStr = "";
    var count = 0;
    for (var i = str.length - 1; i >= 0; i--) {
        if (count % 3 == 0 && count != 0) {
            newStr = str.charAt(i) + "," + newStr;
        } else {
            newStr = str.charAt(i) + newStr;
        }
        count++;
    }
    return newStr;
}

//日期转换
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return Y + M + D;
}

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