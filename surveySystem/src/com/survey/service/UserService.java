package com.survey.service;
import com.survey.model.User;
/*
 * 存储用户业务方法
 */
public interface UserService extends BaseService<User> {

	public boolean isRegisted(String src);

	public User validateLoginInfo(String email, String md5);

}
