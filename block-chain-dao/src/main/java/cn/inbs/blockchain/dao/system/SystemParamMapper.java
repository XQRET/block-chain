package cn.inbs.blockchain.dao.system;

import cn.inbs.blockchain.dao.po.SystemParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("systemParamMapper")
public interface SystemParamMapper{
	
	String selectParamValue(@Param("employeeId") Long employeeId,
                            @Param("paramKey") String paramKey);

	List<SystemParam> selectListByEmployeeIdAndType(@Param("employeeId") Long employeeId, @Param("type") String type);

    int deleteByPrimaryKey(Long id);

    int deleteByType(@Param("type") String type, @Param("employeeId") Long employeeId);
    
    int insertSelective(SystemParam systemParam);

    SystemParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemParam systemParam);

}