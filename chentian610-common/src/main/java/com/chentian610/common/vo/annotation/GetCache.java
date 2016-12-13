package com.chentian610.common.vo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解,采用该注解的方法,表示有缓存到APP本地的功能
 * 首先判断在Redis中存放的版本,如果一致,则直接返回成功,APP端调用本地缓存数据加载
 * 否则,正常处理,并提醒APP端进行新版本数据的缓存
 * @author Chenth
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GetCache {
	String name() default "";
	String value() default "";
}
