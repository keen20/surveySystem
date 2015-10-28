package com.survey.service;

import java.util.List;

import com.survey.model.Survey;
import com.survey.model.User;
/*
 * 以为servey要操作多个实体，而baseService是针对单个实体操作的，因此就不用击继承了
 */
public interface SurveyService{
/*
 * 查询调查列表
 */
	List<Survey> findMyService(User user);

	Survey newSurvey(User user);
/*
 * 按照id查询调查
 */
	Survey getSurvey(Integer sid);

	Survey getSurveyWithChildren(Integer sid);


}
