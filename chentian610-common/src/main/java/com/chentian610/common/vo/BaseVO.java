package com.chentian610.common.vo;

import com.chentian610.common.util.ActionParam;
import com.chentian610.common.util.ActionUtil;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable {

    public BaseVO(){
//        System.out.println("初始化BaseVO:"+this.actionParam.getSysTime());
        this.actionParam = ActionUtil.getActionParam();
    }
	
	/**
	* APP动态添加的SQL
	*/
	private String app_sql;
	
	/**
	* 排序SQL
	*/
	private String order_sql;
	
	/**
	* 查询开始日期
	*/
	private Date start_time;
	
	/**
	* 查询结束日期
	*/
	private Date end_time;
	
	private Integer start;
	
	private Integer limit;
	
	private Integer direction;
	
	/**
	* 创建者
	*/
	private Integer create_by;

	/**
	* 创建日期
	*/
	private Date create_date;
	
	/**
	* 更新者
	*/
	private Integer update_by;

	/**
	* 更新日期
	*/
	private Date update_date;
	
	/**
	* 版本号
	*/
	private Integer version;

    private ActionParam actionParam;

    public ActionParam getActionParam() {
        return actionParam;
    }

    public void setActionParam(ActionParam actionParam) {
        this.actionParam = actionParam;
    }

    public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Integer update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getApp_sql() {
		return app_sql;
	}

	public void setApp_sql(String app_sql) {
		this.app_sql = app_sql;
	}

	public String getOrder_sql() {
		return order_sql;
	}

	public void setOrder_sql(String order_sql) {
		this.order_sql = order_sql;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
}