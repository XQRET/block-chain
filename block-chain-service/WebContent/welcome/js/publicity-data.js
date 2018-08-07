"use strict";
var ledgerFlag = false;
var isUpdateTrade = false;
var pageDone = false;
var pageInterDone = false;

/*//一定周期内用户数量
var getUserCount = function() {
	pageDone = false;
	$.ajax({
		type:"get",
		url:config.bumeng_public + "user/v1/count",
		dataType:"json",
		success:function(returnData) {
			if(returnData.err_code == "0") {
				pageDone = true;
				var total_count = returnData.data.totalCount;
				var set_count = $(".userNum-total").numberAnimate({num:total_count, speed:2000});
				$(".p_userCount").text(formatNum(total_count + ""));
				setInterval(function() {
					$.ajax({
						type:"get",
						url:config.bumeng_public + "user/v1/count",
						dataType:"json",
						success:function(returnData) {
							total_count = returnData.data.totalCount;
							set_count.resetData(total_count);
							$(".p_userCount").text(formatNum(total_count + ""));
						}
					});
				},2000);
			}
		}
	});
}

//一定周期内交易数量
var getTradeCount = function() {
	pageDone = false;
	$.ajax({
		type:"get",
		url:config.bumeng_public + "transaction/v1/txFrequencyCount",
		dataType:"json",
		success:function(returnData) {
			if(returnData.err_code == "0") {
				pageDone = true;
				var hour_count = returnData.data.hourCount;
				var set_count = $(".tradeNum-24h").numberAnimate({num:hour_count, speed:2000});
				$(".p_tradeCount").text(formatNum(hour_count + ""));
				setInterval(function() {
					$.ajax({
						type:"get",
						url:config.bumeng_public + "transaction/v1/txFrequencyCount",
						dataType:"json",
						success:function(returnData) {
							hour_count = returnData.data.hourCount;
							set_count.resetData(hour_count);
							$(".p_tradeCount").text(formatNum(hour_count + ""));
						}
					});
				},2000);
			}
		}
	});
}

//当前区块高度
var getLedgerSequence = function() {
	pageDone = false;
	$.ajax({
		type:"get",
		url:config.bumeng_public + "block/v1/history",
		data:{"size":1},
		dataType: "json",
		success: function(returnData) {
			if(returnData.err_code == "0") {
				pageDone = true;
				var ledger_seq = returnData.data[0].ledger_sequence;
				var set_ledger_content_seq = $(".recent-ledger-content").numberAnimate({num:ledger_seq, speed:2000});
				var set_ledger_seq = $(".recent-ledger").numberAnimate({num:ledger_seq, speed:2000});
				$(".p_ledger_seq").text(formatNum(ledger_seq + ""));
				setInterval(function(){
					$.ajax({
						type:"get",
						url:config.bumeng_public + "block/v1/history",
						data:{"size":1},
						dataType: "json",
						success: function(returnData) {
							if(returnData.err_code == "0") {
								ledger_seq = returnData.data[0].ledger_sequence;
								set_ledger_content_seq.resetData(ledger_seq);
								set_ledger_seq.resetData(ledger_seq);
								$(".p_ledger_seq").text(formatNum(ledger_seq + ""));
							}
						}
					});	
				},2000);
			}
		}
	});	
}*/

//当前节点数
/*var getNodeCount = function() {
	pageDone = false;
	$.ajax({
		type:"POST",
		url:config.bumeng_public + "node/v1/nodeList",
		dataType: "json",
		success:function(returnData){
			if(returnData.err_code == 0) {
				pageDone = true;
				var node_count = returnData.data.length;
				var set_node_count = $(".node-total-num").numberAnimate({num:node_count, speed:2000});
				$(".p_nodeCount").text(formatNum(node_count + ""));
				setInterval(function(){
					$.ajax({
						type:"get",
						url:config.bumeng_public + "node/v1/nodeList",
						dataType: "json",
						success: function(data) {
							node_count = data.data.length;
							set_node_count.resetData(node_count);
							$(".p_nodeCount").text(formatNum(node_count + ""));
						}
					});
				},2000); 
			}
		}
	});	
}*/

