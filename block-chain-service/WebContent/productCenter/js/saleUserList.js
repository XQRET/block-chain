var laypage = layui.laypage,layer = layui.layer,laydate = layui.laydate,layform = layui.form;

// 初始化
$(function() {
    initDataLlist();
});
function Obj(name,data) {
    this.name = name;
    this.data = data;
}

function initDataLlist(pageIndex) {
    pageIndex==null?1:pageIndex;
    var tpl_url = 'template/saleUserList.tpl';
    $.post(""+strRootPath+"/product/queryProductReservationNoticePage.do",$("#agencyForm").serialize(),function(data){
        if (data != null) {
            if (data.resultCode == 0 && data.resultDate != null) {
                pageIndex = data.resultDate.pageIndex;
                var pageInfoList = data.resultDate.pageInfoList;//数据
                var totalCount = data.resultDate.totalCount;
                var pageCount = data.resultDate.pageCount;
                var totalPage = data.resultDate.totalPage;
                var name = $("#name").val();
                var obj = new Obj(name,pageInfoList);
                bindingData(obj,tpl_url,'table_view');
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
