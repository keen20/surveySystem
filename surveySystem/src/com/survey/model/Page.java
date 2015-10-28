package com.survey.model;

import java.util.HashSet;
import java.util.Set;

/*
 * 页面类
 */
public class Page {
private Integer id;
private String title = "未命名";
private String description;
//从页面到调查多对一
private Survey survey;
//从页面到问题一对多
private Set<Question> questions = new HashSet<Question>();
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
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
@Override
public String toString() {
	return "Page [id=" + id + ", title=" + title + ", description="
			+ description + "]";
}

}
