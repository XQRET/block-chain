var pageIndex = 1;
var userId; //用户id
var zcCompanyBlockId;//资产端区块id
$(function () {
    userId = $("#lEmployeeId").val();
    skipPage();
//获取机构关联合约列表
    getAffiliateAssetsList(pageIndex);
})

//请求机构关联列表
function getAffiliateAssetsList(pageIndex) {
    $.ajax({
        type: "POST",
        data: {"userId": userId, "pageIndex": pageIndex},
        dataType: 'json',
        url: strRootPath + "/company/queryRequisitionRelated.do",
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
    $("#AffiliateAssetsTbody").empty();
    var html = "";
    layui.each(pageInfoList, function (index, item) {
        index++;
        var createDate = timestampToTime(item.companyRelation.createTime);
        html += '<tr>' +
            '<td>' + index + '</td>' +
            '<td>' + item.blockCompany.companyName + '</td>' +
            '<td>' + createDate + '</td>' +
            '<td></td>';
        if (item.companyRelation.relationStatus == 0) {
            html += '<td id=' + item.companyRelation.zcCompanyBlockId + '><a class="the_true" onclick="acceptRelation(this,1)">通过</a><a class="the_false ml15" onclick="rejectRelation(this,2)">拒绝</a></td>';
        } else if (item.companyRelation.relationStatus == 1) {
            html += ' <td class="true">关联成功</td>';
        } else {
            html += ' <td class="false">关联拒绝</td>';
        }
        html += '<td><span class="the_blue" publicKey="' + item.companyRelation.relationStatus+","+item.blockCompany.companyPublicKey + '" onclick="showPublicKey(this)">获取公钥</span></td>';
        html += '</tr>';
    });
    $("#AffiliateAssetsTbody").append(html);
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
                    getAffiliateAssetsList(pageIndex);
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

//点击同意
function acceptRelation(aHtml, relationStatus) {
    zcCompanyBlockId = $(aHtml).parent().attr("id");
    $.ajax({
        type: "POST",
        data: {"userId": userId, "relationStatus": relationStatus, "zcCompanyBlockId": zcCompanyBlockId},
        dataType: 'json',
        url: strRootPath + "/company/reviewRelationCompany.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    layer.msg('操作成功！', {
                        icon: 0,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        //do something
                        location.reload();
                    });
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
}

//点击拒绝
function rejectRelation(aHtml, relationStatus) {
    zcCompanyBlockId = $(aHtml).parent().attr("id");
    $.ajax({
        type: "POST",
        data: {"userId": userId, "relationStatus": relationStatus, "zcCompanyBlockId": zcCompanyBlockId},
        dataType: 'json',
        url: strRootPath + "/company/reviewRelationCompany.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    layer.msg('操作成功！', {
                        icon: 0,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        //do something
                        location.reload();
                    });
                }
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

//查询公钥
function showPublicKey(span) {
    $("#publicKeyDialogContent").val("");
    var publicKey = $(span).attr("publicKey")
    var strArr= publicKey.split(",");
    if(strArr[0]==1){
        $("#publicKeyDialogContent").val(strArr[1]);
        showDialog("publicKeyDialog");
    }else{
        layer.alert("请关联成功以后再进行查看！",{icon:0});
    }

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