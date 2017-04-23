<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="com.kugiojotaro.placesshots.util.Consts, com.kugiojotaro.placesshots.dto.AuthUser, org.springframework.web.util.UrlPathHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="authUserInfo" value="/authUserInfo" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />

<%
AuthUser authUser = (AuthUser) request.getSession().getAttribute(Consts.AUTHEN_USER);
String userId = "";
String displayName = "";
String imageURL = "";
if (authUser != null) {
	userId = authUser.getUserId() + "";
	displayName = authUser.getDisplayName();
	imageURL = authUser.getImageURL();
}
String originatingServletPath = new UrlPathHelper().getOriginatingServletPath(request);
%>

<form action="${logoutUrl}" id="logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<!-- <div class="navbar-wrapper"> -->
<!--     <div class="container-fluid"> -->
<!--         <nav class="navbar"> navbar-fixed-top -->
<!--             <div class="container"> -->
               
<!--                 <div id="navbar" class="navbar-collapse collapse"> -->
<!--                     <ul class="nav navbar-nav"> -->
<%--                         <li class="<%=originatingServletPath.equals("/predict/index") ? "active" : ""%>"><a href="<c:url value="/predict/index" />">ทายผล</a></li> --%>
<%--                         <li class="<%=originatingServletPath.equals("/predict/result") ? "active" : ""%>"><a href="<c:url value="/predict/result" />">ผลการทาย</a></li> --%>
<%--                         <li class="<%=originatingServletPath.equals("/predict/standing") ? "active" : ""%>"><a href="<c:url value="/predict/standing" />">อันดับคะแนน</a></li> --%>
<%--                         <li class="<%=originatingServletPath.equals("/predict/history") ? "active" : ""%>"><a href="<c:url value="/predict/history" />">ประวัติการทาย</a></li> --%>
<%--                         <li class="<%=originatingServletPath.equals("/fixture/list") ? "active" : ""%>"><a href="<c:url value="/fixture/list" />">โปรแกรมการแข่งขัน</a></li> --%>
<!--                     </ul> -->
<!--                     <ul class="nav navbar-nav pull-right"> -->
<%--                         <li class="dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=displayName%><img src="<%=imageURL%>" height="20" width="20" /><span class="caret"></span></a> --%>
<!--                             <ul class="dropdown-menu"> -->
<!--                                 <li><a href="a.html">Change Password</a></li> -->
<!--                                 <li class=""><a href="#" onclick="document.getElementById('logout').submit();">Logout</a></li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </nav> -->
<!--     </div> -->
<!-- </div> -->