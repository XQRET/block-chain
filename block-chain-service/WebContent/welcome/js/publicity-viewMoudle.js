"use strict";
var nodeInfo;
var nodeArrList = [
    {'top': '346px', 'left': '420px'}, {'top': '358px', 'left': '420px'}, {'top': '160px', 'left': '470px'},
    {'top': '248px', 'left': '457px'}, {'top': '173px', 'left': '458px'}, {'top': '272px', 'left': '469px'},
    {'top': '272px', 'left': '482px'}, {'top': '148px', 'left': '457px'}, {'top': '86px', 'left': '507px'},
    {'top': '149px', 'left': '408px'}, {'top': '234px', 'left': '223px'}, {'top': '358px', 'left': '396px'},
    {'top': '296px', 'left': '371px'}, {'top': '309px', 'left': '420px'}, {'top': '284px', 'left': '445px'},
    {'top': '259px', 'left': '494px'}, {'top': '284px', 'left': '347px'}, {'top': '173px', 'left': '482px'},
    {'top': '248px', 'left': '433px'}, {'top': '210px', 'left': '322px'}, {'top': '198px', 'left': '433px'},
    {'top': '173px', 'left': '433px'}, {'top': '358px', 'left': '433px'}, {'top': '346px', 'left': '458px'},
    {'top': '247px', 'left': '481px'}, {'top': '234px', 'left': '469px'}, {'top': '346px', 'left': '433px'},
    {'top': '160px', 'left': '444px'}, {'top': '136px', 'left': '494px'}, {'top': '346px', 'left': '444px'},
    {'top': '358px', 'left': '344px'}, {'top': '183px', 'left': '394px'}, {'top': '183px', 'left': '422px'},
    {'top': '358px', 'left': '347px'}, {'top': '183px', 'left': '496px'}, {'top': '183px', 'left': '427px'},
    {'top': '358px', 'left': '349px'}, {'top': '183px', 'left': '498px'}, {'top': '183px', 'left': '425px'},
    {'top': '358px', 'left': '352px'}, {'top': '183px', 'left': '499px'}, {'top': '183px', 'left': '429px'},
    {'top': '358px', 'left': '355px'}, {'top': '183px', 'left': '500px'}, {'top': '183px', 'left': '412px'},
    {'top': '358px', 'left': '353px'}, {'top': '183px', 'left': '488px'}, {'top': '183px', 'left': '414px'},
    {'top': '358px', 'left': '357px'}, {'top': '183px', 'left': '460px'}, {'top': '183px', 'left': '416px'},
    {'top': '358px', 'left': '359px'}, {'top': '183px', 'left': '462px'}, {'top': '183px', 'left': '418px'},
    {'top': '193px', 'left': '361px'}, {'top': '189px', 'left': '468px'}, {'top': '183px', 'left': '420px'}];

$(function () {
    getNodeInfo();
});

//logo滚动播放
function logoSwiper() {
    $(".community-appLogo-wrap").animate({'margin-left': '-84px'}, 3000, 'linear', function () {
        $(".community-appLogo-wrap img:eq(0)").appendTo($(".community-appLogo-wrap"));
        $(".community-appLogo-wrap").css("margin-left", "0px");
    });
}

//节点详情
function viewNode() {
    $('.animate-wrap').on('mouseover', ".node-group", function () {
        var companyNodeInfo = eval(nodeInfo.resultDate[$(this).index() - 2]);
        if (companyNodeInfo.companyPhoto == null) {
            $(".node-logo").attr("src", config.bumengLogoSrc + "loading.gif");
        } else {
            $(".node-logo").attr("src", companyNodeInfo.companyPhoto.url);
        }
        $(".app-name").text(companyNodeInfo.blockCompany.companyName);
        $(".node-place").text(companyNodeInfo.blockCompany.provinceName);
        $('.nodeInfo-bg').show().css({
            'top': parseInt($(this).css('top').substring(0, $(this).css('top').length - 2)) + 10 + 'px',
            'left': parseInt($(this).css('left').substring(0, $(this).css('left').length - 2)) + 10 + 'px',
            'z-index': 9999
        });
        return false;
    });
    $('.animate-wrap').on('mouseout', ".node-group", function () {
        $('.nodeInfo-bg').hide();
        return false;
    });

}

function getNodeInfo() {
    var nodeArr = new Array();
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cockpit/showNodeInfo.do",
        dataType: "json",
        success: function (data) {
            if (data.resultCode == 0) {
                nodeInfo = data;
                for (var i = 0; i < data.resultDate.length; i++) {
                    nodeArr.push(nodeArrList[i]);
                }
                //轮播插入图片
                $(".community-appLogo-wrap").empty();
                var appLogoHtml = "";
                for (var i in data.resultDate) {
                    var companyInfo = eval(nodeInfo.resultDate[i]);
                    if (companyInfo.companyPhoto != null) {
                        appLogoHtml += '<img src="' + companyInfo.companyPhoto.url + '"  class="community-app-logo" alt="" />';
                    }
                }
                $(".community-appLogo-wrap").append(appLogoHtml);
                //节点详情
                viewNode();
                //logo滚动播放
                setInterval(logoSwiper, 0);
                //渲染节点
                for (var i = 0; i < nodeArr.length; i++) {
                    renderNode(nodeArr[i].top, nodeArr[i].left);
                    if (i == 3 || i == 5 || i == 7 || i == 8 || i == 11 || i == 13) {
                        $(".animate-wrap").children().eq(i).addClass("node-group1");
                    } else if (i == 2 || i == 4 || i == 9 || i == 15 || i == 18 || i == 21) {
                        $(".animate-wrap").children().eq(i).addClass("node-group0");
                    }
                }
            }
        }
    });
}

function renderNode(top, left) {
    $(".animate-wrap").append(
        '<div class="node-group" style="top: ' + top + '; left: ' + left + ';">' +
        '<div class="node node-dot"></div>' +
        '<div class="node node-10"></div>' +
        '<div class="node node-20"></div>' +
        '</div>'
    );
}

