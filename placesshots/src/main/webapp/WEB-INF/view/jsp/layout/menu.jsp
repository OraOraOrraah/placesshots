<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="authUserInfo" value="/authUserInfo" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />

<form action="${logoutUrl}" id="logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
    
<span class="notAuthen" style="display:none;">
<a href="<c:url value="/login" />">Login</a> |
</span>
<span class="authenOnly" style="display:none;">
<a href="<c:url value="/predict/index" />">ทายผล</a> |
<a href="<c:url value="/predict/extra" />">ทายแชมป์</a> |
</span>
<a href="<c:url value="/predict/result" />">ผลการทาย</a> |
<a href="<c:url value="/predict/standing" />">อันดับคะแนน</a>
<span class="authenOnly" style="display:none;">
	| <a href="<c:url value='/predict/history' />">ประวัติการทาย</a>
	| <a href="<c:url value="/changepassword" />">เปลี่ยนรหัสผ่าน</a>
	| <a onclick="document.getElementById('logout').submit();">Logout</a>
</span>

<div class="authenOnly" style="text-align:right;display:none;">
	<span id="userDisplay"></span>
</div>
	
<script type="text/javascript">
	if (user != null && user != "" && user != "anonymousUser") {
		var url = "${authUserInfo}/" + $.now();
		ajaxPost(url, {username: user}, header, token, function(response) {
			$("#userDisplay").html(response);
			$(".authenOnly").show();
		}, true);
	}
	else {
		$(".notAuthen").show();
	}
</script>