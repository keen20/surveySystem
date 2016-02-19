package com.atguigu.surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.service.BaseService;

/**
 * 抽象的baseService,专门用于继承
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao ;
	
	private Class<T> clazz ;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	//注入dao
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}
	
	//执行原生的sql语句
	public void executeSQL(String sql,Object...objects){
		dao.executeSQL(sql, objects);
	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	//查询所有实体
	public List<T> findAllEntities(){
		String hql = "from " + clazz.getSimpleName() ;
		return this.findEntityByHQL(hql);
	}
	
	//单值检索,确保查询结果有且只有一条记录
	public Object uniqueResult(String hql,Object...objects){
		return dao.uniqueResult(hql, objects);
	}
	
	//执行sql原生查询
	public List executeSQLQuery(Class clazz,String sql,Object...objects){
		return dao.executeSQLQuery(clazz,sql, objects);
	}
}
