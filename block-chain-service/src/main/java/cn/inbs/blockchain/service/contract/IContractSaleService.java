package cn.inbs.blockchain.service.contract;

import cn.inbs.blockchain.controller.contract.inputparam.AddContract2PresaleListInput;
import cn.inbs.blockchain.controller.contract.inputparam.QueryContractPresaleBasicInfoInput;
import cn.inbs.blockchain.controller.contract.inputparam.QueryPresalePageListInput;
import cn.inbs.blockchain.controller.contract.inputparam.UpdateContractRaiseInput;
import cn.inbs.blockchain.dao.po.BlockContract;

/**
 * @Description: 合约预售服务
 * @Package: cn.inbs.blockchain.service.contract
 * @ClassName: IContractSaleService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/30 14:15
 * @Version: 1.0
 */
public interface IContractSaleService {
    /**
     * 添加合约到预售列表
     *
     * @param input
     */
    void insertByAddContractToPresaleList(AddContract2PresaleListInput input);

    /**
     * 查询预售合约基本信息
     *
     * @param input
     * @return
     */
    BlockContract queryContractPresaleBasicInfo(QueryContractPresaleBasicInfoInput input);

    /**
     * 预售产品分页查询
     *
     * @param input
     * @return
     */
    QueryPresalePageListInput queryPresalePageList(QueryPresalePageListInput input);

    /**
     * 修改合约募集状态
     *
     * @param input
     */
    void updateContractRaiseStatus(UpdateContractRaiseInput input);
}
