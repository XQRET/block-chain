package cn.inbs.blockchain;

import java.math.BigDecimal;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestBigdecimalFormat.java
 * @Date: 2018/7/7 13:10
 * @author: create by zhangmingyang
 **/
public class TestBigdecimalFormat {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal(0);
        BigDecimal d2 = new BigDecimal(1000);
        BigDecimal divide = d1.divide(d2, 2, BigDecimal.ROUND_HALF_DOWN);
        System.err.println(divide);

    }
}
