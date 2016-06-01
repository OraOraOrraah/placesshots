<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="fixtureSave" value="/adm/fixture/save" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<form:form name="formFixture" id="formFixture" action="${fixtureSave}" method="POST" modelAttribute="fixtureDto" acceptCharset="UTF-8">
		<input type="hidden" id="mode" name="mode" value="${mode}" />
		<table>
			<tr>
				<td>League: </td>
				<td><form:select path="leagueId" id="leagueId" items="${listLeague}" /><td>
			</tr>
			<tr>
				<td>Week: </td>
				<td><form:input path="week" id="week" maxlength="2" cssClass="input-text" /><td>
			</tr>
			<tr>
				<td>Date: </td>
				<td><form:input path="fixtureDate" id="fixtureDate" maxlength="10" cssClass="input-text" /><td>
			</tr>
			<tr>
				<td>Home: </td>
				<td><form:select path="homeId" id="homeId" items="${listTeam}" /><td>
			</tr>
			<tr>
				<td>Away: </td>
				<td><form:select path="awayId" id="awayId" items="${listTeam}" /><td>
			</tr>
		</table>
		
		<input type="button" id="buttonSave" value='Submit' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		
	</form:form>
	
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			ajaxPost('${fixtureSave}', $('#formFixture').serialize(), function(response) {
				if (response.result == "success") {
					$("#successMsgDiv").show();
					
					$("#leagueId").prop('selectedIndex', 0);
					$("#homeId").prop('selectedIndex', 0);
					$("#awayId").prop('selectedIndex', 0);
				}
			});
		});
	</script>
</body>
</html>