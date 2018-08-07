package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年05月21-13:29
 * @Author: createBy:zhangmingyang
 **/
public class TestCheckValueCodeUtils {
    private static Logger logger = LoggerFactory.getLogger(TestCheckValueCodeUtils.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        try {
            CheckValueCodeUtils.checkValueCode(true, "5", ValueCodeEnums.CONTRACT_STATUS);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
}
