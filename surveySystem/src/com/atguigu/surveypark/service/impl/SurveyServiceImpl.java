package com.atguigu.surveypark.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.Answer;
import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.util.DataUtil;

/**
 * SurveyServiceʵ��
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao ;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao ;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao ;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao ;
	
	/**
	 * ��ѯ���鼯��
	 */
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?" ;
		return surveyDao.findEntityByHQL(hql,user.getId());
	}
	
	/**
	 * �½�����
	 */
	public Survey newSurvey(User user){
		Survey s = new Survey();
		Page p = new Page();
		
		//���ù�����ϵ
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s ;
	}
	
	/**
	 * ����id��ѯSurvey
	 */
	public Survey getSurvey(Integer sid){
		return surveyDao.getEntity(sid);
	}
	
	/**
	 * ����id��ѯSurvey,ͬʱЯ�����еĺ���
	 */
	public Survey getSurveyWithChildren(Integer sid){
		Survey s = this.getSurvey(sid);
		//ǿ�г�ʼ��pages��questions����
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s; 
	}
	
	/**
	 * ���µ���
	 */
	public void updateSurvey(Survey model){
		surveyDao.updateEntity(model);
	}
	
	/**
	 * ����/����ҳ��
	 */
	public void saveOrUpdatePage(Page model){
		pageDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * ����id��ѯҳ��
	 */
	public Page getPage(Integer pid){
		return pageDao.getEntity(pid);
	}
	
	/**
	 * ����/��������
	 */
	public void saveOrUpdateQuestion(Question model){
		questionDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * ɾ������,ͬʱɾ����
	 */
	public void deleteQuestion(Integer qid){
		//1.ɾ��answers
		String hql = "delete from Answer a where a.questionId = ?" ;
		answerDao.batchEntityByHQL(hql,qid);
		//2.delete question
		hql = "delete from Question q where q.id = ?" ;
		questionDao.batchEntityByHQL(hql,qid);
	}
	
	/**
	 * ɾ��page,ͬʱɾ������,��
	 */
	public void deletePage(Integer pid){
		Page p = this.getPage(pid);
		Survey s = p.getSurvey();
		//ÿ������������һ��ҳ��
		String hql = "select count(*) from Page p where p.survey.id = ?" ;
		Long count = (Long) pageDao.uniqueResult(hql, s.getId());
		
		//delete answer
		hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)" ;
		answerDao.batchEntityByHQL(hql,pid);
		//delete questions
		hql = "delete from Question q where q.page.id = ?" ;
		questionDao.batchEntityByHQL(hql,pid);
		//delete page
		hql = "delete from Page p where p.id = ?" ;
		pageDao.batchEntityByHQL(hql,pid);
		
		//�ж���������һҳ,ɾ�����ٲ����µ�һҳ.
		if(count == 1){
			p = new Page();
			p.setSurvey(s);
			//s.getPages().add(p);
			pageDao.saveEntity(p);
		}
	}
	

	/**
	 * ɾ��survey,ͬʱɾ��page,����,��
	 */
	public void deleteSurvey(Integer sid){
		//delete answers
		String hql = "delete from Answer a where a.surveyId = ?" ;
		answerDao.batchEntityByHQL(hql,sid);
		
		//hibernate��д������,�������������ϵ�����.
		//hql = "delete from Question q where q.page.survey.id = ?" ;
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)" ;
		questionDao.batchEntityByHQL(hql, sid);
		
		//delete page
		hql = "delete from Page p where p.survey.id = ? " ;
		pageDao.batchEntityByHQL(hql, sid);
		
		//delete survey
		hql = "delete from Survey s where s.id = ?" ;
		surveyDao.batchEntityByHQL(hql, sid);
	}
	
	/**
	 * �༭����
	 */
	public Question getQuestion(Integer qid){
		return questionDao.getEntity(qid);
	}
	
	/**
	 * �������
	 */
	public void clearAnswers(Integer sid){
		String hql = "delete from Answer a where a.surveyId = ?" ;
		answerDao.batchEntityByHQL(hql,sid);
	}
	
	/**
	 * �л�����״̬
	 */
	public void toggleStatus(Integer sid){
		Survey s = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?" ;
		surveyDao.batchEntityByHQL(hql,!s.isClosed(),sid);
	}
	
	/**
	 * ����logoPhoto·��
	 */
	public void updateLogoPhotoPath(Integer sid, String path){
		String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?" ;
		surveyDao.batchEntityByHQL(hql, path,sid);
	}
	
	/**
	 * ��ѯ���鼯��,Я��pages
	 */
	public List<Survey> getSurveyWithPages(User user){
		String hql = "from Survey s where s.user.id = ?" ;
		List<Survey> list = surveyDao.findEntityByHQL(hql,user.getId());
		for(Survey s : list){
			s.getPages().size();
		}
		return list ;
	}
	

	/**
	 * 
	 * ����ҳ���ƶ�/����
	 */
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos){
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targPage = this.getPage(targPid);
		Survey targSurvey = targPage.getSurvey();
		//�ж��ƶ�/��ֵ
		if(srcSurvey.getId().equals(targSurvey.getId())){
			setOrderno(srcPage,targPage,pos);
		}
		//����
		else{
			//ǿ�г�ʼ�����⼯��,������ȸ��Ƶ�ҳ�����û�����⼯��
			srcPage.getQuestions().size();
			//��ȸ���
			Page copyPage = (Page) DataUtil.deeplyCopy(srcPage);
			//����ҳ���Ŀ��������
			copyPage.setSurvey(targSurvey);
			//����ҳ��
			pageDao.saveEntity(copyPage);
			for(Question q : copyPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			setOrderno(copyPage,targPage,pos);
		}
				
	}

	/**
	 * ����ҳ��
	 */
	private void setOrderno(Page srcPage, Page targPage, int pos) {
		//֮ǰ
		if(pos == 0){
			//�ж�Ŀ��ҳ�Ƿ�����ҳ
			if(isFirstPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			}
			else{
				//ȡ��Ŀ��ҳ��ǰһҳ
				Page prePage = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + prePage.getOrderno()) / 2);
			}
		}
		//֮��
		else{
			//�ж�Ŀ��ҳ�Ƿ���βҳ
			if(isLastPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			}
			else{
				//ȡ��Ŀ��ҳ����һҳ
				Page nextPage = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + nextPage.getOrderno()) / 2);
			}
		}
	}

	/**
	 * ��ѯָ��ҳ�����һҳ
	 */
	private Page getNextPage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}

	/**
	 * �ж�ָ��ҳ���Ƿ������ڵ���βҳ
	 */
	private boolean isLastPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ? " ;
		Long count = (Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count == 0;
	}

	/**
	 * ��ѯָ��ҳ�����һҳ
	 */
	private Page getPrePage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}

	/**
	 * �ж�ָ��ҳ���Ƿ������ڵ�����ҳ
	 */
	private boolean isFirstPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ? " ;
		Long count = (Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count == 0;
	}
	
	/**
	 * ��ѯ���п��õĵ���
	 */
	public List<Survey> findAllAvailableSurveys(){
		String hql = "from Survey s where s.closed = ?" ;
		return surveyDao.findEntityByHQL(hql,false);
	}
	
	/**
	 * ��ѯ������ҳ
	 */
	public Page getFirstPage(Integer sid){
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql,sid);
		Page p = list.get(0);
		p.getQuestions().size();
		p.getSurvey().getTitle();
		return p ;
	}
	
	/**
	 * �����һҳ
	 */
	public Page getPrePage(Integer currPid){
		Page p = this.getPage(currPid);
		p = this.getPrePage(p);
		p.getQuestions().size();
		return p ;
	}
	/**
	 * �����һҳ
	 */
	public Page getNextPage(Integer currPid){
		Page p = this.getPage(currPid);
		p = this.getNextPage(p);
		p.getQuestions().size();
		return p ;
	}
	
	/**
	 * ���������
	 */
	public void saveAnswers(List<Answer> list){
		Date date = new Date();
		String uuid = UUID.randomUUID().toString();
		for(Answer a : list){
			a.setUuid(uuid);
			a.setAnswerTime(date);
			answerDao.saveEntity(a);
		}
	}
	
	/**
	 * ��ѯָ���������������
	 */
	public List<Question> getQuestions(Integer sid){
		String hql = "from Question q where q.page.survey.id = ?" ;
		return questionDao.findEntityByHQL(hql, sid);
	}
	
	/**
	 * ��ѯָ����������д�
	 */
	public List<Answer> getAnswers(Integer sid){
		String hql = "from Answer a where a.surveyId = ? order by a.uuid asc" ;
		return answerDao.findEntityByHQL(hql,sid);
	}
}
