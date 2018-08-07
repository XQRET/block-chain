package cn.inbs.blockchain.controller.boss;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.utils.Utility;
import cn.inbs.blockchain.web.BusinessService;

public class AgencyController extends BaseController {

	private static final long serialVersionUID = -2831390780797895727L;

	/**
	 * 获取机构列表
	 * 
	 * @param request
	 * @param companyType
	 * @param status
	 * @param strPageIndex
	 * @param strPageSize
	 * @author chenhao
	 * @date 2018-05-28
	 * @return
	 */
	public CDO listAgency(HttpServletRequest request, String companyType, String status, String strPageIndex,
			String strPageSize) {
		int nPageIndex = StringUtils.isNotBlank(strPageIndex) ? Integer.parseInt(strPageIndex) : 0;
		int nPageSize = StringUtils.isNotBlank(strPageSize) ? Integer.parseInt(strPageSize) : 10;

		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "AgencyService");
		cdoRequest.setStringValue("strTransName", "listAgency");
		cdoRequest.setIntegerValue("nPageCount", nPageIndex * nPageSize);
		cdoRequest.setIntegerValue("nPageSize", nPageSize);

		if (StringUtils.isNotBlank(companyType) && !StringUtils.equals(companyType, CompanyConstants.AGENCY_TYPE_ALL)) {
			// 下拉框选项值和数据库对应
			int type = Integer.parseInt(companyType) - 1;
			cdoRequest.setStringValue("companyType", String.valueOf(type));
		}
		if (StringUtils.isNotBlank(status) && !StringUtils.equals(status, CompanyConstants.AGENCY_STATUS_ALL)) {
			cdoRequest.setStringValue("status", status);
		}
		CDO cdoResponse = new CDO();
		Return ret = super.handleTrans(request, null, cdoRequest, cdoResponse);
		if (ret.getCode() != 0) {
			return null;
		}
		// 总条数
		int nRecordCount = cdoResponse.getIntegerValue("nRecordCount");
		// 分页数
		cdoResponse.setIntegerValue("nPageCount", Utility.getPageCount(nRecordCount, nPageSize));
		return cdoResponse;
	}

	/**
	 * 根据机构编号获取机构信息
	 * 
	 * @param companyID
	 * @return
	 * @author chenhao
	 * @date 2018-05-28
	 */
	public CDO getCompanyByID(String companyID) {
		if (StringUtils.isBlank(companyID)) {
			return null;
		}
		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "AgencyService");
		cdoRequest.setStringValue("strTransName", "getCompanyByID");
		cdoRequest.setIntegerValue("id", Integer.parseInt(companyID));

		CDO cdoResponse = new CDO();
		Return ret = BusinessService.getInstance().handleTrans(cdoRequest, cdoResponse);
		if (ret.getCode() != 0) {
			return null;
		}
		return cdoResponse.getCDOValue("cdoCompany");
	}
}
