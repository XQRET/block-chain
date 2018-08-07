package cn.inbs.blockchain.service.contract;

import cn.inbs.blockchain.common.commonbean.PagePo;

/**
 * @Description: 合约分页查询服务
 * @Package: cn.inbs.blockchain.service.contract
 * @ClassName:
 * @Date: 2018年05月25-14:16
 * @Author: createBy:zhangmingyang
 **/
public interface IContractPageService {

    /**
     * 合约分页查询
     *
     * @param pagePo
     * @return
     */
    PagePo queryContractPage(PagePo pagePo);

    /**
     * 资金端查询触发合约列表(* * * 特殊 * * *)
     *
     * @param pagePo
     * @return
     */
    PagePo queryContractPageByFundsTrigger(PagePo pagePo);
}
