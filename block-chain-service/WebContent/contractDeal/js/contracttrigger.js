var pageIndex = 1;
//页面加载
$(function () {
    getContractList(pageIndex);
})

// 请求列表
function getContractList(pageIndex) {
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
            "pageIndex": pageIndex,
            "isFundsTrigger": "1"
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
    $("#checkApplys").empty();
    var html = "";
    layui.each(pageInfoList, function (index, item) {
        var registDate = timestampToTime(item.registDate);
        var repaymentWay = (item.repaymentWay == 1) ? "等本等息" : "先息后本";
        html += '<tr>' +
            '<td>' + item.contractRegister + '</td>' +
            '<td>' + item.contractSigner + '</td>' +
            '<td id=' + item.id + ' onclick="getContractDetail(this.id)"><span class="addUnderline tdHover">' + item.contractId + '</span></td>' +
            '<td>' + repaymentWay + '</td>' +
            '<td>' + item.amount + '</td>' +
            '<td>' + registDate + '</td>';
        if (item.contractStatus == 10) {
            html += '<td><a name=' + item.id + ' type="button" onclick="triggerWindows(this.name)" class="bluebtn">触发</a></td>';
        } else if (item.contractStatus == 21) {
            html += '<td ><a style="background:#C0C0C0" name=' + item.id + ' type="button"  class="bluebtn">已拒绝</a></td>';
        } else if (item.contractStatus == 20) {
            html += '<td ><a style="background:#C0C0C0" name=' + item.id + ' type="button"  class="bluebtn">已触发</a></td>';
        }
        html += '</tr>';
    });
    $("#checkApplys").append(html);
}


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
                    getContractList(pageIndex);
                }
            }
        });
    })
}

//条件查询
function searchContract() {
    getContractList("1");
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

                    var repaymentWay = (data.resultDate.blockContract.repaymentWay == 1) ? "等本等息" : "先息后本";
                    var registDate = (status == 20 || status == 21) ? timestampToTime(data.resultDate.blockContract.updateTime) : "";
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
            $("#personData").on('click',function () {
                openNewView(strUrl + item.url,"个人数据");
            })
        } else if (item.urlType == 2) {
            $("#houseData").on('click',function () {
                openNewView(strUrl + item.url,"房产数据");
            })
        } else if (item.urlType == 3) {
            $("#getExcelData").attr("href", strRootPath + item.url);
        } else if (item.urlType == 4) {
            $("#getData").on('click',function () {
                openNewView(item.url,"数据下载");
            })
        }
    })
}

function openNewView(url,title){
    var _index = layer.open({
        type: 2,
        content: url,
        area: ['100%', '100%'],
        anim: 2,
        title:[title,'font-size:18px;'],
    });
    layer.full(_index);
}

//初始化弹窗数据
function initWindow() {
    $("#personData").removeAttr("href");
    $("#houseData").removeAttr("href");
    $("#getExcelData").removeAttr("href");
    $("#getData").removeAttr("href");
    $("#contractName").text("");
    $("#contractId").text("");
    $("#register").text("");
    $("#signer").text("");
    $("#registDate").text("");
    $("#interestRate").text("");
    $("#repaymentWay").text("");
    $("#amount").text("");
    $("#houseInfo").text("");
    $("#houseCode").text("");
    $("#remark").text("");
    $("#blockId").text("");
}


//点击触发
function triggerWindows(id) {
    $("#triContractId").val(id);
    $("#triggerRemark").val("");
    $("#accept").addClass("quota_check").siblings().removeClass('quota_check');
    showDialog("hetrigger_dialog");
}

//提交合约触发
function submitTrigger() {
    var blockId = $("#triContractId").val();
    var userId = $("#lEmployeeId").val();
    var remark = $("#triggerRemark").val();
    var contractStatusStr = $(".quota_check").text(); //通过20 拒绝21
    var contractStatus = (contractStatusStr == "通过") ? 20 : 21;
    $.ajax({
        type: "POST",
        data: {
            "blockId": blockId,
            "userId": userId,
            "remark": remark,
            "contractStatus": contractStatus
        },
        dataType: 'json',
        url: strRootPath + "/contract/triggerContractStatus.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    closefbE();
                    //注册成功
                    layer.msg('操作成功！', {
                        icon: 0,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        location.reload();
                    });
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                    closefbE();
                }
            }
        }
    })
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
});


//选择触发结果中开通和拒绝的切换效果
$(".opencard span").bind("click", function () {
    $("#remark").val('');
    console.log($("#remark").text())
    $(this).addClass("quota_check").siblings().removeClass("quota_check");
});
//开始时间
/*laydate.render({
	elem : '#startTime'
});*/
//结束时间stopTime
/*laydate.render({
	elem : '#stopTime'
});*/