package com.atguigu.surveypark.datasource;

import com.atguigu.surveypark.model.Survey;

/**
 * 令牌
 */
public class SurveyparkToken {
	//
	private static 	ThreadLocal<SurveyparkToken> l = new ThreadLocal<SurveyparkToken>();
	
	private Survey survey ;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	/**
	 * 将指定的令牌对象绑定到当前线程
	 */
	public static void bindToken(SurveyparkToken token){
		l.set(token);
	}
	
	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unbindToken(){
		l.remove();
	}
	
	/**
	 * 从当前线程获得绑定的令牌
	 */
	public static SurveyparkToken getCurrentToken(){
		return l.get();
	}
}
