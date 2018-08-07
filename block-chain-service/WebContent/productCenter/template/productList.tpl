<!-- 产品列表 -->
 <table class="loan_talbe newlt w tc into_pieces">
        <thead>
          <tr class="taHead">
            <td>序号</td>
            <td>类型</td>
            <td>合约ID</td>
            <td>拆分份数</td>
            <td>合约金额：万元</td>
            <td>合约年利率</td>
            <td>合约期限：月</td>
            <td>发布时间</td>
            <td>状态</td>
            <td>进度</td>
          </tr>
        </thead>
        <tbody>
        {{# layui.each(d, function(index, item){ }}
        <tr>
        <td>{{ layui.empty(item.id) }}</td>
        {{# if (item.contractProductType = 0) { }}
        <td>固收资产</td>
        {{# } else if(item.contractProductType = -1) { }}
        <td>非固收资产</td>
        {{# } else { }}
        <td>--</td>
        {{# } }}
        <td><a class="addUnderline tdHover" onclick="getContractDetail('{{ layui.empty(item.contractId) }}')">{{ layui.empty(item.contractId) }}</a></td>
        <td>{{ layui.empty(item.contractServings) }}</td>
        <td>{{ layui.empty(item.contractAmount) }}</td>
        <td>{{ layui.empty(item.yearOfRate) }}%</td>
        <td>{{ layui.empty(item.contractTerm) }}</td>
        <td>{{ item.releaseTime==null?'--':layui.util.toDateString(item.releaseTime) }}</td>
        {{# if (item.raiseStatus == 1) { }}
        <td>待募集</td>
        <td><a class="bluebtn" onclick='toRaise(this)'>募集</a></td>
        {{# } else if(item.raiseStatus == 2) { }}
        <td>募集中</td>
        <td>{{ layui.empty(item.contractCurrentProgress) }}/{{ layui.empty(item.contractServings) }}</td>
        {{# } else if(item.raiseStatus == 3){ }}
        <td>募集完成</td>
        <td>{{ item.updateTime==null?'--':layui.util.toDateString(item.updateTime) }}</td>
        {{# } }}
        </tr>
        {{# }); }}
        </tbody>
   </table>