var pageIndex = 1;//加载页面指定页数
//页面加载调用方法
$(function () {
    loadContractList(pageIndex);
})

//条件查询
function searchZXContract() {
    loadContractList("1");
}

// 请求列表
function loadContractList(pageIndex) {
    $.ajax({
        type: "POST",
        data: {
            "contractStatus": $("#contractStatus").val(),
            "lEmployeeId": $("#lEmployeeId").val(),
            "contractName": $("#contractNameInput").val(),
            "register": $("#registerInput").val(),
            "signer": $("#signerInput").val(),
            "startTime": $("#startTime").val(),
            "stopTime": $("#stopTime").val(),
            "pageIndex": pageIndex
        },
        dataType: 'json',
        url: strRootPath + "/contract/queryPage.do",
        success: function (data) {
            if (data != null) {
                var pageHtml = "";
                if (data.resultCode == 0) {
                    pageIndex = data.resultDate.pageIndex;
                    var totalCount = data.resultDate.totalCount;
                    loadData(data.resultDate.pageInfoList);
                    pageData(totalCount, data.resultDate.pageCount, pageIndex);
                    pageHtml = '<span class="layui-laypage-count" style="color: #333;font-size:12px;margin-left: 5px;">' + "共" + totalCount + "记录，共" + data.resultDate.totalPage + "页" + '</span>';
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
    //清楚显示数据
    $("#checkApplys").empty();
    var html = "";
    layui.each(pageInfoList, function (index, item) {
        //转换注册时间
        var registDate = timestampToTime(item.registDate);
        var updateTime = timestampToTime(item.updateTime);

        //组装借款方案
        var repaymentWay = (item.repaymentWay == 1) ? "等本等息" : "先息后本";
        html += '<tr>' +
            // 登记人
            '<td>' + item.contractRegister + '</td>' +
            '<td>' + item.contractSigner + '</td>' +
            '<td id=' + item.id + ' onclick="getContractDetail(this.id)"><span class="addUnderline tdHover">' + item.contractId + '</span></td>' +
            '<td>' + repaymentWay + '</td>' +
            '<td>' + item.amount + '</td>' +
            '<td>' + registDate + '</td>' +
            '<td>' + updateTime + '</td>';
        if (item.contractStatus == 40) {
            html += '<td><span contractId="' + item.contractId + '" status="' + item.contractStatus + '" onclick="showLogoutCompany(this)" class="the_true">' + "注销成功" + '</span></td>';
        } else if (item.contractStatus == 41) {
            html += '<td><span contractId="' + item.contractId + '" status="' + item.contractStatus + '" onclick="showLogoutCompany(this)" class="bluebtn">' + "待注销" + '</span> </td>';
        } else {
            html += '<td></td>';
        }
        html += '</tr>';
    });
    $("#checkApplys").append(html);
}

//日期转换
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return Y + M + D;
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
                    loadContractList(pageIndex);
                }
            }
        });
    })
}


//弹窗 合约id
function getContractDetail(id) {
    initWindow();
    $.ajax({
        type: "POST",
        data: {"id": id},
        dataType: 'json',
        url: strRootPath + "/contract/queryDetailContract.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    var registDate = timestampToTime(data.resultDate.blockContract.registDate);
                    var contractToDate = timestampToTime(data.resultDate.blockContract.contractToDate);
                    var repaymentWay = (data.resultDate.blockContract.repaymentWay == 1) ? "等本等息" : "先息后本";
                    var status = (data.resultDate.blockContract.contractStatus).toString().substring(0, 1);  //状态
                    while (status > 0) {
                        var flow = "flow" + status;
                        $(".con-flow [class=" + flow + "]").addClass("fcheck");
                        status--;
                    }

                    $("#contractName").text(data.resultDate.blockContract.contractName);
                    $("#contractId").text("合约ID：" + data.resultDate.blockContract.contractId);
                    $("#register").text(data.resultDate.blockContract.contractRegister);
                    $("#signer").text(data.resultDate.blockContract.contractSigner);
                    $("#registDate").text(registDate);
                    $("#contractToDate").text(contractToDate);
                    $("#interestRate").text(data.resultDate.blockContract.interestRate + "%");
                    $("#repaymentWay").text(repaymentWay);
                    $("#houseInfo").text(data.resultDate.blockContract.houseInfo);
                    $("#houseCode").text(data.resultDate.blockContract.houseCode);
                    $("#remark").text(data.resultDate.blockContract.remark);
                    $("#blockId").text(data.resultDate.blockContract.contractBlockId);

                    if (data.resultDate.blockContract.term != null) {
                        $("#amount").text(data.resultDate.blockContract.amount + "元/" + data.resultDate.blockContract.term + "月");
                    } else {
                        $("#amount").text(data.resultDate.blockContract.amount + "元");
                    }
                    //底层数据
                    if (data.resultDate.blockContract.importType == 0) {
                        $("#underData").css("display", "block");
                        addUrl(data.resultDate.contractInfoUrlList, "");
                    } else if (data.resultDate.blockContract.importType == 1 || data.resultDate.blockContract.importType == null) {
                        $("#underData").css("display", "none");
                    } else if (data.resultDate.blockContract.importType == 2) {
                        $("#underData").css("display", "block");
                        addUrl(data.resultDate.contractInfoUrlList, strRootPath);
                    }
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
    showDialog('contractDialog')
}

//添加url
function addUrl(contractInfoUrlList, strUrl) {
    layui.each(contractInfoUrlList, function (index, item) {
        if (item.urlType == 1) {
            $("#personData").attr("href", strUrl + item.url);
        } else if (item.urlType == 2) {
            $("#houseData").attr("href", strUrl + item.url);
        }
    })
}

//初始化弹窗数据
function initWindow() {
    $("#personData").removeAttr("href");
    $("#houseData").removeAttr("href");
    $("#contractName").text("");
    $("#contractId").text("");
    $("#register").text("");
    $("#signer").text("");
    $("#registDate").text("");
    $("#contractToDate").text("");
    $("#interestRate").text("");
    $("#repaymentWay").text("");
    $("#amount").text("");
    $("#houseInfo").text("");
    $("#houseCode").text("");
    $("#remark").text("");
    $("#blockId").text("");
}

//点击状态
function showLogoutCompany(td) {
    var status = $(td).attr("status");
    var contractId = $(td).attr("contractId");
    $.ajax({
        type: "POST",
        data: {"contractId": contractId, "status": status},
        dataType: 'json',
        url: strRootPath + "/contract/queryLogoutDetailContract.do",
        success: function (data) {
            if (data != null) {
                $("#logoutDialogTitle").text("");
                $("#logoutDialogContent").text("")
                if (data.resultCode == 0) {
                    var companyName = data.resultDate.companyName;
                    if (status == 40) {
                        $("#logoutDialogTitle").text("合约注销成功");
                    } else {
                        $("#logoutDialogTitle").text("合约待注销");
                    }
                    $("#logoutDialogContent").text("注销机构：" + companyName);
                }
            }
        }
    })
    showDialog('logoutDialog')
}

var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate, layform = layui.form;
// 调用layui表单样式：单选框
layui.use('form', function () {
    var form = layui.form;
    // 各种基于事件的操作，下面会有进一步介绍
});
//时间控件
$('.time').each(function (i, e) {
    laydate.render({
        elem: this,
        type: 'datetime',
        btns: ['now', 'confirm'],
    });
})
