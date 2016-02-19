package com.atguigu.surveypark.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.surveypark.service.StatisticsService;

/**
 * 测试TestSurveyService
 */
public class TestStatisticsService {
	
	private static StatisticsService ss ;

	@BeforeClass
	public static void iniSurveyService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (StatisticsService) ac.getBean("statisticsService");
	}
	/**
	 * 新建调查
	 */
	@Test
	public void statistics() throws SQLException{
		ss.statistics(9);
	}
}