var getNodeCount = function () {
    pageDone = false;
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cockpit/showNodeInfo.do",
        dataType: "json",
        success: function (data) {
            if (data.resultCode == 0) {
                pageDone = true;
                var resultDate = eval(data.resultDate);
                var node_count = resultDate.length;
                var set_node_count = $(".node-total-num").numberAnimate({num: node_count, speed: 2000});
                $(".p_nodeCount").text(formatNum(node_count + ""));
                setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: getRootPath() + "/cockpit/showNodeInfo.do",
                        dataType: "json",
                        success: function (data) {
                            var resultDate = eval(data.resultDate);
                            var node_count = resultDate.length;
                            set_node_count.resetData(node_count);
                            $(".p_nodeCount").text(formatNum(node_count + ""));
                        }
                    });
                }, 10000);
            }
        }
    });
}
//加载区块列表
var renderLedger = function () {
    pageDone = false;
    $.ajax({
        type: "get",
        url: config.bumeng_public + "block/v1/history",
        data: {"size": 6},
        dataType: "json",
        success: function (data) {
            if (data.err_code == "0") {
                pageDone = true;
                for (var i = data.data.length - 1; i >= 0; i--) {
                    var ledger_seq = data.data[i].ledger_sequence + "";
                    var interval = data.data[i].interval;
                    var transaction_time = getDate(data.data[i].close_time);
                    var timeDate = transaction_time.substring(0, 10);
                    var timeCount = transaction_time.substring(11, 19);
                    var tx_count = data.data[i].tx_count;
                    var hash = data.data[i].block_hash;
                    currentLedgerTab(ledger_seq, transaction_time, tx_count, setHashStyle(hash, 40));
                    weChatCurrentLedgerTab(ledger_seq, timeDate, timeCount, tx_count, setHashStyle(hash, 24));
                }
                ledgerFlag = true;
            }
        }
    });
}
//更新区块列表
var updateLedgerTab = function () {
    $.ajax({
        type: "get",
        url: config.bumeng_public + "block/v1/history",
        data: {"size": 6},
        dataType: "json",
        success: function (data) {
            if (data.err_code == "0") {
                for (var i = data.data.length - 1; i >= 0; i--) {
                    var ledger_seq = data.data[i].ledger_sequence + "";
                    if (Number(ledger_seq) > Number($(".sequenceTable").children().eq(0).find("td:first-child").text())) {
                        var interval = data.data[i].interval;
                        var transaction_time = getDate(data.data[i].close_time);
                        var timeDate = transaction_time.substring(0, 10);
                        var timeCount = transaction_time.substring(11, 19);
                        var tx_count = data.data[i].tx_count;
                        var hash = data.data[i].block_hash;
                        currentLedgerTab(ledger_seq, transaction_time, tx_count, setHashStyle(hash, 40));
                        weChatCurrentLedgerTab(ledger_seq, timeDate, timeCount, tx_count, setHashStyle(hash, 24));
                    }
                }
                ledgerFlag = true;
            }
        },
        error: function () {
            ledgerFlag = true;
        }
    });
}

