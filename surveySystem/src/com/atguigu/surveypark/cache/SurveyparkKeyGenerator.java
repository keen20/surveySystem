package com.atguigu.surveypark.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.atguigu.surveypark.util.StringUtil;

/**
 * 自定义缓存key生成器
 */
public class SurveyparkKeyGenerator implements KeyGenerator{

	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String className = arg0.getClass().getSimpleName();
		String mname = arg1.getName();
		String params = StringUtil.arr2Str(arg2);
		String key = className + "@" + arg0.hashCode() + "." + mname + "("+params+")" ;
		System.out.println(key);
		return key;
	}

}
