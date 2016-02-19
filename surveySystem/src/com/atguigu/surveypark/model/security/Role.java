package com.atguigu.surveypark.model.security;

import java.util.HashSet;
import java.util.Set;

import com.atguigu.surveypark.model.BaseEntity;

/**
 * 角色
 */
public class Role extends BaseEntity{
	private static final long serialVersionUID = 8666491125061113331L;
	private Integer id;
	private String roleName;
	private String roleValue;
	private String roleDesc;

	//权限集合
	private Set<Right> rights = new HashSet<Right>();

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
