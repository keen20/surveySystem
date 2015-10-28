package com.survey.dao.impl;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import com.survey.dao.BaseDao;
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	//通过注解注入，也可以设置setter方法通过配置文件注入
	@Resource
	private SessionFactory sf;
	private Class<T> clazz;
	public BaseDaoImpl(){
		//通过反射获取泛型化超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取实参,返回的是数组
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}
	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}
	public void BatchEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0; i < objects.length; i++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}
	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}
	public T getEntity(Integer id) {
		
		return (T) sf.getCurrentSession().get(clazz, id);
	}
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0; i < objects.length; i++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
}
