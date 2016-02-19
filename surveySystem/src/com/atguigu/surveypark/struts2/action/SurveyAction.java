package com.atguigu.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.struts2.UserAware;
import com.atguigu.surveypark.util.ValidateUtil;

/**
 * SurveyAction
 */
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware{

	private static final long serialVersionUID = 2438909978838628762L;
	
	//注入SurveyService
	@Resource
	private SurveyService surveyService ;

	//调查集合
	private List<Survey> mySurveys ;

	//接受user对象
	private User user;

	//接受sid参数
	private Integer sid ;

	//动态错误页指定
	private String inputPage ;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	/**
	 * 查询我的调查列表
	 */
	public String mySurveys(){
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage" ;
	}
	
	/**
	 * 新建调查
	 */
	public String newSurvey(){
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage" ;
	}
	
	/**
	 * 设计调查
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage" ;
	}

	/**
	 * 编辑调查
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage" ;
	}
	
	/**
	 * 更新调查
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//保持关联关系
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction" ;
	}

	/**
	 * 该方法只在updateSurvey之前运行
	 */
	public void prepareUpdateSurvey(){
		inputPage = "/editSurvey.jsp" ;
	}
	
	/**
	 * delete survey
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 清除调查答案
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 切换调查状态
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 到达增加logo页面
	 */
	public String toAddLogoPage(){
		return "addLogoPage" ;
	}
	
	//上传文件
	private File logoPhoto ;
	//文件名称
	private String logoPhotoFileName ;

	//接受servletContext
	private ServletContext sc;

	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	/**
	 * 实现logo上传 
	 */
	public String doAddLogo(){
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//1.实现上传
			// /upload文件夹真实路径
			String dir = sc.getRealPath("/upload");
			//扩展名
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//纳秒时间作为文件名
			long l = System.nanoTime();
			File newFile = new File(dir,l + ext);
			//文件另存为
			logoPhoto.renameTo(newFile);
			
			//2.更新路径
			surveyService.updateLogoPhotoPath(sid,"/upload/" + l + ext);
		}
		return "designSurveyAction" ;
	}
	
	/**
	 * 该方法只在updateSurvey之前运行
	 */
	public void prepareDoAddLogo(){
		inputPage = "/addLogo.jsp" ;
	}
	
	/**
	 * 图片是否存在
	 */
	public boolean photoExists(){
		String path = model.getLogoPhotoPath();
		if(ValidateUtil.isValid(path)){
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		return false ;
	}
	
	/**
	 * 分析调查
	 */
	public String analyzeSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage" ;
	}
	
	//注入User对象
	public void setUser(User user) {
		this.user = user ;
	}

	//注入ServletContext对象
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0 ;
	}
}
