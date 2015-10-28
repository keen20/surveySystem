package com.survey.model;
/*
 * 问题类
 */
public class Question {
private Integer id;
private int questionType;//题型0-8
private String title;//题干
private String options;//选项
private String[] optionArr;//选项拆分
private boolean other;//是否有其他项
private int otherStyle;//其他项样式：0无，1文本框，2下拉列表
private String otherSelectOptions;//其他项下拉选项
private String[] otherSelectOptionArr;
private String matrixRowTitle;//矩阵式行标题
private String[] matrixRowTitleArr;
private String matrixColumnTitle;//矩阵式列表题
private String[] matrixColumnTitleArr;
private String matrixSelectOption;//矩阵式下拉选项
private String[] matrixSelectOptionArr;
//建立从page到question的多对多

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

public void setOptions(String options) {
	this.options = options;
}

public String[] getOptionArr() {
	return optionArr;
}

public void setOptionArr(String[] optionArr) {
	this.optionArr = optionArr;
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
}

public String[] getOtherSelectOptionArr() {
	return otherSelectOptionArr;
}

public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
	this.otherSelectOptionArr = otherSelectOptionArr;
}

public String getMatrixRowTitle() {
	return matrixRowTitle;
}

public void setMatrixRowTitle(String matrixRowTitle) {
	this.matrixRowTitle = matrixRowTitle;
}

public String[] getMatrixRowTitleArr() {
	return matrixRowTitleArr;
}

public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
	this.matrixRowTitleArr = matrixRowTitleArr;
}

public String getMatrixColumnTitle() {
	return matrixColumnTitle;
}

public void setMatrixColumnTitle(String matrixColumnTitle) {
	this.matrixColumnTitle = matrixColumnTitle;
}

public String[] getMatrixColumnTitleArr() {
	return matrixColumnTitleArr;
}

public void setMatrixColumnTitleArr(String[] matrixColumnTitleArr) {
	this.matrixColumnTitleArr = matrixColumnTitleArr;
}

public String getMatrixSelectOption() {
	return matrixSelectOption;
}

public void setMatrixSelectOption(String matrixSelectOption) {
	this.matrixSelectOption = matrixSelectOption;
}

public String[] getMatrixSelectOptionArr() {
	return matrixSelectOptionArr;
}

public void setMatrixSelectOptionArr(String[] matrixSelectOptionArr) {
	this.matrixSelectOptionArr = matrixSelectOptionArr;
}

public Page getPage() {
	return page;
}

public void setPage(Page page) {
	this.page = page;
}

}
