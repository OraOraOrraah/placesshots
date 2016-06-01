<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="registerSave" value="/register/save" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<div id="divReg">
		Register
		<form:form name="formUser" id="formUser" method="POST" modelAttribute="userDto" acceptCharset="UTF-8">
			<input type="hidden" id="mode" name="mode" value="${mode}" />
			<table>
				<tr>
					<td width="120px;">username: </td>
					<td>
						<form:input path="username" id="username" maxlength="20" cssClass="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
				<tr>
					<td>password: </td>
					<td>
						<form:password path="password" id="password" maxlength="20" cssClass="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>email: </td> -->
<!-- 					<td> -->
<%-- 						<form:input path="email" id="email" maxlength="100" cssClass="input-text" /> --%>
<!-- 					<td> -->
<!-- 				</tr> -->
				<!--
				<tr>
					<td>firstname: </td>
					<td>
						<form:input path="firstname" id="firstname" maxlength="100" cssClass="input-text" />
					<td>
				</tr>
				<tr>
					<td>lastname: </td>
					<td>
						<form:input path="lastname" id="lastname" maxlength="100" cssClass="input-text" />
					<td>
				</tr>
				-->
			</table>
			<br/>
			<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
			
		</form:form>
	</div>
	
	<div id="divRegSuccess" style="display:none;">
		<a href="<c:url value="/login" />">Login</a>
	</div>
	
	<script type="text/javascript">
		$("#username").on("keypress", function(event) {
		    var englishAlphabetAndWhiteSpace = /[A-Za-z0-9]/g;
		    var key = String.fromCharCode(event.which);
		    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || englishAlphabetAndWhiteSpace.test(key)) {
		        return true;
		    }
		    return false;
		});
	
		$('#username').on("paste",function(e) {
		    e.preventDefault();
		});
	
		$("#buttonSave").click(function(){
			
			$(".fieldError").hide();
			
			ajaxPost('<c:url value="/checkUser" />', $('#formUser').serialize(), function(response) {
				if (response.result == "success") {
					if (response.returnValue == "Y") {
						alert("Username Exists!");
					}
					else {
						ajaxPost('${registerSave}', $('#formUser').serialize(), function(response) {
							if (response.result == "success") {
								$("#successMsgDiv").show();
								$("#divReg").hide();
								$("#divRegSuccess").show();
							}
							else {
								for (var key in response.errorsMap) {
						            $("[name='"+key+"']").after("<span class=\"require fieldError\" id=\""+key+"Id\">&nbsp;"+response.errorsMap[key]+"</span>");
								}
							}
						});
					}
				}
				else {
					alert("Error!");
				}
			});
		});
	</script>
</body>
</html>