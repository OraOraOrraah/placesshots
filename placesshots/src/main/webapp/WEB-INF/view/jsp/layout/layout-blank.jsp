<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	
	<link rel="stylesheet" href="<c:url value="/css/placesshots.css" />" />
	
	<script type="text/javascript" src="<c:url value="/js/form.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.21.custom.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI-2.64.0.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/common.js" />"></script>
</head>
<body>
	<div id="successMsgDiv2" class="ui-widget" style="display: none;">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span></p>
			<p id="successMsgDivP" class="success">Success</p>
		</div>
	</div>
	<tiles:insertAttribute name="content"/>
</body>
</html>