//加载最新交易列表
//var appIndex = {"84b79b341adde491a6649f345fe0797b": 0, "62fb7101ca1e869e36f4a5144a2aa332": 1, "f7d837bdd10997eb304fe191d79b91a0": 2, "33f2209047092831a3413f090e1f3630": 3, "4d0d93fa5d24d4c94c9fb3021482ca56": 4, "09af46a6baa453120d5fa38fbee130e5": 5, "3c1e71a747249b58ac395f7bcb27f92f": 6, "839d97a40b9c6e4987929329cd43021f": 7, "42ed0470b8a5b8bad8edc81a3bdda8e8": 8, "fcb0d0ed737106d5a7bb121bd6fb385a": 9, "40603ec84ca3c864d894d3c6f853414e": 10, "6793a211a614baee03d362054fc56449": 11, "c09afbc1fb4d5302665e9e29415ce2a7": 12, "b1788a08d615d73699df07970ad90252": 13, "24468610ccaba41fce823da77148e4cb": 14, "1f6b68a918186a5c4ceff6b4d4bdc88f": 15};
var renderTrade = function () {
    var dataIndex = [];
    pageDone = false;
    $.ajax({
        type: "get",
        url: config.bumeng_public + "transaction/v2/getNewTx4App",
        dataType: 'json',
        success: function (returnData) {
            if (returnData.err_code == '0') {
                if (!isUpdateTrade) {
                    $('.table-trade-wrap table tbody').empty();
                }
                pageDone = true;
                //数据排序
//				$.each(returnData.data, function(i, item) {
//					var platform_name = item.platform_name;
//					var app_id = item.app_id;
//					if(platform_name != '布比（北京）网络技术有限公司') {
//						dataIndex[appIndex[app_id]] = item;
//					}
//				});
                $.each(returnData.data, function (i, item) {
                    var platform_name = item.platform_name;
                    var app_id = item.app_id;
                    if (app_id != '6f7b4daa3805fdbcdd04f24fd3494173' && app_id != '8b1774d004f8ca2112d0ed3a8b59791e' && app_id != '7b1b2595541d6649217c8b0c84f1f85a' && app_id != '1fedf9b7c73b060bb9d73240fe11a55e') {
                        dataIndex.push(item);
                    }
                });
                //按交易时间降序排序
                var middleware;
                for (var i = 0; i < dataIndex.length; i++) {
                    for (var j = i + 1; j < dataIndex.length; j++) {
                        if (dataIndex[i].tran_time < dataIndex[j].tran_time) {
                            middleware = dataIndex[i];
                            dataIndex[i] = dataIndex[j];
                            dataIndex[j] = middleware;
                        }
                    }
                }
                //渲染数据
                for (var i = 0; i < dataIndex.length; i++) {
                    if (dataIndex[i] != undefined) {
                        var secret_status = dataIndex[i].secret_status;
                        var platform_name = dataIndex[i].platform_name;
                        var appId = dataIndex[i].app_id;
                        var platform_logo = config.bumengLogoSrc + dataIndex[i].platform_logo,
                            hash = dataIndex[i].hash,
                            tran_time = dataIndex[i].tran_time + '',
                            timeDate = getDate(tran_time).substring(0, 10),
                            timeCount = getDate(tran_time).substring(10, 19),
                            asset_amount = "已隐私保护";
                        if (secret_status == 1) {
                            privacyWeChatTransactionRecord(platform_logo, timeDate, timeCount, hash);
                            //						hash = "*******************(已隐私保护)";

                        } else {
                            asset_amount = dataIndex[i].asset_amount;
                            weChatTransactionRecord(platform_logo, timeDate, timeCount, setHashStyle(hash, 24), asset_amount, hash);
                        }
                        tradeModle(secret_status, appId, platform_logo, hash, setHashStyle(hash, 40), asset_amount, getDate(tran_time));
                    }
                }
                isUpdateTrade = true;
            }
        }
    });
}

//刷新交易列表
var updateTradeData = {};
var updateTrade = function () {
    $.ajax({
        type: "get",
        url: config.bumeng_public + "transaction/v2/getNewTx4App",
        dataType: 'json',
        success: function (returnData) {
            if (returnData.err_code == '0') {
                for (var i = 0; i < returnData.data.length; i++) {
                    var app_id = returnData.data[i].app_id;
                    updateTradeData[app_id] = returnData.data[i];
                }
                $.each($("._hash"), function (index, domEle) {
                    var secret_status = $(this).attr("secret_status");
                    var appId = $(this).attr("appId"),
                        eleHash = $(this).attr("hash"),
                        dataJson = updateTradeData[appId],
                        tran_time = dataJson.tran_time + '',
                        timeDate = getDate(tran_time).substring(0, 10),
                        timeCount = getDate(tran_time).substring(11, 19),
                        dataHash = dataJson.hash;
                    if (secret_status == "1") {
                        $(this).parent().find("._hash").attr("hash", dataHash);
                        $(this).parent().find("._hash").text(setHashStyle(dataHash, 40));
                        $(this).parent().find("._tran_time").text(getDate(dataJson.tran_time + ''));
                    } else {
                        if (eleHash != dataHash) {
                            $(this).parent().fadeOut(200);
                            $(this).parent().find("._hash").attr("hash", dataHash);
                            $(this).parent().find("._hash").text(setHashStyle(dataHash, 40));
                            $(this).parent().find("._asset_amount").text(dataJson.asset_amount);
                            $(this).parent().find("._tran_time").text(getDate(dataJson.tran_time + ''));
                            $(this).parent().fadeIn(100);
                        }
                    }
                });
                isUpdateTrade = true;
            }
        }
    });
}

