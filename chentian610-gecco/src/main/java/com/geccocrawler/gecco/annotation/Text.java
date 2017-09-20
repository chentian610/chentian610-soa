package com.geccocrawler.gecco.annotation;

import java.lang.annotation.*;

/**
 * 默认类型，表示获取html元素的inner text。属性支持java基本类型的自动转换。
 * 
 * @author huchengyi
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {

	boolean own() default true;
}
