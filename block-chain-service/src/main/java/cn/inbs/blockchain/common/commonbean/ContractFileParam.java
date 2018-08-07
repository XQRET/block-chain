/**  
 * Project Name:trunk  
 * File Name:ContractFileParam.java  
 * Package Name:cn.inbs.blockchain.dao.po  
 * Date:2018年6月4日下午5:13:56  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.common.commonbean;
// TODO: Auto-generated Javadoc
/**  
 * ClassName: ContractFileParam <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月4日下午5:13:56  
 */
public class ContractFileParam implements Comparable<ContractFileParam>{
	//{"title":"姓名","type":"text","format":"text","multiple":"false","model":"person","sort":"1"}
	
	/** 显示title. */
	private String title;
	
	/** 上传格式. */
	private String type;
	
	/** 匹配类型. */
	private String match;
	
	/** 是否可上传多张. */
	private Boolean multiple;
	
	/** 所属模块. */
	private String model;
	
	/** 排序. */
	private Integer sort;
	
    /** 参数Key. */
    private String paramKey;
	
    /** 返回url */
    private String url;
    
    
    public ContractFileParam() {
    	super();
    }
    
    
	/**  
	 * TODO.  
	 * @param title
	 * @param type
	 * @param match
	 * @param multiple
	 * @param model
	 * @param sort
	 * @param paramKey
	 * @param url  
	 */  
	
	public ContractFileParam(ContractFileParam param, String url) {
		super();
		this.title = param.title;
		this.type = param.type;
		this.match = param.match;
		this.multiple = param.multiple;
		this.model = param.model;
		this.sort = param.sort;
		this.paramKey = param.paramKey;
		this.url = url;
	}

	/**
	 * Gets the 显示title.
	 *
	 * @return the 显示title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the 显示title.
	 *
	 * @param title
	 *            the new 显示title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the 上传格式.
	 *
	 * @return the 上传格式
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the 上传格式.
	 *
	 * @param type
	 *            the new 上传格式
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**  
	 * match.  
	 * @return  the match  
	 */
	public String getMatch() {
		return match;
	}

	/**
	 * 
	 * match.
	 *
	 * @param match
	 *            the new 匹配类型
	 */
	public void setMatch(String match) {
		this.match = match;
	}

	/**
	 * Gets the 是否可上传多张.
	 *
	 * @return the 是否可上传多张
	 */
	public Boolean getMultiple() {
		return multiple;
	}

	/**
	 * Sets the 是否可上传多张.
	 *
	 * @param multiple
	 *            the new 是否可上传多张
	 */
	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * Gets the 所属模块.
	 *
	 * @return the 所属模块
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the 所属模块.
	 *
	 * @param model
	 *            the new 所属模块
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the 排序.
	 *
	 * @return the 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * Sets the 排序.
	 *
	 * @param sort
	 *            the new 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
	
	/**  
	 * paramKey.  
	 * @return  the paramKey  
	 */
	public String getParamKey() {
		return paramKey;
	}

	/**
	 * 
	 * paramKey.
	 *
	 * @param paramKey
	 *            the new 参数Key
	 */
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	
	/**  
	 * url.  
	 * @return  the url  
	 */
	public String getUrl() {
		return url;
	}

	/**  
	 * url.  
	 * @param   url    the url to set  
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）.
	 *
	 * @param obj
	 *            the obj
	 * @return the int
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ContractFileParam obj) {
		 if(obj instanceof ContractFileParam){
			 ContractFileParam fileParam=(ContractFileParam)obj;
	          return sort-fileParam.getSort();
	        }else{
	            return -1;
	        }
	}
	
}
  
