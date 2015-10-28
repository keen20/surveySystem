package com.survey.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.survey.model.User;
import com.survey.service.UserService;
import com.survey.util.DataUtil;
import com.survey.util.ValidateUtil;
/*
 * 注册action
 */
/* spring管理action默认的管理是单例的，应声明为原型
 * action是spring创建的如果不加名称默认类名第一个字母小写
 */
@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;
	//模型驱动器上来就应该初始化，因为struts2会判断他是否为空，不为空才会压入站点
	private String confirmPassword;
	@Resource(name="userService")
	private UserService userSerivce;
	//必须与struts控件上的名字一致，才可以得到
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	//到达注册页面,跳过校验
	@SkipValidation
	public String toRegPage(){
		return "regPage";
	}
	
	//进行用户注册
	public String doReg(){
		//密码加密
		model.setPassword(DataUtil.md5(model.getPassword()));
		//保存数据
		userSerivce.saveEntity(model);
		return SUCCESS;
	}
	
	//信息校验
	@Override
	public void validate() {
		super.validate();
		//判断是否非空，    model.getEmail().trim()不可行会nullPoint
		if(!ValidateUtil.isValidate(model.getEmail())){
			addFieldError("email", "email是必填项！");
			return;
		}
		if(!ValidateUtil.isValidate(model.getPassword())){
			addFieldError("password", "密码是必填项！");
			return;
		}
		if(!ValidateUtil.isValidate(model.getNickName())){
			addFieldError("nickName", "用户昵称是必填项！");
			return;
		}
		
		/*if(hasErrors()){
			return;
		}*/
		//密码一致性
		if(!model.getPassword().equals(confirmPassword)){
			addFieldError("password", "两次输入密码不一致！");
			return ;
		}
		//email占用
		if(userSerivce.isRegisted(model.getEmail())){
			addFieldError("email", "email已占用！");
		}
		
	}
}
