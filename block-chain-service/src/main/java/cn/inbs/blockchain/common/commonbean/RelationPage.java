package cn.inbs.blockchain.common.commonbean;

import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.CompanyRelation;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName:
 * @Date: 2018年05月28-17:13
 * @Author: createBy:zhangmingyang
 **/
public class RelationPage extends BaseCommonBean {
    private BlockCompany blockCompany;
    private CompanyRelation companyRelation;

    public BlockCompany getBlockCompany() {
        return blockCompany;
    }

    public void setBlockCompany(BlockCompany blockCompany) {
        this.blockCompany = blockCompany;
    }

    public CompanyRelation getCompanyRelation() {
        return companyRelation;
    }

    public void setCompanyRelation(CompanyRelation companyRelation) {
        this.companyRelation = companyRelation;
    }

    @Override
    public String toString() {
        return "RelationPage{" +
                "blockCompany=" + blockCompany +
                ", companyRelation=" + companyRelation +
                '}';
    }
}
