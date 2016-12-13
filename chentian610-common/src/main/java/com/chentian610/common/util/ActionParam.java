package com.chentian610.common.util;


import java.util.Date;
import java.util.HashMap;

public class ActionParam {
	/**
	 * 分页开始序号
	 */
	private int start;
	/**
	 * 数据条目
	 */	
	private int limit;
	/**
	 * 排序
	 */
	private String sorter;
	
	/**
	 * 方向，向前还是向后
	 */
	private String direction;
	
	/**
	 * 总数
	 */
	private int total;
	
	private Date sysTime;

	/**
	 * 操作人员ID
	 */
	private Integer user_id;
	
	/**
	 * 操作人员学校ID
	 */
	private Integer school_id;
	
	/**
	 * 客户端类型
	 */
	private String app_type;
	
	/**
	 * 用户类型
	 */
	private String user_type;
	
	
	/**
	 * 学生ID，孩子ID
	 */
	private Integer student_id;
	
	/**
	 * 请求的参数值
	 */
	private HashMap<String, String> param;
	
	/**
	 * 是否分页:网页，网页分页一般带有第几页
	 */
	private boolean isPage_web;
	
	/**
	 * 是否分页:APP，APP分页通过向上或者向下
	 */
	private boolean isPage_app;
	
	/**
	 * 是否升序排列
	 */
	private boolean isSort_up;
	
	/**
	 * 是否向后请求
	 */
	private boolean isDirection_pre;
	
	/**
	 * 是否缓存到APP本地
	 */
	private boolean isCache;
	
	/**
	 * 是否缓存到APP本地Cache版本
	 */
	private long cache_version;
	

	public void setPage_web(boolean isPage_web) {
		this.isPage_web = isPage_web;
	}

	public ActionParam(boolean isPage_web){
		this.isPage_web=isPage_web;
		this.sysTime = new Date();
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		this.isPage_app = true;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isPage_web() {
		return isPage_web;
	}

	public HashMap<String, String> getParam() {
		return param;
	}

	public void setParam(HashMap<String, String> map) {
		this.param = map;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public boolean isPage_app() {
		return isPage_app;
	}

	public void setPage_app(boolean isPage_app) {
		this.isPage_app = isPage_app;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isSort_up() {
		return isSort_up;
	}

	public void setSort_up(boolean isSort_up) {
		this.isSort_up = isSort_up;
	}
	
	public void setDirection_pre(boolean isDirection_pre) {
		this.isDirection_pre = isDirection_pre;
	}
	
	public boolean isDirection_pre() {
		return this.isDirection_pre;
	}

	public boolean isCache() {
		return isCache;
	}

	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}

	public long getCache_version() {
		return cache_version;
	}

	public void setCache_version(long cache_version) {
		this.cache_version = cache_version;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

}
