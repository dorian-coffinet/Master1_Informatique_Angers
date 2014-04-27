<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
		<title><s:text name="title.new.classroom"/></title>
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
				<h1><s:text name="title.new.classroom"/></h1>
				<s:actionerror cssClass="errorCenter" />
				<s:form action="NewClassroom" method="post">
					<table>
						<tr><td></td></tr>
						<tr>
							<td><s:text name="label.name"/></td>
							<td><s:textfield name="classroomBean.name" cssClass="text"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.name"/></td>
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