package com.atguigu.surveypark.model;

import java.io.Serializable;

import com.atguigu.surveypark.util.StringUtil;

/**
 * 问题类
 */
public class Question extends BaseEntity{
	private static final long serialVersionUID = 8327899584886022998L;
	private static final String RN = "\r\n" ;
	//
	private Integer id;
	// 题型0-8
	private int questionType;
	//
	private String title;
	// 选项
	private String options;
	private String[] optionArr ;

	// 其他项
	private boolean other;

	// 其他项样式:0-无 1-文本框 2-下拉列表
	private int otherStyle;

	// 其他项下拉选项
	private String otherSelectOptions;
	private String[] otherSelectOptionArr ;

	// 矩阵式行标题集
	private String matrixRowTitles;
	private String[] matrixRowTitleArr ;

	// 矩阵式列标题集
	private String matrixColTitles;
	private String[] matrixColTitleArr ;
	// 矩阵是下拉选项集
	private String matrixSelectOptions;
	private String[] matrixSelectOptionArr ;
	
	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}

	public String[] getMatrixColTitleArr() {
		return matrixColTitleArr;
	}

	public void setMatrixColTitleArr(String[] matrixColTitleArr) {
		this.matrixColTitleArr = matrixColTitleArr;
	}

	public String[] getMatrixSelectOptionArr() {
		return matrixSelectOptionArr;
	}

	public void setMatrixSelectOptionArr(String[] matrixSelectOptionArr) {
		this.matrixSelectOptionArr = matrixSelectOptionArr;
	}

	//建立从Question到Page之间多对一关联关系
	private Page page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	/**
	 * 重写该方法完成字符串拆分
	 */
	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtil.str2Arr(options, RN);
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr = StringUtil.str2Arr(otherSelectOptions, RN);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr = StringUtil.str2Arr(matrixRowTitles, RN);
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitleArr = StringUtil.str2Arr(matrixColTitles, RN);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionArr = StringUtil.str2Arr(matrixSelectOptions, RN);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
