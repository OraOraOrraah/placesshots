<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="predictExtraSave" value="/predict/extrasave" />
<c:url var="predictExtraResult" value="/predict/extraresult" />
<c:url var="predictGettime" value="/predict/gettime" />

<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
/* Jquery UI overide */
.ui-widget-header {
    background: #0066cc;
    border: 0;
    color: #fff;
    font-weight: normal;
}
.ui-dialog-title {
    font-size: 18px;
}
.ui-dialog-content {
	font-size: 18px;
	font-weight: bold;
	color: #000000;
}
</style>
</head>
<body>
	<br/><br/>
	<table border="0" align="center">
	<c:if test="${prediected == 'N'}">
		<c:if test="${not empty listTeam}">
		<tr id="formSave">
			<td valign="top" style="width:600px;">
				<form:form name="formPredictChampion" id="formPredictChampion" method="POST" modelAttribute="predictChampionDto" acceptCharset="UTF-8">
					<form:hidden path="round" />
					แชมป์ Euro 2016 คือทีม
					<form:select path="teamId" id="teamId">
						<form:option value="" />
			            <form:options items="${listTeam}" />
					</form:select>
					<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" disabled="disabled" />
					<br/><br/>
						<%-- 					<c:if test="${prediected == 'Y'}"> --%>
	<%-- 						<c:out value="${predictedChampionTeam}" /> --%>
	<%-- 						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictedChampionTeam)}.png" width="32" height="16" /> --%>
	<!-- 						<br/> -->
	<!-- 						รอบที่ทาย :  -->
	<%-- 						<c:choose> --%>
	<%-- 							<c:when test="${predictedChampionRound == '16'}"> --%>
	<!-- 						    	16 ทีม -->
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${predictedChampionRound == '8'}"> --%>
	<!-- 						    	8 ทีม -->
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${predictedChampionRound == '4'}"> --%>
	<!-- 						    	รองชนะเลิศ -->
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${predictedChampionRound == '2'}"> --%>
	<!-- 						    	ชิงชนะเลิศ -->
	<%-- 							</c:when> --%>
	<%-- 							<c:otherwise> --%>
	<!-- 							&nbsp; -->
	<%-- 							</c:otherwise> --%>
	<%-- 					</c:choose> --%>
	<%-- 					</c:if> --%>
				</form:form>
			</td>
		</tr>
		</c:if>
	</c:if>
		<tr>
			<td valign="top" style="width:600px;">
				<div style="text-align:center;">
					<div id="extraResult"></div>
				</div>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		$(document).ready(function() {
			window.setTimeout(function loadExtraResult() {
		    	ajaxGet('${predictExtraResult}', null, header, token, function(response) {
					$("#extraResult").show();
					$("#extraResult").html(response);
				}, true);
		    }, 1000);
		});
		
		$("#teamId").change(function(){
			if ($("#teamId").val() == "") {
				$("#buttonSave").prop("disabled", true);
			}
			else {
				$("#buttonSave").removeAttr('disabled');
			}
		});
		
		$("#buttonSave").click(function(){
			if ($("#teamId").val() != "") {
				$.kui.dialog.confirmBox('เมื่อ กด save แล้ว จะไม่สามารถแก้ไขได้อีก <br/>ยืนยันการ save?', 'Confirm', 'Yes', function() {
					//$("#successMsgDiv").hide();
					$("#formSave").hide();
					$("#extraResult").hide();
					ajaxPost('${predictExtraSave}', $('#formPredictChampion').serialize(), header, token, function(response) {
						if (response.result == "success") {
							window.setTimeout(function loadExtraResult() {
						    	ajaxGet('${predictExtraResult}', null, header, token, function(response) {
									$("#extraResult").show();
									$("#extraResult").html(response);
									$("#successMsgDiv").hide();
								}, true);
						    }, 1000);
						}
					});
				});
			}
		});
	</script>
</body>
</html>