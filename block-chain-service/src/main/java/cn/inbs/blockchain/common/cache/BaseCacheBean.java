package cn.inbs.blockchain.common.cache;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.cache
 * @ClassName:
 * @Date: 2018年04月26-18:32
 * @Author: createBy:zhangmingyang
 **/
public class BaseCacheBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
