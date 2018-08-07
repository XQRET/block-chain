<!-- 合约注册模板-->
<div class="alltitle">
<p>合约注册</p>
</div>
<div class="dd_info">
<div class="dd_text">
<div class="FB">请求参数</div>
<table>
<thead>
<tr><td>字段名</td><td>变量名</td><td>必填</td><td>类型</td><td>说明</td></tr>
</thead>
<tbody>
<tr><td class="FB" colspan="5">协议参数</td></tr>
<tr><td>接口版本</td><td>version</td><td>是</td><td>String(8)</td><td>版本号，默认为1.0</td></tr>
<tr><td>签名方式</td><td>sign_type</td><td>是</td><td>String(8)</td><td>签名类型</td></tr>
<tr><td>通讯方ID</td><td>mch_id</td><td>是</td><td>String(32)</td><td>通讯方的id</td></tr>
<tr><td>通讯方签名</td><td>mch_sign</td><td>是</td><td>String(256)</td><td>签名结果</td></tr>
<tr><td class="FB" colspan="5">业务参数</td></tr>
<tr><td>节点ID</td><td>node_id</td><td>否</td><td>String(32)</td><td>节点ID,可以通过平台基本信息页面中获取</td></tr>
<tr><td>链ID</td><td>chain_id</td><td>是</td><td>String(32)</td><td>链ID,可以通过平台基本信息页面中获取</td></tr>
<tr><td>合同ID</td><td>asset_id</td><td>是</td><td>String</td><td>发行成功的合同的唯一标识</td></tr>
<tr><td>放款金额</td><td>amount</td><td>是</td><td>String(32)</td><td>放款的金额</td></tr>
<tr><td>放款时间</td><td>time</td><td>是</td><td>Int</td><td>放款成功触发的时间</td></tr>
<tr><td>放款渠道</td><td>channel</td><td>是</td><td>String(32)</td><td>放款的资金通道</td></tr>
<tr><td>交易订单号</td><td>order_id</td><td>是</td><td>String(32)</td><td>放款订单对应的交易单号</td></tr>
<tr><td>合约概要</td><td>outline</td><td>是</td><td>JSON</td><td>合约概要内容，不同阶段合约概要内容不同，系统生成，格式为json</td></tr>
<tr><td>请求时间</td><td>timestamp</td><td>是</td><td>Int</td><td>unix时间戳</td></tr>
</tbody>
</table>
<div class="FB ">返回参数</div>
<table>
<thead> <tr><td>字段名</td><td>变量名</td><td>必填</td><td>类型</td><td>说明</td></tr> </thead>
<tbody>
<tr><td class="FB" colspan="5">协议参数</td></tr>
<tr><td>接口版本</td><td>version</td><td>是</td><td>String(8)</td><td>版本号，默认为1.0</td></tr>
<tr><td>签名方式</td><td>sign_type</td><td>是</td><td>String(8)</td><td>签名类型</td></tr>
<tr><td>通讯方ID</td><td>mch_id</td><td>是</td><td>String(32)</td><td>通讯方的id</td></tr>
<tr><td>通讯方签名</td><td>mch_sign</td><td>是</td><td>String(256)</td><td>签名结果</td></tr>
</tbody>
</table>
</div>
<div class="dd_tips">
<p><img src="../images/notlogged/dd_tips.png"/>注意：发行方的私钥、公钥是由平台生成，私钥仅对申请返回的sign_str进行签名。</p>
<p>如果是测试链，可以直接使用如下私钥和公钥：prikey : vL/lFwaGGRxG0ND13xu1XxQK7qP6ny3Kt3LLLKWn8kM=pubkey : Aj56Q9WYRtRhv7EVGmm29mOmLYSSPdMaoH9lchnGJM1x</p>
</div>
</div>