package com.atguigu.surveypark.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;

/**
 * ����TestSurveyService
 */
public class TestSurveyService {
	
	private static SurveyService ss ;

	@BeforeClass
	public static void iniSurveyService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (SurveyService) ac.getBean("surveyService");
	}
	/**
	 * �½�����
	 */
	@Test
	public void newSurvey() throws SQLException{
		User u = new User();
		u.setId(3);
		ss.newSurvey(u);
	}
	/**
	 * ��ѯ����
	 */
	@Test
	public void getSurvey() throws SQLException{
		ss.getSurvey(1);
	}
	
	/**
	 * �л�״̬
	 */
	@Test
	public void toggleStatus() throws SQLException{
		ss.toggleStatus(1);
	}
}
