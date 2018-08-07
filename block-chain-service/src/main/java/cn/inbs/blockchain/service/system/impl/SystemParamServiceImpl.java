/**  
 * Project Name:trunk  
 * File Name:SystemParamServiceImpl.java  
 * Package Name:cn.inbs.blockchain.service.system.impl  
 * Date:2018年5月28日下午3:32:25  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.inbs.blockchain.common.cache.BaseCache;
import cn.inbs.blockchain.common.cache.CacheIndex;
import cn.inbs.blockchain.common.cache.RedisCache;
import cn.inbs.blockchain.dao.po.SystemParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.inbs.blockchain.common.constants.RedisConfigConstants;
import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.common.utils.MapUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.dao.system.SystemParamMapper;
import cn.inbs.blockchain.service.system.ISystemParamService;

/**  
 * ClassName: SystemParamServiceImpl <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日下午3:32:25  
 */
@Service("systemParamService")
public class SystemParamServiceImpl implements ISystemParamService {
	
	@Resource 
	private SystemParamMapper systemParamMapper;
	
	@Resource 
	private RedisTemplate<String,?> redisTemplate;
	
	/**
	 * 
	 * 新增系统参数.  
	 * @see cn.inbs.blockchain.service.system.ISystemParamService#insertSystemParam(java.lang.Long, java.util.Map, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,String> insertSystemParam(Long employeeId,Map<String,String[]> parameterMap,String type) {
		BaseCache systemParamCache = new RedisCache(String.class, redisTemplate, CacheIndex.SYSTEM_PARAM_INDEX.ordinal());
		//清除redis map数据
		String mapKey = RedisConfigConstants.BLOCK_SYSTEM_PARAM_MAP_KEY+employeeId+":"+type;
		systemParamCache.del(mapKey);
		//清除redis type 数据
		String typeKey = RedisConfigConstants.BLOCK_SYSTEM_PARAM_KEY+employeeId+":"+type;
		systemParamCache.del(typeKey);
		//清除数据库数据
		systemParamMapper.deleteByType(type, employeeId);
		Date date = new Date();
		SystemParam systemParam = null;
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) { 
			if(!"employeeId".equals(entry.getKey())) {
			  systemParam = new SystemParam();
	    	  systemParam.setParamKey(entry.getKey());
        	  systemParam.setParamValue(entry.getValue()[0]);
        	  systemParam.setEmployeeId(employeeId);
        	  systemParam.setType(type);
	    	  systemParam.setCreateTime(date);
	    	  systemParamMapper.insertSelective(systemParam);  				
			}
		}  
		return getEmployeeParamValues(employeeId,type);
	}
	
	/**
	 * 
	 * 根据employeeId，type,paramKey获取对应参数.  
	 * @see cn.inbs.blockchain.service.system.ISystemParamService#getEmployeeParamValue(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getEmployeeParamValue(Long employeeId,String type,String paramKey) {
		BaseCache systemParamCache = new RedisCache(String.class, redisTemplate, CacheIndex.SYSTEM_PARAM_INDEX.ordinal());
    	String key = RedisConfigConstants.BLOCK_SYSTEM_PARAM_KEY+employeeId+":"+type+":"+paramKey;
    	String paranValue = (String)systemParamCache.get(key);
    	if(StringUtils.isEmpty(paranValue)) {
    		paranValue = systemParamMapper.selectParamValue(employeeId, paramKey);
    		if(StringUtils.isNotEmpty(paranValue)) {
    			systemParamCache.put(key, paranValue);
    		}
    	}
    	if(StringUtils.isEmpty(paranValue) && employeeId != -1) {
    		paranValue = getEmployeeParamValue(-1L,type,paramKey);
    	}
    	return paranValue;
    }
    
	/**
	 * 
	 * 根据employeeId获取所有参数.  
	 * @see cn.inbs.blockchain.service.system.ISystemParamService#getEmployeeParamValues(java.lang.Long)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String,String> getEmployeeParamValues(Long employeeId,String type) {
		BaseCache systemParamCache = new RedisCache(String.class, redisTemplate, CacheIndex.SYSTEM_PARAM_INDEX.ordinal());
    	String key = RedisConfigConstants.BLOCK_SYSTEM_PARAM_MAP_KEY+employeeId+":"+type;
    	Map<String,String> paranValues = (Map<String,String>)systemParamCache.get(key);
    	if(MapUtils.isEmpty(paranValues)) {
    		List<SystemParam> list = systemParamMapper.selectListByEmployeeIdAndType(employeeId,type);
    		if(CollectionUtils.isNotEmpty(list)) {
				paranValues = new HashMap<String, String>();
    			for(SystemParam param : list) {
    				paranValues.put(param.getParamKey(), param.getParamValue());
    			}
    			systemParamCache.put(key, paranValues);
    		}
    	}
    	if(MapUtils.isEmpty(paranValues) && employeeId != -1) {
    		paranValues = getEmployeeParamValues(-1L,type);
    	}
    	return paranValues;
    }
	
}
  
