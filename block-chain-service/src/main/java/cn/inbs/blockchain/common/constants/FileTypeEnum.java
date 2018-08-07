package cn.inbs.blockchain.common.constants;

// TODO: Auto-generated Javadoc
/**
 * 
 * ClassName: 下载文件类型 <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月1日上午10:30:49
 */
public enum FileTypeEnum {
	
	ALL("all",""),
	
	 /** The pdf. */
  	PDF("pdf","pdf"),
	  
  	/** The img. */
  	IMG("img","jpeg|jpg|png|gif"),
	  
  	/** The doc. */
  	DOC("doc","doc|docx"),
	
  	TXT("text","txt"),
  	
  	/** The execl. */
  	EXECL("execl","xls|xlsx");
	
	public static String getType(String suffix) {
		for (FileTypeEnum temp : FileTypeEnum.values()) {
			if (temp.getRxtensionName().contains(suffix)) {
				return temp.getType();
			}
		}
		return null;
	}
	
	public static String getRxtensionName(String type) {
		for (FileTypeEnum temp : FileTypeEnum.values()) {
			if (temp.getType().equals(type)) {
				return temp.getRxtensionName();
			}
		}
		return "";
	}
	
	/**
	 * Instantiates a new file type enum.
	 *
	 * @param type
	 *            the type
	 * @param rxtensionName
	 *            the rxtension name
	 */
	private FileTypeEnum(String type,String rxtensionName) {
		this.type = type;
		this.rxtensionName = rxtensionName;
	}
	
	/** 类型. */
	private String type;

	/** 拓展名. */
	private String rxtensionName;

	/**
	 * Gets the 类型.
	 *
	 * @return the 类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the 类型.
	 *
	 * @param type
	 *            the new 类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the 拓展名.
	 *
	 * @return the 拓展名
	 */
	public String getRxtensionName() {
		return rxtensionName;
	}

	/**
	 * Sets the 拓展名.
	 *
	 * @param rxtensionName
	 *            the new 拓展名
	 */
	public void setRxtensionName(String rxtensionName) {
		this.rxtensionName = rxtensionName;
	}

}
