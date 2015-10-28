package com.survey.model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/*
 * 调查类
 */
/**
 * @author lenovo
 *
 */
public class Survey {
private Integer id;
private String title="未命名";
private String preText="上一页";
private String nextText="下一页";
private String exitText="退出";
private String doneText="完成";
private Date createTime = new Date();//创建时间
//建立从用户到调查的单向一对多关联
private User user;
//建立从调查到页面双向一对多关联
private Set<Page> pages = new HashSet<Page>();
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPreText() {
	return preText;
}
public void setPreText(String preText) {
	this.preText = preText;
}
public String getNextText() {
	return nextText;
}
public void setNextText(String nextText) {
	this.nextText = nextText;
}
public String getExitText() {
	return exitText;
}
public void setExitText(String exitText) {
	this.exitText = exitText;
}
public String getDoneText() {
	return doneText;
}
public void setDoneText(String doneText) {
	this.doneText = doneText;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Set<Page> getPages() {
	return pages;
}
public void setPages(Set<Page> pages) {
	this.pages = pages;
}
@Override
public String toString() {
	return "Survey [id=" + id + ", title=" + title + ", preText=" + preText
			+ ", nextText=" + nextText + ", exitText=" + exitText
			+ ", doneText=" + doneText + ", createTime=" + createTime
			+ ", user=" + user + ", pages=" + pages + "]";
}

}
