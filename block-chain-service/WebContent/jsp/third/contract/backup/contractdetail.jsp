<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<%@ include file="../../../common/common.jsp" %>
<body class="body-content">
<div class="alltitle">
    <p>合约详情</p>
</div>
<div class="allcenter pt20 pb20">
    <div class="aloneinfo con-info clearfix">
        <div class="con-title">
            <label id="contractName">${detailContractInfo.blockContract.contractName}</label>
            <span class="fr cGray" id="contractId">合约ID:${detailContractInfo.blockContract.contractId}</span>
        </div>
        <div class="con-logo"><img src="../images/contract/img_logos.png"/></div>
        <ul>
            <li><label>登记日期：</label><span id="registDate"></span></li>
            <li style="display: none"><label>注销日期：</label><span id="contractToDate"></span></li>
            <li><label>登记人：</label><span
                    id="register">${detailContractInfo.blockContract.contractRegister}</span></li>
            <li><label>合约签订人：</label><span id="signer">${detailContractInfo.blockContract.contractSigner}</span></li>
            <li><label>利率：</label><span id="interestRate"></span></li>
            <li><label>还款方式：</label><span id="repaymentWay">
                   <c:choose>
                       <c:when test="${detailContractInfo.blockContract.repaymentWay == 1 }"> <!--如果 -->
                           等本等息
                       </c:when>
                       <c:otherwise> <!--否则 -->
                           先息后本
                       </c:otherwise>
                   </c:choose>
               </span></li>
            <li><label>合约金额：</label><span id="amount">${detailContractInfo.blockContract.amount}</span></li>
            <li><label>资产编号：</label><span id="houseCode"
                                          class="F13">${detailContractInfo.blockContract.houseCode}</span></li>
            <li class="w100b"><label>房产信息：</label><span
                    id="houseInfo">${detailContractInfo.blockContract.houseInfo}</span></li>
            <li class="w100b"><label>区块 ID：</label><span
                    id="blockId">${detailContractInfo.blockContract.contractBlockId}</span></li>
            <li class="w100b"><label>合约概要：</label>
                <textarea rows="4" cols="64" id="remark">${detailContractInfo.blockContract.remark}</textarea>
            </li>
            <li id="underData" class="w100b"><label>底层数据：</label>
                <c:forEach var="urlData" items="${detailContractInfo.contractInfoUrlList}">
                    <c:choose>
                        <c:when test="${urlData.urlType == 1 }"> <!--如果 -->
                            <a id="personData"  class="addUnderline tdHover"
                               onclick="${urlData.url}">查看个人数据</a>
                        </c:when>
                        <c:when test="${urlData.urlType == 2 }">
                            <a id="houseData"  class="addUnderline tdHover"
                               onclick="${urlData.url}">查看房产数据</a>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </li>
            <li class=" w100b">
                <div class="con-flow">
                    <span><b class="flow1"></b>合约注册</span>
                    <img src="../images/contract/greater-than.png"/>
                    <span><b class="flow2"></b>合约触发</span>
                    <img src="../images/contract/greater-than.png"/>
                    <span><b class="flow3"></b>合约执行</span>
                    <img src="../images/contract/greater-than.png"/>
                    <span><b class="flow4"></b>合约注销</span>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
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

    $(function () {
        var strRootPath = getRootPath();
        var registDate = '${detailContractInfo.blockContract.registDate}';
        var contractStatus = '${detailContractInfo.blockContract.contractStatus}';
        var importType = '${detailContractInfo.blockContract.importType}';

        registDate = timestampToTime(registDate);
        contractToDate = timestampToTime(contractToDate);
        var term = '${detailContractInfo.blockContract.term}';
        var amount = '${detailContractInfo.blockContract.amount}';
        var status = contractStatus.toString().substring(0, 1);  //状态
        while (status > 0) {
            var flow = "flow" + status;
            $(".con-flow [class=" + flow + "]").addClass("fcheck");
            status--;
        }
        $("#registDate").text(registDate);
        $("#interestRate").text("******" + "%");

        if (term != null) {
            $("#amount").text(amount + "元/" + term + "月");
        } else {
            $("#amount").text(amount + "元");
        }
        if (status > 2) {
            $("#contractToDate").text(contractToDate);
            $("#contractToDate").parent().css("display", "block");
        }
        //根据导入类型来判断显示底层数据
        if (importType == 1) {
            $("#underData").css("display", "none");
        } else {
            $("#underData").css("display", "block");
        }



        var personData = $("#personData").attr("onclick");//个人报告
        var houseData = $("#houseData").attr("onclick");//房产信息

        if (importType==0){
            // 数据插入类型为0(http入数据)
            var personDataOnclick = "openNewView('" + personData + "','个人报告')";
            $("#personData").attr("onclick",personDataOnclick);

            var houseDataOnclick = "openNewView('" + houseData + "','房产信息')";
            $("#houseData").attr("onclick",houseDataOnclick);
        }else if(importType==2){
            var personDataOnclick = "openNewView('" + strRootPath+ personData + "','个人报告')";
            $("#personData").attr("onclick",personDataOnclick);

            var houseDataOnclick = "openNewView('" + strRootPath+ houseData + "','房产信息')";
            $("#houseData").attr("onclick",houseDataOnclick);
        }
    })


    //时间格式转换
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = ((date.getMonth() + 1 < 10) ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = (date.getDate() < 10) ? '0' + date.getDate() : date.getDate();
        return Y + M + D;
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
</script>
