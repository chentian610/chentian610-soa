package com.geccocrawler.gecco.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Downloader {

	/**
	 * 下载器名称
	 * 
	 * @return 下载器名称
	 */
	String value();
	
}
