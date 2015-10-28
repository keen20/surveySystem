package com.survey.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.survey.model.User;
import com.survey.struts2.UserAware;
import com.survey.struts2.action.BaseAction;
import com.survey.struts2.action.LoginAction;
import com.survey.struts2.action.RegAction;

public class LoginInterceptor implements Interceptor{
	private static final long serialVersionUID = 7393785378026479895L;
	public void destroy() {
		
	}
	public void init() {
		
	}
	/*
	 * ActionInvocation是中间人，可以拿到action
	 */
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction ||action instanceof RegAction){
			return arg0.invoke();
		}else{
			User user = (User)arg0.getInvocationContext().getSession().get("user");
			if(user==null){
				return "login";
			}else{
				//放行
				if(action instanceof UserAware){
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}
}
