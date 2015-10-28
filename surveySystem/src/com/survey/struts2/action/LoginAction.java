package com.survey.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.survey.model.User;
import com.survey.service.UserService;
import com.survey.util.DataUtil;
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
//sessionAware是struts2的一个servletConfig拦截器，专门负责注入，可以获取session
private static final long serialVersionUID = 1L;
@Resource
private UserService userService;
//接收session的map
private Map<String,Object> sessionMap;
public String toLoginPage(){
	return "loginPage";
}
/*
 * 登陆处理
 */
public String doLogin(){
	return SUCCESS;
}
/*
 * 校验登陆信息
 */
public void validateDoLogin() {
	super.validate();
User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
if(user==null){
	addActionError("email/password错误！");
}else{
	 /* ServletActionContext.getRequest().getSession().setAttribute("user", user);
	 * 一般不采用，耦合度太高
	 */
	sessionMap.put("user", user);
}
}
//注入session的map
public void setSession(Map<String, Object> session) {
this.sessionMap = session;
}
}
