<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<br/>
	<h1>Login</h1>
	<div id="login-error">${error}</div>
	<form method="post" action="<c:url value='/j_spring_security_check' />">
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td align="right"><label for="j_username">username</label></td>
				<td><input id="j_username" name="j_username" type="text" /></td>
			</tr>
			<tr>
				<td align="right"><label for="j_password">password</label></td>
				<td><input id="j_password" name="j_password" type="password" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" value="Login" style="cursor:pointer;" />
					&nbsp;&nbsp;&nbsp;
					<a href="<c:url value="/forgotpassword" />">ลืมรหัสผ่าน</a>
				</td>
			</tr>
		</table>
<!-- 		<input type="checkbox" name="_spring_security_remember_me" /> Keep me signed in -->
		<br/>
		<a href="<c:url value="/register" />">ลงทะเบียน</a>
	</form>
</body>
</html>