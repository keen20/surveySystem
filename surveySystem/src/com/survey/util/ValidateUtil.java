package com.survey.util;

import java.util.Collection;

/*
 * 校验工具类
 */
public class ValidateUtil {
/*
 * 验空
 */
	public static boolean isValidate(String src) {
		if (src == null || "".equals(src.trim())) {
			return false;
		}
		return true;
	}
/*
 * 判断集合是否为空
 */
	public static boolean isValidate(Collection c){
		if(c==null || c.isEmpty()){
			return false;
		}
		return true;
	}
}
