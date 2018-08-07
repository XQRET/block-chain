package cn.inbs.blockchain.service.cockpit;

import cn.inbs.blockchain.common.commonbean.CompanyDetail;
import cn.inbs.blockchain.dao.vo.cockpit.AssertVO;
import cn.inbs.blockchain.dao.vo.cockpit.IncomeVO;
import cn.inbs.blockchain.dao.vo.cockpit.NumVO;
import cn.inbs.blockchain.dao.vo.cockpit.RegionVO;
import cn.inbs.blockchain.dao.vo.cockpit.SummaryDataVO;

import java.util.List;


/**
 * @Description:返回驾驶舱数据业务接口
 * @Package: cn.inbs.blockchain.service.cockpit.ICockpitService
 * @ClassName:
 * @Date: 2018年07月03-11:29
 * @Author: createBy:chenhao
 **/
public interface ICockpitService {
    /**
     * 返回资金端TOP5
     *
     * @return
     */
    AssertVO getCapital();

    /**
     * 返回资产端TOP5
     *
     * @return
     */
    AssertVO getAssets();

    /**
     * 返回合约分布地区
     *
     * @return
     */
    List<RegionVO> getDistrict();

    /**
     * 近6个月预期
     *
     * @return
     * @param month
     * @param rate
     */
    IncomeVO getForecast(int month, double rate);

    /**
     * 返回新增
     *
     * @return
     * @param monthCount
     */
    NumVO getNew(int monthCount);

    /**
     * 合约数量及金额 总量
     *
     * @return
     * @param monthCount
     */
    NumVO getSum(int monthCount);

    /**
     * 返回驾驶舱头部八个统计数据
     *
     * @return
     */
    SummaryDataVO getTotalItem();

    /**
     * 资金端全部
     *
     * @return
     */
    AssertVO getCapitalAll();

    /**
     * 资产端全部
     *
     * @return
     */
    AssertVO getAssetsAll();

    /**
     * 获取所有节点的公司信息
     *
     * @return
     */
    List<CompanyDetail> getNodeList();
}
