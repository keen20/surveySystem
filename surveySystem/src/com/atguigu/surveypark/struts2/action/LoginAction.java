package com.atguigu.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.RightService;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.DataUtil;

/**
 * 登陆action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {

	private static final long serialVersionUID = -7312742576085870361L;

	@Resource
	private UserService userService ;
	
	@Resource
	private RightService rightService ;
	
	//接受session的map
	private Map<String,Object> sessionMap ;
	
	/**
	 * 到达登陆页面
	 */
	public String toLoginPage(){
		return "loginPage" ;
	}
	
	/**
	 * 进行登陆处理
	 */
	public String doLogin(){
		return "success";
	}

	/**
	 * 校验登陆信息
	 */
	public void validateDoLogin(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email/password错误");
		}
		else{
			//初始化权限总和数组
			int maxPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			//计算用户权限总和
			user.calculateRightSum();
			sessionMap.put("user", user);
		}
	}

	//注入session的map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0 ;
	}
}
