package com.atguigu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.struts2.UserAware;

/**
 * 移动/复制页面Action
 */
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = -9110302844350748208L;
	//原页面id
	private Integer srcPid;

	//目标页面id
	private Integer targPid;

	//位置:0-之前 1-之后
	private int pos;

	//目标调查id
	private Integer sid;

	public Integer getTargPid() {
		return targPid;
	}

	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<Survey> mySurveys;

	@Resource
	private SurveyService surveyService;

	// 接受User
	private User user;

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	/**
	 * 到达移动/赋值页列表页面
	 */
	public String toSelectTargetPage() {
		this.mySurveys = surveyService.getSurveyWithPages(user);
		return "moveOrCopyPageListPage";
	}

	/**
	 * 进行页面移动/复制
	 */
	public String doMoveOrCopyPage() {
		surveyService.moveOrCopyPage(srcPid,targPid,pos);
		return "designSurveyAction";
	}

	// 注入User
	public void setUser(User user) {
		this.user = user;
	}

}
