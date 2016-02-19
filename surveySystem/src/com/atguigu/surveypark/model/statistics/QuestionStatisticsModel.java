package com.atguigu.surveypark.model.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.surveypark.model.Question;

/**
 * 问题统计模型
 */
public class QuestionStatisticsModel implements Serializable {
	private static final long serialVersionUID = -2442560103597422985L;
	private Question question;//
	private int count;// 回答该问题的人数

	private List<OptionStatisticsModel> osms = new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}

}
