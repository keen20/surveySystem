package com.survey.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.survey.model.User;
import com.survey.service.SurveyService;

public class TestSurveyService {

	private static SurveyService us;
	@BeforeClass
	public static void initUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		us = (SurveyService) ac.getBean("surveyService");
	}
	@Test
	public void testNewSurvey() {
		User u = new User();
		u.setId(3);
		us.newSurvey(u);
	}

}
