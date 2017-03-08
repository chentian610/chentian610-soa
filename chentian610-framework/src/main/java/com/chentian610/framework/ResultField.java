package com.chentian610.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义返回Json字段注解
 * @author com.chentian610
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultField {
    /**
     * 需要返回的字段
     * @author com.chentian610
     * @return
     */
    String[] includes() default {};

    /**
     * 需要去除的字段
     * @author com.chentian610
     * @return
     */
    String[] excludes() default {};

    /**
     * 数据是否需要加密
     * @author com.chentian610
     * @return
     */
    boolean encode() default false;
}