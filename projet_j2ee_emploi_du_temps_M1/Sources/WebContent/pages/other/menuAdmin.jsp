<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="menu">
	<div id="menu-left"></div>
		<ul>
			<li><s:a action="ShowUser"><span><s:text name="module.user"/></span></s:a></li>
			<li><s:a action="ShowGroup"><span><s:text name="module.group"/></span></s:a></li>
			<li><s:a action="ShowClassroom"><span><s:text name="module.classroom"/></span></s:a></li>
			<li><s:a action="ShowSchedule"><span><s:text name="module.schedule"/></span></s:a></li>
			<li><s:a action="ShowCalendar"><span><s:text name="module.calendar"/></span></s:a></li>
			<li><s:a action="LogoutAction"><span><s:text name="module.logout"/></span></s:a></li>
		</ul>
	<div id="menu-right"></div>
</div>

