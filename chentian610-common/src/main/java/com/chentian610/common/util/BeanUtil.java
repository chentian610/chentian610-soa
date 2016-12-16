package com.chentian610.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*******************************************************************
 * Fastjson的SerializerFeature序列化属性
 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
 * WriteMapNullValue——–是否输出值为null的字段,默认为false
 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
 ********************************************************************/
public class BeanUtil {

	/**
	 * 将Json数组格式的字符串转换成List集合
	 * @param bean
	 * @return
	 */
	public static <T> String beanToJson(T bean){
		return JSON.toJSONString(bean, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
	}

	/**
	 * 将Json数组格式的字符串转换成List集合
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz){
		List<T> list = new ArrayList<T>();
		if (StringUtil.isEmpty(jsonStr)) return list;
		return JSONArray.parseArray(jsonStr,clazz);
	}


	/**
	 * 将List数据集转换成json格式数组
	 * @param list
	 * @return
	 */
	public static String ListTojson(List<?> list){
		return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
	}


	/**
	 * 将Map转换成javaBean，返回需要的实体对象
	 * @param map 待转换的Map
	 * @param clazz 需要转换的实体类
	 * @author chentian610
	 */
	public static <T> T formatMapToBean(Map<?, ?> map, Class<T> clazz){
		return JSON.parseObject(JSON.toJSONBytes(map),clazz);
	}


	/**
	 * 将传来的参数转换成javaBean，返回需要的实体对象
	 * @param clazz 需要转换的实体类
	 * @author chentian610
	 */
	public static <T> T formatToBean(Class<T> clazz){
		return formatMapToBean(ActionUtil.getParameterMap(),clazz);
	}

	/**
	 * 拼接某属性的 got方法
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName(String fieldName,String add) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return add + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}
}
