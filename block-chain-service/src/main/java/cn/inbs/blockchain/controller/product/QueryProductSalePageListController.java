package cn.inbs.blockchain.controller.product;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.product.inputparam.QueryProductSalePageListInput;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询产品预售列表
 * @Package: cn.inbs.blockchain.controller.product
 * @ClassName: QueryProductSalePageListController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 16:44
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class QueryProductSalePageListController extends BaseController {
    @Resource
    private IProductSaleService productSaleService;

    @RequestMapping(value = "/queryProductSalePageList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryProductSalePageList(QueryProductSalePageListInput input) {
        return retContent(productSaleService.queryProductSalePageList(input));
    }
}
