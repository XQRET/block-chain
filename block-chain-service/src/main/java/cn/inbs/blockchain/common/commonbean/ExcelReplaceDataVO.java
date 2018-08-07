/**  
 * Project Name:trunk  
 * File Name:ExcelReplaceDataVO.java  
 * Package Name:cn.inbs.blockchain.dao.po  
 * Date:2018年6月11日下午3:36:28  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.common.commonbean;
// TODO: Auto-generated Javadoc
/**  
 * ClassName: ExcelReplaceDataVO <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月11日下午3:36:28  
 */
public class ExcelReplaceDataVO {
	
	public static final String REPLACE_TYPE_STRING = "string";
	
	public static final String REPLACE_TYPE_URL = "url";
		
	/** Excel单元格行. */
	private int row;
	
	/** Excel单元格列. */
    private int column;
    
    private String key;
    
    /** 替换的文本. */
    private String value;
    
    /** 类型 string 文字 url 超链接. */
    private String type;

	/**  
	 * TODO.  
	 * @param row
	 * @param column
	 * @param value
	 * @param type  
	 */  
	
	public ExcelReplaceDataVO(int row, int column, String value, String type) {
		super();
		this.row = row;
		this.column = column;
		this.value = value;
		this.type = type;
	}

	/**
	 * Gets the excel单元格行.
	 *
	 * @return the excel单元格行
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the excel单元格行.
	 *
	 * @param row
	 *            the new excel单元格行
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the excel单元格列.
	 *
	 * @return the excel单元格列
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets the excel单元格列.
	 *
	 * @param column
	 *            the new excel单元格列
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the 替换的文本.
	 *
	 * @return the 替换的文本
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the 替换的文本.
	 *
	 * @param value
	 *            the new 替换的文本
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the 类型 string 文字 url 超链接.
	 *
	 * @return the 类型 string 文字 url 超链接
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the 类型 string 文字 url 超链接.
	 *
	 * @param type
	 *            the new 类型 string 文字 url 超链接
	 */
	public void setType(String type) {
		this.type = type;
	}
    
    
    
	
}
  
