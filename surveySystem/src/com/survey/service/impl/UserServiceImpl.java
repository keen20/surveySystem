package com.survey.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.survey.dao.BaseDao;
import com.survey.model.User;
import com.survey.service.UserService;
import com.survey.util.ValidateUtil;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	//在每个service里自行注入
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}
	//是否注册
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email=?";
		List<User> emails = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValidate(emails);
	}
	//登陆验证
	public User validateLoginInfo(String email, String md5) {
		String hql = "from User u where u.email=? and u.password=?";
		List<User> list = this.findEntityByHQL(hql, email,md5);
		return ValidateUtil.isValidate(list) ? list.get(0):null;
	}
	
}
