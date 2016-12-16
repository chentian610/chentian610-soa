package com.chentian610.common.util;

import org.springframework.core.NamedThreadLocal;

import java.util.Date;
import java.util.HashMap;


/**
 * 用于存放分页参数、用户信息
 * 采用ThreadLocal是为了使每个线程保存一份属于自己的数据。
 * @author chentian610
 * */
public class ActionUtil {

	private static final ThreadLocal<ActionParam> ActionParamHolder = new NamedThreadLocal<ActionParam>(
			"ActionParam Holder");

    /**
	 * 初始化线程参数
	 */
	public static void initParam(Integer school_id, String user_type, Integer user_id){
		if (getActionParam()==null) ActionParamHolder.set(new ActionParam(false));
		ActionParam param = ActionParamHolder.get();
		param.setSchool_id(school_id);
		param.setUser_id(user_id);
		param.setUser_type(user_type);
	}

	/**
	 * 设置线程参数
	 * @param p
	 */
	public static void setActionParam(ActionParam p){
		ActionParamHolder.set(p);
	}
	
	public static ActionParam getActionParam(){
		return ActionParamHolder.get();
	}
	
	public static int getStart(){
		return getActionParam().getStart();
	}
	
	public static int getLimit(){
		return getActionParam().getLimit();
	}
	
	public static String getSorter(){
		return getActionParam().getSorter();
	}
	
	public static void setTotal(int total){
		getActionParam().setTotal(total);
	}
	
	public static int getTotal(){
		return getActionParam().getTotal();
	}
	
	public static boolean isPage_web(){
		if (getActionParam()==null) return false;
		return getActionParam().isPage_web();
	}
	
	public static boolean isPage_app(){
		if (getActionParam()==null) return false;
		return getActionParam().isPage_app();
	}
	
	public static boolean isBootStrapTable(){
		return getActionParam().getParam().get("offset")!=null;
	}
	
	public static boolean exist(){
		return getActionParam() != null;
	}

	/**
	 * 获取系统时间
	 * @return
	 */
	public static Date getSysTime() {
		return getActionParam().getSysTime();
	}

	/**
	 * 获取当前操作用户
	 * @return
	 */
	public static Integer getUserID() {
		return getActionParam().getUser_id();
	}
	
	/**
	 * 获取当前操作用户所在的学校ID
	 * @return
	 */
	public static Integer getSchoolID() {
		Integer schoolID = getActionParam().getSchool_id();
		return schoolID;
	}
	
	
	/**
	 * 获取客户端类型
	 * @return
	 */
	public static String getAppType() {
		return getActionParam().getApp_type();
	}
	
	/**
	 * 获取用户类型
	 * @return
	 */
	public static String getUserType() {
		return getActionParam().getUser_type();
	}
	
	/**
	 * 获取用户类型
	 * @return
	 */
	public static Integer getStudentID() {
		return getActionParam().getStudent_id();
	}
	
	/**
	 * 获取传递过来的某个参数的值
	 * @return
	 */
	public static String getParameter(String key) {
		return getActionParam().getParam().get(key);
	}
	
	/**
	 * 获取参数Map
	 * @return
	 */
	public static HashMap<String, String> getParameterMap() {
		return ActionUtil.getActionParam().getParam();
	}
	
	public static void setPage_app(boolean isPage_app) {
		ActionUtil.getActionParam().setPage_app(isPage_app);
	}
	
	public static void setPage_web(boolean isPage_web) {
		ActionUtil.getActionParam().setPage_web(isPage_web);
	}
	
	public static void setSort_up(boolean sort_up) {
		ActionUtil.getActionParam().setSort_up(sort_up);
	}
	
	public static boolean isSort_up() {
		return ActionUtil.getActionParam().isSort_up();
	}
	
	public static void setDirection_pre(boolean isDirection_pre) {
		ActionUtil.getActionParam().setDirection_pre(isDirection_pre);
	}
	
	public static boolean isDirection_pre() {
		return ActionUtil.getActionParam().isDirection_pre();
	}
	
	public static void setCache(boolean isCache) {
		ActionUtil.getActionParam().setCache(isCache);
	}
	
	public static boolean isCache() {
		return ActionUtil.getActionParam().isCache();
	}
	
	public static long getCache_version() {
		return ActionUtil.getActionParam().getCache_version();
	}

	public static void setCache_version(long cache_version) {
		ActionUtil.getActionParam().setCache_version(cache_version);
	}
}
