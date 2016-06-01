<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript">
	function setPredictedScore(index, homeScore, awayScore) {
		$("#homeScore" + index).val(homeScore);
		$("#awayScore" + index).val(awayScore);
	}
	
	/*
	function setPredictedRedCardFlag(index, redCardFlag) {
		if (redCardFlag == "1") {
			$("#redCardFlagCheckBox" + index).attr("checked", checked);
			$("#redCardFlag" + index).val(redCardFlag);
		}
	}
	
	function setRedCardFlag(index, redCardFlag) {
		$("#redCardFlag" + index).attr("checked", checked);
	}
	*/
</script>
</head>
<body>
	<br/><br/>
	Date 
	<select id="week">
		<c:forEach var="data" items="${listWeek}">
			<option value="${data.key}">${data.value}</option>
		</c:forEach>
	</select>
	<br/><br/>
	
	<form id="formMain" name="formMain">
		<input type="hidden" id="formMainWeek" name="week" />
	</form>
	
	<div id="result">
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#week").val("${week}");
			
			$("#formMainWeek").val($("#week").val());
			var url = "<c:url value="/predict/data" />/" + $("#week").val() + "/" + $.now();
			ajaxPost(url, $('#formMain').serialize(), function(response) {
				$("#result").html(response);
			}, true);
		});
		
		$("#week").live('change', function(){
			$("#result").html("");
			$("#formMainWeek").val($("#week").val());
			var url = "<c:url value="/predict/data" />/" + $("#week").val() + "/" + $.now();
			ajaxPost(url, $('#formMain').serialize(), function(response) {
				$("#result").html(response);
			}, true);
		});
	</script>
</body>
</html>