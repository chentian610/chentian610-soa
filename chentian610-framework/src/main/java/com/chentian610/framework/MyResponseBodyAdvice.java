package com.chentian610.framework;

import com.alibaba.fastjson.JSON;
import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.vo.ResultVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据过滤的切面处理
 * 注意：如果业务代码返回null，那么不会进入到这个切面
 * @author chenth
 */
@ControllerAdvice(annotations = Controller.class)
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	protected static Log logger = LogFactory.getLog(MyResponseBodyAdvice.class);
    //包含项
    private String[] includes = {};
    //排除项
    private String[] excludes = {};
    //是否加密
    private boolean encode = true;

    @Override  
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) { 
        return true;  
    }

    @Override
    public Object beforeBodyWrite(Object resultVO, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    	String requestUrl = serverHttpRequest.getURI().getPath();
    	logger.info("["+requestUrl+"],Request take time:"+(System.currentTimeMillis()- ActionUtil.getSysTime().getTime())+"ms");
        //防止直接返回字符串的时候被转换成Map而导致错误
    	if (String.class == methodParameter.getParameterType()) return JSON.toJSONString(ResponseUtils.sendSuccess(resultVO));
    	ResultField serializedField = methodParameter.getMethodAnnotation(ResultField.class);
    	//判断是否有字段过滤设置
    	if (serializedField==null) {
    		if ((resultVO instanceof Map) && (((Map<?,?>)resultVO).containsKey(ResponseUtils.RESPONSE_SUCCESS_KEY)))  
    			return resultVO; 
    		else return ResponseUtils.sendSuccess(resultVO);
    	} else {
    		includes = serializedField.includes();//需要返回的字段
    		excludes = serializedField.excludes();//需要过滤的字段
    		encode = serializedField.encode();
    		//如果从ResponseUtils过来的，那么取出数据再进行过滤
    		if (resultVO instanceof HashMap) {
    			HashMap<?,?> oldMap = (HashMap<?,?>) resultVO;
    			if (oldMap.containsKey(ResponseUtils.RESPONSE_SUCCESS_KEY)) {
    				ResultVO vo = (ResultVO) oldMap.get(ResponseUtils.RESPONSE_DATA);
    				return handleResultObject(vo.getData());
    			}
    		}
            return handleResultObject(resultVO);
    	}
    }

    /**
     * 处理过滤字段，返回API指定的字段
     * @param resultVO
     * @return
     */
	private Object handleResultObject(Object resultVO) {
		if (resultVO instanceof HashMap) {
			HashMap<?,?> oldMap = (HashMap<?,?>) resultVO;
			return	ResponseUtils.sendSuccess(handleMap(oldMap));
		} else if (resultVO instanceof List){
		    List<?> list = (List<?>) resultVO;
		    return	ResponseUtils.sendSuccess(handleList(list));
		} else return ResponseUtils.sendSuccess(handleSingleObject(resultVO));
	}

    /**
     * 处理返回值是单个enity对象
     * @param oldmap
     * @return
     */
    private HashMap<String, Object> handleMap(HashMap<?,?> oldmap){
    	HashMap<String,Object> map = new HashMap<String, Object>();
        for (String field:includes) {
        	map.put(field, oldmap.get(field));
        }         
        for (String field:excludes) {
        	map.remove(field);
        } 
        return map;
    }
    
    /**
     * 处理返回值是单个enity对象
     * @param o
     * @return
     */
    private HashMap<String, Object> handleSingleObject(Object o){
    	HashMap<String,Object> map = new HashMap<String, Object>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field:fields){
            //如果未配置表示全部的都返回
            if(includes.length==0 && excludes.length==0){
                Object newVal = getNewVal(o, field);
                map.put(field.getName(), newVal);
            }else if(includes.length>0){
                //有限考虑包含字段
                if(Helper.isStringInArray(field.getName(), includes)){
                	Object newVal = getNewVal(o, field);
                    map.put(field.getName(), newVal);
                }
            }else{
                //去除字段
                if(excludes.length>0){
                    if(!Helper.isStringInArray(field.getName(), excludes)){
                    	Object newVal = getNewVal(o, field);
                        map.put(field.getName(), newVal);
                    }
                }
            }

        }
        return map;
    }

    /**
     * 处理返回值是列表
     *
     * @param list
     * @return
     */
    private List<?> handleList(List<?> list){
        List<Object> retList = new ArrayList<Object>();
        for (Object o:list){
        	if (o instanceof HashMap) 
        		retList.add(handleMap((HashMap<?,?>) o));
        	else retList.add(handleSingleObject(o));
        }
        return retList;
    }

    /**
     * 获取加密后的新值
     * @param o
     * @param field
     * @return
     */
    private Object getNewVal(Object o, Field field){
        try {
        	field.setAccessible(true);
			return field.get(o)==null?"":field.get(o);
		} catch (Exception e) {
			return "";
		}
    }
    
    /**
     * 获取加密后的新值
     *
     * @param o
     * @param field
     * @return
     */
    private String getNewVal_back(Object o, Field field){
        String newVal = "";
        try {
            field.setAccessible(true);
            Object val = field.get(o);

            if(val!=null){
                if(encode){
                    newVal = Helper.encode(val.toString());
                }else{
                    newVal = val.toString();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newVal;
    }
}