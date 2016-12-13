package com.chentian610.chentian610.news.vo;


import com.chentian610.common.vo.BaseVO;

public class NewsFileVO extends BaseVO{

	/**
	* 主键，自增长
	*/
	private Integer file_id;
	/**
	* 新闻ID
	*/
	private Integer news_id;

	/**
	* 学校id
	*/
	private Integer school_id;
	/**
	* 文件URL,绝对路径
	*/
	private String file_url;

	public Integer getFile_id() {
		return file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
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
}