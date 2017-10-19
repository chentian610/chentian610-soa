package com.chentian610.user.vo;

import com.chentian610.common.vo.BaseVO;

public class UserVO extends BaseVO {


	/**
	* 用户ID，自增长
	*/
	private Integer user_id;

	@Override
	public String toString() {
		return "UserVO{" +
				"user_id=" + user_id +
				", user_name='" + user_name + '\'' +
				", pass_word='" + pass_word + '\'' +
				", name_cn='" + name_cn + '\'' +
				'}';
	}

	/**
	* 账户
	*/
	private String user_name;

	/**
	* 密码
	*/
	private String pass_word;

	/**
	* 中文姓名
	*/
	private String name_cn;

	/**
	 * 手机号码
	 */
	private String phone;

	public void setUser_id(Integer user_id)  {
		this.user_id = user_id;
	}

	public Integer getUser_id()  {
		return user_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUser_name(String user_name)  {
		this.user_name = user_name;
	}

	public String getUser_name()  {
		return user_name;
	}

	public void setPass_word(String pass_word)  {
		this.pass_word = pass_word;
	}

	public String getPass_word()  {
		return pass_word;
	}

	public void setName_cn(String name_cn)  {
		this.name_cn = name_cn;
	}

	public String getName_cn()  {
		return name_cn;
	}

}