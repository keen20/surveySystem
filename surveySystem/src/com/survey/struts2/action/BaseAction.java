package com.survey.struts2.action;
import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/*
 * 抽象action，模型驱动，用于继承
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {
	private static final long serialVersionUID = 1L;
	public void prepare() throws Exception {}
	public T model;
	//通过反射获取泛型化超类
	public BaseAction() {
		try {
			ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
			Class clazz = (Class)type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//模型驱动
	/*public abstract T getModel();*/
	public T getModel(){
		return model;
	}
}
