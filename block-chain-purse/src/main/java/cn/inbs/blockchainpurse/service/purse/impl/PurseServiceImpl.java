package cn.inbs.blockchainpurse.service.purse.impl;

import cn.inbs.blockchain.dao.purse.po.PurseAmount;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchain.dao.purse.user.PurseAmountMapper;
import cn.inbs.blockchain.dao.purse.user.PurseUserMapper;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.common.utils.PurseUtils;
import cn.inbs.blockchainpurse.controller.purse.inputparam.QueryPurseAmountInput;
import cn.inbs.blockchainpurse.controller.purse.outputparam.QueryPurseAmountOutput;
import cn.inbs.blockchainpurse.service.purse.IPurseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: //TODO
 * @Package: cn.inbs.blockchainpurse.service.purse.impl
 * @ClassName: PurseServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 18:19
 * @Version: 1.0
 */
@Service("purseService")
public class PurseServiceImpl implements IPurseService {
    private static Logger logger = LoggerFactory.getLogger(PurseServiceImpl.class);
    @Resource
    private PurseUserMapper purseUserMapper;

    @Resource
    private PurseAmountMapper purseAmountMapper;

    @Override
    public List<QueryPurseAmountOutput> queryPurseAmount(QueryPurseAmountInput input) {
        List<QueryPurseAmountOutput> outputs = new ArrayList<>();
        //从token中获取钱包名称
        String purseNameInToken = PurseUtils.getPurseNameInToken(input.getPurseToken());

        //用户存在性检查
        PurseUser userQuery = new PurseUser();
        userQuery.setPurseUserName(purseNameInToken);
        userQuery = purseUserMapper.selectPurseUser(userQuery);
        if (null == userQuery) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0004, purseNameInToken);
        } else {
            //查询用户钱包余额
            PurseAmount amountQuery = new PurseAmount();
            amountQuery.setPurseUserId(userQuery.getId());
            amountQuery = purseAmountMapper.queryPurseAmountByUserId(amountQuery);

            //金额转换
            for (String currency : input.getCurrencies()) {
                QueryPurseAmountOutput output = new QueryPurseAmountOutput();
                output.setCurrency(currency);
                output.setPurseAmount(getPurseAmountByCurrency(amountQuery, currency));
                outputs.add(output);
            }

            if (logger.isInfoEnabled()) {
                logger.info("[{}]钱包余额:[{}]", purseNameInToken, outputs.toString());
            }
            return outputs;
        }
    }

    /**
     * 根据币种转换金额
     *
     * @param purseAmount
     * @param currency
     * @return
     */
    private PurseAmount getPurseAmountByCurrency(PurseAmount purseAmount, String currency) {
        PurseAmount newPurseAmount = new PurseAmount();
        newPurseAmount.setTotalAmount(PurseUtils.getAmonutByCurrency(purseAmount.getTotalAmount(), purseAmount.getCurrency(), currency));
        newPurseAmount.setFixedAssetsAmount(PurseUtils.getAmonutByCurrency(purseAmount.getFixedAssetsAmount(), purseAmount.getCurrency(), currency));
        newPurseAmount.setUnFixedAssetsAmount(PurseUtils.getAmonutByCurrency(purseAmount.getUnFixedAssetsAmount(), purseAmount.getCurrency(), currency));
        newPurseAmount.setTransferAmount(PurseUtils.getAmonutByCurrency(purseAmount.getTransferAmount(), purseAmount.getCurrency(), currency));
        newPurseAmount.setAvailableAmount(PurseUtils.getAmonutByCurrency(purseAmount.getAvailableAmount(), purseAmount.getCurrency(), currency));
        return newPurseAmount;
    }
}
