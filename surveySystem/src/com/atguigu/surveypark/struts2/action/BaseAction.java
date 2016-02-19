package com.atguigu.surveypark.struts2.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象action,专门用于继承
 */
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	private static final long serialVersionUID = 9180917383072055589L;

	public T model ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction(){
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepare() throws Exception {
	}

	public T getModel(){
		return model ;
	}
}
