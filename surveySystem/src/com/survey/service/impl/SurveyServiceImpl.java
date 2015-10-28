package com.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.survey.dao.BaseDao;
import com.survey.model.Page;
import com.survey.model.Survey;
import com.survey.model.User;
import com.survey.service.SurveyService;
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	public List<Survey> findMyService(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	public Survey newSurvey(User user) {
		Survey survey = new Survey();
		Page page = new Page();
		survey.setUser(user);
		survey.getPages().add(page);
		page.setSurvey(survey);
		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);
		return survey;
	}

	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}
/*
 * 查询Survey同时携带所有孩子，解决懒加载问题
 */
	public Survey getSurveyWithChildren(Integer sid) {
		Survey s = this.getSurvey(sid);
		//强行将其所关联的都初始化，不再是代理
		for(Page p: s.getPages()){
			p.getQuestions().size();
		}
		return s;
	}

}
