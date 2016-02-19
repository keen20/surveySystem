<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>矩阵型问题设计</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader">矩阵式题型调查结果分析:</td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader"><s:property value="qsm.question.title" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td>
						<table>
							<!-- 画表头 -->
							<tr>
								<td width="30px"></td>
								<s:iterator value="qsm.question.matrixColTitleArr">
									<td><s:property /></td>
								</s:iterator>
							</tr>
							<s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
								<tr>
									<!-- 行头 -->
									<td><s:property/></td>
									<s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
										<td>
											<!-- ognl不仅可以访问属性,还可以直接调方法 -->
											<s:property value='getScale(#rst.index,#cst.index)' />
										</td>
									</s:iterator>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">共有&nbsp;<s:property value="qsm.count" />&nbsp;人参与问卷!</td>
				</tr>
			</table>
	</body>
</html>