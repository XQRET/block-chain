package cn.inbs.blockchain.controller.cockpit;

import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.service.cockpit.ICockpitService;
import cn.inbs.blockchain.service.contract.IBlockContracService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:返回驾驶舱数据
 * @Package: cn.inbs.blockchain.controller.CockpitController
 * @ClassName:
 * @Date: 2018年07月03-11:29
 * @Author: createBy:chenhao
 **/
@Controller
@RequestMapping(value = "/cockpit")
public class CockpitController extends BaseController {

    @Resource(name = "cockpitServiceImpl")
    private ICockpitService cockpitService;

    @Resource
    private IBlockContracService blockContracService;

    /**
     * 返回驾驶舱头部八个统计数据
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showTotalItem.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getTotalItem(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getTotalItem());
    }

    /**
     * 合约数量及金额 新增(从当前月开始 过去12个月)
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/showAdd.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getNew() {
        int monthCount = 12;
        return retContent(cockpitService.getNew(monthCount));
    }

    /**
     * 合约数量及金额 总量(从当前月开始 过去12个月)
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showSum.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getSum(HttpServletRequest httpServletRequest) {
        int monthCount = 12;
        return retContent(cockpitService.getSum(monthCount));
    }

    /**
     * 资金端TOP5
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showCapital.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAffiliate(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getCapital());
    }

    /**
     * 资金端全部
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showCapitalAll.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAffiliateAll(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getCapitalAll());
    }

    /**
     * 资产端TOP5
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showAssets.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAssets(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getAssets());
    }

    /**
     * 资产端全部
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showAssetsAll.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAssetsAll(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getAssetsAll());
    }

    /**
     * 合约地区分布
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showRegion.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getDistrict(HttpServletRequest httpServletRequest) {
        return retContent(cockpitService.getDistrict());
    }

    /**
     * 近6月预测收入
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/showIncome.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getForecast(HttpServletRequest httpServletRequest) {
        int month = 6;
        double rate = 0.015;
        return retContent(cockpitService.getForecast(month, rate));
    }

    /**
     * 首页
     * 查询 当前进件数 用户总数 交易笔数
     *
     * @return
     */
    @RequestMapping(value = "/showIndexInfo.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showIndexInfo() {
        return retContent(blockContracService.showIndexInfo());
    }

    /**
     * 首页
     * 查询 当前进件数 用户总数 交易笔数
     *
     * @return
     */
    @RequestMapping(value = "/showNodeInfo.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showNodeInfo() {
        return retContent(cockpitService.getNodeList());
    }
}
