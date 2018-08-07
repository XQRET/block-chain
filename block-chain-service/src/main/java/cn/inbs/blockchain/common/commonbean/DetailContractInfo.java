package cn.inbs.blockchain.common.commonbean;

import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.dao.po.ContractFileUrl;
import cn.inbs.blockchain.dao.po.ContractInfoUrl;
import cn.inbs.blockchain.dao.po.ContractSerial;

import java.util.List;

/**
 * @Description:资金端合约触发详细信息
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName:
 * @Date: 2018年05月23-19:11
 * @Author: createBy:zhangmingyang
 **/
public class DetailContractInfo extends BaseCommonBean {
    private BlockContract blockContract;
    private List<ContractInfoUrl> contractInfoUrlList;

    private List<ContractSerial> contractSerials;

    private List<ContractFileUrl> contractFileUrls;

    public List<ContractFileUrl> getContractFileUrls() {
        return contractFileUrls;
    }

    public void setContractFileUrls(List<ContractFileUrl> contractFileUrls) {
        this.contractFileUrls = contractFileUrls;
    }

    public List<ContractSerial> getContractSerials() {
        return contractSerials;
    }

    public void setContractSerials(List<ContractSerial> contractSerials) {
        this.contractSerials = contractSerials;
    }

    public BlockContract getBlockContract() {
        return blockContract;
    }

    public void setBlockContract(BlockContract blockContract) {
        this.blockContract = blockContract;
    }

    public List<ContractInfoUrl> getContractInfoUrlList() {
        return contractInfoUrlList;
    }

    public void setContractInfoUrlList(List<ContractInfoUrl> contractInfoUrlList) {
        this.contractInfoUrlList = contractInfoUrlList;
    }

    @Override
    public String toString() {
        return "DetailContractInfo{" +
                "blockContract=" + blockContract +
                ", contractInfoUrlList=" + contractInfoUrlList +
                ", contractSerials=" + contractSerials +
                ", contractFileUrls=" + contractFileUrls +
                '}';
    }
}
