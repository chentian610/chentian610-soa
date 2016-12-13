package com.chentian610.chentian610.news.vo;


import com.chentian610.common.vo.BaseVO;

public class NewsItemVO extends BaseVO{

	/**
	* 主键，自增长
	*/
	private Integer item_id;
	/**
	* 新闻ID
	*/
	private Integer news_id;

	/**
	* 学校id
	*/
	private Integer school_id;

	/**
	* 校园风采 内容
	*/
	private String content;

	private String content_text;//排序
	private String template_type;
	public String getTemplate_type() {
		return template_type;
	}//模板类型

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}
	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public void setNews_id(Integer news_id)  {
		this.news_id = news_id;
	}

	public Integer getNews_id()  {
		return news_id;
	}

	public void setSchool_id(Integer school_id)  {
		this.school_id = school_id;
	}

	public Integer getSchool_id()  {
		return school_id;
	}

	public void setContent(String content)  {
		this.content = content;
	}

	public String getContent()  {
		return content;
	}
}