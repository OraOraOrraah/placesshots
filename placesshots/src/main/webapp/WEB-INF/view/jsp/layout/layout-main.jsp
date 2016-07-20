<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="com.kugiojotaro.placesshots.constant.PlaceShotsConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
String username = (String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER);
if (username == null) {
	username = "";
}
%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	
	<link rel="stylesheet" href="<c:url value="/css/placesshots.css" />" />
	<link rel="stylesheet" href="<c:url value="/css/jquery-ui.css" />" />
	
	<script type="text/javascript" src="<c:url value="/js/form.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.21.custom.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI-2.64.0.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/common.js" />"></script>
	
	<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	var user = "<%=username%>";
	$(document).ready(function() {
		//alert("ready");
	});
	
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