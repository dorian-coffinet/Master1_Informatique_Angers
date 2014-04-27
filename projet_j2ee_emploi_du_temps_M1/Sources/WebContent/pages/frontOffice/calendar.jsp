<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="./css/pageCss/css/mainstyle.css" type="text/css" />
	<title><s:text name="title.calendar"/></title>
	<link rel="stylesheet" type='text/css' href="/ProjetEDT/css/smoothness/jquery-ui-1.10.4.custom.css"/>
	<link rel='stylesheet' type='text/css' href='/ProjetEDT/css/jquery-ui-1.8.11.custom.css' />
	<link rel='stylesheet' type='text/css' href='/ProjetEDT/css/jquery.weekcalendar.css' />
	<script type='text/javascript' src='/ProjetEDT/javascript/jquery/jquery-1.4.4.min.js'></script>
	<script type='text/javascript' src="/ProjetEDT/javascript/jquery/jquery-ui-1.10.4.custom.js"></script>
	<script type='text/javascript' src='/ProjetEDT/javascript/jquery/jquery-ui-1.8.11.custom.min.js'></script>
	<script type="text/javascript" src="/ProjetEDT/javascript/calendar/date.js"></script>
	<script type='text/javascript' src='/ProjetEDT/javascript/calendar/jquery.weekcalendar.js'></script>
	<script type="text/javascript" src="./javascript/utils/form.js"></script>
	<s:set name="view" value="view"/>
	<s:if test="%{#view!=2}">
	<s:if test="%{#view==1}">
	<script type='text/javascript'>
	var year = new Date().getFullYear();
	var month = new Date().getMonth();
	var day = new Date().getDate();
	var eventData = {
	  events : [
			<s:iterator value="listScheduleBean" id="schedule">
				{ 	'id':'<s:property value="id"/>',
					'title':'<s:property value="name"/>',
					'start':'<s:property value="dayStart"/>',
					'end':'<s:property value="dayEnd"/>',
					'color':'<s:property value="colorSubject"/>'
				},
			</s:iterator>
	    ]
	};
	</script>
	<script type='text/javascript'>
	$(document).ready(function() {
		$('#calendar').weekCalendar({
			timeslotsPerHour: 4,
			timeslotHeigh: 10,
			dateFormat: 'd M Y',
			use24Hour: true,
			hourLine: true,
			allowEventCreation:false,
			timeSeparator: ' à ',
			data: eventData,
			defaultEventLength: 1,
	        timeslotsPerHour: 1,
			businessHours: {start: 0, end: 0, limitDisplay: false},
			height: function($calendar) {
				return $(window).height() - $('h1').outerHeight(true);
			},
			deletable: function(calEvent, element) {
				return false;
			},
			draggable: function(calEvent, element) {
				return false;
			},
			resizable: function(calEvent, element) {
				return false;
			},
			firstDayOfWeek: function(calendar) {
				return 1;
			},
			minDate: '<s:property value="dayMin"/>',
		    maxDate: '<s:property value="dayMax"/>',
		    shortMonths: ['<s:text name="calendar.short.month.january"/>', '<s:text name="calendar.short.month.february"/>',
			              '<s:text name="calendar.short.month.march"/>', '<s:text name="calendar.short.month.april"/>', 
			              '<s:text name="calendar.short.month.may"/>', '<s:text name="calendar.short.month.june"/>', 
			              '<s:text name="calendar.short.month.july"/>', '<s:text name="calendar.short.month.august"/>', 
			              '<s:text name="calendar.short.month.september"/>', '<s:text name="calendar.short.month.october"/>', 
			              '<s:text name="calendar.short.month.november"/>', '<s:text name="calendar.short.month.december"/>'],
			              
			longMonths: ['<s:text name="calendar.long.month.january"/>', '<s:text name="calendar.long.month.february"/>',
			              '<s:text name="calendar.long.month.march"/>', '<s:text name="calendar.long.month.april"/>', 
			              '<s:text name="calendar.long.month.may"/>', '<s:text name="calendar.long.month.june"/>', 
			              '<s:text name="calendar.long.month.july"/>', '<s:text name="calendar.long.month.august"/>', 
			              '<s:text name="calendar.long.month.september"/>', '<s:text name="calendar.long.month.october"/>', 
			              '<s:text name="calendar.long.month.november"/>', '<s:text name="calendar.long.month.december"/>'],
			              

			shortDays: ['<s:text name="calendar.short.day.sunday"/>', '<s:text name="calendar.short.day.monday"/>', 
			            '<s:text name="calendar.short.day.tuesday"/>', '<s:text name="calendar.short.day.wednesday"/>', 
			            '<s:text name="calendar.short.day.thursday"/>', '<s:text name="calendar.short.day.friday"/>', 
			            '<s:text name="calendar.short.day.saturday"/>'],
			            
			longDays: ['<s:text name="calendar.long.day.sunday"/>', '<s:text name="calendar.long.day.monday"/>', 
			            '<s:text name="calendar.long.day.tuesday"/>', '<s:text name="calendar.long.day.wednesday"/>', 
			            '<s:text name="calendar.long.day.thursday"/>', '<s:text name="calendar.long.day.friday"/>', 
			            '<s:text name="calendar.long.day.saturday"/>'],
		});
	
		function displayMessage(message) {
		  $('#message').html(message).fadeIn();
		}
	
		$('<div id="message" class="ui-corner-all"><\/div>').prependTo($('body'));
	});
	</script>
	</s:if>
	<s:else>
   <script type='text/javascript'>
	var year = new Date().getFullYear();
	var month = new Date().getMonth();
	var day = new Date().getDate();
	var eventData = {
	  events : [
			<s:iterator value="listScheduleBean" id="schedule">
				{ 	'id':'<s:property value="id"/>',
					'title':'<s:property value="name"/>',
					'start':'<s:property value="dayStart"/>',
					'end':'<s:property value="dayEnd"/>',
					'comment':'<s:property value="comment"/><br /><s:property value="mapTeacher[idTeacher]"/><br /><s:property value="mapSubject[idSubject]"/><br /><s:property value="mapClassroom[idClassroom]"/><br /><s:property value="mapGroup[idGroup]"/>',
					'color':'<s:property value="colorSubject"/>'
				},
			</s:iterator>
	    ]
	};
	</script>
	<script type='text/javascript'>
	//<![CDATA[ 
	$(document).ready(function() {
		$('#calendar').weekCalendar({
			timeslotsPerHour: 4,
			timeslotHeigh: 10,
			dateFormat: 'd M Y',
			use24Hour: true,
			hourLine: true,
			allowEventCreation:false,
			timeSeparator: ' à ',
			data: eventData,
			businessHours: {start: 0, end: 0, limitDisplay: false},
			height: function($calendar) {
				return $(window).height() - $('h1').outerHeight(true);
			},
			deletable: function(calEvent, element) {
				return false;
			},
			draggable: function(calEvent, element) {
				return false;
			},
			resizable: function(calEvent, element) {
				return false;
			},
			firstDayOfWeek: function(calendar) {
				return 1;
			},
			minDate: '<s:property value="dayMin"/>',
		    maxDate: '<s:property value="dayMax"/>',
			shortMonths: ['<s:text name="calendar.short.month.january"/>', '<s:text name="calendar.short.month.february"/>',
			              '<s:text name="calendar.short.month.march"/>', '<s:text name="calendar.short.month.april"/>', 
			              '<s:text name="calendar.short.month.may"/>', '<s:text name="calendar.short.month.june"/>', 
			              '<s:text name="calendar.short.month.july"/>', '<s:text name="calendar.short.month.august"/>', 
			              '<s:text name="calendar.short.month.september"/>', '<s:text name="calendar.short.month.october"/>', 
			              '<s:text name="calendar.short.month.november"/>', '<s:text name="calendar.short.month.december"/>'],
			              
			longMonths: ['<s:text name="calendar.long.month.january"/>', '<s:text name="calendar.long.month.february"/>',
			              '<s:text name="calendar.long.month.march"/>', '<s:text name="calendar.long.month.april"/>', 
			              '<s:text name="calendar.long.month.may"/>', '<s:text name="calendar.long.month.june"/>', 
			              '<s:text name="calendar.long.month.july"/>', '<s:text name="calendar.long.month.august"/>', 
			              '<s:text name="calendar.long.month.september"/>', '<s:text name="calendar.long.month.october"/>', 
			              '<s:text name="calendar.long.month.november"/>', '<s:text name="calendar.long.month.december"/>'],
			              

			shortDays: ['<s:text name="calendar.short.day.sunday"/>', '<s:text name="calendar.short.day.monday"/>', 
			            '<s:text name="calendar.short.day.tuesday"/>', '<s:text name="calendar.short.day.wednesday"/>', 
			            '<s:text name="calendar.short.day.thursday"/>', '<s:text name="calendar.short.day.friday"/>', 
			            '<s:text name="calendar.short.day.saturday"/>'],
			            
			longDays: ['<s:text name="calendar.long.day.sunday"/>', '<s:text name="calendar.long.day.monday"/>', 
			            '<s:text name="calendar.long.day.tuesday"/>', '<s:text name="calendar.long.day.wednesday"/>', 
			            '<s:text name="calendar.long.day.thursday"/>', '<s:text name="calendar.long.day.friday"/>', 
			            '<s:text name="calendar.long.day.saturday"/>'],
		});
	
		function displayMessage(message) {
		  $('#message').html(message).fadeIn();
		}
	
		$('<div id="message" class="ui-corner-all"><\/div>').prependTo($('body'));
	}); //]]>
	</script>
	</s:else>
	</s:if>
	</head>
	<body onload="hideAndShowSearchType();">
		<div id="wrap">
			<%@ include file="../other/applicationName.jsp" %>
			<s:if test="#session.user.idGroup==1">
				<%@ include file="../other/menuAdmin.jsp" %>
			</s:if>
			<s:else>
				<%@ include file="../other/menuEtu.jsp" %>
			</s:else>
			<div id="content-top"></div>
			<div id="content-middle">
				<h1><s:text name="title.calendar"/></h1>
				<s:form action="ShowCalendar" method="post">
					<table>
						<tr><td></td></tr>
						<tr>
							<td><s:text name="label.type.calendar"/></td>
							<td><s:radio label="Type" name="view" list="listView" value="defaultViewValue" /></td>
						</tr>
												<tr>
							<td><s:text name="label.type.search"/></td>				
							<td><s:radio label="Type" name="search" list="listSearch" 
									value="defaultSearchValue" onclick="hideAndShowSearchType();" id="idTypSearch"/></td>
						</tr>
						<tr  id="grpId">
							<td><s:text name="label.group"/></td>				
							<td><s:select name="scheduleBean.idGroup" list="mapGroup" /></td>
						</tr>
						<tr id="trVideId"><td></td></tr>
						<tr id="roomId">
							<td><s:text name="label.classroom"/></td>				
							<td><s:select name="scheduleBean.idClassroom" list="mapClassroom" /></td>
						</tr>
						<tr>
							<td><s:text name="label.dayStart"/></td>
							<td><s:textfield id="heure_debut" name="dayMin" key="label.dayStart"   maxlength="100"/></td>
						</tr>
						<tr>
							<td><s:text name="label.dayEnd"/></td>
							<td><s:textfield id="heure_fin" name="dayMax" key="label.dayEnd"  maxlength="100" /></td>
						</tr>
					</table>
					<div><s:submit key="label.valide"  cssClass="user_button"/></div>
				</s:form>
				<div id='calendar'>
					<s:if test="%{#view==2}">
						<table>
						<tr>
							<td><s:text name="label.dayStart"/></td>
							<td><s:text name="label.dayEnd"/></td>
							<td><s:text name="label.information"/></td>
						</tr>
						<s:iterator value="listScheduleBean" id="schedule">
							<tr>
								<td><s:property value="dayStart"/></td>
								<td><s:property value="dayEnd"/></td>
								<td><s:property value="name"/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><s:property value="comment"/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><s:property value="mapTeacher[idTeacher]"/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><s:property value="mapSubject[idSubject]"/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><s:property value="mapClassroom[idClassroom]"/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><s:property value="mapGroup[idGroup]"/></td>
							</tr>
						</s:iterator>
						</table>
					</s:if>
				</div>
			</div>
			<div id="content-bottom"></div>
		</div>
	<script type='text/javascript'>
	 $(function() {
		$( "#heure_debut" ).datetimepicker({
			dateFormat: "yy-mm-dd",
			timeFormat:  "HH:mm:ss"
		});
		$( "#heure_fin" ).datetimepicker({
			dateFormat: "yy-mm-dd",
			timeFormat:  "HH:mm:ss"
		});
	 });
	</script>
	</body>
</html>