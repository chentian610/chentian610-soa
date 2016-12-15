package com.chentian610.news.vo;


import com.chentian610.common.vo.BaseVO;

public class NewsVO extends BaseVO{


	/**
	* 主键，自增长
	*/
	private Integer news_id;

	/**
	* 学校id
	*/
	private Integer school_id;

	/**
	* 新闻编码
	*/
	private String news_code;
	
	/**
	* 新闻
	*/
	private String dict_group;

	/**
	* 校园风采 标题
	*/
	private String title;

	/**
	* 校园风采 内容
	*/
	private String content;

	/**
	* 未设置，请在数据库中设置
	*/
	private String content_text;

	/**
	* 主图URL，缩略图
	*/
	private String main_pic_url;
	
	/**
	* 是否在首页显示
	*/
	private Integer is_main;

	/**
	* 发布部门
	*/
	private String dept_name;

	/**
	* 发布时间
	*/
	private String deploy_date;

	/**
	 * 搜索条件
	 */
	private String search;

	/**
	 * 模块code
	 */
	private String module_code;
	
	/**
	 * 项目名称
	 */
	private String code_name;
	private String item_list;
	private String file_list;
	private String template_type;
	public String getTemplate_type() {
		return template_type;
	}//模板类型

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public String getItem_list() {
		return item_list;
	}

	public void setItem_list(String item_list) {
		this.item_list = item_list;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getDict_group() {
		return dict_group;
	}

	public void setDict_group(String dict_group) {
		this.dict_group = dict_group;
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

	public void setNews_code(String news_code)  {
		this.news_code = news_code;
	}

	public String getNews_code()  {
		return news_code;
	}

	public void setTitle(String title)  {
		this.title = title;
	}

	public String getTitle()  {
		return title;
	}

	public void setContent(String content)  {
		this.content = content;
	}

	public String getContent()  {
		return content;
	}

	public void setContent_text(String content_text)  {
		this.content_text = content_text;
	}

	public String getContent_text()  {
		return content_text;
	}

	public void setMain_pic_url(String main_pic_url)  {
		this.main_pic_url = main_pic_url;
	}

	public String getMain_pic_url()  {
		return main_pic_url;
	}

	public void setDept_name(String dept_name)  {
		this.dept_name = dept_name;
	}

	public String getDept_name()  {
		return dept_name;
	}

	public String getDeploy_date() {
		return deploy_date;
	}

	public void setDeploy_date(String deploy_date) {
		this.deploy_date = deploy_date;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public Integer getIs_main() {
		return is_main;
	}

	public void setIs_main(Integer is_main) {
		this.is_main = is_main;
	}

}