package com.atguigu.surveypark.service;

import com.atguigu.surveypark.model.User;

/**
 * UserService
 */
public interface UserService extends BaseService<User> {

	/**
	 *  判断email是否占用
	 */
	public boolean isRegisted(String email);

	/**
	 * 验证登陆信息
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * 更新用户授权(只能更新角色设置)
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * 清除授权
	 */
	public void clearAuthorize(Integer userId);
}
