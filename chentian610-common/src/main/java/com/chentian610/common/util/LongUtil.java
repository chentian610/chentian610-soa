package com.chentian610.common.util;

public class LongUtil {
	public static boolean isEmpty(Long i){
		return i == null || i == 0;
	}
	
	public static boolean isNotEmpty(Long i){
		return i != null && i > 0;
	}
	
	public static boolean isNull(Long i){
		return i==null;
	}
	
	public static long getValue(Object i){
		try {
			return Long.parseLong(i.toString());
		} catch (Exception e) {
			return 0L;
		}
	}
}
