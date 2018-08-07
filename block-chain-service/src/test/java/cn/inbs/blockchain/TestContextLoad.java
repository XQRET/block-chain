package cn.inbs.blockchain;

import cn.inbs.blockchain.common.property.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestContextLoad.java
 * @Date: 2018/6/22 20:52
 * @author: create by zhangmingyang
 **/
public class TestContextLoad {
    private static Logger logger = LoggerFactory.getLogger(TestContextLoad.class);

    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
//        try {
//            String stringValue = PropertyUtils.getStringValue("schedule.job.thread.pool.size_test", null);
//            logger.info("配置：【{}】", stringValue);
//
//            Thread.sleep(20000);
//            String stringValue1 = PropertyUtils.getStringValue("schedule.job.thread.pool.size_test", null);
//            logger.info("配置：【{}】", stringValue1);
//            System.in.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void test02() {
        for (int i = 0; i < 1000; i++) {
            System.err.println("CODE_" + i + "=CODE_" + i);

        }
    }
}
