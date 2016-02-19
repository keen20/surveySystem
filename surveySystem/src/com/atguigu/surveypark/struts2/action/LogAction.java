package com.atguigu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.Log;
import com.atguigu.surveypark.service.LogService;

/**
 * LogAction
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 2714227687760768212L;
	
	private List<Log> logs ;
	
	//Ĭ�ϲ�ѯ��־���·���
	private int monthNum = 2 ;
	
	public int getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}

	@Resource
	private LogService logService ;
	
	
	public List<Log> getLogs() {
		return logs;
	}



	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}



	/**
	 * ��ѯȫ����־
	 */
	public String findAllLogs(){
		this.logs = logService.findAllEntities();
		return "logListPage" ;
	}
	
	/**
	 * ��ѯ�������־ 
	 */
	public String findNearestLogs(){
		this.logs = logService.findNearestLogs(monthNum);
		return "logListPage" ;
	}
}
