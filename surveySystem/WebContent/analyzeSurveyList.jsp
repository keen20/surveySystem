<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设计调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src="<s:url value="/jquery-1.7.1.js" />"></script>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:set var="sId" value="id" />
		<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader">分析调查:</td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader"><s:property value="title" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<!-- 遍历调查的页面集合 -->
				<s:iterator var="p" value="pages" status="pst">
					<!-- 设置变量,对当前的Page的id属性进行保持 -->
					<s:set var="pId" value="#p.id" />
					<tr>
						<td colspan="2" class="tdPHeaderL"><s:property value="#p.title" /></td>
					</tr>
					<tr>
						<td width="30px"></td>
						<td>
							<table>
								<!-- 遍历问题集合 -->
								<s:iterator var="q" value="#p.questions" status="qst">
									<s:set var="qId" value="#q.id" />
									<s:set var="qt" value="#q.questionType" />
									<tr>
										<!-- count:从1开始 index:从0开始 -->
										<td class="tdQHeaderL" style="border-bottom: 1px solid white"><s:property value="#qst.count+'.' + #q.title" /></td>
										<td class="tdQHeaderR" style="border-bottom: 1px solid white">
											<s:form action="ChartOutputAction" namespace="/" method="post" target="_blank">
												<input type="hidden" name="qid" value='<s:property value="#qId" />'>
												<!-- 判断当前题型是否矩阵式题型 -->
												<s:if test='#qt > 5 '>
													<!-- 提交给另外一个action,改变form的提交地址 -->
													<s:submit action="MatrixStatisticsAction" value="查看矩阵式问题统计结果" cssClass="btn"/>
												</s:if>
												<s:elseif test='#qt lt 5'>
													<s:set var="chartList" value="#{0:'平面饼图',
																					1:'立体饼图',
																					2:'横向平面柱状图',
																					3:'纵向平面柱状图',
																					4:'横向立体柱状图',
																					5:'纵向立体柱状图',
																					6:'平面折线图',
																					7:'立体折线图'}"/>
													<s:select name="chartType" list="#chartList" listKey="key" listValue="value" />
													<s:submit value="查看" cssClass="btn" />
												</s:elseif>
											</s:form>
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</s:iterator>
			</table>
	</body>
</html>