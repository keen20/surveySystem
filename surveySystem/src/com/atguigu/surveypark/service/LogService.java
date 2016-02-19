package com.atguigu.surveypark.service;

import java.util.List;

import com.atguigu.surveypark.model.Log;

/**
 * LogService
 */
public interface LogService extends BaseService<Log> {

	/**
	 * ͨ������������־��
	 */
	public void createLogTable(String tableName);

	/**
	 * ��ѯ���ָ���·�������־
	 */
	public List<Log> findNearestLogs(int i);
	
}
