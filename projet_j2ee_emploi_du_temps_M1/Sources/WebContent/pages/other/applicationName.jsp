<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="logo">
	<h1><s:text name="title.app.name"/></h1>
	<s:if test="#session.user!=null">
		<p>
			<s:text name="title.app.connect"/>
			<s:property value="#session.user.firstName" />
			<s:property value="#session.user.name" /><br/>
			-
			<br/><s:property value="#session.userGroup" />
		</p>
	</s:if>
</div>