<!-- 接口概述-->
<div class="alltitle">
    <p>接口概述</p>
</div>
<div class="dd_info">
<div class="dd_text">
<div class="ports_title">API接口说明</div>
<p>reset 不带 cursor 参数调用此接口时，此字段值为 true。或者在服务器端有维护需要强制通知客户端重新同步时为 true，其余为 false。</p>
<p>cursor 一个字符串，表示已返回的列表在操作记录中的位置。下次调用此接口时把此值传递上来，以获取此后的列表。</p>
<p>has_more 如果是 true ，表示还有更多记录可以返回，可继续调用此接口获取。如果是 false，表示没有更多记录了，建议至少 5 分钟后再调用。</p>
</div>
</div>