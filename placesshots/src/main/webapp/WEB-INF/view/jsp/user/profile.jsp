<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="profileSave" value="/profile/save" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<div id="divReg">
		<form:form name="formUser" id="formUser" method="POST" modelAttribute="userDto" acceptCharset="UTF-8">
			<table>
				<tr>
					<td width="120px;" valign="top">Favorite Team: </td>
					<td>
						<form:radiobuttons items="${radioItem}" path="icon" htmlEscape="false" delimiter=" " />
					<td>
				</tr>
			</table>
			<br/>
			<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		</form:form>
	</div>
	
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			
			$("#successMsgDiv").hide();
			
			ajaxPost('${profileSave}', $('#formUser').serialize(), function(response) {
				if (response.result == "success") {
					$("#successMsgDiv").show();
					
					//
					ajaxPost("<c:url value="/user/icon" />", {username: user}, function(response) {
						if (response != "") {
							$("#userIconDisplay").html("<img src=" + response + " height=16 width=32 />");
						}
					}, true);
				}
			});
		});
	</script>
</body>
</html>