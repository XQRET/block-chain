package cn.inbs.blockchain.controller.product;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.product.inputparam.QueryProductSaleDetailInput;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询预售产品详细信息
 * @Package: cn.inbs.blockchain.controller.product
 * @ClassName: QueryProductSaleDetailController
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 18:26
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class QueryProductSaleDetailController extends BaseController {
    @Resource
    private IProductSaleService productSaleService;

    @RequestMapping(value = "/queryProductSaleDetail.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryProductSaleDetail(QueryProductSaleDetailInput input) {
        return retContent(productSaleService.queryProductSaleDetail(input));
    }
}
