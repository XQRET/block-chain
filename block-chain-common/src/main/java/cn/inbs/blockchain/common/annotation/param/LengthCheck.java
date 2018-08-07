package cn.inbs.blockchain.common.annotation.param;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 参数长度校验注解
 * @Package: cn.inbs.blockchain.common.annotation.param
 * @ClassName: LengthCheck
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 11:12
 * @Version: 1.0
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Documented
public @interface LengthCheck {
    /**
     * 参数名中文描述
     *
     * @return
     */
    String fieldDescription();

    /**
     * 最大长度
     *
     * @return
     */
    int maxLength();

    /**
     * 最小长度
     *
     * @return
     */
    int minLength() default 0;
}
