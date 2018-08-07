/**  
 * Project Name:trunk  
 * File Name:ISystemParamService.java  
 * Package Name:cn.inbs.blockchain.service.system  
 * Date:2018年5月28日下午3:31:57  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.service.system;

import java.util.Map;

/**  
 * ClassName: ISystemParamService <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日下午3:31:57  
 */
public interface ISystemParamService {
	
	/**
	 * 
	 * insertSystemParam:新增系统参数. <br/>  
	 * @author anxiaoyu
	 * @param employeeId 用户ID
	 * @param parameterMap 新增参数map
	 * @param type 新增类型
	 * @return
	 */
	Map<String,String> insertSystemParam(Long employeeId,Map<String,String[]> parameterMap,String type);
	
	/**
	 * 
	 * getEmployeeParamValue:根据employeeId，type,paramKey获取对应参数. <br/>  
	 * @author anxiaoyu
	 * @param employeeId 用户ID
	 * @param type 类型
	 * @param paramKey 参数Key
	 * @return
	 */
	String getEmployeeParamValue(Long employeeId,String type,String paramKey);
	
	/**
	 * 
	 * getEmployeeParamValues:根据employeeId获取所有参数. <br/>  
	 * @author anxiaoyu 
	 * @param employeeId 用户ID
	 * @param type 类型
	 * @return
	 */
	Map<String,String> getEmployeeParamValues(Long employeeId,String type);
	
}
  
