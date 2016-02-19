package com.atguigu.surveypark.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ҳ����
 */
public class Page extends BaseEntity{
	private static final long serialVersionUID = -2411740930390551507L;
	private Integer id;
	private String title = "δ����";
	private String description;
	
	//ҳ��
	private float orderno ;

	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}

	//������Page��Survey֮����һ������ϵ
	//transient:��ʱ��
	private transient Survey survey;

	//������Page��Question֮��һ�Զ������ϵ
	private Set<Question> questions = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(id != null){
			this.orderno = id ;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
