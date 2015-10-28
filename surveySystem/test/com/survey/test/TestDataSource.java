package com.survey.test;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestDataSource {
/*
 *测试数据源
 */
	@Test
	public void testDataSource() throws SQLException {
		ApplicationContext ac= new ClassPathXmlApplicationContext("beans.xml");
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}

}
