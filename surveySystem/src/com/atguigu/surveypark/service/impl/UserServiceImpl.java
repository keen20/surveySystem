package com.atguigu.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.model.security.Role;
import com.atguigu.surveypark.service.RoleService;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Resource
	private RoleService roleService ;
	/**
	 * ��д�÷���,Ŀ����Ϊ�˸��ǳ����и÷�����ע��,ָ��ע��ָ����Dao����,����spring
	 * �޷�ȷ��ע���ĸ�Dao---���ĸ�����������Dao.
	 */
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}
	
	/**
	 *  �ж�email�Ƿ�ռ��
	 */
	public boolean isRegisted(String email){
		String hql = "from User u where u.email = ?" ;
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list) ;
	}
	
	/**
	 * ��֤��½��Ϣ
	 */
	public User validateLoginInfo(String email, String md5){
		String hql = "from User u where u.email = ? and u.password = ?" ;
		List<User> list = this.findEntityByHQL(hql,email,md5);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	/**
	 * �����û���Ȩ(ֻ�ܸ��½�ɫ����)
	 */
	public void updateAuthorize(User model, Integer[] ids){
		//��ѯ�¶���,�����Զ�ԭ�ж������
		User newUser = this.getEntity(model.getId());
		if(!ValidateUtil.isValid(ids)){
			newUser.getRoles().clear();
		}
		else{
			List<Role> roles = roleService.findRolesInRange(ids);
			newUser.setRoles(new HashSet<Role>(roles));
		}
	}
	
	/**
	 * �����Ȩ
	 */
	public void clearAuthorize(Integer userId){
		this.getEntity(userId).getRoles().clear();
	}
}
