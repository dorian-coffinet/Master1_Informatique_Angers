<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="./javascript/utils/form.js"></script>
		<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
		<title><s:text name="title.login"/></title>
	</head>
	<body onload="hideField('trVideId')">
		<div id="wrap">
			<%@ include file="../other/applicationName.jsp" %>
			<%@ include file="../other/menuEtu.jsp" %>
			<div id="content-top"></div>
			<div id="content-middle">
				<s:form action="LoginAction" method="post">
					<h1><s:text name="title.login"/></h1>
					<s:actionerror cssClass="errorCenter" />
					<table>
						<tr><td> </td></tr>
						<tr>
							<td><s:text name="label.email"/></td>
							<td><s:textfield name="userBean.email"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.email"/></td>
						</tr>
						<tr id="trVideId"><td> </td></tr>
						<tr>
							<td><s:text name="label.password"/></td>
							<td><s:password name="userBean.password"  maxlength="100"/></td>
							<td class="error"><s:fielderror fieldName="error.password"/></td>
						</tr>
					</table>
					<div><br/><s:submit key="label.valide"  cssClass="user_button"/>	</div>
				</s:form>
				<div class="clear"></div>
			</div>
			<div id="content-bottom"></div>
		</div>
	</body>
</html>

