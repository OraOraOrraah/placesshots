<%@page import="com.sun.tools.internal.ws.processor.model.Response"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="com.kugiojotaro.placesshots.constant.PlaceShotsConstant, com.kugiojotaro.placesshots.dto.AuthUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
AuthUser userDetail = (AuthUser) request.getSession().getAttribute(PlaceShotsConstant.AUTHEN_USER);
String username = "";
if (userDetail != null) {
	username = userDetail.getUsername();
}
%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta name="_csrf" content="${_csrf.token}" />
 
	<script type="text/javascript" src="<spring:url value="/webjars/jquery/3.0.0/jquery.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/webjars/datatables/1.10.12/js/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/webjars/jquery-blockui/2.65/jquery.blockUI.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/common.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.spring-friendly.min.js" />"></script>
	
	<link type="text/css" rel="stylesheet" href="<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css" />" />
<%-- 	<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap-cerulean-311.min.css" />" /> --%>
	<link type="text/css" rel="stylesheet" href="<spring:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" />" />
	<link type="text/css" rel="stylesheet" href="<spring:url value="/webjars/datatables/1.10.12/css/dataTables.bootstrap.css" />" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/placesshots.css" />" />
	
	<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	var user = "<%=username%>";
	$(document).ready(function() {
		//alert("ready");
	});
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
    
	/*
	$.ajaxSetup({
		cache: false,
		global: false,
		type: "GET",
		beforeSend: function() {
			$("#dialog-pleasewait", parent.document).dialog('open');
			window.parent.openPleaseWaitDialog();
	  	},
	  	complete: function(){
	  		window.parent.closePleaseWaitDialog();
		}
	});
	*/
	</script>
	<script>
		$(function() {
	    	$("#tabs").tabs();
	  	});
	</script>
</head>
<body>
	<tiles:insertAttribute name="menu"/>
	<tiles:insertAttribute name="content"/>
	<div id="successMsgDiv" class="ui-widget" style="display: none;">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span></p>
			<p id="successMsgDivP" class="success">Success</p>
		</div>
	</div>
</body>
</html>