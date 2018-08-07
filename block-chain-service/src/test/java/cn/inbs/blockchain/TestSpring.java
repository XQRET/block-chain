package cn.inbs.blockchain;

import cn.inbs.blockchain.common.commonbean.CompanyDetail;
import cn.inbs.blockchain.service.cockpit.ICockpitService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestSpring.java
 * @Date: 2018/7/3 10:10
 * @author: create by zhangmingyang
 **/
public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        ICockpitService cockpitServiceImpl = (ICockpitService) context.getBean("cockpitServiceImpl");
        List<CompanyDetail> nodeList = cockpitServiceImpl.getNodeList();
        System.err.println(nodeList.size() + "================");
    }
}
