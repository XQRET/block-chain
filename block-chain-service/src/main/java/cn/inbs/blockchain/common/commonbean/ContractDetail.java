package cn.inbs.blockchain.common.commonbean;

import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.dao.po.ContractFileUrl;

import java.util.Date;

/**
 * @AUTHOR leijian
 * @Date Created in 2018/7/16
 **/
public class ContractDetail {
    //合约图片地址
    private String contractUrl;
    private BlockContract blockContract;
    private ContractFileUrl contractFileUrl;


    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public BlockContract getBlockContract() {
        return blockContract;
    }

    public void setBlockContract(BlockContract blockContract) {
        this.blockContract = blockContract;
    }

    public ContractFileUrl getContractFileUrl() {
        return contractFileUrl;
    }

    public void setContractFileUrl(ContractFileUrl contractFileUrl) {
        this.contractFileUrl = contractFileUrl;
    }


    @Override
    public String toString() {
        return "ContractDetail{" +
                "contractUrl='" + contractUrl + '\'' +
                ", blockContract=" + blockContract +
                ", contractFileUrl=" + contractFileUrl +
                '}';
    }
}
