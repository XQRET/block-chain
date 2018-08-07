package cn.inbs.blockchain.controller.product;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.product.inputparam.AddProduct2SaleListInput;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 添加预售产品
 * @Package: cn.inbs.blockchain.controller.product
 * @ClassName: AddProduct2SaleListController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 15:34
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class AddProduct2SaleListController extends BaseController {
    @Resource
    private IProductSaleService productSaleService;

    @RequestMapping(value = "/addProductToSaleList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addProductToSaleList(AddProduct2SaleListInput input) {
        productSaleService.insertProductToSaleList(input);
        return retContent(null);
    }
}
