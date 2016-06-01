<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	Change Password
	<div id="changePasswordDiv">
		<form name="formUser" id="formUser" method="POST">
			<table>
				<tr>
					<td>Input new password: </td>
					<td>
						<input type="password" id="password" name="password" maxlength="20" class="input-text" />
						<span class="require">*</span>
					<td>
				</tr>
			</table>
			<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		</form>
	</div>
	<script type="text/javascript">
		$("#buttonSave").click(function(){
			if ($("#password").val().length < 8) {
				alert("Please input password atleast 8 characters.");
			}
			else {
				ajaxPost('<c:url value="/changepassword/save" />', $('#formUser').serialize(), function(response) {
					if (response.result == "success") {
						$("#successMsgDiv").show();
						$("#changePasswordDiv").hide();
					}
					else {
						alert("Change password error!");
					}
				});
			}
		});
	</script>
</body>
</html>