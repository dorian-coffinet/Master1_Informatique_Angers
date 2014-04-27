<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
		<title><s:text name="title.user"/></title>
	</head>
	<body>
		<div id="wrap">
			<%@ include file="../other/applicationName.jsp" %>
			<s:if test="#session.user.idGroup==1">
			</s:if>
			<s:else>
				<% response.sendRedirect("AccessDenied");	%> 
			</s:else>
			<%@ include file="../other/menuAdmin.jsp" %>
			<div id="content-top"></div>
			<div id="content-middle">
				<h1><s:text name="title.user"/></h1>
				<s:form action="UpdateUser" method="post">
					<div>
						<s:hidden name="id" value="%{userBean.id}"/>
						<s:hidden name="userBean.id" value="%{userBean.id}"/>
					</div>
					<table>
						<tr><td> </td></tr>
						<tr>
							<td><s:text name="label.firstname" /></td>
							<td><s:textfield name="userBean.firstName"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.firstname" /></td>
						</tr>
						<tr>
							<td><s:text name="label.name"/></td>
							<td><s:textfield name="userBean.name"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.name"/></td>
						</tr>
						<tr>
							<td><s:text name="label.group"/></td>
							<td>
								<s:select id="groupList" list="userBean.mapGroup" name="userBean.idGroup" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td><s:text name="label.email"/></td>
							<td><s:textfield name="userBean.email"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.email"/></td>
						</tr>
						<tr>
							<td><s:text name="label.password.new"/></td>
							<td><s:password name="userBean.newPassword"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.newpassword"/></td>
						</tr>
						<tr>
							<td><s:text name="label.password.confirm"/></td>
							<td><s:password name="userBean.confirmPassword"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.confirmpassword"/></td>
						</tr>
					</table>
					<div><br/><s:submit key="label.valide"  cssClass="user_button"/></div>
				</s:form>
				<div class="clear"></div>
			</div>
			<div id="content-bottom"></div>
		</div>
	</body>
</html>