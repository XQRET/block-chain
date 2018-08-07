 <div class="allinfo">
    <div class="F16">预售用户列表</div>
    <table class="all_table mt20">
    <thead>
    <tr>
    <td>序号</td>
    <td>预售项目</td>
    <td>用户姓名</td>
    <td>手机号</td>
    <td>预约时间</td>
    </tr>
    </thead>
    <tbody>
    {{# layui.each(d.data, function(index, item){ }}
    <tr>
     <td>{{ layui.empty(item.id) }}</td>
     <td>{{ layui.empty(d.namesS) }}</td>
    <td>{{ layui.empty(item.reservationNoticeName) }}</td>
    <td>{{ layui.empty(item.sendMessageMobile) }}</td>
    <td>{{ item.releaseTime==null?'--':layui.util.toDateString(item.reservationNoticeTime) }}</td>
    </tr>
    {{# }); }}
    </tbody>
    </table>
</div>


