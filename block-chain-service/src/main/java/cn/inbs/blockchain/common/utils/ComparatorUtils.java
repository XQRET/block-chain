package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.dao.po.chart.RecentSomeMonthSingle;
import cn.inbs.blockchain.dao.po.chart.SomeMonthIncomeSingle;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Description: 定义比较规则
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: ComparatorUtils
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 11:16
 * @Version: 1.0
 */
public class ComparatorUtils {


    /**
     * 根据流水时间排序
     *
     * @param someMonthIncomeSingles
     * @param dateFormat
     * @return
     */
    public static List<SomeMonthIncomeSingle> orderRecentSomeMonthIncome(List<SomeMonthIncomeSingle> someMonthIncomeSingles, final String dateFormat) {
        Collections.sort(someMonthIncomeSingles, new Comparator<SomeMonthIncomeSingle>() {
            @Override
            public int compare(SomeMonthIncomeSingle o1, SomeMonthIncomeSingle o2) {
                try {
                    Date o1Date = DateUtils.parseDate(o1.getMonthStr(), dateFormat);
                    Date o2Date = DateUtils.parseDate(o2.getMonthStr(), dateFormat);
                    return o1Date.compareTo(o2Date);
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
        return someMonthIncomeSingles;
    }


    /**
     * 根据流水时间排序
     *
     * @param recentSomeMonthDatas
     * @param dateFormat
     * @return
     */
    public static List<RecentSomeMonthSingle> orderRecentSomeMonthData(List<RecentSomeMonthSingle> recentSomeMonthDatas, final String dateFormat) {
        Collections.sort(recentSomeMonthDatas, new Comparator<RecentSomeMonthSingle>() {
            @Override
            public int compare(RecentSomeMonthSingle o1, RecentSomeMonthSingle o2) {
                try {
                    Date o1Date = DateUtils.parseDate(o1.getDataBelongMonthTime(), dateFormat);
                    Date o2Date = DateUtils.parseDate(o2.getDataBelongMonthTime(), dateFormat);
                    return o1Date.compareTo(o2Date);
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
        return recentSomeMonthDatas;
    }
}
