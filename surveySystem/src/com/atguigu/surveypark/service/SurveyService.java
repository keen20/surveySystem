package com.atguigu.surveypark.service;

import java.util.List;

import com.atguigu.surveypark.model.Answer;
import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;

/**
 * SurveyService
 */
public interface SurveyService {

	/**
	 * 查询调查列表
	 */
	public List<Survey> findMySurveys(User user);

	/**
	 * 新建调查
	 */
	public Survey newSurvey(User user);

	/**
	 * 按照id查询Survey
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * 按照id查询Survey,同时携带所有的孩子
	 */
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * 更新调查
	 */
	public void updateSurvey(Survey model);

	/**
	 * 保存/更新页面
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * 按照id查询页面
	 */
	public Page getPage(Integer pid);

	/**
	 * 保存/更新问题
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除问题,同时删除答案
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 删除page,同时删除问题,答案
	 */
	public void deletePage(Integer pid);

	/**
	 * 删除survey,同时删除page,问题,答案
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * 编辑问题
	 */
	public Question getQuestion(Integer qid);

	/**
	 * 清除调查
	 */
	public void clearAnswers(Integer sid);

	/**
	 * 切换调查状态
	 */
	public void toggleStatus(Integer sid);

	/**
	 * 更新logoPhoto路径
	 */
	public void updateLogoPhotoPath(Integer sid, String string);

	/**
	 * 查询调查集合,携带pages
	 */
	public List<Survey> getSurveyWithPages(User user);

	/**
	 * 
	 * 进行页面移动/复制
	 */
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);

	/**
	 * 查询所有可用的调查
	 */
	public List<Survey> findAllAvailableSurveys();

	/**
	 * 查询调查首页
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * 获得上一页
	 */
	public Page getPrePage(Integer currPid);
	
	/**
	 * 获得下一页
	 */
	public Page getNextPage(Integer currPid);

	/**
	 * 批量保存答案
	 */
	public void saveAnswers(List<Answer> processAnswers);

	/**
	 * 查询指定调查的所有问题
	 */
	public List<Question> getQuestions(Integer sid);

	
	/**
	 * 查询指定调查的所有答案
	 */
	public List<Answer> getAnswers(Integer sid);

}
