package com.atguigu.surveypark.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.atguigu.surveypark.model.Survey;

/**
 * �Զ�������Դ·����(�ֲ�ʽ���ݿ�)
 */
public class SurveyparkDataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * ��⵱ǰkey
	 */
	protected Object determineCurrentLookupKey() {
		//�õ���ǰ�̰߳󶨵�����
		SurveyparkToken token = SurveyparkToken.getCurrentToken();
		if(token != null){
			Integer id = token.getSurvey().getId();
			//������Ƶİ�
			SurveyparkToken.unbindToken();
			return ((id % 2) == 0) ? "even" : "odd" ;
		}
		return null;
	}
}
