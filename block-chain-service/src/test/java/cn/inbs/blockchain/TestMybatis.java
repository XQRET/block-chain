package cn.inbs.blockchain;

import cn.inbs.blockchain.dao.company.CompanyRelationMapper;
import cn.inbs.blockchain.dao.po.CompanyRelation;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年06月12-12:15
 * @Author: createBy:zhangmingyang
 **/
public class TestMybatis {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        CompanyRelationMapper companyRelationMapper = (CompanyRelationMapper) context.getBean("companyRelationMapper");
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setUpdateTime(new Date());
        companyRelation.setRelationStatus("1");
        companyRelation.setZcCompanyBlockId("82iiiivP9PSwt6rCCt6rfDSw82656545sgD5CCCC");
        companyRelation.setZjCompanyBlockId("82hGSwea6hGSwSwsgD5Swii82656545iiea6ea6h");
        companyRelationMapper.updateRelatedStatus(companyRelation);
    }
}
