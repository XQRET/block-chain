var pageIndex = 1;
var userId;
layform = layui.form;
$(function () {
     userId = $("#lEmployeeId").val();
    //跳转页面判断
    skipPage();
    //获取机构关联合约列表
    getAffiliateList(pageIndex);
});

//请求机构关联列表
function getAffiliateList(pageIndex) {
    $.ajax({
        type: "POST",
        data:{"userId":userId,"pageIndex":pageIndex},
        dataType: 'json',
        url: strRootPath + "/company/queryRelation.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    pageIndex = data.resultDate.pageIndex;
                    var pageInfoList = data.resultDate.pageInfoList;//数据
                    var totalCount = data.resultDate.totalCount;
                    var pageCount = data.resultDate.pageCount;
                    var totalPage = data.resultDate.totalPage
                    loadData(pageInfoList);
                    pageData(totalCount, pageCount, pageIndex);
                    pageHtml = '<span class="layui-laypage-count" style="color: #333;font-size:12px;margin-left: 5px;">' + "共" + totalCount + "记录，共" + totalPage + "页" + '</span>';
                    $("#pageTool").append(pageHtml);
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
}

//加载数据
function loadData(pageInfoList) {
    $("#AffiliateTbody").empty();
    var html = "";
    layui.each(pageInfoList, function (index, item) {
        index++;
        var createDate = timestampToTime(item.companyRelation.createTime);
        var statusStr="";
        if(item.companyRelation.relationStatus ==0){
            statusStr ="待关联";
        }else if(item.companyRelation.relationStatus==1){
            statusStr="关联成功";
        }else{
            statusStr="关联拒绝";
        }
        html += '<tr>' +
            '<td>' +  index + '</td>' +
            '<td>' + item.blockCompany.companyName + '</td>' +
            '<td>' + createDate+ '</td>' +
            '<td></td>' +
            '<td>' + statusStr  + '</td>' +
            '</tr>';
    });
    $("#AffiliateTbody").append(html);
}

//分页
function pageData(totalCount, pageCount, pageIndex) {
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'pageTool'
            , count: totalCount
            , limit: pageCount
            , curr: pageIndex
            , first: '首页'
            , last: '尾页'
            // , layout: ['page', 'count']
            , jump: function (obj, first) {
                pageIndex = obj.curr;  //这里是后台返回给前端的当前页数
                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr  ajax 再次请求
                    getAffiliateList(pageIndex);
                }
            }
        });
    })
}

//时间格式转换
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return Y + M + D ;
}
//添加资金端
function addaffiliate() {
    $.ajax({
        type: "POST",
        data: {"userId": userId, "companyStatus": 2, "companyType": 1},
        url: strRootPath + "/company/queryUnRelation.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    if(data.resultDate==""){
                        $(".active8").css("background-color","#A0A0A0");
                        $(".active8").removeAttr("onclick");
                    }else{
                        $("#affiliateUl").empty();
                        var html = "";
                        layui.each(data.resultDate, function (index, item) {
                            html += '<li>' +
                                '<input class="layui-bg-blue" type="checkbox" lay-skin="primary">' +
                                '<span style="display:none;">'+ item.companyBlockId +'</span>'+
                                '<span>' + item.companyName + '</span>' +
                                '</li>';
                        })
                        $("#affiliateUl").append(html);
                        layform.render();
                    }
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
    showDialog('addaffiliateDialog');
}

//点击提交
function comfireAffiliate() {
    var relations=new Array()
    $(".layui-form-checked").next().each(function () {
        relations.push($(this).text());
    });
    $.ajax({
        type: "POST",
        data: {"userId": userId,"relations":relations},
        url: strRootPath + "/company/relatedCompanys.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    layer.msg('提交成功！', {
                        icon: 0,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        closefbE();
                        location.reload();
                    });
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
}

//跳转页面判断
function skipPage() {
    $.ajax({
        type: "POST",
        url: strRootPath + "/company/queryCertifiedInfo.do",
        data: {"lEmployeeId": userId},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    var status = data.resultDate.companyStatus;
                    if (status == 2) {
                        $(".auth_tips").remove();
                        var html = "";
                        html += '<div class="alltitle">' +
                            '<p>关联机构</p>' +
                            '</div>';
                        $(".body-content").prepend(html);
                    }
                }
                $(".basic_info").css("display", "block");
            }
        }
    });
}

