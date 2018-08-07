<!-- 合约触发模板-->
<div class="alltitle">
<p>合约触发</p>
</div> 
<div class="dd_info">
<div class="dd_text">
<div class="FB">返回参数</div>
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
<tr><td>合同ID</td><td>asset_id</td><td>是</td><td>String</td><td>发行成功的合同的唯一标识</td></tr>
<tr><td>放款时间</td><td>time</td><td>是</td><td>Int</td><td>放款成功触发的时间</td></tr>
<tr><td>放款渠道</td><td>channel</td><td>是</td><td>String(32)</td><td>放款的资金通道</td></tr>
<tr><td>合约概要</td><td>outline</td><td>是</td><td>JSON</td><td>合约概要内容，不同阶段合约概要内容不同，系统生成，格式为json</td></tr>
</tbody>
</table>
</div>
</div>