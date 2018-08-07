package cn.inbs.blockchain.dao.product;

import cn.inbs.blockchain.dao.po.ProductSale;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 产品预售
 * @Package: cn.inbs.blockchain.dao.product
 * @ClassName: ProductSaleMapper
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:15
 * @Version: 1.0
 */
@Repository("productSaleMapper")
public interface ProductSaleMapper {
    /**
     * 添加预售产品
     *
     * @param insert
     * @return
     */
    int insertProductSale(ProductSale insert);

    /**
     * 索引查询
     *
     * @param query
     * @return
     */
    ProductSale selectProductSaleByIndx(ProductSale query);

    /**
     * 分页查询
     *
     * @param paramMap
     * @return
     */
    List<ProductSale> selectProductSalePageList(Map<String, Object> paramMap);

    /**
     * 查询总行数
     *
     * @param paramMap
     * @return
     */
    int selectProductSaleTotalCount(Map<String, Object> paramMap);

    /**
     * 修改产品信息
     *
     * @param update
     * @return
     */
    int updateProductInfoById(ProductSale update);
}
