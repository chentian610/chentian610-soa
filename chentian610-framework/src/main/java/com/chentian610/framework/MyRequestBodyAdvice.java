package com.chentian610.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by jeff on 15/10/23.
 */
@Order(1)
@ControllerAdvice(basePackages = "com.chentian610")
public class MyRequestBodyAdvice implements RequestBodyAdvice {
	protected static Log logger = LogFactory.getLog(RequestInterceptor.class);

	@Override
	public Object afterBodyRead(Object arg0, HttpInputMessage arg1,
			MethodParameter arg2, Type arg3,
			Class<? extends HttpMessageConverter<?>> arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage arg0,
			MethodParameter arg1, Type arg2,
			Class<? extends HttpMessageConverter<?>> arg3) throws IOException {
		logger.info("请不要离开我beforeBodyRead"); 
		return null;
	}

	@Override
	public Object handleEmptyBody(Object arg0, HttpInputMessage arg1,
			MethodParameter arg2, Type arg3,
			Class<? extends HttpMessageConverter<?>> arg4) {
		logger.info("请不要离开我handleEmptyBody");
		return null;
	}

	@Override
	public boolean supports(MethodParameter arg0, Type arg1,
			Class<? extends HttpMessageConverter<?>> arg2) {
		logger.info("请不要离开我supports");
		return false;
	}
}