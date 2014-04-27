function hideField(id)
{
	document.getElementById(id).style.display="none";
}

function showField(id)
{
	document.getElementById(id).style.display="table-row";
}

function showPwdTeach() 
{
	var groupIndex= document.getElementById('groupList').selectedIndex ;
	var groupValue= document.getElementById('groupList').options[groupIndex].value;
	if(groupValue!=1)
	{
		hideField('pwdTeacher');
	}
	else
	{
		showField('pwdTeacher');
	}
}

function hideAndShowSearchType()
{
	var typeSearch=document.getElementById('idTypSearch0').checked;
	
	hideField("trVideId");
	if(typeSearch==0)
	{
		hideField("grpId");
		showField("roomId");
	}
	else
	{
		hideField("roomId");
		showField("grpId");
	}
}


