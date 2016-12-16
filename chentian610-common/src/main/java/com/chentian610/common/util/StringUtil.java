package com.chentian610.common.util;

import com.chentian610.common.DictConstants;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isEmpty(String str){
		return str == null || str.trim().length() == 0 || "[]".equals(str);
	}
	public static boolean isEmpty(Collection<?> col){
		return col == null || col.size() == 0;
	}
	public static boolean isEmpty(String[] strArr){
		return strArr == null || strArr.length == 0;
	}
	
	
	public static boolean isNotEmpty(String str){
		return str != null && str.trim().length() > 0 && (!"[]".equals(str));
	}
	
	public static boolean isNotEmpty(Collection<?> col){
		return col != null && col.size() > 0 ;
	}
	
	public static String upperFirstWord(String str){
		if(str == null || str.trim().length() == 0) return str;
		if(str.length()>1){
			return str.substring(0,1).toUpperCase()+str.substring(1);
		}else{
			return str.toUpperCase();
		}
	}
	
	/**
	 * 将字符串截取前面几个数字，多出来的部分用...代替
	 * @author chentian610
	 * @return 截取后的字符串
	 */
	public static String subString(String str, int length) {
		if (isEmpty(str)) return "";
		if (str.length()>length) return str.substring(0,length-1)+"...";
		else return str;
	}

	/**
	 * 是否是全角字符，可以引申为判断一个字符串是否是中文，中文是全角字符
	 * @param str
	 * @return
	 */
	public static String getStringValue(Object str) {
		if (str==null) return "";
        return str.toString();    
    }
	
	
	/**
	 * 是否是全角字符，可以引申为判断一个字符串是否是中文，中文是全角字符
	 * @param str
	 * @return
	 */
	public boolean hasFullChar(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        } 
        return true;    
    }
	
	/**
	 * 将标点符号切换成空格
	 * @param str
	 * @return
	 */
	public static String replaceSignToSpace(String str) {
        return replaceSign(str," ");
    }
	
	/**
	 * 将标点符号切换成空格
	 * @param str
	 * @return
	 */
	public static String replaceSign(String str, String to) {
        return str.replaceAll("[\\pP‘’“”]", to);
    }
	
	/**
	 * 将数字变成中文数字
	 * @param num
	 * @return
	 */
	public static String changeToBigNum(Integer num) {
		String numStr;
		switch (num)
		{ 
		case 1: 
			numStr="一"; 
		break; 
		case 2: 
			numStr="二";
		break; 
		case 3: 
			numStr="三"; 
		break; 
		case 4: 
			numStr="四"; 
		break; 
		case 5: 
			numStr="五";
		break; 
		case 6: 
			numStr="六"; 
		break;
		case 7: 
			numStr="七"; 
		break; 
		case 8: 
			numStr="八";
		break; 
		case 9: 
			numStr="九"; 
		break;
		default: 
			numStr=num+""; 
		break; 
		}
		return numStr;
    }

	/**
	 * 随机码组合
	 * @param count 随机次数
	 * @return 每次随机数的组合
	 */
	public static String randomCodeNumber(final int count)
	{
	    char[] codeSequence = { '0',	'1', '2', '3', '4', '5', '6', '7', '8', '9'};

		String code = "";
		for (int i = 0; i < count; i++) {
			code += codeSequence[(int) (codeSequence.length * Math.random())];
		}
		return code;
	}
	
	/**
	 * 将方法计算出结果
	 * @param str
	 * @return 如果返回是整数，那么不要小数点，否则保留两位小数点
	 */
	public static String mathByString(String str) {
		String suffix = "";
		if (str.lastIndexOf("%")==(str.length()-1)) {
			suffix = "%";
			str = str.replaceAll("%", "");
		}
        ScriptEngineManager sem=new ScriptEngineManager();
        ScriptEngine engine=sem.getEngineByExtension("js");
        DecimalFormat df = new DecimalFormat("0.00");
        Double result;
		try {
			result = (Double) engine.eval(str);
		} catch (Exception e) {
			return "";
		}
		if (result.isNaN()) return "";
		else if (Math.round(result)-result==0)
		   return result.intValue()+suffix;
		else {
			if ("100.00".equals(String.valueOf(df.format(result)))) {
				if (result<100) return "99.99"+suffix;
			}
			if ("0.00".equals(String.valueOf(df.format(result)))) {
				if (result>0.00) return "0.01"+suffix;
			}
			return String.valueOf(df.format(result))+suffix;
		} 
    }
	
	/**
	 * 判断手机号归属
	 * 
	 * @param mobile
	 * @return -1 错误的号码 1移动 2联通 3电信     1移动2电信3联通
	 */
	public static String checkMoblie(String mobile) {
		Pattern p1 = Pattern.compile("^((13[4-9])|(147)|(15[0|1|2|7|8|9])|(18[2|7|8]))\\d{8}$");
		Pattern p2 = Pattern.compile("^((13[0-2])|(15[5|6])|(18[5|6]))\\d{8}$");
		Pattern p3 = Pattern.compile("^((133)|(153)|(18[0|9]))\\d{8}$");
		if (p1.matcher(mobile).matches()) {
			return DictConstants.PHONE_TYPE_CMCC;
		} else if (p2.matcher(mobile).matches()) {
			return DictConstants.PHONE_TYPE_CUCC;
		} else if (p3.matcher(mobile).matches()) {
			return DictConstants.PHONE_TYPE_CTC;
		}
		return DictConstants.PHONE_TYPE_ERROR;
	}
}
