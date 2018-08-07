var httpClient = new HttpClient("handleTrans.cdo");
var CompanyStatus = {};
CompanyStatus.Uncertificated = 0;
CompanyStatus.ToAudit = 1;
CompanyStatus.OK = 2;
CompanyStatus.NotPass = 3;
$(function() {
	var companyID = _public.getVal("id");
	var companyName = _public.getVal("companyName");
	var employeeId = _public.getVal("employeeId");
	bindButton(".bg44", "确定通过审核吗？", CompanyStatus.OK, companyID, companyName,
			employeeId);
	bindButton(".bgf0", "确定审核拒绝吗？", CompanyStatus.NotPass, companyID);
});

// 绑定同意按钮
function bindButton(className, message, status, companyID, companyName,
		employeeId) {
	// 1.获取按钮
	$(className).bind('click', function() {
		layer.confirm(message, {
			btn : [ '确定', '取消' ]
		// 按钮确定事件
		}, function(index) {
			var cdoReq = new CDO();
			var cdoRes = new CDO();
			cdoReq.setStringValue("strServiceName", "AgencyService");
			cdoReq.setStringValue("strTransName", "updateCompanyStatus");
			cdoReq.setLongValue("status", status);
			cdoReq.setLongValue("companyID", Number(companyID));
			// 审核通过才修改employee表的strName字段
			if (status === CompanyStatus.OK) {
				cdoReq.setStringValue("companyName", companyName);
				cdoReq.setLongValue("employeeId", Number(employeeId));
			}
			var ret = httpClient.handleTrans(cdoReq, cdoRes);
			if (ret != null) {
				layer.alert(ret.getText());
				if (ret.getCode() == 0) {
					window.location.replace("./agencyList.htm");
				}
			} else {
				layer.alert("执行异常");
			}
			layer.close(index);
		}, function(index) {
			layer.close(index);
		});
	});
}
