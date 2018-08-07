<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests"/>--%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<link rel="shortcut icon" href="${strRootPath}/images/bitbug_favicon.ico"/>
<title>合约详情</title>
<link rel="stylesheet" href="${strRootPath}/css/common.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${strRootPath}/jsp/third/contract/contract.css?v1.0" type="text/css" media="screen"/>

<body>
<div class="newcon">
    <div class="newcon_center">
        <div class="nc_top">
            <img class="nc_logo" src=""/>
            <div class="nc_title t_c">房产销售合约
                <span>
          <b>售</b>
        </span>
            </div>
            <p>合约编号：${detailContractInfo.blockContract.contractId}</p>
            <div class="nc_info clearfix">
                <div class="ncinfo_left">
                    <img id="houseImg"/></div>
                <div class="ncinfo_right">
                    <ul>
                        <li id="sketchyhouseInfo" class="F18 c18"></li>
                        <li id="detailhouseInfo" class="F18 c18 mt10 mH55 LH25"></li>
                        <li>
                            <label class="cee">
                                <em class="amount"></em>元</label>
                            <em>2000</em>份
                        </li>
                        <li class="cGray pl5">
                            <label>合约价格</label>合约发行数量
                        </li>
                        <li class="mt20 pl5 F16">
                            <span class="cGray">登记日期：</span><span id="registDate"></span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="ass_btn mt20 mb20" onclick="getInvest()">我要投资</div>
            <p class="F16">区块ID:${detailContractInfo.blockContract.contractBlockId}</p>
            <div class="nc_cp">
                <img style="left: 45%" src="${strRootPath}/jsp/third/contract/img/cp4.png"/>
            </div>
        </div>
        <!-- 后面新增部分 -->
        <div class="nc_nav clearfix">
            <ul>
                <li class="nc_liactive">合约概要</li>
                <li>合约监控</li>
                <c:forEach var="urlData" items="${detailContractInfo.contractInfoUrlList}">
                    <c:choose>
                        <c:when test="${urlData.urlType == 1 }"> <!--如果 -->
                            <li url="${urlData.url}" onclick="getPersonData(this)">借款人</li>
                        </c:when>
                        <c:when test="${urlData.urlType == 2 }">
                            <li url="${urlData.url}" onclick="getHouseData(this)">房屋信息</li>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <!--合约概要 -->
        <div class="nc_item nc_active">
            <table class="nc_outline">
                <tr>
                    <td>名称</td>
                    <td>墨海外房产销售合约</td>
                </tr>
                <tr>
                    <td>房产介绍</td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td>合约起止日期</td>
                    <td id="contractToDate"></td>
                </tr>
                <tr>
                    <td>合约编号</td>
                    <td>${detailContractInfo.blockContract.contractId}</td>
                </tr>
                <tr>
                    <td>合约类型</td>
                    <td>长租分期合约</td>
                </tr>
                <tr>
                    <td>借款信息</td>
                    <td>
                        <p>借款金额：<em class="amount"></em>元</p>
                        <p>借款期限：<em>${detailContractInfo.blockContract.term}个月</em></p>

                        <p>还款方式：
                            <em>
                                <c:choose>
                                    <c:when test="${detailContractInfo.blockContract.repaymentWay == 1 }"> <!--如果 -->
                                        等本等息
                                    </c:when>
                                    <c:otherwise> <!--否则 -->
                                        先息后本
                                    </c:otherwise>
                                </c:choose>
                            </em>
                        </p>
                        <p>本合约属于担保贷款有保障，还款来源于房屋每月租金。贷款用于日常消费。</p>
                    </td>
                </tr>
                <tr>
                    <td>登记人</td>
                    <td id="contractRegister"><span><img
                            src="${strRootPath}/jsp/third/contract/img/certified.png"/></span></td>
                </tr>
                <tr>
                    <td>合约签订人</td>
                    <td>${detailContractInfo.blockContract.contractSigner}<span><img
                            src="${strRootPath}/jsp/third/contract/img/certified.png"/></span></td>
                </tr>
                <tr>
                    <td>资产编号</td>
                    <td>${detailContractInfo.blockContract.houseCode}<span><img
                            src="${strRootPath}/jsp/third/contract/img/certified.png"/></span></td>
                </tr>
                <tr>
                    <td>房产信息</td>
                    <td id="houseinfo"><span><img
                            src="${strRootPath}/jsp/third/contract/img/certified.png"/></span></td>
                </tr>
                <tr>
                    <td>合约流程</td>
                    <td>
                        <div class="con-flow">
                            <span><b class="flow1 fcheck"></b>合约注册</span>
                            <img src="${strRootPath}/images/contract/greater-than.png">
                            <span><b class="flow2"></b>合约触发</span>
                            <img src="${strRootPath}/images/contract/greater-than.png">
                            <span><b class="flow3"></b>合约执行</span>
                            <img src="${strRootPath}/images/contract/greater-than.png">
                            <span><b class="flow5"></b>合约监督</span>
                            <img src="${strRootPath}/images/contract/greater-than.png">
                            <span><b class="flow4"></b>合约结束</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>区块ID</td>
                    <td>${detailContractInfo.blockContract.contractBlockId}</td>
                </tr>
            </table>
        </div>
        <!-- 合约监控 -->
        <div class="nc_item">
            <div class="main">
                <ul class="time-axis">
                </ul>
            </div>
        </div>
        <!-- 借款人 -->
        <div class="nc_item">
            <iframe id="personData" style="width: 100%;height:2300px;border: 0px;"></iframe>
        </div>
        <!-- 房屋信息 -->
        <div class="nc_item">
            <iframe id="houseData" style="width: 100%;height:3200px;border: 0px;"></iframe>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="${strRootPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${strRootPath}/js/layui/layui.all.js"></script>
