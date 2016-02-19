package com.atguigu.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.datasource.SurveyparkToken;
import com.atguigu.surveypark.model.Answer;
import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.util.StringUtil;
import com.atguigu.surveypark.util.ValidateUtil;

/**
 * �������action
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware{

	private static final long serialVersionUID = 1467691158363376023L;
	//��ǰ�����key
	private static final String CURRENT_SURVEY = "current_survey";
	//���в�����map
	private static final String ALL_PARAMS_MAP = "all_params_map";
	
	private List<Survey> surveys ;
	
	@Resource
	private SurveyService surveyService ;

	//����ServletContext
	private ServletContext sc;
	
	private Integer sid ;
	
	//��ǰҳ��
	private Page currPage ;
	
	private Integer currPid ;

	public Integer getCurrPid() {
		return currPid;
	}


	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}


	//����sessionMap
	private Map<String, Object> sessionMap;
	
	//�������в�����map
	private Map<String, String[]> paramsMap;
	
	public Page getCurrPage() {
		return currPage;
	}


	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}


	public Integer getSid() {
		return sid;
	}


	public void setSid(Integer sid) {
		this.sid = sid;
	}


	public List<Survey> getSurveys() {
		return surveys;
	}


	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}


	/**
	 * ��ѯ���п��õĵ���
	 */
	public String findAllAvailableSurveys(){
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage" ;
	}
	
	/**
	 * ���ͼƬ��url��ַ
	 */
	public String getImageUrl(String path){
		if(ValidateUtil.isValid(path)){
			String absPath = sc.getRealPath(path);
			File f = new File(absPath);
			if(f.exists()){
				// /lsn_surveypark001/upload/xxx.jpg
				return sc.getContextPath() + path ;
			}
		}
		return sc.getContextPath() + "/question.bmp"  ;
	}
	
	/**
	 * �״ν���������
	 */
	public String entry(){
		//��ѯ��ҳ
		this.currPage = surveyService.getFirstPage(sid);
		//���survey--> session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		//��������в����Ĵ�map -->session��(�û�����𰸺ͻ���).
		sessionMap.put(ALL_PARAMS_MAP,new HashMap<Integer,Map<String,String[]>>());
		return "engageSurveyPage" ;
	}
	
	/**
	 * ���������� 
	 */
	public String doEngageSurvey(){
		String submitName = getSubmitName();
		//��һ��
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage" ;
		}
		//��һ��
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage" ;
		}
		//���
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			
			//��token����ǰ�߳�
			SurveyparkToken token = new SurveyparkToken();
			token.setSurvey(getCurrentSurvey());
			SurveyparkToken.bindToken(token);//������
			
			surveyService.saveAnswers(processAnswers());
			
			clearSessionData();
			return "engageSurveyAction" ;
		}
		//ȡ��
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSurveyAction" ;
		}
		return null ;
	}


	/**
	 * �����
	 */
	private List<Answer> processAnswers() {
		//����ʽ��ѡ��ť��map
		Map<Integer, String> matrixRadioMap = new HashMap<Integer,String>();
		//���д𰸵ļ���
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null ;
		String key = null ;
		String[] value = null ;
		Map<Integer, Map<String,String[]>> allMap = getAllParamsMap();
		for(Map<String,String[]> map : allMap.values()){
			for(Entry<String, String[]> entry : map.entrySet()){
				key = entry.getKey();
				value = entry.getValue();
				//��������q��ͷ�Ĳ���
				if(key.startsWith("q")){
					//�������
					if(!key.contains("other") && !key.contains("_")){
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));//answerids
						a.setQuestionId(getQid(key));//qid
						a.setSurveyId(getCurrentSurvey().getId());//sid
						a.setOtherAnswer(StringUtil.arr2Str(map.get(key + "other")));//otherAnswer
						answers.add(a);
					}
					//����ʽ��ѡ��ť
					else if(key.contains("_")){
						Integer radioQid = getMatrixRadaioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue == null){
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						}
						else{
							matrixRadioMap.put(radioQid, oldValue + "," + StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		
		//��������ʽ��ѡ��ť
		processMatrixRadioMap(matrixRadioMap,answers);
		return answers; 
	}


	/**
	 * ��������ʽ��ѡ��ť
	 */
	private void processMatrixRadioMap(Map<Integer, String> map,
			List<Answer> answers) {
		Answer a = null ;
		Integer key = null ;
		String value  = null ;
		for(Entry<Integer,String> entry : map.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);//answerids
			a.setQuestionId(key);//qid
			a.setSurveyId(getCurrentSurvey().getId());
			answers.add(a);
		}
	}


	/**
	 * ��ȡ����ʽ����id:q12_0 ---> 12
	 */
	private Integer getMatrixRadaioQid(String key) {
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}


	/**
	 * ��ȡ��ǰ�������
	 */
	private Survey getCurrentSurvey() {
		return (Survey)sessionMap.get(CURRENT_SURVEY);
	}


	/**
	 * ��ȡ����id--> q12 --> 12
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}


	/**
	 * ���session����
	 */
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}


	/**
	 * �ϲ�������session��
	 */
	private void mergeParamsIntoSession() {
		Map<Integer,Map<String,String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}


	/**
	 * ��ȡsession������в�����map
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>)sessionMap.get(ALL_PARAMS_MAP);
	}


	/**
	 * ����ύ��ť������
	 */
	private String getSubmitName() {
		for(String key : paramsMap.keySet()){
			if(key.startsWith("submit_")){
				return key ;
			}
		}
		return null;
	}


	//ע��ServletContext����
	public void setServletContext(ServletContext context) {
		this.sc = context ;
	}

	/**
	 * ע��sessionMap
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session ;
	}

	/**
	 * ע���ύ�����в�����map
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.paramsMap = parameters ;
	}
	
	/**
	 * ���ñ��,���ڴ𰸻���,��Ҫ����radio|checkbox|select��ѡ�б��
	 */
	public String setTag(String name,String value,String selTag){
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		if(StringUtil.contains(values,value)){
			return selTag;
		}
		return "" ;
	}
	
	/**
	 * ���ñ��,���ڴ𰸻���,�����ı������
	 */
	public String setText(String name){
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		return "value='"+values[0]+"'" ;
	}
}
