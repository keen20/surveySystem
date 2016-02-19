package com.atguigu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.atguigu.surveypark.model.security.Role;

/**
 * roleService
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * 保存/更新角色
	 */
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds);

	/**
	 * 查询不在指定范围中的角色集合
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles);

	/**
	 * 查询在指定范围中的角色集合
	 */
	public List<Role> findRolesInRange(Integer[] ids);
	
}
