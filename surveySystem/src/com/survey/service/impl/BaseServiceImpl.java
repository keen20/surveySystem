package com.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.survey.dao.BaseDao;
import com.survey.service.BaseService;
//单个实体继承该类
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao<T> baseDao;
	/*
	 * 注入，他会按名称找名叫baseDao的dao找不到后按类型找还是泛型，找到了四个
		会出现：No unique bean of type [com.survey.dao.BaseDao] is defined: expected single matching bean but found 4:
 		[pageDao, questionDao, surveyDao, userDao]的错误，
 		可以在每个service里覆写set方法按名查找
 		【因此这个注入必需加到set方法上不可以加到字段上，因为字段无法overide】
	 */
	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}
	public void BatchEntityByHQL(String hql, Object... objects) {
		baseDao.BatchEntityByHQL(hql, objects);
	}
	public T loadEntity(Integer id) {
		return baseDao.loadEntity(id);
	}
	public T getEntity(Integer id) {
		return baseDao.getEntity(id);
	}
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return baseDao.findEntityByHQL(hql, objects);
	}
}
