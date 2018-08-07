package cn.inbs.blockchain.service.company.impl;

import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.common.commonbean.RelationPage;
import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.FileUpload2AliYun;
import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.controller.company.inputparam.CertifiedCompanyInfoInput;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.company.CompanyPhotoMapper;
import cn.inbs.blockchain.dao.company.CompanyRelationMapper;
import cn.inbs.blockchain.dao.po.*;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.company.impl
 * @ClassName:
 * @Date: 2018年05月15-17:53
 * @Author: createBy:zhangmingyang
 **/
@Service("blockCompanyService")
public class BlockCompanyServiceImpl implements IBlockCompanyService {
    private static Logger logger = LoggerFactory.getLogger(BlockCompanyServiceImpl.class);

    @Resource
    private BlockCompanyMapper blockCompanyMapper;//公司

    @Resource
    private CompanyPhotoMapper companyPhotoMapper;//图片

    @Resource
    private CompanyRelationMapper companyRelationMapper;//关联

    /**
     * 查询用户公司信息
     *
     * @param blockCompany
     * @return
     */
    @Override
    public BlockCompany queryBlockCompanyByIndex(BlockCompany blockCompany) {
        if (logger.isDebugEnabled()) {
            logger.debug("根据索引查询公司信息请求参数:{}", blockCompany.toString());
        }
        return blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);
    }

    /**
     * 用户信息认证
     *
     * @param input
     * @param userId
     * @throws IOException
     */
    @Override
    public void updateCompanyInfo(CertifiedCompanyInfoInput input, Long userId) {
        //检查公司是否存在
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        if (null == blockCompany) {
            throw new BusinessException(BusinessErrorConstants.CERTIFIED_INFO_0004, String.valueOf(userId));
        } else {
            //待上传图片
            MultipartFile companyPhoto = input.getCompanyPhotoFile();//身份证图片

            if (null == companyPhoto &&
                    blockCompany.getCompanyStatus().equals(CompanyConstants.COMPANY_STATUS_0)) {
                throw new BusinessException(BusinessErrorConstants.CERTIFIED_INFO_0002);
            } else {
                if (null != companyPhoto) {
                    //上传图片
                    String companyPhotoUrl;
                    try {
                        //获取文件存储路径
                        companyPhotoUrl = FileUpload2AliYun.photoFileUpload(companyPhoto, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PHOTO_FILE_PATH_KEY, null));

                        if (logger.isDebugEnabled()) {
                            logger.debug(" 公司营业执照图片url:{}", companyPhotoUrl);
                        }
                    } catch (IOException e) {
                        if (logger.isErrorEnabled()) {
                            logger.error("认证公司信息上传图片失败", e);
                        }
                        throw new BusinessException(BusinessErrorConstants.CERTIFIED_INFO_0001);
                    }

                    //保存图片URL
                    updatePhotoUrl(companyPhotoUrl, blockCompany);
                }

                //更新公司信息
                updateCompany(input, blockCompany);
            }
        }
    }

    /**
     * 查询用户认证信息
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> queryCertifiedInfo(String userId) {
        //查询公司信息
        Map<String, String> returnMap = new HashMap<String, String>();
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(Long.valueOf(userId));

        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        if (null == blockCompany) {
            returnMap.put(CompanyConstants.COMPANY_STATUS, CompanyConstants.COMPANY_STATUS_4);//公司-认证状态
            return returnMap;
        } else {
            if (CompanyConstants.COMPANY_STATUS_0.equals(blockCompany.getCompanyStatus())) {
                returnMap.put(CompanyConstants.COMPANY_STATUS, String.valueOf(blockCompany.getCompanyStatus()));//公司-认证状态
                return returnMap;
            } else {
                //查询公司图片URl
                CompanyPhoto companyPhoto = new CompanyPhoto();
                companyPhoto.setCompanyId(blockCompany.getId());
                companyPhoto.setType(CompanyConstants.COMPANY_PHOTO_TYPE_CERTIFICATE);
                companyPhoto = companyPhotoMapper.selectCompanyPhotoByIndex(companyPhoto);

                CompanyPhoto logo = new CompanyPhoto();
                logo.setCompanyId(blockCompany.getId());
                logo.setType(CompanyConstants.COMPANY_PHOTO_TYPE_LOGO);
                logo = companyPhotoMapper.selectCompanyPhotoByIndex(logo);

                //组装返回参数
                returnMap.put(CompanyConstants.COMPANY_NAME, blockCompany.getCompanyName());//公司名称
                returnMap.put(CompanyConstants.COMPANY_PROVINCE_NAME, blockCompany.getProvinceName());//公司-省
                returnMap.put(CompanyConstants.COMPANY_CITY_NAME, blockCompany.getCityName());//公司-市
                returnMap.put(CompanyConstants.COMPANY_LINKMAN, blockCompany.getCompanyLinkman());//公司-联系人
                returnMap.put(CompanyConstants.COMPANY_PHONE, blockCompany.getCompanyLinkmanPhone());//公司-联系人手机号
                returnMap.put(CompanyConstants.COMPANY_STATUS, String.valueOf(blockCompany.getCompanyStatus()));//公司-认证状态
                returnMap.put(CompanyConstants.COMPANY_FILE_PHOTO, companyPhoto.getUrl());//公司证书图片
                if (null != logo) {
                    returnMap.put(CompanyConstants.COMPANY_LOGO_PHOTO, logo.getUrl());//公司证书图片
                }

                return returnMap;
            }
        }
    }

    @Override
    public List<BlockCompany> queryUnRelation(BlockCompany blockCompany, Long userId) {
        //获取当前机构信息
        BlockCompany query = new BlockCompany();
        query.setEmployeeId(userId);
        query = blockCompanyMapper.selectBlockCompanyByIndex(query);

        //查询当前机构已经关联的机构
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setZcCompanyBlockId(query.getCompanyBlockId());
        List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationByIndex(companyRelation);

        List<BlockCompany> blockCompanies = blockCompanyMapper.selectBlockCompanysByStatusAndType(blockCompany);
        //数据过滤
        if (CollectionUtils.isNotEmpty(companyRelations)) {
            for (CompanyRelation relation : companyRelations) {
                for (int i = 0; i < blockCompanies.size(); i++) {
                    if (relation.getZjCompanyBlockId().equals(blockCompanies.get(i).getCompanyBlockId())) {
                        blockCompanies.remove(i);
                    }
                }
            }
        }
        return blockCompanies;
    }

    @Override
    public void updateRelatedCompanys(List<String> relations, Long userId) {
        //获取当前机构信息
        BlockCompany query = new BlockCompany();
        query.setEmployeeId(userId);
        query = blockCompanyMapper.selectBlockCompanyByIndex(query);

        //执行状态关联
        if (!CompanyConstants.COMPANY_STATUS_2.equals(query.getCompanyStatus())) {
            throw new BusinessException(BusinessErrorConstants.COMPANY_RELATION_0002);

        } else {
            for (String relation : relations) {
                CompanyRelation companyRelation = new CompanyRelation();
                companyRelation.setZcCompanyBlockId(query.getCompanyBlockId());
                companyRelation.setZjCompanyBlockId(relation);
                companyRelation.setRelationStatus(CompanyConstants.COMPANY_RELATION_STATUS_0);
                Date date = new Date();
                companyRelation.setCreateTime(date);
                companyRelation.setUpdateTime(date);
                int count = companyRelationMapper.insertCompanyRelation(companyRelation);
                if (1 != count) {
                    throw new BusinessException(BusinessErrorConstants.COMPANY_RELATION_0001);
                }
            }
        }
    }

    @Override
    public PagePo queryRelationPage(PagePo pagePo, Long userID) {
        //查询公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userID);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //组装分页查询条件
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        conditionParamMap.put("zcCompanyBlockId", blockCompany.getCompanyBlockId());

        //查询分页信息
        List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRelationPage(conditionParamMap);
        List<RelationPage> relationPageList = new ArrayList<RelationPage>();
        queryCompanyBasicInfo(companyRelations, relationPageList, CompanyConstants.COMPANY_TYPE_ZC);

        //查询总行数
        int totalCount = companyRelationMapper.selectCompanyRelationPageCount(conditionParamMap);
        pagePo.setTotalCount(totalCount);
        pagePo.setPageInfoList(relationPageList);
        return pagePo;
    }

    @Override
    public PagePo queryRequisitionRelated(PagePo pagePo, Long userID) {
        //查询公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userID);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //组装分页查询条件
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        conditionParamMap.put("zjCompanyBlockId", blockCompany.getCompanyBlockId());

        //查询分页信息
        List<CompanyRelation> companyRelations = companyRelationMapper.selectCompanyRequisitionRelationPage(conditionParamMap);
        List<RelationPage> relationPageList = new ArrayList<RelationPage>();
        queryCompanyBasicInfo(companyRelations, relationPageList, CompanyConstants.COMPANY_TYPE_ZJ);

        //查询总行数
        int totalCount = companyRelationMapper.selectCompanyRequisitionRelationPageCount(conditionParamMap);
        pagePo.setTotalCount(totalCount);
        pagePo.setPageInfoList(relationPageList);
        return pagePo;
    }

    @Override
    public void updateRelatedStatus(CompanyRelation companyRelation, Long userId) {
        //查询公司信息
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(userId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);
        companyRelation.setZjCompanyBlockId(blockCompany.getCompanyBlockId());

        int conut = companyRelationMapper.updateRelatedStatus(companyRelation);
        if (1 != conut) {
            throw new BusinessException(BusinessErrorConstants.COMPANY_RELATION_0003);
        }
    }

    /**
     * 查询公司基本信息
     *
     * @param companyRelations
     * @param relationPageList
     */
    private void queryCompanyBasicInfo(List<CompanyRelation> companyRelations,
                                       List<RelationPage> relationPageList,
                                       String companyType) {
        for (CompanyRelation companyRelation : companyRelations) {
            RelationPage relationPage = new RelationPage();

            BlockCompany query = new BlockCompany();
            if (CompanyConstants.COMPANY_TYPE_ZC.equals(companyType)) {
                query.setCompanyBlockId(companyRelation.getZjCompanyBlockId());
            } else {
                query.setCompanyBlockId(companyRelation.getZcCompanyBlockId());
            }
            query = blockCompanyMapper.selectBlockCompanyByIndex(query);
            relationPage.setCompanyRelation(companyRelation);
            relationPage.setBlockCompany(query);
            relationPageList.add(relationPage);
        }
    }


    /**
     * 修改公司信息
     *
     * @param input
     */
    private void updateCompany(CertifiedCompanyInfoInput input, BlockCompany blockCompany) {
        //修改用户对应公司信息
        blockCompany.setCompanyName(input.getName());//公司名称
        blockCompany.setProvinceName(input.getProvinceName());//省
        blockCompany.setCityName(input.getCityName());//市
        blockCompany.setCompanyLinkman(input.getLinkman());//联系人
        blockCompany.setCompanyLinkmanPhone(input.getPhone());//联系人电话
        blockCompany.setCompanyStatus(CompanyConstants.COMPANY_STATUS_1);//设置状态为待审核状态
        blockCompany.setUpdateTime(new Date());//设置修改时间
        int count = blockCompanyMapper.updateBlockCompanyByIndex(blockCompany);

        //结果集处理
        if (1 != count) {
            if (logger.isErrorEnabled()) {
                logger.error("认证公司信息时修改公司相关信息失败");
            }
            throw new BusinessException(BusinessErrorConstants.CERTIFIED_INFO_0001);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("认证公司信息修改公司相关信息成功");
            }
        }
    }

    /**
     * 更新URl
     *
     * @param companyPhotoUrl
     * @param blockCompany
     */
    private void updatePhotoUrl(String companyPhotoUrl,
                                BlockCompany blockCompany) {
        //保存身份证图片URL


        //保存公司图片URL
        updatePhoto(companyPhotoUrl, blockCompany, CompanyConstants.COMPANY_PHOTO_TYPE_CERTIFICATE);
        if (logger.isDebugEnabled()) {
            logger.debug("认证公司信息保存公司图片URL成功");
        }
    }

    /**
     * 保存单张图片
     *
     * @param photoUrl
     * @param blockCompany
     * @param photoType
     */
    private void updatePhoto(String photoUrl, BlockCompany blockCompany, String photoType) {
        //判断是否已经存在图片URL
        CompanyPhoto companyPhoto = new CompanyPhoto();
        companyPhoto.setCompanyId(blockCompany.getId());
        companyPhoto.setType(photoType);
        companyPhoto = companyPhotoMapper.selectCompanyPhotoByIndex(companyPhoto);

        int count;
        if (null == companyPhoto) {
            //不存在插入
            CompanyPhoto insert = new CompanyPhoto();
            insert.setCompanyId(blockCompany.getId());
            insert.setType(photoType);
            insert.setCreateTime(new Date());
            insert.setUrl(photoUrl);
            count = companyPhotoMapper.insertCompanyPhoto(insert);
        } else {
            //存在执行修改
            Date date = new Date();
            companyPhoto.setCreateTime(date);
            companyPhoto.setUpdateTime(date);
            companyPhoto.setUrl(photoUrl);
            count = companyPhotoMapper.updateCompanyPhotoByIndex(companyPhoto);
        }

        if (1 != count) {
            if (logger.isErrorEnabled()) {
                logger.error("认证公司信息时保存图像URL失败");
            }
            throw new BusinessException(BusinessErrorConstants.CERTIFIED_INFO_0001);
        }
    }


    @Override
    public void updateCompanyByUploadCompanyLogo(MultipartFile companyLogo, String companyBlockId) throws IOException {
        Date date = new Date();
        //查询当前公司ID
        BlockCompany blockCompany = new BlockCompany();
        blockCompany.setCompanyBlockId(companyBlockId);
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);

        //查询当前公司是否已经上传logo
        CompanyPhoto companyPhoto = new CompanyPhoto();
        companyPhoto.setCompanyId(blockCompany.getId());
        companyPhoto.setType("2");
        companyPhoto = companyPhotoMapper.selectCompanyPhotoByIndex(companyPhoto);

        //获取文件存储路径
        String filePath = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PHOTO_FILE_PATH_KEY, null);
        String logoURL = FileUpload2AliYun.photoFileUpload(companyLogo, filePath);

        int count;
        if (null == companyPhoto) {
            CompanyPhoto insert = new CompanyPhoto();
            insert.setUrl(logoURL);
            insert.setType("2");
            insert.setCompanyId(blockCompany.getId());
            insert.setCreateTime(date);
            insert.setUpdateTime(date);
            count = companyPhotoMapper.insertCompanyPhoto(insert);
        } else {
            CompanyPhoto update = new CompanyPhoto();
            update.setCompanyId(blockCompany.getId());
            update.setUrl(logoURL);
            update.setType("2");
            update.setUpdateTime(date);
            count = companyPhotoMapper.updateCompanyPhotoByIndex(update);
        }

        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.COMPANY_RELATION_0004);
        }
    }
}
