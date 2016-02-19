package com.atguigu.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.Log;
import com.atguigu.surveypark.service.LogService;
import com.atguigu.surveypark.util.LogUtil;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements
		LogService {
	
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}
	
	/**
	 * 通过表明创建日志表
	 */
	public void createLogTable(String tableName){
		String sql = "create table if not exists " +tableName + " like logs" ;
		this.executeSQL(sql);
	}

	/**
	 * 重写该方法,动态插入日志记录(动态表) 
	 */
	public void saveEntity(Log t) {
		//insert into logs_2013_9()
		String sql = "insert into " 
				+ LogUtil.generateLogTableName(0) 
				+ "(id,operator,opername,operparams,operresult,resultmsg,opertime) "
				+ "values(?,?,?,?,?,?,?)" ;
		UUIDHexGenerator uuid = new UUIDHexGenerator();
		String id = (String) uuid.generate(null, null);
		this.executeSQL(sql, id,
							t.getOperator(),
							t.getOperName(),
							t.getOperParams(),
							t.getOperResult(),
							t.getResultMsg(),
							t.getOperTime());
	}
	
	/**
	 * 查询最近指定月份数的日志
	 */
	public List<Log> findNearestLogs(int n){
		String tableName = LogUtil.generateLogTableName(0);
		//
		//查询出最近的日志表名称
		String sql = "select table_name from information_schema.tables "
				+ "where table_schema='lsn_surveypark001' "
				+ "and table_name like 'logs_%' "
				+ "and table_name <= '"+tableName+"' "
				+ "order by table_name desc limit 0," + n ;
		
		List list = this.executeSQLQuery(null,sql);
		//查询最近若干月内的日志
		String logSql = "" ;
		String logName = null ;
		for(int i = 0 ; i < list.size() ; i ++){
			logName = (String) list.get(i);
			if(i == (list.size() - 1)){
				logSql = logSql + " select * from " + logName ;
			}
			else{
				logSql = logSql + " select * from " + logName + " union " ;
			}
		}
		//指定Log实体类
		return this.executeSQLQuery(Log.class,logSql);
	}
}
