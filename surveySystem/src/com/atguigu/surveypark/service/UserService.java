package com.atguigu.surveypark.service;

import com.atguigu.surveypark.model.User;

/**
 * UserService
 */
public interface UserService extends BaseService<User> {

	/**
	 *  �ж�email�Ƿ�ռ��
	 */
	public boolean isRegisted(String email);

	/**
	 * ��֤��½��Ϣ
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * �����û���Ȩ(ֻ�ܸ��½�ɫ����)
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * �����Ȩ
	 */
	public void clearAuthorize(Integer userId);
}
