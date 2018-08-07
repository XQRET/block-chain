package cn.inbs.blockchain.dao.cockpit;

import cn.inbs.blockchain.dao.vo.cockpit.BarDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:驾驶舱数据接口
 * @Package: cn.inbs.blockchain.dao.cockpit
 * @ClassName:
 * @Date: 2018年07月04-15:49
 * @Author: createBy:chenhao
 **/
@Repository("cockpitMapper")
public interface CockpitMapper {
    /**
     * 当月新增合约数（个）
     *
     * @return
     */
    int getCurrentNewAdd();

    /**
     * 当月新增合约金额（万元）
     *
     * @return
     */
    float getCurrentNewMoney();

    /**
     * 资产端用户数（个）
     *
     * @return
     */
    int getAsserNumber();

    /**
     * 资金端用户数（个）
     *
     * @return
     */
    int getCapitalNumber();

    /**
     * 平台生效合约数（个）
     *
     * @return
     */
    int getPropertContractNumber();

    /**
     * 平台生效合约金额（万元）
     *
     * @return
     */
    float getPropertContractMoney();

    /**
     * 平台合约总数（个）
     *
     * @return
     */
    int getContractNumber();

    /**
     * 平台合约总金额（万元）
     *
     * @return
     */
    float getContractMoney();

    /**
     * 获取当月新增合约数量及金额
     *
     * @param month
     * @return
     */
    BarDO getNew(@Param("month") int month);

    /**
     * 获取当月总量合约数量及金额
     *
     * @param month
     * @return
     */
    BarDO getSum(@Param("month") int month);

    /**
     * 返回资金端TOP5
     *
     * @return
     */
    List<BarDO> listCapitalTop5();

    /**
     * 返回资产端TOP5
     *
     * @return
     */
    List<BarDO> listAssetTop5();

    /**
     * 返回资金端全部
     *
     * @return
     */
    List<BarDO> listCapital();

    /**
     * 返回资产端全部
     *
     * @return
     */
    List<BarDO> listAsset();

    /**
     * 获得当前月的预测收入
     * 最近6个月，平台每个月合约预计的收益情况，收益为资金方触发的合约金额*平台手续费利率（1.5%），目前平台手续费为0
     *
     * @param month
     * @return
     */
    BarDO getForecast(@Param("month") int month, @Param("rate") double rate);

    /**
     * 获得合约分布地区及数量
     *
     * @return
     */
    List<BarDO> listDistrict();
}
