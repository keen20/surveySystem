<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="divOuterFrame">
	<div class="divInnerFrame">欢迎使用SurveyDoor调查系统!</div>
</div>
<div class="divWhiteLine"></div>
<div class="divNavigatorOuterFrame">
	<div class="divNavigatorInnerFrame">
		<s:a action="LoginAction_toLoginPage" namespace="/">[首页]</s:a>&nbsp;
		<s:a action="SurveyAction_newSurvey" namespace="/">[新建调查]</s:a>&nbsp;
		<s:a action="SurveyAction_mySurveys" namespace="/">[我的调查]</s:a>&nbsp;
		<s:a namespace="/" action="EngageSurveyAction_findAllAvailableSurveys">[参与调查]</s:a>&nbsp;
		<s:a action="RegAction_toRegPage" namespace="/">[用户注册]</s:a>&nbsp;
		<s:a namespace="/" action="UserAuthorizeAction_findAllUsers">[用户授权管理]</s:a>&nbsp;
		<s:a namespace="/" action="RoleAction_findAllRoles">[角色管理]</s:a>&nbsp;
		<s:a namespace="/" action="RightAction_findAllRights">[权限管理]</s:a>&nbsp;
		<s:a namespace="/" action="LogAction_findNearestLogs">[日志管理]</s:a>&nbsp;
	</div>
</div>
<div class="divWhiteLine"></div>