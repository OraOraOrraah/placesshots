<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="predictIndex" value="/predict/index" />
<c:url var="predictSave" value="/predict/save" />

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
	<form id="formPredict" name="formPredict">
		<input type="hidden" name="week" value="${week}" />
		<table>
			<c:forEach var="predictDisplayDto" items="${listPredictDisplayDto}" varStatus="idx">
				<input type="hidden" id="fixtureId" name="fixtureId" value="${predictDisplayDto.fixtureId}" />
				<input type="hidden" id="redCardFlag<c:out value="${idx.index}" />" name="redCardFlag" value="${predictDisplayDto.redCardFlag}" />
				<tr>
					<td>
					    <c:out value="${predictDisplayDto.homeTitle}" />
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictDisplayDto.homeShortTitle)}.png" width="32" height="16" />
						-
						<c:out value="${predictDisplayDto.awayTitle}" />
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictDisplayDto.awayShortTitle)}.png" width="32" height="16" />
					</td>
					<td>
						<select id="homeScore<c:out value="${idx.index}" />" name="homeScore">
							<option value="">-</option>
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</td>
					<td>-</td>
					<td>
						<select id="awayScore<c:out value="${idx.index}" />" name="awayScore">
							<option value="">-</option>
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</td>
					<!--
					<td>
						<input type="checkbox" id="redCardFlagCheckBox<c:out value="${idx.index}" />" class="checkboxRedCardFlag" value="<c:out value="${idx.index}" />" />
					</td>
					-->
				</tr>
				<script type="text/javascript">
					setPredictedScore(<c:out value="${idx.index}" />, <c:out value="${predictDisplayDto.homeScore}" />, <c:out value="${predictDisplayDto.awayScore}" />);
					//setPredictedRedCardFlag(<c:out value="${idx.index}" />, <c:out value="${predictDisplayDto.redCardFlag}" />);
				</script>
			</c:forEach>
		</table>
	</form>
	<br/>
	
	<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer; display:none;" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			if ("${live}" == "1") {
				$("#buttonSave").show();
			}
		});
	
		$("#buttonSave").click(function(){
			ajaxPost('${predictSave}', $('#formPredict').serialize(), header, token, function(response) {
				if (response.result == "success") {
					$("#successMsgDiv2").show();
				}
			});
		});
		
		/*
		$(".checkboxRedCardFlag").click(function(){
			if ($(this).attr("checked") == "checked") {
				$("#redCardFlag" + $(this).val()).val("1");
			}
			else {
				$("#redCardFlag" + $(this).val()).val("");
			}
		});
		*/
	</script>
</body>
</html>