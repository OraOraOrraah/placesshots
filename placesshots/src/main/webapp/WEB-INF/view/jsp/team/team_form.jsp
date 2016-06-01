<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="teamSave" value="/team/save" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<form:form name="formTeam" id="formTeam" action="${teamSave}" method="POST" modelAttribute="teamDto" acceptCharset="UTF-8">
		<input type="hidden" id="mode" name="mode" value="${mode}" />
		<table>
			<tr>
				<td>League: </td>
				<td><form:select path="leagueId" id="leagueId" items="${listLeague}" /><td>
			</tr>
			<tr>
				<td>Title: </td>
				<td><form:input path="title" id="title" maxlength="50" cssClass="input-text" /><td>
			</tr>
			<tr>
				<td>Short Title: </td>
				<td><form:input path="shortTitle" id="shortTitle" maxlength="3" cssClass="input-text" /><td>
			</tr>
		</table>
		
		<input type="button" id="buttonSave" value='Submit' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		
	</form:form>
	
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			ajaxPost('${teamSave}', $('#formTeam').serialize(), function(response) {
				if (response.result == "success") {
					$("#successMsgDiv").show();
					
					$("#leagueId").prop('selectedIndex', 0);
					$("#title").val("");
					$("#shortTitle").val("");
				}
			});
		});
	</script>
</body>
</html>