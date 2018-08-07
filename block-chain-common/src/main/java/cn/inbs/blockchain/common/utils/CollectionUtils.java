package cn.inbs.blockchain.common.utils;

import java.util.Collection;

/**
 * @Description: 集合工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: CollectionUtils.java
 * @Date: 17:55 2018/4/2
 * @author: create by zhangmingyang
 **/
public class CollectionUtils {
    /**
     * 集合空判断
     *
     * @param collection 待判断集合
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.size() == 0;
    }

    /**
     * 集合非空判断
     *
     * @param collection 待判断集合
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
