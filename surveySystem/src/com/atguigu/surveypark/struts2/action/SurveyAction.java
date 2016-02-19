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
	
	//ע��SurveyService
	@Resource
	private SurveyService surveyService ;

	//���鼯��
	private List<Survey> mySurveys ;

	//����user����
	private User user;

	//����sid����
	private Integer sid ;

	//��̬����ҳָ��
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
	 * ��ѯ�ҵĵ����б�
	 */
	public String mySurveys(){
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage" ;
	}
	
	/**
	 * �½�����
	 */
	public String newSurvey(){
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage" ;
	}
	
	/**
	 * ��Ƶ���
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage" ;
	}

	/**
	 * �༭����
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage" ;
	}
	
	/**
	 * ���µ���
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//���ֹ�����ϵ
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction" ;
	}

	/**
	 * �÷���ֻ��updateSurvey֮ǰ����
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
	 * ��������
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * �л�����״̬
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * ��������logoҳ��
	 */
	public String toAddLogoPage(){
		return "addLogoPage" ;
	}
	
	//�ϴ��ļ�
	private File logoPhoto ;
	//�ļ�����
	private String logoPhotoFileName ;

	//����servletContext
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
	 * ʵ��logo�ϴ� 
	 */
	public String doAddLogo(){
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//1.ʵ���ϴ�
			// /upload�ļ�����ʵ·��
			String dir = sc.getRealPath("/upload");
			//��չ��
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//����ʱ����Ϊ�ļ���
			long l = System.nanoTime();
			File newFile = new File(dir,l + ext);
			//�ļ����Ϊ
			logoPhoto.renameTo(newFile);
			
			//2.����·��
			surveyService.updateLogoPhotoPath(sid,"/upload/" + l + ext);
		}
		return "designSurveyAction" ;
	}
	
	/**
	 * �÷���ֻ��updateSurvey֮ǰ����
	 */
	public void prepareDoAddLogo(){
		inputPage = "/addLogo.jsp" ;
	}
	
	/**
	 * ͼƬ�Ƿ����
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
	 * ��������
	 */
	public String analyzeSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage" ;
	}
	
	//ע��User����
	public void setUser(User user) {
		this.user = user ;
	}

	//ע��ServletContext����
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0 ;
	}
}
