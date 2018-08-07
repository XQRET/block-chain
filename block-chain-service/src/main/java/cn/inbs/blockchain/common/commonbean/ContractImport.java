/**  
 * Project Name:trunk  
 * File Name:ContractImportPo.java  
 * Package Name:cn.inbs.blockchain.dao.po  
 * Date:2018年5月28日上午11:04:12  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */    
package cn.inbs.blockchain.common.commonbean;

import java.lang.reflect.Field;
/**  
 * ClassName: ContractImportPo <br/>  
 * Description: 导入参数实体. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日上午11:04:12  
 */
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.BlockContract;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.BlockUtils;
import cn.inbs.blockchain.common.utils.StringUtils;

public class ContractImport {
	
	/** 房屋编号 */
	private String houseCode;
	
	/** 合约签订人 */
	private String signer;
	
	/** 房屋信息 */
	private String houseInfo;
	
	/** 贷款金额 */
	private BigDecimal amount;
	
	/** 贷款利率 */
	private BigDecimal interestRate;
	
	/** 贷款期限 */
	private Integer term;
	
	/** 合约注册时间 */
	private Date registDate;
	
	/** 合约状态 */
	private String status = ContractConstants.CONTRACT_STATUS_XH;
	
	/** 银行名称 */
	private String bank;
	
	/** 身份证号 */
	private String idNo;
	
	/** 手机号码 */
	private String phoneNumber;
	
	/** 唯一标示 */
	private String importUniqueSign;
	
	/** 附加信息 */
	private String attachInfoJson;
	
	
	public ContractImport(JSONObject jsonObject,Map<String,Integer> intParamMap,Row row){
		Map<String,String> attachInfoMap = new HashMap<String,String>();
		for (Map.Entry<String, Integer> entry : intParamMap.entrySet()) {
			String key = entry.getKey();
			
			Class<?> clazz = this.getClass();
			Field filed = null;
			String temp = null;
			try {
				if(ContractConstants.REGIST_DATE.equals(key)) { //时间类型
					registDate = row.getCell(intParamMap.get(ContractConstants.REGIST_DATE)).getDateCellValue();
				}else{
					if(intParamMap.get(key) != -1) {
						row.getCell(intParamMap.get(key)).setCellType(Cell.CELL_TYPE_STRING);
						temp = row.getCell(intParamMap.get(key)).getStringCellValue();	
						if(ContractConstants.SIGNER.equals(key)  
								|| ContractConstants.BANK.equals(key)
								|| ContractConstants.HOUSE_CODE.equals(key)
								|| ContractConstants.SIGNER_IDNO.equals(key)
								|| ContractConstants.SIGNER_PHONE_NUMBER.equals(key)
								|| ContractConstants.HOUSE_INFO.equals(key)
								|| ContractConstants.IMPORT_UNIQUE_SIGN.equals(key)
								) {//文本类型
							filed = clazz.getDeclaredField(key);
					        filed.set(this,temp);
						}else if(ContractConstants.AMOUNT.equals(key) 
								|| ContractConstants.INTEREST_RATE.equals(key)
								) { //浮点数类型
							filed = clazz.getDeclaredField(key);
							filed.set(this, StringUtils.isEmpty(temp)?null:new BigDecimal(temp));
						}else if(ContractConstants.TERM.equals(key)) { //整数类型
							filed = clazz.getDeclaredField(key);
				        	filed.set(this, StringUtils.isEmpty(temp)?null:Integer.parseInt(temp));
				        }else{ //附加数据
						    attachInfoMap.put(key, StringUtils.isEmpty(temp)?"":temp);
						}
					}
				}
			}catch (Exception e) {
				throw new BusinessException(jsonObject.getString(key)+"单元格格式错误");
			}
		}
		attachInfoJson = JSONUtils.toJSONString(attachInfoMap);
	}
	
	public String getHouseCode() {
		return houseCode;
	}
	

	public Date getRegistDate() {
		return registDate;
	}	

	public String getImportUniqueSign() {
		return importUniqueSign;
	}


	/**
	 * 
	 * validate:校验参数. <br/>  
	 * @author anxiaoyu
	 * @return
	 */
	public String validate() {

		if(StringUtils.isEmpty(bank)) {
			return new BusinessException("资金方为空").getMessage();
		}
		if(StringUtils.isEmpty(houseCode)) {
			return new BusinessException("房产证编号为空").getMessage();
		}
		if(StringUtils.isEmpty(houseInfo)) {
			return new BusinessException("房屋信息为空").getMessage();
		}
		if(StringUtils.isEmpty(signer)) {
			return new BusinessException("姓名为空").getMessage();
		}
		if(StringUtils.isEmpty(idNo)) {
			return new BusinessException("身份证号为空").getMessage();
		}
		if(StringUtils.isEmpty(phoneNumber)) {
			return new BusinessException("手机号码为空").getMessage();
		}
		if(amount == null) {
			amount = new BigDecimal((new Random().nextInt(20) + 1)*1000);
			//return new BusinessException("借款金额为空").getMessage();
		}
		if(term == null) {
			term = 12;
			//return new BusinessException("借款期限为空").getMessage();
		}
		if(registDate == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE,-(new Random().nextInt(300) + (term/12)*365));
			registDate = calendar.getTime();
			//return new BusinessException("放款日期为空").getMessage();
		}
		if(interestRate == null) {
			interestRate = new BigDecimal("18");
			//return new BusinessException("借款年利率为空").getMessage();
		}
		if(StringUtils.isEmpty(importUniqueSign)) {
			return new BusinessException("导入唯一标示为空").getMessage();
		}
		return null;
	}
	
	/**
	 * 
	 * createContract:创建合约. <br/>  
	 * @author anxiaoyu
	 * @param blockCompany
	 * @return
	 */
	public BlockContract createContract(BlockCompany blockCompany) {
		BlockContract blockContract = new BlockContract();
        Calendar calendar = Calendar.getInstance();

        blockContract.setCompanyId(blockCompany.getId()); 
        blockContract.setRegistCompanyBlockId(blockCompany.getCompanyBlockId());
        blockContract.setContractRegister(blockCompany.getCompanyName());
        blockContract.setContractName(signer + "分期租赁合约");
        blockContract.setContractId(BlockUtils.createContractId(houseCode));
        blockContract.setContractBlockId(BlockUtils.createContractBlockId(houseCode));
        blockContract.setAmount(amount);
        blockContract.setBank(bank);
        blockContract.setHouseCode(houseCode.trim().replace(" ", ""));
        blockContract.setHouseInfo(houseInfo);
        blockContract.setInterestRate(interestRate);
	    calendar.setTime(registDate);
        calendar.add(Calendar.DATE,-3);
        blockContract.setRegistDate(calendar.getTime());
        blockContract.setRepaymentWay("1");
        blockContract.setContractSigner(signer);
        blockContract.setTerm(term); 
        blockContract.setContractStatus(status);
        blockContract.setIdNo(idNo);
        blockContract.setPhoneNumber(phoneNumber);
        blockContract.setImportType(ContractConstants.CONTRACT_TYPE_1);
        blockContract.setImportUniqueSign(importUniqueSign);
        blockContract.setAttachInfoJson(attachInfoJson);
        calendar.setTime(registDate);
        calendar.add(Calendar.MONTH,term);
        blockContract.setUpdateTime(calendar.getTime());
        blockContract.setContractToDate(calendar.getTime());
       
        return blockContract;
	}



    
}
  
