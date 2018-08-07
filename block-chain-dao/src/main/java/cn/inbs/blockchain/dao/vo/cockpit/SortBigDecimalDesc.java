package cn.inbs.blockchain.dao.vo.cockpit;

import java.math.BigDecimal;
import java.util.Comparator;

public class SortBigDecimalDesc implements Comparator<BigDecimal>{
    @Override
    public int compare(BigDecimal o1, BigDecimal o2) {
        if (o1.compareTo(o2) == 0) {
            return 0;
        } else if (o1.compareTo(o2) == 1) {
            return -1;
        } else {
            return 1;
        }
    }
}
