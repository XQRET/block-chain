package cn.inbs.blockchain.dao.vo.cockpit;

import java.util.Comparator;

/**
 * @Description:地区合约分布倒序比较器
 * @Package: cn.inbs.blockchain.dao.vo.cockpit
 * @ClassName:
 * @Date: 2018年07月05-15:35
 * @Author: createBy:chenhao
 **/
public class SortRegionDesc implements Comparator<RegionVO> {
    @Override
    public int compare(RegionVO o1, RegionVO o2) {
        if (o1.getNumber().compareTo(o2.getNumber()) == 0) {
            return 0;
        } else if (o1.getNumber().compareTo(o2.getNumber()) == 1) {
            return -1;
        } else {
            return 1;
        }
    }
}
