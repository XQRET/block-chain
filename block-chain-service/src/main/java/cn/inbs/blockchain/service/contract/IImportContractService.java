/**  
 * Project Name:trunk  
 * File Name:IImportContractService.java  
 * Package Name:cn.inbs.blockchain.service.contract  
 * Date:2018年5月28日上午11:40:43  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.service.contract;

import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.BlockContract;
import org.springframework.web.multipart.MultipartFile;

import cn.inbs.blockchain.common.commonbean.ContractImport;

/**  
 * ClassName: IImportContractService <br/>  
 * Description: 合约导入. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日上午11:40:43  
 */
public interface IImportContractService {
	
	byte[] createContractExecel(BlockContract bo, boolean isThird, String path);
	
	/**
	 * 
	 * insertImportContract:合约导入. <br/>  
	 * @author anxiaoyu
	 * @param file
	 * @param lEmployeeId
	 * @return
	 */
	String insertImportContract(MultipartFile file,Long lEmployeeId);
	
	/**
	 * 
	 * newInsertContract:新增合约信息. <br/>  
	 * @author anxiaoyu
	 * @param contractImport
	 * @param blockCompany
	 */
	void newInsertContract(ContractImport contractImport,BlockCompany blockCompany);
}
  
