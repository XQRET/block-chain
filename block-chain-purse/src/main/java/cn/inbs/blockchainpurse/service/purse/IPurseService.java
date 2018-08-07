package cn.inbs.blockchainpurse.service.purse;

import cn.inbs.blockchain.dao.purse.po.PurseAmount;
import cn.inbs.blockchainpurse.controller.purse.inputparam.QueryPurseAmountInput;
import cn.inbs.blockchainpurse.controller.purse.outputparam.QueryPurseAmountOutput;

import java.util.List;

/**
 * @Description: 钱包管理接口
 * @Package: cn.inbs.blockchainpurse.service.purse
 * @ClassName: IPurseService
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 17:47
 * @Version: 1.0
 */
public interface IPurseService {
    /**
     * 查询钱包余额
     *
     * @param input
     * @return
     */
    List<QueryPurseAmountOutput> queryPurseAmount(QueryPurseAmountInput input);
}
