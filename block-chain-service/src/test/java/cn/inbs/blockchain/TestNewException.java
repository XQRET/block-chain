package cn.inbs.blockchain;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.exception.PlatformException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNewException {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();

        try {
            throw new BusinessException(BusinessErrorConstants.CODE_0001,"mm");
        } catch (PlatformException e) {
            e.printStackTrace();
        }
    }
}
