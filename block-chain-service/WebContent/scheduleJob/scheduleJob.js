var pageIndex = 1;//加载页面指定页数
//页面加载调用方法
$(function () {
    queryScheduleJobList(pageIndex);
});

//查询分页数据
function queryScheduleJobList(pageIndex) {
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/scheduleJob/queryScheduleJobPageList.do",
        data: {
            "pageIndex": pageIndex
        },

        success: function (data) {
            if (data != null) {
                var pageHtml = "";
                if (data.resultCode == 0) {
                    //返回数据
                    var resultDate = data.resultDate;

                    //页数
                    pageIndex = resultDate.pageIndex;

                    //总行数
                    var totalCount = resultDate.totalCount;

                    //数据列表
                    var pageInfoList = resultDate.pageInfoList;

                    //加载页面数据
                    appendData(pageInfoList);

                    //添加分页插件
                    pageDataTool(totalCount, data.resultDate.pageCount, pageIndex);
                    pageHtml = '<span class="layui-laypage-count" style="color: #333;font-size:12px;margin-left: 5px;">' + "共" + totalCount + "记录，共" + resultDate.totalPage + "页" + '</span>';
                    $("#pageTool").append(pageHtml);
                } else {
                    layer.alert(data.resultMessage, {icon: 0});
                }
            }
        }
    })
}

//拼装页面数据
function appendData(pageInfoList) {
    //清楚显示数据
    $("#taskListTbody").empty();

    //数据拼装
    var html = "";

    //循环拼装数据
    layui.each(pageInfoList, function (index, item) {
        var jobId = item.jobId;
        var jobGroup = item.jobGroup;
        var jobName = item.jobName;
        var jobStatus = item.jobStatus;
        var cronExpression = item.cronExpression;
        var operationDes = item.operationDes;
        var description = item.description;
        var isConcurrent = item.isConcurrent;
        var springId = item.springId;
        var count = item.count;

        html +=
            '<tr>' +
            '<td>' + jobId + '</td>' +
            '<td>' + jobGroup + '</td>' +
            '<td>' + jobName + '</td>' +
            '<td>' + getJobRunStatus(jobStatus, jobId) + '</td>' +
            '<td>' + cronExpression + '</td>' +
            '<td>' + operationDes + '</td>' +
            '<td>' + description + '</td>' +
            '<td>' + isConcurrent + '</td>' +
            '<td>' + springId + '</td>' +
            '<td>' + count + '</td>' +
            '<td>' +
            '<input onclick="updateCron(' + jobId + ')" value="修改" class="save_btn"/>' +
            '</td>' +
            '<tr>';
    });
    $("#taskListTbody").append(html);
}

//获取任务执行状态
function getJobRunStatus(jobStatus, jobId) {
    var jobEndStatus = "";
    if (1 == jobStatus) {
        //运行状态
        jobEndStatus = "运行中 / " + '<input onclick="stopJob(' + jobId + ')" value="停止" class="stop_btn"/>';
    } else if (0 == jobStatus) {
        //停止状态
        jobEndStatus = "停止 / " + '<input onclick="startJob(' + jobId + ')" value="启动" class="start_btn"/>';
    }
    return jobEndStatus;
}

//启动任务
function startJob(jobId) {
    //发送请求
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/scheduleJob/startJob.do",
        data: {
            "jobId": jobId
        },
        success: function (data) {
            if (data != null) {
                layer.alert(data.resultMessage, {icon: 0});
                queryScheduleJobList(pageIndex);
            }
        }
    })
}

//修改时间表达式
function updateCron(jobId) {
    var cronExpression = prompt("输入cron表达式！");
    if(cronExpression==null||cronExpression.trim()==""){
        layer.alert("请输入表达式", {icon: 0});
        return;
    }
//发送请求
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/scheduleJob/updateCronExpression.do",
        data: {
            "jobId": jobId,
            "cronExpression": cronExpression
        },
        success: function (data) {
            if (data != null) {
                layer.alert(data.resultMessage, {icon: 0});
                queryScheduleJobList(pageIndex);
            }
        }
    })

}

// 停止一个正在运行的定时任务
function stopJob(jobId) {
    layer.confirm('确定停止吗吗?', {
            btn: ['确定', '取消'] //按钮
        },
        function (index) {
            //发送请求
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: strRootPath + "/scheduleJob/stopJob.do",
                data: {
                    "jobId": jobId
                },
                success: function (data) {
                    if (data != null) {
                        layer.alert(data.resultMessage, {icon: 0});
                        queryScheduleJobList(pageIndex);
                    }
                }
            });
            layer.close(index);
        },
        function (index) {
            layer.close(index);
        });
}

//添加分页插件
function pageDataTool(totalCount, pageCount, pageIndex) {
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'pageTool',
            count: totalCount,
            limit: pageCount,
            curr: pageIndex,
            first: '首页',
            last: '尾页'
            , jump: function (obj, first) {
                pageIndex = obj.curr;  //这里是后台返回给前端的当前页数
                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr  ajax 再次请求
                    queryScheduleJobList(pageIndex);
                }
            }
        });
    })
}

// 配置创建定时任务
function createScheduleJob() {
    // 参数处理
    var jobGroup = $("#jobGroup").val();//组名
    var jobName = $("#jobName").val();//任务名称
    var cronExpression = $("#cronExpression").val();//时间表达式
    var description = $("#description").val();//描述
    var isConcurrent = $("#isConcurrent").val();//任务状态
    var springId = $("#springId").val();//beanId

    //发送请求
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: strRootPath + "/scheduleJob/createScheduleJob.do",
        data: {
            "jobGroup": jobGroup,
            "jobName": jobName,
            "cronExpression": cronExpression,
            "description": description,
            "isConcurrent": isConcurrent,
            "springId": springId
        },
        success: function (data) {
            if (data != null) {
                layer.alert(data.resultMessage, {icon: 0});
                queryScheduleJobList(pageIndex);
            }
        }
    })
}

