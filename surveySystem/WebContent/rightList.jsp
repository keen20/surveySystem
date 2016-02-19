<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>权限管理</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src='<s:url value="/jquery-1.7.1.js" />'></script>
		<script type="text/javascript">
		$(function(){
			$("input[name^=r_]").parent().click(function(){
				$(this).children("input[name^=r_]").removeAttr("disabled");
				if($(this).children("input").attr("value") == "未命名"){
					$(this).children("input").attr("value","");
				}
				$(this).children("input[name^=r_]").focus();
			});	
			$("input[name^=r_]").attr("disabled","disabled");
		});
		
		//全选
		$(function(){
			$('#cbSelectAll').click(function(){
				var v = $(this).attr("checked");
				if(v == "checked"){
					$("input[type=checkbox]").attr("checked","checked");
				}
				else{
					$("input[type=checkbox]").removeAttr("checked");
				}
			});
			
			$('#inverseSelectAll').click(function(index){
				$("input[type=checkbox]").each(function(){
					var v = $(this).attr("checked");
					if("checked" == v){
						$(this).removeAttr("checked");
					}
					else{
						$(this).attr("checked","checked");
					}
				});
			});
		});
		</script>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td colspan="10" style="height: 5px"></td>
			</tr>
			<tr>
				<td colspan="10" class="tdPHeaderR"><s:a action="RightAction_toAddRightPage" namespace="/">添加权限</s:a></td>
			</tr>
			<tr>
				<td colspan="10" style="height: 5px"></td>
			</tr>
		</table>
		<s:if test="allRights.isEmpty() == true">目前您没有任何权限!</s:if >
		<s:else>
			<s:form action="RightAction_batchUpdateRights" namespace="/" method="post">
			<table>
				<thead>
					<tr>
						<td colspan="10" style="height: 5px"></td>
					</tr>
					<tr>
						<td colspan="10" class="tdHeader">权限管理:</td>
					</tr>
					<tr>
						<!-- 输出分页条 -->
						<td colspan="10" style="height: 5px;text-align:left;">
						</td>
					</tr>
					<tr>
						<td class="tdListHeader">ID</td>
						<td class="tdListHeader">权限名称</td>
						<td class="tdListHeader">
							公共资源<br>
							<input type="checkbox" id="cbSelectAll">全选
							<a id="inverseSelectAll" href="#">反选</a>
						</td>
						<td class="tdListHeader">权限URL</td>
						<td class="tdListHeader">权限位</td>
						<td class="tdListHeader">权限码</td>
						<td class="tdListHeader">修改</td>
						<td class="tdListHeader">删除</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="allRights" status="st">
						<s:set var="rightId" value="id" />
						<tr>
							<td>
								<s:textfield name="allRights[%{#st.index}].id" cssClass="text" cssStyle="width:50px" readonly="true"/>
							</td>
							<td>
								<s:textfield name="allRights[%{#st.index}].rightName" cssClass="text" cssStyle="width:150px"/>
							</td>
							<td style="text-align: left;">
								<s:checkbox name="allRights[%{#st.index}].common" />
							</td>
							<td style="text-align: left;"><s:property value="rightUrl" /></td>
							<td style="color: gray;text-align: right;"><s:property value="rightPos" /></td>
							<td style="color: gray;text-align: right;"><s:property value="rightCode" /></td>
							<td><s:a action="RightAction_editRight?rightId=%{#rightId}" cssClass="aList">修改</s:a></td>
							<td><s:a action="RightAction_deleteRight?rightId=%{#rightId}" cssClass="aList">删除</s:a></td>
						</tr>
					</s:iterator>
				</tbody>
				<tr>
					<td colspan="10" style="height: 5px"><s:submit cssClass="btn" value="提交"/></td>
				</tr>
			</table>
			</s:form>
		</s:else>
	</body>
</html>

