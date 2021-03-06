<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="changepasswordSave" value="/changepassword/save" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<div id="divReg">
		<b>เปลี่ยนรหัสผ่าน</b>
		<br/><br/>
		<form:form name="formUser" id="formUser" method="POST" modelAttribute="userChangePasswordDto" acceptCharset="UTF-8">
			<input type="hidden" id="mode" name="mode" value="${mode}" />
			<table cellspacing="2" cellpadding="2">
				<tr>
					<td align="right">old password: </td>
					<td>
						<form:password path="oldPassword" id="oldPassword" maxlength="20" cssClass="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
				<tr>
					<td align="right">new password: </td>
					<td>
						<form:password path="password" id="password" maxlength="20" cssClass="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
				<tr>
					<td align="right">new password confirm: </td>
					<td>
						<form:password path="passwordConfirm" id="passwordConfirm" maxlength="20" cssClass="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>
						<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
					<td>
				</tr>
			</table>
			<br/>
		</form:form>
	</div>
	
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			
			$(".fieldError").hide();
			
			ajaxPost('<c:url value="/checkUserPassword" />', $('#formUser').serialize(), function(response) {
				if (response.result == "success") {
					if (response.returnValue == "N") {
						alert("old password not correct!");
					}
					else {
						ajaxPost('${changepasswordSave}', $('#formUser').serialize(), function(response) {
							if (response.result == "success") {
								$("#successMsgDiv").show();
								$("#divReg").hide();
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