package com.chentian610.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义返回Json字段注解
 * @author chenth
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultField {
    /**
     * 需要返回的字段
     * @author chenth
     * @return
     */
    String[] includes() default {};

    /**
     * 需要去除的字段
     * @author chenth
     * @return
     */
    String[] excludes() default {};

    /**
     * 数据是否需要加密
     * @author chenth
     * @return
     */
    boolean encode() default false;
}