var tradeModle = function (secret_status, appId, platform_logo, hash, short_hash, asset_amount, tran_time) {
    if (secret_status == 1) {
        $('.table-trade-wrap table tbody').append(
            '<tr>' +
            '<td>' +
            '<img src="' + platform_logo + '" />' +
            '</td>' +
            '<td class="_hash" secret_status="' + secret_status + '" appId="' + appId + '" hash="' + hash + '">' + short_hash + '</td>' +
            '<td class="_asset_amount private-protect">已隐私保护</td>' +
            '<td class="_tran_time">' + tran_time + '</td>' +
            '</tr>'
        );
    } else {
        $('.table-trade-wrap table tbody').append(
            '<tr>' +
            '<td>' +
            '<img src="' + platform_logo + '" />' +
            '</td>' +
            '<td class="_hash" secret_status="' + secret_status + '" appId="' + appId + '" hash="' + hash + '">' + short_hash + '</td>' +
            '<td class="_asset_amount">' + asset_amount + '</td>' +
            '<td class="_tran_time">' + tran_time + '</td>' +
            '</tr>'
        );
    }
}

//时间转码
function getDate(targetDate) {
    var _date = targetDate.substring(0, 10);
    var getDate = new Date(_date * 1000) + "";
    var _getDate = new Date(_date * 1000);
    var dateArr = getDate.split(" ");
    switch (dateArr[1]) {
        case "Jan":
            dateArr[1] = 1;
            break;
        case "Feb":
            dateArr[1] = 2;
            break;
        case "Mar":
            dateArr[1] = 3;
            break;
        case "Apr":
            dateArr[1] = 4;
            break;
        case "May":
            dateArr[1] = 5;
            break;
        case "Jun":
            dateArr[1] = 6;
            break;
        case "Jul":
            dateArr[1] = 7;
            break;
        case "Aug":
            dateArr[1] = 8;
            break;
        case "Sep":
            dateArr[1] = 9;
            break;
        case "Oct":
            dateArr[1] = 10;
            break;
        case "Nov":
            dateArr[1] = 11;
            break;
        case "Dec":
            dateArr[1] = 12;
            break;
    }
    var rdate = _getDate.getFullYear() + "-" + timeDouble(dateArr[1]) + "-" + timeDouble(dateArr[2]) + " " + timeDouble(_getDate.getHours()) + ":" + timeDouble(_getDate.getMinutes()) + ":" + timeDouble(_getDate.getSeconds());
    return rdate;
}

//时间格式调整
function timeDouble(num) {
    var time = num + "";
    var numLen = time.length;
    if (numLen < 2) {
        time = "0" + time;
    }
    return time;
}

//数字格式
var formatNum = function (str) {
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
    str = newStr;
    return str;
}
//hash掩码
var setHashStyle = function (str, len) {
    var hashStyle = str.substring(0, len);
    var hash = hashStyle + "...";
    return hash;
}

