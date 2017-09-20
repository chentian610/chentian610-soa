package com.geccocrawler.gecco.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlField {

	/**
	 * jquery风格的元素选择器，使用jsoup实现。jsoup在分析html方面提供了极大的便利
	 * 
	 * @return 元素选择器
	 */
	String cssPath();
}
