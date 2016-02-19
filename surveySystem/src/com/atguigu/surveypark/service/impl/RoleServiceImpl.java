package com.atguigu.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.security.Right;
import com.atguigu.surveypark.model.security.Role;
import com.atguigu.surveypark.service.RightService;
import com.atguigu.surveypark.service.RoleService;
import com.atguigu.surveypark.util.DataUtil;
import com.atguigu.surveypark.util.StringUtil;
import com.atguigu.surveypark.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {
	
	@Resource
	private RightService rightService ;
	
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}
	

	/**
	 * 保存/更新角色
	 */
	public void saveOrUpdateRole(Role model, Integer[] ids){
		//没有给角色授予任何权限
		if(!ValidateUtil.isValid(ids)){
			model.getRights().clear();
		}
		else{
			List<Right> rights = rightService.findRightsInRange(ids);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);
	}


	/**
	 * 查询不在指定范围中的角色
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles)){
			return this.findAllEntities();
		}
		else{
			String hql = "from Role r where r.id not in("+DataUtil.extractRightIds(roles)+")" ;
			return this.findEntityByHQL(hql);
		}
	}
	
	/**
	 * 查询在指定范围中的角色集合
	 */
	public List<Role> findRolesInRange(Integer[] ids){
		if(ValidateUtil.isValid(ids)){
			String hql = "from Role r where r.id in ("+StringUtil.arr2Str(ids)+")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}
}