//数字格式化
var formatNum = function (str) {
    var _str = str + "";
    var newStr = "";
    var count = 0;
    for (var i = _str.length - 1; i >= 0; i--) {
        if (count % 3 == 0 && count != 0) {
            newStr = _str.charAt(i) + "," + newStr;
        } else {
            newStr = _str.charAt(i) + newStr;
        }
        count++;
    }
    _str = newStr;
    return _str;
}
//区块高度列表
var currentLedgerTab = function (currentLedger, transaction_time, tx_count, hash) {
    $(".sequenceTable").prepend("<tr>" +
        "<td class='_currentLedger'>" + currentLedger + "</td>" +
        "<td>" + hash + "</td>" +
        "<td>" + tx_count + "</td>" +
        //								"<td>" + interval + "</td>" +
        "<td>" + transaction_time + "</td>" +
        "</tr>");
    $(".sequenceTable >tr").first().fadeOut();
    $(".sequenceTable >tr").first().fadeIn(500);
    if ($(".sequenceTable >tr").length > 6) {
        $(".sequenceTable >tr").last().fadeOut(200, function () {
            $(".sequenceTable >tr").last().detach();
        });
    }
}
//移动端区块高度
var weChatCurrentLedgerTab = function (currentLedger, timeDate, timeCount, tx_count, hash) {
    var _currentLedger = currentLedger;
    var _timeDate = timeDate;
    var _timeCount = timeCount;
    var _tx_count = tx_count;
    var _hash = hash;
    $(".blockTable-box").prepend(
        '<li class="p-list-item">' +
        '<div class="p-list-row clear-fix">' +
        '<div class="p-hash-wrap fl">' +
        '<h6 class="p-hash-tit">区块高度</h6>' +
        '<p class="p-hash-content">' + currentLedger + '</p>' +
        '</div>' +
        '<div class="p-tx-wrap fr">' +
        '<img src="images/p-tradeCount.png"/*tpa=http://gongshi.bumeng.cn/js/images/p-tradeCount.png*/ class="p-tx-icon"/>' +
        '<span class="p-tx-count">' + _tx_count + '</span>' +
        '</div>' +
        '</div>' +
        '<div class="p-list-row clear-fix">' +
        '<div class="p-hash-wrap fl">' +
        '<h6 class="p-hash-tit">区块hash</h6>' +
        '<p class="p-hash-content">' + _hash + '</p>' +
        '</div>' +
        '<div class="p-hash-wrap fr">' +
        '<p class="p-tx-time p-tx-year">' + _timeDate + '</p>' +
        '<p class="p-tx-time">' + _timeCount + '</p>' +
        '</div>' +
        '</div>' +
        '</li>'
    );
}

function privacyWeChatTransactionRecord(merLogo, timeDate, timeCount, fullhash) {
    var _merLogo = merLogo;
    var _timeDate = timeDate;
    var _timeCount = timeCount;
    var hash = fullhash;
    $(".tradeTable-box").append(
        '<li class="p-list-item">' +
        '<div class="p-list-row clear-fix">' +
        '<img src="' + merLogo + '" class="p-app-logo fl"/>' +
        '<div class="p-tx-wrap fr">' +
        '<img src="images/p-tradeCount.png"/*tpa=http://gongshi.bumeng.cn/js/images/p-tradeCount.png*/ class="p-tx-icon"/>' +
        '<span class="p-tx-count privacy-content">已隐私保护</span>' +
        '</div>' +
        '</div>' +
        '<div class="p-list-row clear-fix">' +
        '<div class="p-hash-wrap fl mesgTableContent" hash="' + hash + '">' +
        '<h6 class="p-hash-tit">交易hash</h6>' +
        '<p class="p-hash-content">' + setHashStyle(hash, 24) + '</p>' +
        '</div>' +
        '<div class="p-hash-wrap fr">' +
        '<p class="p-tx-time p-tx-year">' + _timeDate + '</p>' +
        '<p class="p-tx-time">' + _timeCount + '</p>' +
        '</div>' +
        '</div>' +
        '</li>'
    );
}

//移动端交易列表
function weChatTransactionRecord(merLogo, timeDate, timeCount, hash, asset_amount, fullhash) {
    var _merLogo = merLogo;
    $(".tradeTable-box").append(
        '<li class="p-list-item">' +
        '<div class="p-list-row clear-fix">' +
        '<img src="' + merLogo + '" class="p-app-logo fl"/>' +
        '<div class="p-tx-wrap fr">' +
        '<img src="images/p-tradeCount.png"/*tpa=http://gongshi.bumeng.cn/js/images/p-tradeCount.png*/ class="p-tx-icon"/>' +
        '<span class="p-tx-count">' + asset_amount + '</span>' +
        '</div>' +
        '</div>' +
        '<div class="p-list-row clear-fix">' +
        '<div class="p-hash-wrap fl mesgTableContent" hash="' + fullhash + '">' +
        '<h6 class="p-hash-tit">交易hash</h6>' +
        '<p class="p-hash-content">' + hash + '</p>' +
        '</div>' +
        '<div class="p-hash-wrap fr">' +
        '<p class="p-tx-time p-tx-year">' + timeDate + '</p>' +
        '<p class="p-tx-time">' + timeCount + '</p>' +
        '</div>' +
        '</div>' +
        '</li>'
    );
}

