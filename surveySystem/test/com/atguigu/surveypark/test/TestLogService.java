package com.atguigu.surveypark.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.surveypark.service.LogService;

/**
 * ≤‚ ‘UserService
 */
public class TestLogService {
	
	private static LogService ls ;

	@BeforeClass
	public static void iniUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ls = (LogService) ac.getBean("logService");
	}
	/**
	 * ≤Â»Î”√ªß 
	 */
	@Test
	public void test0() throws SQLException{
		ls.findNearestLogs(3);
	}
}
