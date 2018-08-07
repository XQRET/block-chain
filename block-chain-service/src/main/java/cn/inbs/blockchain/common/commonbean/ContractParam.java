/**  
 * Project Name:trunk  
 * File Name:ContractParam.java  
 * Package Name:cn.inbs.blockchain.dao.po  
 * Date:2018年5月31日下午5:00:39  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.common.commonbean;

import java.math.BigDecimal;
import java.util.Date;

/**  
 * ClassName: ContractParam <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月31日下午5:00:39  
 */
public class ContractParam {
	
	/** 房屋编号 */
	private Integer houseCode;
	
	/** 合约签订人 */
	private Integer signer;
	
	/** 房屋信息 */
	private Integer houseInfo;
	
	/** 贷款金额 */
	private Integer amount;
	
	/** 贷款利率 */
	private Integer rate;
	
	/** 贷款期限 */
	private Integer term;
	
	/** 合约注册时间 */
	private Integer registDate;
	
	/** 合约状态 */
	private Integer status;
	
	/** 银行名称 */
	private Integer bank;

	/**  
	 * houseCode.  
	 * @return  the houseCode  
	 */
	public Integer getHouseCode() {
		return houseCode;
	}

	/**  
	 * houseCode.  
	 * @param   houseCode    the houseCode to set  
	 */
	public void setHouseCode(Integer houseCode) {
		this.houseCode = houseCode;
	}

	/**  
	 * signer.  
	 * @return  the signer  
	 */
	public Integer getSigner() {
		return signer;
	}

	/**  
	 * signer.  
	 * @param   signer    the signer to set  
	 */
	public void setSigner(Integer signer) {
		this.signer = signer;
	}

	/**  
	 * houseInfo.  
	 * @return  the houseInfo  
	 */
	public Integer getHouseInfo() {
		return houseInfo;
	}

	/**  
	 * houseInfo.  
	 * @param   houseInfo    the houseInfo to set  
	 */
	public void setHouseInfo(Integer houseInfo) {
		this.houseInfo = houseInfo;
	}

	/**  
	 * amount.  
	 * @return  the amount  
	 */
	public Integer getAmount() {
		return amount;
	}

	/**  
	 * amount.  
	 * @param   amount    the amount to set  
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**  
	 * rate.  
	 * @return  the rate  
	 */
	public Integer getRate() {
		return rate;
	}

	/**  
	 * rate.  
	 * @param   rate    the rate to set  
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	/**  
	 * term.  
	 * @return  the term  
	 */
	public Integer getTerm() {
		return term;
	}

	/**  
	 * term.  
	 * @param   term    the term to set  
	 */
	public void setTerm(Integer term) {
		this.term = term;
	}

	/**  
	 * registDate.  
	 * @return  the registDate  
	 */
	public Integer getRegistDate() {
		return registDate;
	}

	/**  
	 * registDate.  
	 * @param   registDate    the registDate to set  
	 */
	public void setRegistDate(Integer registDate) {
		this.registDate = registDate;
	}

	/**  
	 * status.  
	 * @return  the status  
	 */
	public Integer getStatus() {
		return status;
	}

	/**  
	 * status.  
	 * @param   status    the status to set  
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**  
	 * bank.  
	 * @return  the bank  
	 */
	public Integer getBank() {
		return bank;
	}

	/**  
	 * bank.  
	 * @param   bank    the bank to set  
	 */
	public void setBank(Integer bank) {
		this.bank = bank;
	}
	
	
	
}
  
