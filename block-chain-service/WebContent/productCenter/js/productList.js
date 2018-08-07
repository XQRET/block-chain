var laypage = layui.laypage,layer = layui.layer,laydate = layui.laydate,layform = layui.form;

// 初始化
$(function() {
    initDataLlist();
});
function initDataLlist(pageIndex) {
     pageIndex==null?1:pageIndex;
    var tpl_url = 'template/productList.tpl';
    $.post(""+strRootPath+"/contract/queryPresalePageList.do",$("#agencyForm").serialize(),function(data){
        if (data != null) {
            if (data.resultCode == 0 && data.resultDate != null) {
                 pageIndex = data.resultDate.pageIndex;
                var pageInfoList = data.resultDate.pageInfoList;//数据
                var totalCount = data.resultDate.totalCount;
                var pageCount = data.resultDate.pageCount;
                var totalPage = data.resultDate.totalPage;
                bindingData(pageInfoList,tpl_url,'table_view');
                pageData(totalCount, pageCount, pageIndex);
                pageHtml = '<span class="layui-laypage-count" style="color: #333;font-size:12px;margin-left: 5px;">' + "共" + totalCount + "记录，共" + totalPage + "页" + '</span>';
                $("#pageTool").append(pageHtml);
            }else{
                layer.open({
                    title: '温馨提示'
                    , content: data.resultMessage
                });
            }
        }else{
            layer.open({
                title: '温馨提示'
                , content: data.resultMessage
            });
        }
    });
}

//生成分页
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
                    $("#pageIndex").val(pageIndex);
                    initDataLlist(pageIndex);
                }
            }
        });
    })
}
function toRaise(item){
    var contractId=$(item).parent().parent().find("td:eq(2)").text();
    var raiseStatusStr=$(item).parent().parent().find("td:eq(8)").text();
    var raiseStatus=null;
    if(raiseStatusStr=='待募集'){
        raiseStatus=2;
    }
    $.post(""+strRootPath+"/contract/updateContractRaise.do",{"contractId":contractId,"raiseStatus":raiseStatus},function(data){
        if (data != null) {
            layer.open({
                content:data.resultCode == 0?'操作成功':data.resultMessage
                ,yes: function(index, layero){
                    layer.close(index);
                    initDataLlist();
                }
                ,end:function(){
                    closefbE();
                }
            });
        }else{
            layer.open({
                title: '温馨提示'
                , content: data.resultMessage
            });
        }
    });
}
$("#query").click(function(){
    initDataLlist();
});
//弹窗 合约id
function getContractDetail(contractId) {
    initWindow();
    $.ajax({
        type: "POST",
        data: {"contractId": contractId},
        dataType: 'json',
        url: strRootPath + "/contract/queryDetailContract.do",
        success: function (data) {
            if (data != null) {
                if (data.resultCode == 0) {
                    var registDate = timestampToTime(data.resultDate.blockContract.updateTime);
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
