package cn.inbs.blockchain.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: DateUtils.java
 * @Date: 18:11 2018/4/2
 * @author: create by zhangmingyang
 **/
public class DateUtils {
    /**
     * 日期格式,以后如果有需要,可以直接一次往下添加
     */
    public static class DateFormat {
        public static final String DATE_FORMAT_1 = "yyyyMMdd";
        public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
        public static final String DATE_FORMAT_3 = "yyyy/MM/dd";
        public static final String DATE_FORMAT_4 = "yyyyMMddHHmmss";
        public static final String DATE_FORMAT_5 = "yyyyMMdd HH:mm:ss";
        public static final String DATE_FORMAT_6 = "yyyyMMddHHmmssSSS";
        public static final String DATE_FORMAT_7 = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
        public static final String DATE_FORMAT_8 = "yyyy年MM月dd日";
        public static final String DATE_FORMAT_9 = "yyyy-MM-dd HH:mm:ss";
        public static final String DATE_FORMAT_10 = "yyyy-MM";
        public static final String DATE_FORMAT_11 = "yyyyMM";
    }

    /**
     * 日期转字符串
     *
     * @param date   日期
     * @param format 日期格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (null == date) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    /**
     * 字符串转日期
     *
     * @param strDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String strDate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(strDate);
    }

    /**
     * sql日期转化为util日期
     *
     * @param sqlDate
     * @return
     */
    public static Date sqlDate2UtilDate(java.sql.Date sqlDate) {
        if (null == sqlDate) {
            return null;
        } else {
            return new Date(sqlDate.getTime());
        }

    }

    /**
     * util日期转换sql日期
     *
     * @param utilDate
     * @return
     */
    public static java.sql.Date utilDate2SQLDate(Date utilDate) {
        if (null == utilDate) {
            return null;
        } else {
            return new java.sql.Date(utilDate.getTime());
        }
    }

    /**
     * 获取开始时间
     *
     * @return
     */
    public static long getStartTime() {
        return System.currentTimeMillis();
    }

    /**
     * 根据开始时间计算耗时,单位为秒
     *
     * @param startTime
     * @return
     */
    public static long getConsumeTimeByStartTime(long startTime) {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    /**
     * 获取当前日期的前后指定月数
     *
     * @param date
     * @param val
     * @return
     */
    public static Date getDateAfterOrBeforeMonth(Date date, int val) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, val);
        return calendar.getTime();
    }

    /**
     * 获取每个月开始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStartTime(Date date) {
        try {
            String s = formatDate(date, DateFormat.DATE_FORMAT_10);
            s = s.concat("-01 00:00:00");
            return parseDate(s, DateFormat.DATE_FORMAT_9);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前月第一天时间
     *
     * @param date
     * @return
     */
    public static Date getCurrentMonthFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取当前月最后一天时间
     *
     * @param date
     * @return
     */
    public static Date getCurrentMonthLastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
