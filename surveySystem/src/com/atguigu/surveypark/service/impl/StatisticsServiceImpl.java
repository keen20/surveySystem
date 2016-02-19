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
 * 统计服务实现
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
		
		//统计回答问题的总人数
		String qhql = "select count(*) from Answer a where a.questionId = ?" ;
		Long qcount = (Long) answerDao.uniqueResult(qhql, qid);
		qsm.setCount(qcount.intValue());
		
		String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',',a.answerIds,',') like ?" ;
		Long ocount = null ;
		
		//统计每个选项的情况
		int qt = q.getQuestionType();
		switch(qt){
			//非矩阵式问题
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
					osm.setOptionLabel("其他");
					ocount = (Long)answerDao.uniqueResult(ohql, qid,"%other%");
					osm.setCount(ocount.intValue());
					qsm.getOsms().add(osm);
				}
				break ;
			//矩阵式问题
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
