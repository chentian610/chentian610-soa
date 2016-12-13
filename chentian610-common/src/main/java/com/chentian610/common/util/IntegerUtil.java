package com.chentian610.common.util;

import javax.servlet.http.HttpServletRequest;

public class IntegerUtil {
	public static boolean isEmpty(Integer i){
		return i == null || i == 0;
	}
	
	public static boolean isNotEmpty(Integer i){
		return i != null && i > 0;
	}
	
	public static boolean isNull(Integer i){
		return i==null;
	}
	
	public static Integer getValue(Object i){
		try {
			return Integer.parseInt(i+"");
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将请求里面的所有参数设置到Param中
	 * @return
	 */
	public static Integer getParametValue(HttpServletRequest request, String field) {
		String requestValue = request.getParameter(field);
		if (StringUtil.isEmpty(requestValue)) {
			Object sessionValue =  request.getSession().getAttribute(field);
			requestValue = sessionValue + "";
		} 
		try {
			return Integer.parseInt(requestValue);
		} catch (Exception e) {
			return 0;
		}
	}
}
