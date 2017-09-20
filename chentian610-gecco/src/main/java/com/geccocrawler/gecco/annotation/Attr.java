package com.geccocrawler.gecco.annotation;

import java.lang.annotation.*;

/**
 * 获取html元素的attribute。属性支持java基本类型的自动转换。
 * 
 * @author huchengyi
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attr {
	
	/**
	 * 表示属性名称
	 * @return 属性名称
	 */
	String value();
	
}
