package cn.inbs.blockchain.dao.company;


import cn.inbs.blockchain.dao.po.CompanyRelation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("companyRelationMapper")
public interface CompanyRelationMapper {

    /**
     * 查询
     *
     * @param companyRelation
     * @return
     */
    List<CompanyRelation> selectCompanyRelationByIndex(CompanyRelation companyRelation);

    /**
     * 插入
     *
     * @param companyRelation
     * @return
     */
    int insertCompanyRelation(CompanyRelation companyRelation);

    /**
     * 查询分页
     *
     * @param map
     * @return
     */
    List<CompanyRelation> selectCompanyRelationPage(Map<String, Object> map);

    /**
     * 查询总行数
     *
     * @param map
     * @return
     */
    int selectCompanyRelationPageCount(Map<String, Object> map);

    /**
     * 查询分页
     *
     * @param map
     * @return
     */
    List<CompanyRelation> selectCompanyRequisitionRelationPage(Map<String, Object> map);

    /**
     * 查询总行数
     *
     * @param map
     * @return
     */
    int selectCompanyRequisitionRelationPageCount(Map<String, Object> map);

    /**
     * 修改关联状态
     *
     * @param companyRelation
     * @return
     */
    int updateRelatedStatus(CompanyRelation companyRelation);
}