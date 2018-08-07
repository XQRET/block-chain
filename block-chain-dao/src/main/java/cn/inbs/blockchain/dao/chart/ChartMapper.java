package cn.inbs.blockchain.dao.chart;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.chart
 * @ClassName: ChartMapper.java
 * @Date: 2018/7/6 17:38
 * @author: create by zhangmingyang
 **/
@Repository("chartMapper")
public interface ChartMapper {
    /**
     * 统计合约数
     *
     * @param conditionMap 参数Map
     * @return
     */
    int selectContractCountByCondition(Map<String, Object> conditionMap);

    /**
     * 统计合约金额
     *
     * @param conditionMap 参数Map
     * @return
     */
    BigDecimal selectContractAmountByCondition(Map<String, Object> conditionMap);

    /**
     * 根据公司类型查询公司总个数
     *
     * @param companyType 公司类型
     * @return
     */
    int selectCompanyTotalCountByType(@Param("companyType") String companyType);

    /**
     * 资产top
     *
     * @param topCount
     * @return
     */
    List<Map<String, Object>> selectZcTop(@Param("topCount") Integer topCount);

    /**
     * 资金top
     *
     * @param topCount
     * @return
     */
    List<Map<String, Object>> selectZjTop(@Param("topCount") Integer topCount);

    /**
     * 根据省份统计合约
     *
     * @return
     */
    List<Map<String, Object>> selectContractNumGroupByProvince();
}
