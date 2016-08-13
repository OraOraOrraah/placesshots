<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="authenUser" value="/authenUser" />
<c:url var="signinFacebook" value="/signin/facebook" />
<c:url var="predictIndex" value="/predict/index" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<br/>
	<h1>Login</h1>
	<form id="formLogin" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td align="right"><label for="username">username</label></td>
				<td><input id="username" name="username" type="text" value="OraOraOrraah" /></td>
			</tr>
			<tr>
				<td align="right"><label for="password">password</label></td>
				<td><input id="password" name="password" type="password" value="Bsrg1123" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="button" id="bLogin" value="Login" style="cursor:pointer;" />
					&nbsp;&nbsp;&nbsp;
					<a href="<c:url value="/forgotpassword" />">ลืมรหัสผ่าน</a>
				</td>
			</tr>
		</table>
<!-- 		<input type="checkbox" name="_spring_security_remember_me" /> Keep me signed in -->
		<br/>
		<a href="<c:url value="/register" />">ลงทะเบียน</a>
	</form>
	
	<div id="login-error" class="error"></div>
	
	<form action="${signinFacebook}" method="POST">
		<input type="hidden" name="scope" value="public_profile" />
		<input type="submit" value="Connect to Facebook" />
	</form>
	
	<script type="text/javascript">
		$("#bLogin").click(function(){
			$("#login-error").html("");
			ajaxPost('${authenUser}', $('#formLogin').serialize(), header, token, function(response) {
				if (response.result == "success") {
					location = '${predictIndex}';
				}
				else {
					$("#login-error").html("Username or Password is incorrect!");
				}
			});
		});
	</script>
</body>
</html>