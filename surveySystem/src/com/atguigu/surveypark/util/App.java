package com.atguigu.surveypark.util;


public class App {

	public static void main(String[] args) throws Exception {
		System.out.println(LogUtil.generateLogTableName(0));
		System.out.println(LogUtil.generateLogTableName(1));
		System.out.println(LogUtil.generateLogTableName(2));
		System.out.println(LogUtil.generateLogTableName(3));
		System.out.println(LogUtil.generateLogTableName(-1));
		System.out.println(LogUtil.generateLogTableName(-2));
	}
}
