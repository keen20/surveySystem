package com.atguigu.surveypark.util;

/**
 * �ַ���������
 */
public class StringUtil {
	/**
	 * ���ַ���ת��������,����tag�ָ�
	 */
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
		}
		return null ;
	}

	/**
	 * �ж���values�������Ƿ���ָ��value�ַ���
	 */
	public static boolean contains(String[] values, String value) {
		if(ValidateUtil.isValid(values)){
			for(String s : values){
				if(s.equals(value)){
					return true ;
				}
			}
		}
		return false;
	}

	/**
	 * ������任���ַ���,ʹ��","�ŷָ�
	 */
	public static String arr2Str(Object[] arr) {
		String temp = "" ;
		if(ValidateUtil.isValid(arr)){
			for(Object s : arr){
				temp = temp + s + "," ;
			}
			return temp.substring(0,temp.length() - 1);
		}
		return temp;
	}
	
	//����ַ�����������Ϣ
	public static String getDescString(String str){
		if(str != null && str.trim().length() > 30){
			return str.substring(0,30);
		}
		return str ;
	}
}
