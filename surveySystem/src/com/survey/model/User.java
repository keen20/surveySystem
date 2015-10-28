package com.survey.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户类
 */
public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String nickName;
	private Date regDate = new Date();// 注册时间不可修改
	/*
	 * 因为要做单向关联，因此不需要再在user类里管理survey属性 private Set<Survey> surveys = new
	 * HashSet<Survey>(); public Set<Survey> getSurveys() { return surveys; }
	 * public void setSurveys(Set<Survey> surveys) { this.surveys = surveys; }
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", nickName=" + nickName + "]";
	}

}
