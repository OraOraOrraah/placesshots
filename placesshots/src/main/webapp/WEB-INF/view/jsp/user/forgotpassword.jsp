<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<div id="divReg">
		Forgot Password
		<form name="formUser" id="formUser" method="POST" acceptCharset="UTF-8">
			<table>
				<tr>
					<td>username: </td>
					<td>
						<input type="text" name="username" id="username" maxlength="20" class="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
			</table>
			<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		</form>
	</div>
	
	<div id="divRegSuccess" style="display:none;">
		new password : <span id="newpassword"></span>
		<br/>
		<a href="<c:url value="/login" />">Login</a>
	</div>
	
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			ajaxPost('<c:url value="/checkUser" />', $('#formUser').serialize(), function(response) {
				if (response.result == "success") {
					if (response.returnValue == "Y") {
						
						ajaxPost('<c:url value="/forgotpassword/save" />', $('#formUser').serialize(), function(response) {
							if (response.result == "success") {
								$("#successMsgDiv").show();
								
								$("#divReg").hide();
								$("#divRegSuccess").show();
								$("#newpassword").html(response.returnValue);
							}
							else {
								alert("Change password error!");
							}
						});
						
					}
					else {
						alert("Username not found!");
					}
				}
				else {
					alert("Change password error!");
				}
			});
		});
	</script>
</body>
</html>