package com.atguigu.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.Answer;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.statistics.OptionStatisticsModel;
import com.atguigu.surveypark.model.statistics.QuestionStatisticsModel;
import com.atguigu.surveypark.service.StatisticsService;

/**
 * ͳ�Ʒ���ʵ��
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao ;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao ;

	public QuestionStatisticsModel statistics(Integer qid) {
		Question q = questionDao.getEntity(qid);
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		qsm.setQuestion(q);
		
		//ͳ�ƻش������������
		String qhql = "select count(*) from Answer a where a.questionId = ?" ;
		Long qcount = (Long) answerDao.uniqueResult(qhql, qid);
		qsm.setCount(qcount.intValue());
		
		String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',',a.answerIds,',') like ?" ;
		Long ocount = null ;
		
		//ͳ��ÿ��ѡ������
		int qt = q.getQuestionType();
		switch(qt){
			//�Ǿ���ʽ����
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				String[] arr = q.getOptionArr();
				OptionStatisticsModel osm = null ;
				for(int i = 0 ; i < arr.length ; i ++){
					osm = new OptionStatisticsModel();
					osm.setOptionLabel(arr[i]);
					osm.setOptionIndex(i);
					ocount = (Long)answerDao.uniqueResult(ohql, qid,"%,"+i+",%");
					osm.setCount(ocount.intValue());
					qsm.getOsms().add(osm);
				}
				//other
				if(q.isOther()){
					osm = new OptionStatisticsModel();
					osm.setOptionLabel("����");
					ocount = (Long)answerDao.uniqueResult(ohql, qid,"%other%");
					osm.setCount(ocount.intValue());
					qsm.getOsms().add(osm);
				}
				break ;
			//����ʽ����
			case 6:
			case 7:
			case 8:
				String[] rows = q.getMatrixRowTitleArr();
				String[] cols = q.getMatrixColTitleArr();
				String[] opts = q.getMatrixSelectOptionArr();
				
				for(int i = 0 ; i < rows.length ; i ++){
					for(int j = 0 ; j < cols.length ; j ++){
						//matrix radio | checkbox
						if(qt != 8){
							osm = new OptionStatisticsModel();
							osm.setMatrixRowIndex(i);
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixColIndex(j);
							osm.setMatrixColLabel(cols[j]);
							ocount = (Long)answerDao.uniqueResult(ohql, qid,"%,"+i + "_" + j + ",%");
							osm.setCount(ocount.intValue());
							qsm.getOsms().add(osm);
						}
						//matrix select
						else{
							for(int k = 0 ; k < opts.length ; k ++){
								osm = new OptionStatisticsModel();
								osm.setMatrixRowIndex(i);
								osm.setMatrixRowLabel(rows[i]);
								osm.setMatrixColIndex(j);
								osm.setMatrixColLabel(cols[j]);
								ocount = (Long)answerDao.uniqueResult(ohql, qid,"%,"+i + "_" + j + "_" + k + ",%");
								osm.setCount(ocount.intValue());
								qsm.getOsms().add(osm);
							}
						}
					}
				}
				break ;
		}
		return qsm;
	}

}
