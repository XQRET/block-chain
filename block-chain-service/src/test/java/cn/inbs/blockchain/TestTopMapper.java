package cn.inbs.blockchain;

import cn.inbs.blockchain.dao.chart.ChartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestTopMapper.java
 * @Date: 2018/7/7 16:44
 * @author: create by zhangmingyang
 **/
public class TestTopMapper {
    private static Logger logger = LoggerFactory.getLogger(TestTopMapper.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        ChartMapper chartMapper = (ChartMapper) context.getBean("chartMapper");
        List<Map<String, Object>> maps = chartMapper.selectZcTop(1);
        logger.info("top:[{}]", maps);
    }
}
