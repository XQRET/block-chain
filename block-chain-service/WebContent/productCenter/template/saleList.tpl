<table class="loan_talbe newlt w tc into_pieces">
    <thead>
      <tr class="taHead">
        <td>序号</td>
        <td>类型</td>
        <td>预售项目</td>
        <td>项目详情</td>
        <td>发布时间</td>
        <td>状态</td>
        <td>预售用户列表</td>
        <td>操作</td>
      </tr>
    </thead>
    <tbody>
      {{# layui.each(d, function(index, item){ }}
        <tr>
        <td>{{ layui.empty(item.id) }}</td>
        {{# if (item.productType == 0) { }}
        <td>固收资产</td>
        {{# } else if(item.productType == 1) { }}
        <td>非固收资产</td>
        {{# } else { }}
        <td>--</td>
        {{# } }}
        <td>{{ layui.empty(item.productName) }}</td>
        <td><a class="orangebtn" onclick="getProductSaleDetail('{{ layui.empty(item.id) }}')">查看</a></td>
        <td>{{ item.releaseTime==null?'--':layui.util.toDateString(item.releaseTime) }}</td>
        {{# if (item.productStatus == 1) { }}
        <td>待预售</td>
        {{# } else if(item.productStatus == 2) { }}
        <td>预售中</td>
        {{# } else if(item.productStatus == 3) { }}
         <td>预售完成</td>
        {{# } else { }}
        <td>--</td>
        {{# } }}
        <td><a class="orangebtn" onclick="window.location.href='saleUserList.htm?id={{ layui.empty(item.id) }}&name={{ layui.empty(item.productName) }}'">查看</a></td>

        {{# if (item.productStatus == 1) { }}
        <td><a class="bluebtn" onclick="updateType('2','{{ layui.empty(item.id) }}')">上架</a></td>
        {{# } else if(item.productStatus == 2) { }}
        <td><a class="bluebtn" onclick="updateType('3','{{ layui.empty(item.id) }}')">下架</a></td>
        {{# } else if(item.productStatus == 3) { }}
        <td class="cf2">已下架</td>
        {{# } else { }}
        <td>--</td>
        {{# } }}
        </tr>
    {{# }); }}
    </tbody>
 </table>