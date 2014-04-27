<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="./javascript/utils/form.js"></script>
		<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
		<title><s:text name="title.register"/></title>
	</head>
	<body onload="showPwdTeach();">
		<div id="wrap">
			<%@ include file="../other/applicationName.jsp" %>
			<%@ include file="../other/menuEtu.jsp" %>
			<div id="content-top"></div>
			<div id="content-middle">
				<s:form action="InscriptionAction" method="post">	
					<h1><s:text name="title.register"/></h1>
					<s:actionerror cssClass="errorCenter" />
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
								<s:select id="groupList" list="userBean.mapGroup" name="userBean.idGroup" onchange="showPwdTeach()" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td><s:text name="label.email"/></td>
							<td><s:textfield name="userBean.email"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.email"/></td>
						</tr>
						<tr>
							<td><s:text name="label.password"/></td>
							<td><s:password name="userBean.password"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.password"/></td>
						</tr>
						<tr>
							<td><s:text name="label.password.confirm"/></td>
							<td><s:password name="userBean.confirmPassword"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.confirmpassword"/></td>
						</tr>
						<tr id="pwdTeacher">
							<td><s:text name="label.password.teacher" /></td>
							<td><s:password name="userBean.passwordTeacher"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.teacherpassword"/></td>
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




