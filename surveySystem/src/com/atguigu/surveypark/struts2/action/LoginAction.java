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
 * ��½action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {

	private static final long serialVersionUID = -7312742576085870361L;

	@Resource
	private UserService userService ;
	
	@Resource
	private RightService rightService ;
	
	//����session��map
	private Map<String,Object> sessionMap ;
	
	/**
	 * �����½ҳ��
	 */
	public String toLoginPage(){
		return "loginPage" ;
	}
	
	/**
	 * ���е�½����
	 */
	public String doLogin(){
		return "success";
	}

	/**
	 * У���½��Ϣ
	 */
	public void validateDoLogin(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email/password����");
		}
		else{
			//��ʼ��Ȩ���ܺ�����
			int maxPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			//�����û�Ȩ���ܺ�
			user.calculateRightSum();
			sessionMap.put("user", user);
		}
	}

	//ע��session��map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0 ;
	}
}
