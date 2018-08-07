package cn.inbs.blockchain.service.product.impl;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.FileUpload2AliYun;
import cn.inbs.blockchain.controller.product.inputparam.*;
import cn.inbs.blockchain.dao.po.ProductSale;
import cn.inbs.blockchain.dao.product.ProductSaleMapper;
import cn.inbs.blockchain.dao.purse.product.PurseProductReservationNoticeMapper;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.product.impl
 * @ClassName: ProductSaleServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:54
 * @Version: 1.0
 */
@Service("productSaleService")
public class ProductSaleServiceImpl implements IProductSaleService {
    private static Logger logger = LoggerFactory.getLogger(ProductSaleServiceImpl.class);

    @Resource
    private ProductSaleMapper productSaleMapper;

    @Resource
    private PurseProductReservationNoticeMapper purseProductReservationNoticeMapper;

    @Override
    public void insertProductToSaleList(AddProduct2SaleListInput input) {
        //根据项目名称检查该产品是否已经存在
        ProductSale queryProductSale = new ProductSale();
        queryProductSale.setProductName(input.getProductName());
        queryProductSale = productSaleMapper.selectProductSaleByIndx(queryProductSale);
        if (null != queryProductSale) {
            String productStatus = queryProductSale.getProductStatus();
            if (!ProductSale.PRODUCT_STATUS_3.equals(productStatus)) {
                throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0001);
            }
        }

        //上传图片
        String bannerFileUrl;
        String productDetailsFileUrl;
        try {
            bannerFileUrl = FileUpload2AliYun.photoFileUpload(input.getProductBanner(), PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PRODUCT_FILE_PATH, null));
            productDetailsFileUrl = FileUpload2AliYun.photoFileUpload(input.getProductDetails(), PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PRODUCT_FILE_PATH, null));
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("上传图片失败:", e);
            }
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0002);
        }

        //添加预售产品
        Date insertDate = new Date();
        ProductSale insertProductSale = new ProductSale();
        insertProductSale.setProductType(input.getProductType());
        insertProductSale.setProductName(input.getProductName());
        insertProductSale.setProductBanner(bannerFileUrl);
        insertProductSale.setProductDetails(productDetailsFileUrl);
        insertProductSale.setProductStatus(ProductSale.PRODUCT_STATUS_1);
        insertProductSale.setReleaseTime(insertDate);
        insertProductSale.setCreateTime(insertDate);
        insertProductSale.setUpdateTime(insertDate);
        int insertCount = productSaleMapper.insertProductSale(insertProductSale);
        if (1 != insertCount) {
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0002);
        }

        if (logger.isInfoEnabled()) {
            logger.info("添加产品:[{}]成功", input.getProductName());
        }
    }

    @Override
    public QueryProductSalePageListInput queryProductSalePageList(QueryProductSalePageListInput input) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productType", input.getProductType());
        paramMap.put("productStatus", input.getProductStatus());
        paramMap.put("startIndex", input.getPageStartCountIndex());
        paramMap.put("pageCount", input.getPageCount());
        input.setPageInfoList(productSaleMapper.selectProductSalePageList(paramMap));
        input.setTotalCount(productSaleMapper.selectProductSaleTotalCount(paramMap));

        if (logger.isInfoEnabled()) {
            logger.info("产品预售分页查询列表:[{}]", input.toString());
        }

        return input;
    }

    @Override
    public ProductSale queryProductSaleDetail(QueryProductSaleDetailInput input) {
        ProductSale query = new ProductSale();
        query.setId(input.getId());
        query = productSaleMapper.selectProductSaleByIndx(query);
        if (null == query) {
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0003);
        }
        return query;
    }

    @Override
    public QueryProductReservationNoticePageInput queryProductReservationNoticePage(QueryProductReservationNoticePageInput input) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", input.getProductId());
        paramMap.put("startIndex", input.getPageStartCountIndex());
        paramMap.put("pageCount", input.getPageCount());
        input.setPageInfoList(purseProductReservationNoticeMapper.selectPurseProductReservationNoticePageList(paramMap));//数据
        input.setPageCount(purseProductReservationNoticeMapper.selectPurseProductReservationNoticeTotalCount(paramMap));//条数

        if (logger.isInfoEnabled()) {
            logger.info("产品:[{}]预售通知列表:[{}]", input.getProductId(), input.toString());
        }

        return input;
    }

    @Override
    public void updateProductInfoById(UpdateProductInfoInput input) {
        //产品存在性检查
        ProductSale query = new ProductSale();
        query.setId(input.getId());
        query = productSaleMapper.selectProductSaleByIndx(query);
        if (null == query) {
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0003);
        }

        //修改非状态字段信息时,只有待预售产品可修改信息
        if (null == input.getProductStatus()) {
            if (ProductSale.PRODUCT_STATUS_2.equals(query.getProductStatus()) || ProductSale.PRODUCT_STATUS_3.equals(query.getProductStatus())) {
                throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0005);
            }
        }

        //修改产品信息
        ProductSale update = new ProductSale();
        update.setId(input.getId());
        update.setProductName(input.getProductName());
        update.setProductStatus(input.getProductStatus());
        update.setUpdateTime(new Date());
        try {
            if (null != input.getProductBanner()) {
                String bannerFileUrl = FileUpload2AliYun.photoFileUpload(input.getProductBanner(), PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PRODUCT_FILE_PATH, null));
                update.setProductBanner(bannerFileUrl);
            }
            if (null != input.getProductDetails()) {
                String productDetailsFileUrl = FileUpload2AliYun.photoFileUpload(input.getProductDetails(), PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_PRODUCT_FILE_PATH, null));
                update.setProductDetails(productDetailsFileUrl);
            }
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("修改产品:[{}]信息失败:", input.getId(), e);
            }
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0004);
        }
        int updateCount = productSaleMapper.updateProductInfoById(update);
        if (1 != updateCount) {
            throw new BusinessException(BusinessErrorConstants.PRODUCT_SALE_0004);
        }

        if (logger.isInfoEnabled()) {
            logger.info("修改产品:[{}]信息成功", input.getId());
        }
    }
}
