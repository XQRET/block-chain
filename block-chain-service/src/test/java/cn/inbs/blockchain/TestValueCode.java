package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestValueCode {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            context.start();
            CheckValueCodeUtils.checkValueCode(true, "9", ValueCodeEnums.COMPANY_STATUS);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
