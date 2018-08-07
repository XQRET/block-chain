package cn.inbs.blockchain.service.company;

import cn.inbs.blockchain.controller.company.inputparam.CertifiedCompanyInfoInput;
import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.CompanyRelation;
import cn.inbs.blockchain.common.commonbean.PagePo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.company
 * @ClassName:
 * @Date: 2018年05月15-17:53
 * @Author: createBy:zhangmingyang
 **/
public interface IBlockCompanyService {
    /**
     * 查询公司信息
     *
     * @param blockCompany
     * @return
     */
    BlockCompany queryBlockCompanyByIndex(BlockCompany blockCompany);

    /**
     * 更新公司信息
     *
     * @param input
     * @param userId
     * @return
     */
    void updateCompanyInfo(CertifiedCompanyInfoInput input, Long userId) throws IOException;

    /**
     * 查询用户认证信息
     *
     * @param userId
     * @return
     */
    Map<String, String> queryCertifiedInfo(String userId);

    /**
     * 查询公司信息列表
     *
     * @param blockCompany
     * @return
     */
    List<BlockCompany> queryUnRelation(BlockCompany blockCompany, Long userId);

    /**
     * 关联结构
     *
     * @param relations
     * @param userId
     */
    void updateRelatedCompanys(List<String> relations, Long userId);


    /**
     * 查询公司关联列表
     *
     * @param pagePo
     * @return
     */
    PagePo queryRelationPage(PagePo pagePo, Long userID);

    /**
     * 查询请求关联列表
     *
     * @param pagePo
     * @param userID
     * @return
     */
    PagePo queryRequisitionRelated(PagePo pagePo, Long userID);


    /**
     * 资金端关联机构
     *
     * @param companyRelation
     */
    void updateRelatedStatus(CompanyRelation companyRelation, Long userId);

    /**
     * 上传公司logo
     *
     * @param companyLogo
     * @param companyBlockId
     */
    void updateCompanyByUploadCompanyLogo(MultipartFile companyLogo, String companyBlockId) throws IOException;
}
