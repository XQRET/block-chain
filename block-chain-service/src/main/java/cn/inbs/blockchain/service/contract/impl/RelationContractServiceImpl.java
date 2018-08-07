/**  
 * Project Name:trunk  
 * File Name:RelationContractService.java  
 * Package Name:cn.inbs.blockchain.service.contract.impl  
 * Date:2018年6月5日上午10:38:03  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.service.contract.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.inbs.blockchain.common.commonbean.ContractFileParam;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.dao.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractFileUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractInfoUrlMapper;
import cn.inbs.blockchain.service.contract.IRelationContractService;

/**  
 * ClassName: RelationContractService <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月5日上午10:38:03  
 */
@Service("relationContractService")
public class RelationContractServiceImpl implements IRelationContractService {
	
    private static Logger logger = LoggerFactory.getLogger(RelationContractServiceImpl.class);

	
	@Resource
	private ContractFileUrlMapper contractFileUrlMapper;
	
    @Resource
    private BlockContractMapper blockContractMapper;
    
    @Resource
    private ContractInfoUrlMapper contractInfoUrlMapper;//关联信息
    
	/**  
	 * 新增关联合约底层数据.  
	 * @see cn.inbs.blockchain.service.contract.IRelationContractService#insertRelationContract(java.lang.String, java.util.List)  
	 */
	@Override
	public void insertRelationContract(String companyBlockId, BlockContract blockContract, Map<String,String> paramMap) {
		Date date = new Date();
		int num = 0;
		String  contractId = blockContract.getContractId();
		List<ContractFileUrl>  files = blockContract.getFiles();
		if(!CollectionUtils.isEmpty(files)) {
			for(ContractFileUrl file : files) {
				String type = file.getFileType();
				if(!paramMap.containsKey(type)) {
					break;
				}
				ContractFileParam fileParam = JSON.parseObject(paramMap.get(type),ContractFileParam.class);
				file.setContractId(contractId);
				file.setFileMode(fileParam.getModel());
				file.setCreateTime(date);
				file.setUpdateTime(date);
				num = contractFileUrlMapper.insertSelective(file);
				if(num != 1) {
		            if (logger.isErrorEnabled()) {
		                logger.error("新增底层资料文件失败");
		            }
					throw new BusinessException(BusinessErrorConstants.CONTRACT_0030);
				}
			}
			ContractInfoUrl contractInfoUrl = new ContractInfoUrl();
	        contractInfoUrl.setCreateTime(date);//创建时间
	        contractInfoUrl.setUpdateTime(date);//修改时间
	        contractInfoUrl.setContractId(contractId);//合约ID
	        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_PERSON_INFO);
	        contractInfoUrl.setUrl(ContractConstants.CONTRACT_IMPORT_PERSON_INFO_URL+contractId);
	        num = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
			if(num != 1) {
	            if (logger.isErrorEnabled()) {
	                logger.error("新增个人信息地址失败");
	            }
				throw new BusinessException(BusinessErrorConstants.CONTRACT_0030);
			}
	        
	        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_HOUSE_INFO);
	        contractInfoUrl.setUrl(ContractConstants.CONTRACT_IMPORT_HOUSE_INFO_URL+contractId);
	        num = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
			if(num != 1) {
	            if (logger.isErrorEnabled()) {
	                logger.error("新增房屋信息地址失败");
	            }
				throw new BusinessException(BusinessErrorConstants.CONTRACT_0030);
			}
	        
	        contractInfoUrl.setUrlType(ContractConstants.CONTRACT_INFO_FILE_INFO);
	        contractInfoUrl.setUrl(ContractConstants.CONTRACT_IMPORT_FILE_INFO_URL+contractId);
	        num = contractInfoUrlMapper.insertContractInfoUrl(contractInfoUrl);
			if(num != 1) {
	            if (logger.isErrorEnabled()) {
	                logger.error("新增ZIP下载地址失败");
	            }
				throw new BusinessException(BusinessErrorConstants.CONTRACT_0030);
			}
		}

		num = blockContractMapper.updateImportTypeSuccessBycontractId(companyBlockId,contractId,ContractConstants.CONTRACT_TYPE_2);
		if(num != 1) {
            if (logger.isErrorEnabled()) {
                logger.error("更新合约状态失败");
            }
			throw new BusinessException(BusinessErrorConstants.CONTRACT_0030);
		}
	}


	/**  
	 * 删除合约.  
	 * @see cn.inbs.blockchain.service.contract.IRelationContractService#deleteImportContract(java.lang.String)  
	 */
	@Override
	public void deleteImportContract(String companyBlockId,String contractId) {
		int num =blockContractMapper.deleteImportContract(companyBlockId,contractId);
		if(num != 1) {
            if (logger.isErrorEnabled()) {
                logger.error("删除合约失败");
            }
			throw new BusinessException(BusinessErrorConstants.CONTRACT_0031);
		}
	}


	/**  
	 * 查询待关联合约.  
	 * @see cn.inbs.blockchain.service.contract.IRelationContractService#queryContractPage(PagePo)
	 */
	@Override
	public PagePo queryContractPage(PagePo pagePo) {
        //输出分页请求参数
        if (logger.isDebugEnabled()) {
            logger.debug("合约列表查询请求参数:{}", pagePo.toString());
        } 
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        List<BlockContract> blockContracts = blockContractMapper.selectPageList(conditionParamMap);
        pagePo.setPageInfoList(blockContracts);
        //查询总条数
        pagePo.setTotalCount(blockContractMapper.selectTotalCount(conditionParamMap));
        //记录日志值返回
        if (logger.isDebugEnabled()) {
            logger.debug("查询合约分页信息结果:{}", pagePo.toString());
        }
        return pagePo;
	}

}
  
