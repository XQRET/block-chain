package cn.inbs.blockchain.dao.company;


import cn.inbs.blockchain.dao.po.CompanyPhoto;
import org.springframework.stereotype.Repository;

@Repository("companyPhotoMapper")
public interface CompanyPhotoMapper {
    /**
     * 查询
     *
     * @param companyPhoto
     * @return
     */
    CompanyPhoto selectCompanyPhotoByIndex(CompanyPhoto companyPhoto);

    /**
     * 插入
     *
     * @param companyPhoto
     * @return
     */
    int insertCompanyPhoto(CompanyPhoto companyPhoto);


    /**
     * 修改
     *
     * @param companyPhoto
     * @return
     */
    int updateCompanyPhotoByIndex(CompanyPhoto companyPhoto);



}