//获取用户总数 进件 和交易笔数
function getIndexInfo() {
    pageDone = false;
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cockpit/showIndexInfo.do",
        dataType: "json",
        success: function (data) {
            if (data.resultCode == 0) {
                pageDone = true;
                var moneySums = data.resultDate.amountSums;
                var curSums = data.resultDate.curSums;
                var userSums = data.resultDate.userSums;
                var transactionSums = data.resultDate.transactionSums;

                var set_moneySums = $(".recent-ledger-content").numberAnimate({num: moneySums, speed: 2000});
                var set_curSums = $(".userNum-curTotal").numberAnimate({num: curSums, speed: 2000});
                var set_userSums = $(".userNum-total").numberAnimate({num: userSums, speed: 2000});
                var set_transactionSums = $(".tradeNum-24h").numberAnimate({num: transactionSums, speed: 2000});

                set_moneySums.resetData(moneySums);
                set_curSums.resetData(curSums);
                set_userSums.resetData(userSums);
                set_transactionSums.resetData(transactionSums);

                $(".p_ledger_seq").text(formatNum(moneySums + ""));
                $(".p_userCount").text(formatNum(userSums + ""));
                $(".p_tradeCount").text(formatNum(transactionSums));
                setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: getRootPath() + "/cockpit/showIndexInfo.do",
                        dataType: "json",
                        success: function (data) {
                            var moneySums = data.resultDate.amountSums;
                            var curSums = data.resultDate.curSums;
                            var userSums = data.resultDate.userSums;
                            var transactionSums = data.resultDate.transactionSums;

                            var set_moneySums = $(".recent-ledger-content").numberAnimate({
                                num: moneySums,
                                speed: 2000
                            });
                            var set_curSums = $(".userNum-curTotal").numberAnimate({num: curSums, speed: 2000});
                            var set_userSums = $(".userNum-total").numberAnimate({num: userSums, speed: 2000});
                            var set_transactionSums = $(".tradeNum-24h").numberAnimate({
                                num: transactionSums,
                                speed: 2000
                            });

                            set_moneySums.resetData(moneySums);
                            set_curSums.resetData(curSums);
                            set_userSums.resetData(userSums);
                            set_transactionSums.resetData(transactionSums);

                            $(".p_ledger_seq").text(formatNum(moneySums + ""));
                            $(".p_userCount").text(formatNum(userSums + ""));
                            $(".p_tradeCount").text(formatNum(transactionSums));
                        }
                    });
                }, 10000);
            }
        }
    });
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

$(function () {
    getNodeCount();
    getIndexInfo();
    /*getUserCount();
    getTradeCount();
    getLedgerSequence();*/
    renderLedger();
    setInterval(function () {
        if (ledgerFlag) {
            if ($(".sequenceTable").children().length >= 6) {
                ledgerFlag = false;
                updateLedgerTab();
            }
        }
    }, 3000);
    renderTrade();
//	setInterval(function() {
//		if(isUpdateTrade) {
//			isUpdateTrade = false;
//			updateTrade();
//		}
//	}, 3000);
    setInterval(function () {
        if (isUpdateTrade) {
            isUpdateTrade = false;
            renderTrade();
        }
    }, 3000);
    //加载进度条
    $(".progress-strip").animate({'width': '88%'}, 3000);
    var progressInter = setInterval(function () {
        if (pageDone) {
            $(".progress-strip").animate({'width': '100%'}, 200);
            pageInterDone = true;
            clearInterval(progressInter);
        }
    }, 0);
    var pageInter = setInterval(function () {
        if (pageInterDone) {
            $(".progress-wrap").hide();
            clearInterval(pageInter);
        }
    }, 1200);
})