<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/predict/index" />">Main</a>
| <a href="<c:url value="/predict/rule" />">Rule</a>
| <a href="<c:url value="/predict/result" />">Result</a>
<%-- | <a href="<c:url value="/predict/extra" />">Extra</a> --%>
| <a href="<c:url value="/predict/standing" />">Standing</a>

<span class="authenOnly" style="display:none;">
<%-- 	| <a href="<c:url value="/changepassword" />">Change Password</a> --%>
	| <a href="<c:url value='/j_spring_security_logout' />"> Logout</a>
</span>

<div class="authenOnly" style="text-align:right;display:none;">
	<span id="userDisplay"></span>
	<span id="userIconDisplay"></span>
</div>
	
<script type="text/javascript">
	if (user != null && user != "" && user != "anonymousUser") {
		//$("#userDisplay").html(user);

		var url = "<c:url value="/user/logined" />/" + $.now();
		ajaxPost(url, {username: user}, function(response) {
			if (response != "") {
				$("#userDisplay").html(response);
				$(".authenOnly").show();
			}
		}, true);
		
		/*
		ajaxPost("<c:url value="/user/icon" />", {username: user}, function(response) {
			if (response != "") {
				$("#userIconDisplay").html("<img src=" + response + " height=16 width=32 />");
			}
		}, true);
		*/
	}
	
	/*
	$("#userDisplay").click(function(){
		location = "${pageContext.request.contextPath}/predict/myresult";
	});
	*/
</script>