package com.chentian610.common.vo;

import com.chentian610.common.DictConstants;

public class ReceiveVO {
	/**
	 * 学校ID
	 */
	private Integer school_id;
	
	/**
	 * 分组类型,班级、寝室、自定义（011）
	 */
	private String team_type;
	
	/**
	* 团队ID，对应教室或者寝室ID
	*/
	private Integer team_id;
	
	/**
	 * 团队分组ID，指的是年级ID或者寝室分组ID
	 */
	private Integer group_id;
	
	
	private String user_type;
	/**
	 * 指定某人的接受者
	 */
	private Integer user_id;
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	/**
	 * 指定某个学生的接受者
	 */
	private Integer student_id;
	
	public ReceiveVO(Integer school_id, String team_type, Integer group_id, Integer team_id) {
		this.school_id = school_id;
		this.team_type = team_type;
		this.group_id = group_id;
		this.team_id = team_id;
		this.user_type = DictConstants.USERTYPE_ALL;
	}
	
	public ReceiveVO(){
		//doSomething;
	};

	public ReceiveVO(Integer school_id, String user_type, Integer id){
		this.school_id = school_id;
		this.user_type = user_type;
		if (DictConstants.USERTYPE_STUDENT.equals(user_type))
			this.student_id=id;
		else this.user_id=id;
	};
	
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getTeam_type() {
		return team_type;
	}

	public void setTeam_type(String team_type) {
		this.team_type = team_type;
	}

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
}
