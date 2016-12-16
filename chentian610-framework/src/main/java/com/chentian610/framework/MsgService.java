package com.chentian610.framework;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 通用消息配置类，将中文提示配置到消息文件里面，用代码获取，防止乱码
 * @author chentian610
 */
public class MsgService {
	private static ResourceBundle rb = ResourceBundle.getBundle("messages");
	
	public static String getMsg(String key,Object...params){
		String msg = null;
		if(!rb.containsKey(key)) {
			msg = rb.getString("提示信息出错，消息代码无效");
			msg = MessageFormat.format(msg, key);
		}else{
			msg = rb.getString(key);
			msg = MessageFormat.format(msg, params);
		}
		return msg;
	}
	
	public static String getMsg(String key){
		if(!rb.containsKey(key)) return "";
		else return	rb.getString(key);
	}
	
}
