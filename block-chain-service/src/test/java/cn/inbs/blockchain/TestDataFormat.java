package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestDataFormat.java
 * @Date: 2018/7/2 15:46
 * @author: create by zhangmingyang
 **/
public class TestDataFormat {
    private static Logger logger = LoggerFactory.getLogger(TestDataFormat.class);

    public static void main(String[] args) {
        Date dateAfterOrBeforeMonth = DateUtils.getDateAfterOrBeforeMonth(new Date(), -1);
        Date currentMonthFirstDate = DateUtils.getCurrentMonthFirstDate(dateAfterOrBeforeMonth);
        Date currentMonthLastDate = DateUtils.getCurrentMonthLastDate(dateAfterOrBeforeMonth);
        String start = DateUtils.formatDate(currentMonthFirstDate, "yyyy-MM-dd 00:00:00:000");
        String last = DateUtils.formatDate(currentMonthLastDate, "yyyy-MM-dd 23:59:59:999");
        logger.info("\nstart:[{}]\nlast:[{}]", start, last);

    }

    private static void test02() {
        Date monthStartTime = DateUtils.getMonthStartTime(new Date());
        String date = DateUtils.formatDate(monthStartTime, DateUtils.DateFormat.DATE_FORMAT_9);
        System.err.println(date);
    }

    private static void test01() {
        try {
            Date dateAfterOrBeforeMonth = DateUtils.getDateAfterOrBeforeMonth(DateUtils.parseDate("2018-06-20", DateUtils.DateFormat.DATE_FORMAT_2), -5);
            String s = DateUtils.formatDate(dateAfterOrBeforeMonth, DateUtils.DateFormat.DATE_FORMAT_2);
            logger.info(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
