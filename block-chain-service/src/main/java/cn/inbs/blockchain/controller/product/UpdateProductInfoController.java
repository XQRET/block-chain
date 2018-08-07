package cn.inbs.blockchain.controller.product;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.product.inputparam.UpdateProductInfoInput;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 修改预售产品信息
 * @Package: cn.inbs.blockchain.controller.product
 * @ClassName: UpdateProductInfoController
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 15:39
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class UpdateProductInfoController extends BaseController {
    @Resource
    private IProductSaleService productSaleService;

    @RequestMapping(value = "/updateProductInfo.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateProductInfo(UpdateProductInfoInput input) {
        productSaleService.updateProductInfoById(input);
        return retContent(null);
    }
}
