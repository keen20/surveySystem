package com.survey.struts2.action;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.survey.model.Survey;
import com.survey.model.User;
import com.survey.service.SurveyService;
import com.survey.struts2.UserAware;
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {
	private static final long serialVersionUID = 1L;
	@Resource
	private SurveyService surveyService;
	//调查集合，必须有get，set方法，因为struts是通过get方法获取
	private List<Survey> mySurveys;
	//接收sessionMap
	//private Map<String, Object> sessionMap;
	private User user;
	//接收页面传过来的属性，必须有getset方法，不然会出现id to load is required for loading错误
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public List<Survey> getMySurveys() {
		return mySurveys;
	}
	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	//我的调查
	public String mySurveys(){
		//User user = (User)sessionMap.get("user");
		this.mySurveys = surveyService.findMyService(user);
		return "mySurveyListPage";
	}
	//新建调查
	public String newSurvey(){
		//User user = (User)sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	//设计调查
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	/*prepare在modelDriven过滤器之前
	 * <!-- 定义拦截器栈 -->
		<interceptor-stack name="interceptorStack">
		<interceptor-ref name="LoginInterceptor"></interceptor-ref>
		<interceptor-ref name="paramsPrepareParamsStack"></interceptor-ref>
		</interceptor-stack>
		</interceptors>
	在designSurvey方法前执行 执行顺序：preparedesignSurvey-》getModel-》designSurvey
	public void prepareDesignSurvey(){
	this.model = surveyService.getSurvey(sid);
	}*/
	/*public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}*/
	public void setUser(User user) {
		this.user = user;
	}

}
