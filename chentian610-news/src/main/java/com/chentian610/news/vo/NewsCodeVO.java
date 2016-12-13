package com.chentian610.chentian610.news.vo;

import com.chentian610.common.vo.BaseVO;

public class NewsCodeVO extends BaseVO{


	/**
	* 未设置，请在数据库中设置
	*/
	private Integer id;

	/**
	* 学校ID
	*/
	private Integer school_id;

	/**
	* 新闻类型
	*/
	private String news_code;

	/**
	* 新闻分组
	*/
	private String news_group;

	/**
	* 新闻名称
	*/
	private String code_name;
	
	/**
	* 新闻显示类型
	*/
	private String css_code;
	
	/**
	* 新闻样式
	*/
	private String css_value;
	
	/**
	* 新闻显示条数
	*/
	private Integer css_main_count;
	
	/**
	* 扣分描述
	*/
	private String description;

	/**
	* 排序
	*/
	private Integer sort;
	
	/**
	 * 是否为默认(0/1)
	 */
	private Integer is_default;
	
	private String dict_code;
	
	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	private String news_list;	

	public String getNews_list() {
		return news_list;
	}

	public void setNews_list(String news_list) {
		this.news_list = news_list;
	}

	public String getCss_code() {
		return css_code;
	}

	public void setCss_code(String css_code) {
		this.css_code = css_code;
	}

	public String getCss_value() {
		return css_value;
	}

	public void setCss_value(String css_value) {
		this.css_value = css_value;
	}

	public Integer getCss_main_count() {
		return css_main_count;
	}

	public void setCss_main_count(Integer css_main_count) {
		this.css_main_count = css_main_count;
	}

	public Integer getIs_default() {
		return is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}

	public void setId(Integer id)  {
		this.id = id;
	}

	public Integer getId()  {
		return id;
	}

	public void setSchool_id(Integer school_id)  {
		this.school_id = school_id;
	}

	public Integer getSchool_id()  {
		return school_id;
	}

	public void setNews_code(String news_code)  {
		this.news_code = news_code;
	}

	public String getNews_code()  {
		return news_code;
	}

	public void setNews_group(String news_group)  {
		this.news_group = news_group;
	}

	public String getNews_group()  {
		return news_group;
	}

	public void setCode_name(String code_name)  {
		this.code_name = code_name;
	}

	public String getCode_name()  {
		return code_name;
	}

	public void setDescription(String description)  {
		this.description = description;
	}

	public String getDescription()  {
		return description;
	}

	public void setSort(Integer sort)  {
		this.sort = sort;
	}

	public Integer getSort()  {
		return sort;
	}
}