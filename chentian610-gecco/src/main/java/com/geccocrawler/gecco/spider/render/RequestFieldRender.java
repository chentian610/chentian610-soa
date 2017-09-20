package com.geccocrawler.gecco.spider.render;

import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import net.sf.cglib.beans.BeanMap;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

public class RequestFieldRender implements FieldRender {

	@Override
	@SuppressWarnings({"unchecked" })
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Set<Field> requestFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Request.class));
		for(Field field : requestFields) {
			beanMap.put(field.getName(), request);
		}
	}
	
}
