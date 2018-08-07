package cn.inbs.blockchain.common.commonbean;

import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.ContractSerial;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName:
 * @Date: 2018年05月24-15:15
 * @Author: createBy:zhangmingyang
 **/
public class ContractTriggerInfo extends BaseCommonBean {
    private BlockCompany blockCompany;
    private ContractSerial contractSerial;

    public BlockCompany getBlockCompany() {
        return blockCompany;
    }

    public void setBlockCompany(BlockCompany blockCompany) {
        this.blockCompany = blockCompany;
    }

    public ContractSerial getContractSerial() {
        return contractSerial;
    }

    public void setContractSerial(ContractSerial contractSerial) {
        this.contractSerial = contractSerial;
    }

    @Override
    public String toString() {
        return "ContractTriggerInfo{" +
                "blockCompany=" + blockCompany +
                ", contractSerial=" + contractSerial +
                '}';
    }
}
