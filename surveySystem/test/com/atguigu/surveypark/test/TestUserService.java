package com.atguigu.surveypark.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;

/**
 * ≤‚ ‘UserService
 */
public class TestUserService {
	
	private static UserService us ;

	@BeforeClass
	public static void iniUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		us = (UserService) ac.getBean("userService");
	}
	/**
	 * ≤Â»Î”√ªß 
	 */
	@Test
	public void insertUser() throws SQLException{
		User u = new User();
		u.setEmail("xupccc@hotmail.com");
		u.setPassword("123456");
		u.setNickName("stone");
		us.saveEntity(u);
	}
}
