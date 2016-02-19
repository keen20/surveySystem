package com.atguigu.surveypark.struts2.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.statistics.OptionStatisticsModel;
import com.atguigu.surveypark.model.statistics.QuestionStatisticsModel;
import com.atguigu.surveypark.service.StatisticsService;

/**
 * MatrixStatisticsAction
 */
@Controller
@Scope("prototype")
public class MatrixStatisticsAction extends BaseAction<Question> {

	private static final long serialVersionUID = -4132717571784363709L;
	//颜色数组 rgb
	private String[] colors = {
			"#ff0000",
			"#00ff00",
			"#0000ff",
			"#ffff00",
			"#ff00ff",
			"#00ffff"
	};
	
	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	private Integer qid ;
	
	private QuestionStatisticsModel qsm ;
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public QuestionStatisticsModel getQsm() {
		return qsm;
	}

	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}

	@Resource
	private StatisticsService ss ;
	
	public String execute() throws Exception {
		//先进行统计
		this.qsm = ss.statistics(qid);
		return "" + qsm.getQuestion().getQuestionType();
	}
	
	/**
	 * 计算每个选项的统计结果
	 * 
	 */
	public String getScale(int rowIndex,int colIndex){
		//问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0 ;
		//选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsms()){
			if(osm.getMatrixRowIndex() == rowIndex
					&& osm.getMatrixColIndex() == colIndex){
				ocount = osm.getCount();
				break ;
			}
		}
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / qcount * 100;
		}
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###.00");
		return "" + ocount + "(" + df.format(scale) + "%)";
	}
	
	/**
	 * 计算每个选项的统计结果
	 * 
	 */
	public String getScale(int rowIndex,int colIndex,int optIndex){
		//问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0 ;
		//选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsms()){
			if(osm.getMatrixRowIndex() == rowIndex
					&& osm.getMatrixColIndex() == colIndex
					&& osm.getMatrixSelectIndex() == optIndex){
				ocount = osm.getCount();
				break ;
			}
		}
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / qcount * 100;
		}
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###.00");
		return "" + ocount + "(" + df.format(scale) + "%)";
	}
	
	/**
	 * 
	 * 获得百分比的整数部分,作为选项的显示长度
	 */
	public int getPercent(int rowIndex,int colIndex,int optIndex){
		//问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0 ;
		//选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsms()){
			if(osm.getMatrixRowIndex() == rowIndex
					&& osm.getMatrixColIndex() == colIndex
					&& osm.getMatrixSelectIndex() == optIndex){
				ocount = osm.getCount();
				break ;
			}
		}
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / qcount * 100;
		}
		return (int)scale ;
	}

}
