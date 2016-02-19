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
	 * ��ѯ�����б�
	 */
	public List<Survey> findMySurveys(User user);

	/**
	 * �½�����
	 */
	public Survey newSurvey(User user);

	/**
	 * ����id��ѯSurvey
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * ����id��ѯSurvey,ͬʱЯ�����еĺ���
	 */
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * ���µ���
	 */
	public void updateSurvey(Survey model);

	/**
	 * ����/����ҳ��
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * ����id��ѯҳ��
	 */
	public Page getPage(Integer pid);

	/**
	 * ����/��������
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * ɾ������,ͬʱɾ����
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * ɾ��page,ͬʱɾ������,��
	 */
	public void deletePage(Integer pid);

	/**
	 * ɾ��survey,ͬʱɾ��page,����,��
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * �༭����
	 */
	public Question getQuestion(Integer qid);

	/**
	 * �������
	 */
	public void clearAnswers(Integer sid);

	/**
	 * �л�����״̬
	 */
	public void toggleStatus(Integer sid);

	/**
	 * ����logoPhoto·��
	 */
	public void updateLogoPhotoPath(Integer sid, String string);

	/**
	 * ��ѯ���鼯��,Я��pages
	 */
	public List<Survey> getSurveyWithPages(User user);

	/**
	 * 
	 * ����ҳ���ƶ�/����
	 */
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);

	/**
	 * ��ѯ���п��õĵ���
	 */
	public List<Survey> findAllAvailableSurveys();

	/**
	 * ��ѯ������ҳ
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * �����һҳ
	 */
	public Page getPrePage(Integer currPid);
	
	/**
	 * �����һҳ
	 */
	public Page getNextPage(Integer currPid);

	/**
	 * ���������
	 */
	public void saveAnswers(List<Answer> processAnswers);

	/**
	 * ��ѯָ���������������
	 */
	public List<Question> getQuestions(Integer sid);

	
	/**
	 * ��ѯָ����������д�
	 */
	public List<Answer> getAnswers(Integer sid);

}
