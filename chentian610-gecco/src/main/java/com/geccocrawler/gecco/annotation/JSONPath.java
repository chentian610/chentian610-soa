package com.geccocrawler.gecco.annotation;

import java.lang.annotation.*;

/**
 * fastjson,jsonpath语法
 * 
 * @author huchengyi
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONPath {
	
	/**
	 * jsonpath
	 * 
	 * @return jsonpath
	 */
	String value();
	
}
