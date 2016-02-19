package com.atguigu.surveypark.service;

import com.atguigu.surveypark.model.statistics.QuestionStatisticsModel;

/**
 * 统计服务
 */
public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qid);
}
