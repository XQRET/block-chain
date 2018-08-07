package cn.inbs.blockchain.controller.boss;

import org.apache.commons.lang.StringUtils;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import cn.inbs.blockchain.web.BusinessService;

/**
 * 合同详情处理
 * 
 * @author chenhao 2018-6-8 10:32:57
 */
public class ContractController extends BaseController {

	private static final long serialVersionUID = -7757184225399465072L;

	/**
	 * 根据合同编号获取用户信息
	 * 
	 * @param contractID
	 * @return
	 */
	public CDO getPersonByContractID(String contractID) {
		if (StringUtils.isBlank(contractID)) {
			return null;
		}
		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "ContractService");
		cdoRequest.setStringValue("strTransName", "getPersonByContractID");
		cdoRequest.setStringValue("contractId", contractID);

		CDO cdoResponse = new CDO();
		Return ret = BusinessService.getInstance().handleTrans(cdoRequest, cdoResponse);
		if (ret.getCode() != 0) {
			return null;
		}
		return cdoResponse.getCDOValue("cdoPerson");
	}

	/**
	 * 根据合同编号获取房产信息
	 * 
	 * @param contractID
	 * @return
	 */
	public CDO getHouseByContractID(String contractID) {
		if (StringUtils.isBlank(contractID)) {
			return null;
		}
		CDO cdoRequest = new CDO();
		cdoRequest.setStringValue("strServiceName", "ContractService");
		cdoRequest.setStringValue("strTransName", "getHouseByContractID");
		cdoRequest.setStringValue("contractId", contractID);

		CDO cdoResponse = new CDO();
		Return ret = BusinessService.getInstance().handleTrans(cdoRequest, cdoResponse);
		if (ret.getCode() != 0) {
			return null;
		}
		return cdoResponse.getCDOValue("cdoHouse");
	}
}
