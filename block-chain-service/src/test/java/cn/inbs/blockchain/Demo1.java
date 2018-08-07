package cn.inbs.blockchain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年07月04-17:11
 * @Author: createBy:chenhao
 **/
public class Demo1 {
    public static void main(String... args) {
        System.out.println("hello");
        DecimalFormat df = new DecimalFormat(",###.##");
        String format = df.format(new BigDecimal(56581.2200).setScale(2,BigDecimal.ROUND_HALF_UP));
        System.out.println(format);
        System.out.println(new BigDecimal(56581.2200).setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
