<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="predictExtraSave" value="/predict/extrasave" />
<c:url var="predictExtraResult" value="/predict/extraresult" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<form:form name="formPredictChampion" id="formPredictChampion" method="POST" modelAttribute="predictChampionDto" acceptCharset="UTF-8">
		<form:hidden path="round" />
		<table>
			<tr>
				<td>
				ทีมที่จะเป็นแชมป์:
					<c:if test="${prediected == 'N'}">
						<form:select path="teamId">
							<form:option value="" label="--- ยังไม่เลือก ---" />
		                    <form:options items="${listTeam}" />
						</form:select>
					</c:if>
					<c:if test="${prediected == 'Y'}">
						<c:out value="${predictedChampionTeam}" />
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictedChampionTeam)}.png" width="32" height="16" />
						<br/>
						รอบที่ทาย : 
						<c:choose>
							<c:when test="${predictedChampionRound == '8'}">
						    	8 ทีม
							</c:when>
							<c:when test="${predictedChampionRound == '4'}">
						    	รองชนะเลิศ
							</c:when>
							<c:when test="${predictedChampionRound == '2'}">
						    	ชิงชนะเลิศ
							</c:when>
							<c:otherwise>
							&nbsp;
							</c:otherwise>
					</c:choose>
					</c:if>
				</td>
			</tr>
		</table>
		
		<c:if test="${enableSave == 1}">
			<br/>
			<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		</c:if>
	</form:form>
	<br/><br/>
	
	<div style="text-align:center;">
		<!--
		<input type="button" id="buttonRefresh" value="Refresh" style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
		-->

		<div id="extraResult"></div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			window.setTimeout(function loadExtraResult() {
		    	ajaxGet('${predictExtraResult}', null, function(response) {
					$("#extraResult").show();
					$("#extraResult").html(response);
				}, true);
		    }, 1000);
		});
		
		$("#buttonSave").click(function(){
			$("#successMsgDiv").hide();
			$("#extraResult").hide();
			
			ajaxPost('${predictExtraSave}', $('#formPredictChampion').serialize(), function(response) {
				if (response.result == "success") {
					
					$("#successMsgDiv").show();
					
					window.setTimeout(function loadExtraResult() {
				    	ajaxGet('${predictExtraResult}', null, function(response) {
							$("#extraResult").show();
							$("#extraResult").html(response);
						}, true);
				    }, 1000);
				}
			});
		});
		
		$("#buttonRefresh").click(function(){
			ajaxGet('${predictExtraResult}', null, function(response) {
				$("#extraResult").html(response);
			}, true);
		});
	</script>
</body>
</html>