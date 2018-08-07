package cn.inbs.blockchain.common.utils;

import java.util.Map;

/**
 * @Description:Map 工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: MapUtils.java
 * @Date: 2018年04月04-14:25
 * @Author: createBy:zhangmingyang
 **/
public class MapUtils {
    /**
     * map空判断
     *
     * @param val
     * @return
     */
    public static boolean isEmpty(Map val) {
        return null == val || val.size() == 0;
    }

    /**
     * map非空判断
     *
     * @param val
     * @return
     */
    public static boolean isNotEmpty(Map val) {
        return !isEmpty(val);
    }
}