<script type="text/javascript" src="${strRootPath}/js/layui/lay/modules/layer.js"></script>
<Script type="text/javascript">
    var strRootPath;
    $(function () {
        strRootPath = '${strRootPath}';
        //详情
        var jsonObj = eval(${detailContractInfo.blockContract.attachInfoJson});
        var amount=formatNum(jsonObj.houseTotalAmount);
        $(".amount").text(amount);

        var registDate = '${detailContractInfo.blockContract.registDate}';
        var contractToDate = '${detailContractInfo.blockContract.contractToDate}';
        var contractRegister = '${detailContractInfo.blockContract.contractRegister}';
        contractRegister = contractRegister.replace("爱租商业", "****");
        $("#contractRegister").text(contractRegister);
        $("#registDate").text(timestampToTime(registDate, 1));
        if(contractToDate==""){
            $("#contractToDate").text("");
        }else{
            $("#contractToDate").text(timestampToTime(contractToDate, 1));
        }

        var houseinfo = '${detailContractInfo.blockContract.houseInfo}';
        var houseinfoArray = houseinfo.split("|");
        if (houseinfoArray.length == 2) {
            var strHouseinfoArray = houseinfoArray[1].split("-");
            if (strHouseinfoArray.length > 0) {
                $("#sketchyhouseInfo").text(strHouseinfoArray[0] + " " + strHouseinfoArray[1]);
                $("#detailhouseInfo").text(strHouseinfoArray[2] + " " + strHouseinfoArray[3]);
            }
        } else if (houseinfoArray.length == 1) {
            $("#sketchyhouseInfo").text(houseinfoArray[0]);
        }
        $("#houseinfo").text(houseinfoArray[0]);

        //监控
        var contractArray = eval('${contractSerials}');
        if (contractArray.length > 0) {
            var html = "";
            $(".time-axis").empty();
            layui.each(contractArray, function (index, item) {
                var updateTime = timestampToTime(item.updateTime, 2);
                var updateTimeArray = updateTime.split("|");
                var statusName = "";
                if (item.contractStatus == 10) {
                    statusName = "合约注册";
                } else if (item.contractStatus == 20 || item.contractStatus == 21) {
                    statusName = "合约触发";
                } else if (item.contractStatus == 30) {
                    statusName = "合约执行";
                } else if (item.contractStatus == 40) {
                    statusName = "合约注销";
                }
                if (index == 0) {
                    html += '<li class="time-axis-item time-title tim-axis-check">';
                } else {
                    html += '<li class="time-axis-item time-title ">';

                }
                html += '<div class="time-axis-date">' +
                    '<p>' + updateTimeArray[0] + '<br/>' +
                    '<em>' + updateTimeArray[1] + '</em></p>' +
                    '<span></span>' +
                    '</div>' +
                    '<div class="time-axis-title">' + statusName + '</div>' +
                    '<p class="time-axis-achievement">' + item.contractRemark + '</p></li>' +
                    ' <li class="time-axis-item">' +
                    ' <div class="time-axis-date">' +
                    ' <p>&nbsp;' +
                    ' <br/></p>' +
                    '<span></span>' +
                    '</div>' +
                    '<div class="time-axis-title">' +
                    '<p class="time-axis-achievement">' + statusName + '成功</p></div>' +
                    '</li>';
                if (index == 0) {
                    html += '<li class="time-axis-item tim-axis-check">';
                } else {
                    html += '<li class="time-axis-item">';
                }
                html += '<div class="time-axis-date">' +
                    '<p>&nbsp;' +
                    '<br/></p>' +
                    '<span></span>' +
                    '</div>' +
                    '<div class="time-axis-title">' +
                    '<p class="time-axis-achievement">等待付款</p></div>' +
                    '</li>';
            })
            $(".time-axis").append(html);
        }
        //房屋照片
        var contractFileArray = eval('${contractFileUrls}');
        if (contractFileArray.length > 0) {
            $("#houseImg").attr("src", contractFileArray[0].fileUrl);
        } else {
            $("#houseImg").attr("src", strRootPath + "/jsp/third/contract/img/nc_demo.png");
        }
    })

    //点击投资
    function getInvest() {
        parent.location.replace(strRootPath+"/notLogged/buyProduct.htm");
    }

    //点击借款人
    function getPersonData(documentHtml) {
        var url = $(documentHtml).attr("url");
        $("#personData").attr("src", url);
    }

    //点击房产数据
    function getHouseData(documentHtml) {
        var url = $(documentHtml).attr("url");
        $("#houseData").attr("src", url);
    }

    //时间格式转换
    function timestampToTime(timestamp, flag) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = ((date.getMonth() + 1 < 10) ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = ((date.getDate() < 10) ? '0' + date.getDate() : date.getDate());
        HH = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
        mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
        ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()) + ' ';
        if (flag == 1) {
            return Y + M + D;
        } else {
            return Y + M + D + '|' + HH + mm + ss;
        }
    }

    //数字每三位逗号隔开
    function formatNum(str) {
        str=str.split(".")[0];
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
        return newStr;
    }

    $(".nc_outline tr:odd").css("background", "#fafafa")
    /* 切换部分 */
    $(".nc_nav li").on('click', function () {
        var navindex = parseInt($(this).index());
        $(this).addClass("nc_liactive").siblings().removeClass("nc_liactive");
        $(".nc_item").eq(navindex).addClass("nc_active").siblings().removeClass("nc_active");
    });

</Script>