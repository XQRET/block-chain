package cn.inbs.blockchain.common.commonbean;

import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.dao.po.CompanyPhoto;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.dao.po
 * @ClassName: CompanyDetail.java
 * @Date: 2018/7/3 14:59
 * @author: create by zhangmingyang
 **/
public class CompanyDetail {
    private BlockCompany blockCompany;
    private CompanyPhoto companyPhoto;

    public BlockCompany getBlockCompany() {
        return blockCompany;
    }

    public void setBlockCompany(BlockCompany blockCompany) {
        this.blockCompany = blockCompany;
    }

    public CompanyPhoto getCompanyPhoto() {
        return companyPhoto;
    }

    public void setCompanyPhoto(CompanyPhoto companyPhoto) {
        this.companyPhoto = companyPhoto;
    }

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "blockCompany=" + blockCompany +
                ", companyPhoto=" + companyPhoto +
                '}';
    }
}
