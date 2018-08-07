//根据页面传的参数判断标题的对齐方式
if ($("#indexfloat").val() > 0) {
    var titlefloat = 'left';
} else {
    var titlefloat = 'center';
}

//************平台统计的数据为平台所有账户对应的合约情况*********
function showContractInfo() {
    $.ajax({
        url: strRootPath + "/cockpit/showTotalItem.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                $("#contractInfo span:eq(0)").text(data.resultDate.t);
                $("#contractInfo span:eq(1)").text(data.resultDate.c);
                $("#contractInfo span:eq(2)").text(data.resultDate.p);
                $("#contractInfo span:eq(3)").text(data.resultDate.m);
                $("#contractInfo span:eq(4)").text(data.resultDate.cm);
                $("#contractInfo span:eq(5)").text(data.resultDate.pc);
                $("#contractInfo span:eq(6)").text(data.resultDate.cn+data.resultDate.a);
                $("#contractInfo span:eq(7)").text(data.resultDate.cn);
                $("#contractInfo span:eq(8)").text(data.resultDate.a);
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

/***************************************资产端top5****************************************/
function showAssets() {
    $.ajax({
        url: strRootPath + "/cockpit/showAssets.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#assetsTable tbody").empty();
                var x = data.resultDate.x;
                var y = data.resultDate.y;
                //填充表格
                for (var i in x) {
                    html += '<tr>' +
                        '<td>' + y[i] + '</td>' +
                        '<td>' + x[i] + '</td>' +
                        '</tr>';
                }
                $("#assetsTable tbody").append(html);
                //echarts
                var xArray=new Array();
                var yArray=new Array();
                for (var i=x.length-1;i>=0 ; i--) {
                    xArray.push(x[i]);
                    yArray.push(y[i]);
                }
                var option = {
                    title: {
                        text: '资产端TOP5',
                        x: titlefloat
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value'
                    },
                    yAxis: {
                        type: 'category',
                        data: yArray
                    },
                    series: [
                        {
                            type: 'bar',
                            color:'#70c7fb',
                            data: xArray
                        }
                    ]
                };
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('assets'));
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}
//资金加载更多
function loadAssets(divLabel){
    $.ajax({
        url: strRootPath + "/cockpit/showAssetsAll.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#assetsTable tbody").empty();
                var x = data.resultDate.x;
                var y = data.resultDate.y;
                //填充表格
                for (var i in x) {
                    html += '<tr>' +
                        '<td>' + y[i] + '</td>' +
                        '<td>' + x[i] + '</td>' +
                        '</tr>';
                }
                $("#assetsTable tbody").append(html);
                $(divLabel).css("display","none");
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

/***************************************资产端top5 end****************************************/
/***************************************资金端top5****************************************/
function showCapital() {
    $.ajax({
        url: strRootPath + "/cockpit/showCapital.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#capitalTable tbody").empty();
                var x = data.resultDate.x;
                var y = data.resultDate.y;
                //填充表格
                for (var i in x) {
                    html += '<tr>' +
                        '<td>' + y[i] + '</td>' +
                        '<td>' + x[i] + '</td>' +
                        '</tr>';
                }
                $("#capitalTable tbody").append(html);

                var xArray=new Array();
                var yArray=new Array();
                for (var i=x.length-1;i>=0 ; i--) {
                    xArray.push(x[i]);
                    yArray.push(y[i]);
                }
                //echarts
                var option = {
                    title: {
                        text: '资金端TOP5',
                        x: titlefloat
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value'
                    },
                    yAxis: {
                        type: 'category',
                        data: yArray
                    },
                    series: [
                        {
                            type: 'bar',
                            color:'#70c7fb',
                            data: xArray
                        }
                    ]
                };
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('capital'));
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })

}
//资产加载更多
function loadCapital(divLabel){
    $.ajax({
        url: strRootPath + "/cockpit/showCapitalAll.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#capitalTable tbody").empty();
                var x = data.resultDate.x;
                var y = data.resultDate.y;
                //填充表格
                for (var i in x) {
                    html += '<tr>' +
                        '<td>' + y[i] + '</td>' +
                        '<td>' + x[i] + '</td>' +
                        '</tr>';
                }
                $("#capitalTable tbody").append(html);
                $(divLabel).css("display","none");
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}
/***************************************资金端top5 end****************************************/
/***************************************合约数量及金额新增****************************************/
function showNumSum(flag) {
    var url = "";
    if (flag == 1) {
        url = strRootPath + "/cockpit/showAdd.do";
        $(".ns_btn a").eq(0).addClass("nscheck").siblings().removeClass("nscheck");
    } else {
        url = strRootPath + "/cockpit/showSum.do";
        $(".ns_btn a").eq(1).addClass("nscheck").siblings().removeClass("nscheck");
    }
    $.ajax({
        url: url,
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#numsumTable tbody").empty();
                var t = data.resultDate.t;
                var n = data.resultDate.n;
                var m = data.resultDate.m;
                //填充表格
                for (var i in t) {
                    html += '<tr>' +
                        '<td>' + t[i] + '</td>' +
                        '<td>' + n[i] + '</td>' +
                        '<td>' + m[i] + '</td>' +
                        '</tr>';
                }
                $("#numsumTable tbody").append(html);

                //echarts
                var option = {
                    title: {
                        text: '合约数量及金额',
                        x: titlefloat
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['合约数量（个）', '合约金额（万元）'],
                        orient: 'horizontal',//图例的排列方向
                        x: 'center',//图例的位置
                        y: 'bottom'

                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: t
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            splitLine: {show: false},
                            min: 0,
                            max: 1400,
                            interval: 200,
                            axisLabel: {
                                formatter: '{value} '
                            }
                        },
                        {
                            type: 'value',
                            splitLine: {show: false},
                            min: 0,
                            max: 12000,
                            interval: 2000,
                            axisLabel: {
                                formatter: '{value} '
                            }
                        }
                    ],
                    series: [

                        {
                            name: '合约数量（个）',
                            type: 'bar',
                            /*设置柱状图颜色*/
                            itemStyle: {
                                normal: {
                                    color: '#e4f8fe'
                                }
                            },
                            data: n
                        },
                        {
                            symbolSize: 6,   //设定实心点的大小
                            symbol: 'rect',     //设定为实心点
                            name: '合约金额（万元）',
                            type: 'line',
                            itemStyle: {
                                /*设置折线颜色*/
                                normal: {
                                    color: '#497fca'
                                }
                            },
                            data: m
                        }
                    ]
                };
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('conNumsum'));
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

/***************************************合约数量及金额end****************************************/

/***************************************合约地区分布****************************************/
function showAddress(data) {
    //初始化数据
    var category = new Array();
    var barData = new Array();
    for (var i = data.length - 1; i >= 0; i--) {
        category.push(data[i].name);
        barData.push(data[i].value);
    }

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
        },
        xAxis: {
            type: 'value',
            show: false,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            data: category,
            position: 'right',
            splitLine: {show: false},
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            offset: 10,
            nameTextStyle: {
                fontSize: 15
            }
        },

        series: [
            {
                name: '合约数量(个)',
                type: 'bar',
                data: barData,
                barWidth: 10,
                barMaxHeight: 110,
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'inside',
                        offset: [5, 2],
                        textStyle: {
                            color: '#fff',
                            fontSize: 13
                        }
                    }
                },

                itemStyle: {
                    normal: {
                        color:'#bdc4e3',
                        },
                    }
                },
        ]
    };
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('address'));
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function showRegion() {
    $.ajax({
        url: strRootPath + "/cockpit/showRegion.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var Regionoption = {
                    title: {
                        text: '合约地区分布',
                        textStyle: {
                            fontWeight: 'bold',
                        },
                        x: titlefloat
                    },
                    tooltip: {//提示框组件。
                        trigger: 'item'//数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                    },
                    legend: {
                        data: [''],//这里不用放数据
                        orient: 'vertical',//图例的排列方向
                        x: 'left',//图例的位置
                    },
                    toolbox: {//工具栏
                        show: false,
                        orient: 'vertical',//工具栏 icon 的布局朝向
                        x: 'right',
                        y: 'center',
                        feature: {//各工具配置项。
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},//数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
                            restore: {show: true},//配置项还原。
                            saveAsImage: {show: true}//保存为图片。
                        }
                    },
                    roamController: {//控制地图的上下左右放大缩小 图上没有显示
                        show: true,
                        x: 'right',
                        mapTypeControl: {
                            'china': true
                        }
                    },
                    dataRange: {//颜色的变化设置
                        x: 'left',
                        y: 'top',
                        top:30,
                        textAlign: null,
                        color: ['#6395db', '#a1c8ff'],
                        splitList: [
                            {start: 1500},
                            {start: 900, end: 1500},
                            {start: 310, end: 1000},
                            {start: 200, end: 309},
                            {start: 10, end: 200, label: '100'},
                            {start: 5, end: 5, label: '5'},
                            {end: 10}
                        ],
                        // calculable : true,//颜色呈条状
                        text: ['高', '低'],// 文本，默认为数值文本
                    },
                    series: [
                        {
                            name: '合约地区分布',
                            x: 'center',
                            type: 'map',
                            mapType: 'china',
                            showLegendSymbol: false,
                            roam: false,//是否开启鼠标缩放和平移漫游
                            itemStyle: {//地图区域的多边形 图形样式
                                normal: {//是图形在默认状态下的样式
                                    label: {
                                        show: false,//是否显示标签
                                        textStyle: {
                                            color: "#fff"
                                        }
                                    }
                                },
                                emphasis: {//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                                    label: {
                                        show: false,
                                        color: 'blue',
                                        textStyle: {color: '#fff'}
                                    }
                                }
                            },
                            top: "3%",//组件距离容器的距离
                            data: data.resultDate
                        }
                    ]
                };
                var Regionchart = echarts.init(document.getElementById('conRegion'), 'shine');
                Regionchart.setOption(Regionoption);

                showAddress(data.resultDate);

            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

/***************************************合约地区分布****************************************/
/***************************************近六月预测收入****************************************/
function showIncome() {
    $.ajax({
        url: strRootPath + "/cockpit/showIncome.do",
        type: "post",
        success: function (data) {
            if (data.resultCode == 0) {
                var html = "";
                $("#incomeTable tbody").empty();
                var t = data.resultDate.t;
                var m = data.resultDate.m;
                //填充表格
                for (var i in t) {
                    html += '<tr>' +
                        '<td>' + t[i] + '</td>' +
                        '<td>' + m[i] + '</td>' +
                        '</tr>';
                }
                $("#incomeTable tbody").append(html);
                //echarts
                var Incomeoption = {
                    title: {
                        text: '近6月预测收入（万元）',
                        x: titlefloat
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: t
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: m,
                        type: 'line',
                        areaStyle: {color: '#ffe1df'},
                        itemStyle: {normal: {label: {show: true}, color:'#E79590'}}
                    }]
                };

                var Incomechart = echarts.init(document.getElementById('forecastIncome'));
                Incomechart.setOption(Incomeoption);
            } else {
                layer.alert(data.resultMessage, {icon: 0});
            }
        }
    })
}

/***************************************近六月预测收入end****************************************/
