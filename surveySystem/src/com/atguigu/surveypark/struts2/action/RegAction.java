package com.atguigu.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.DataUtil;
import com.atguigu.surveypark.util.ValidateUtil;

/**
 * 注册action
 */
@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {

	private static final long serialVersionUID = 7351588309970506225L;
	
	private String confirmPassword ;
	
	//注入userService
	@Resource
	private UserService userService ;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * 到达注册页面
	 */
	@SkipValidation
	public String toRegPage(){
		return "regPage" ;
	}
	
	/**
	 * 进行用户注册
	 */
	public String doReg(){
		//密码加密
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS ;
	}

	/**
	 * 校验
	 */
	public void validate() {
		//1.非空
		if(!ValidateUtil.isValid(model.getEmail())){
			addFieldError("email", "email是必填项!");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "password是必填项!");
		}
		if(!ValidateUtil.isValid(model.getNickName())){
			addFieldError("nickName", "nickName是必填项!");
		}
		if(hasErrors()){
			return ;
		}
		//2.密码一致性
		if(!model.getPassword().equals(confirmPassword)){
			addFieldError("password", "密码不一致!");
			return  ;
		}
		//3.email占用
		if(userService.isRegisted(model.getEmail())){
			addFieldError("email", "email已占用!");
		}
	}
	
	
}