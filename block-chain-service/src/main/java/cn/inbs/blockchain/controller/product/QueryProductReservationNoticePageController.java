package cn.inbs.blockchain.controller.product;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.controller.product.inputparam.QueryProductReservationNoticePageInput;
import cn.inbs.blockchain.service.product.IProductSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 查询预售通知列表
 * @Package: cn.inbs.blockchain.controller.product
 * @ClassName: QueryProductReservationNoticePageController
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 14:50
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class QueryProductReservationNoticePageController extends BaseController {
    @Resource
    private IProductSaleService productSaleService;

    @RequestMapping(value = "/queryProductReservationNoticePage.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryProductReservationNoticePage(QueryProductReservationNoticePageInput input) {
        return retContent(productSaleService.queryProductReservationNoticePage(input));
    }
}
