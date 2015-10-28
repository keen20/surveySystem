package com.survey.test;
import java.sql.SQLException;

import javax.faces.application.Application;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.survey.model.User;
import com.survey.service.UserService;
public class TestTranction {
/*
 * 测试事务
 */
	private static UserService us;
	@BeforeClass
	public static void initUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		us = (UserService) ac.getBean("userService");
	}
	@Test
	public void testInsertUser() throws SQLException {
		User user = new User();
		user.setEmail("1104656459@qq.com");
		user.setName("王昆");
		user.setPassword("123456");
		user.setNickName("老王");
		us.saveEntity(user);
	}

}
