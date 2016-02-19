<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑角色</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src='<s:url value="/jquery-1.7.1.js" />'></script>
		<script type="text/javascript">
			$(function(){
				$('#one1').click(function(){
					var size = $('#left>option:selected').size();
					if(size != 0){
						$('#left > option:selected').appendTo($('#right'));
					}
					else{
						$('#left>option:first-child').appendTo($('#right'));
					}
				});	
				$('#all1').click(function(){
					$('#left > option').appendTo($('#right'));
				});	
				$('#one2').click(function(){
					var size = $('#right>option:selected').size();
					if(size != 0){
						$('#right > option:selected').appendTo($('#left'));
					}
					else{
						$('#right>option:first-child').appendTo($('#left'));
					}
				});	
				$('#all2').click(function(){
					$('#right > option').appendTo($('#left'));
				});	
			});
			
			function submitForm1(){
				$('#left > option').attr('selected','selected');
				return true ;
			}
		</script>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td class="tdHeader">编辑角色:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form id="form1" action="RoleAction_saveOrUpdateRole" method="post">
								<s:hidden name="id" />
								<table>
									<tr>
										<td class="tdFormLabel" width="200px">角色名称</td>
										<td class="tdFormControl"><s:textfield name="roleName" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel" width="200px">角色值</td>
										<td class="tdFormControl"><s:textfield name="roleValue" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel" width="200px">角色描述</td>
										<td class="tdFormControl"><s:textarea name="roleDesc" cssClass="text" rows="10"/></td>
									</tr>
									<tr>
										<td class="tdFormLabel" colspan="2">
											<table>
												<tr>
													<td width="45%" align="right">
														<s:select id="left" 
															name="ownRightIds" 
															list="rights" 
															listKey="id" 
															listValue="rightName"
															multiple="true"
															size="15"
															cssStyle="width:100px">
														</s:select>
													</td>
													<td width="10%" valign="middle" align="center">
														<input type="button" id="one1" value="&gt;" class="btn"></input><br><br>
														<input type="button" id="one2" value="&lt;" class="btn"></input><br><br>
														<input type="button" id="all1" value="&gt;&gt;" class="btn"></input><br><br>
														<input type="button" id="all2" value="&lt;&lt;" class="btn"></input><br><br>
													</td>
													<td width="45%" align="left">
														<s:select id="right" 
															list="noOwnRights" 
															name="noOwnRightIds"
															listKey="id" 
															listValue="rightName"
															multiple="true"
															size="15"
															cssStyle="width:100px">
														</s:select>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="确定" cssClass="btn" onclick='return submitForm1()'/></td>
									</tr>
								</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>