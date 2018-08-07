package cn.inbs.blockchain.controller.boss;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import cn.inbs.blockchain.common.utils.Utility;

public class EmployeeController extends BaseController {

	private static final long serialVersionUID = -97817617419329999L;

	/**
	 * 获取员工列表
	* @param req
	* @return
	* @author tanglei
	* @date 2017-10-24
	 */
	public CDO queryEmployeeList(HttpServletRequest request,String strId,String strMobile,String strName,String strPageIndex,String strPageSize) {

		int nPageIndex = StringUtils.isNotBlank(strPageIndex) ? Integer.parseInt(strPageIndex) : 0;
		int nPageSize = StringUtils.isNotBlank(strPageSize) ? Integer.parseInt(strPageSize) : 10 ;

		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "EmployeeService");
		cdoRequest.setStringValue("strTransName", "queryEmployee");
		cdoRequest.setIntegerValue("nPageCount", nPageIndex * nPageSize);
		cdoRequest.setIntegerValue("nPageSize", nPageSize);

		if(StringUtils.isNotBlank(strName)){
			cdoRequest.setStringValue("strName", "%"+strName+"%");
		}
		if(StringUtils.isNotBlank(strId)){
			cdoRequest.setLongValue("lId",Long.parseLong(strId));
		}
		if(StringUtils.isNotBlank(strMobile)){
			cdoRequest.setStringValue("strMobile", strMobile);
		}

		CDO cdoResponse = new CDO();
		Return ret = super.handleTrans(request,null,cdoRequest,cdoResponse);
		if(ret.getCode()!=0)
		{
			return null;
		}
		int nRecordCount = cdoResponse.getIntegerValue("nRecordCount");//总条数
		cdoResponse.setIntegerValue("nPageCount", Utility.getPageCount(nRecordCount, nPageSize));//分页数
		return cdoResponse;
	}
}
