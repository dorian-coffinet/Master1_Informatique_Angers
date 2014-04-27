<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="menu">
	<div id="menu-left"></div>
		<ul>
			<s:if test="#session.user==null">
				<li><s:a action=""><span><s:text name="module.home"/></span></s:a></li>
				<li><s:a action="Login"><span><s:text name="module.login"/></span></s:a></li>
				<li><s:a action="InscriptionForm"><span><s:text name="module.register"/></span></s:a></li>
			</s:if>
			<li><s:a action="ShowCalendar"><span><s:text name="module.calendar"/></span></s:a></li>
			<s:if test="#session.user!=null">
				<li><s:a action="LogoutAction"><span><s:text name="module.logout"/></span></s:a></li>
			</s:if>
		</ul>
	<div id="menu-right"></div>
</div>

