<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
		<title><s:text name="title.show.user"/></title>
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
				<h1><s:text name="title.show.user"/></h1>
				<table>
					<tr>
						<th><s:text name="label.email"/></th>
						<th><s:text name="label.firstname"/></th>
						<th><s:text name="label.name"/></th>
						<th><s:text name="label.group"/></th>
						<th></th>
					</tr>
					<s:iterator value="listUserBean" id="group">
						<tr>
							<s:url id="url" action="GetUser">
								<s:param name="id"><s:property value="id"/></s:param>
						    </s:url>
							<td><s:a href="%{url}"><s:property value="email"/></s:a></td>
							<td><s:a href="%{url}"><s:property value="name"/></s:a></td>
							<td><s:a href="%{url}"><s:property value="firstName"/></s:a></td>
							<td><s:a href="%{url}"><s:property value="mapGroup[idGroup]"/></s:a></td>
							<td class="bine">
								<s:url id="url" action="DeleteUser">
									<s:param name="id"><s:property value="id"/></s:param>
							    </s:url>
							    <s:a href="%{url}">
								    <img src="./css/pageCss/images/btn_corbeille.png" alt="<s:text name="label.delete"/>" style="width:20px"  title="<s:text name="label.delete"/>" />
								</s:a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<div class="clear"></div>
			</div>
			<div id="content-bottom"></div>
		</div>
	</body>
</html>