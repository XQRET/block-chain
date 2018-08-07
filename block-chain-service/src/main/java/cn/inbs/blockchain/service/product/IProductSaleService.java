package cn.inbs.blockchain.service.product;

import cn.inbs.blockchain.controller.product.inputparam.*;
import cn.inbs.blockchain.dao.po.ProductSale;

/**
 * @Description: 产品销售
 * @Package: cn.inbs.blockchain.service.product
 * @ClassName: IProductSaleService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:54
 * @Version: 1.0
 */
public interface IProductSaleService {
    /**
     * 添加预售产品
     *
     * @param input
     */
    void insertProductToSaleList(AddProduct2SaleListInput input);

    /**
     * 产品列表分页查询
     *
     * @param input
     * @return
     */
    QueryProductSalePageListInput queryProductSalePageList(QueryProductSalePageListInput input);

    /**
     * 查询产品详细信息
     *
     * @param input
     * @return
     */
    ProductSale queryProductSaleDetail(QueryProductSaleDetailInput input);

    /**
     * 预售通知列表
     *
     * @param input
     * @return
     */
    QueryProductReservationNoticePageInput queryProductReservationNoticePage(QueryProductReservationNoticePageInput input);

    /**
     * 修改产品信息
     *
     * @param input
     */
    void updateProductInfoById(UpdateProductInfoInput input);
}
