package cn.inbs.blockchain.common.constants;

/**
 * 
 * ClassName: 下载文件类型 <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月1日上午10:30:49
 */
public enum RepaymentWayEnum {
	
	DBDX("1","等本本息"),
	
	XXHB("2","先息后本");
	
	
	public static RepaymentWayEnum getEnumById(String id) {
		for (RepaymentWayEnum temp : RepaymentWayEnum.values()) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		return null;
	}
	
	public static String getRemarkById(String id) {
		for (RepaymentWayEnum temp : RepaymentWayEnum.values()) {
			if (temp.getId().equals(id)) {
				return temp.getRemark();
			}
		}
		return "";
	}
	
	private RepaymentWayEnum(String id,String remark) {
		this.id = id;
		this.remark = remark;
	}
	
	private String id;

	private String remark;

	/**  
	 * id.  
	 * @return  the id  
	 */
	public String getId() {
		return id;
	}

	/**  
	 * id.  
	 * @param   id    the id to set  
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**  
	 * remark.  
	 * @return  the remark  
	 */
	public String getRemark() {
		return remark;
	}

	/**  
	 * remark.  
	 * @param   remark    the remark to set  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
