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
</script>
</head>
<body>
	<br/><br/>
	<div style="margin-left: 20px;">
	Date 
	<select id="week">
		<c:forEach var="data" items="${listWeek}">
			<option value="${data.key}">${data.value}</option>
		</c:forEach>
	</select>
	<div id="90" style="display:none;">
		<br/>
		<span class="require">* คิดคะแนนจากผลการแข่งขัน 90 นาที</span>
	</div>
	<br/><br/>
	<form id="formMain" name="formMain">
		<table border="0">
			<c:forEach var="predictDisplayDto" items="${listPredictDisplayDto}" varStatus="idx">
				<input type="hidden" name="week" value="${week}" />
				<input type="hidden" id="fixtureId" name="fixtureId" value="${predictDisplayDto.fixtureId}" />
				<tr>
					<td align="right">
					    <c:out value="${predictDisplayDto.homeTitle}" />
					</td>
					<td valign="bottom">
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictDisplayDto.homeShortTitle)}.png" width="32" height="16" />
					</td>
					<td align="center">
						<select id="homeScore<c:out value="${idx.index}" />" name="homeScore">
							<option value="">&nbsp;</option>
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
					<td align="center">-</td>
					<td align="center">
						<select id="awayScore<c:out value="${idx.index}" />" name="awayScore">
							<option value="">&nbsp;</option>
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
					<td valign="bottom">
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictDisplayDto.awayShortTitle)}.png" width="32" height="16" />
					</td>
					<td>
						<c:out value="${predictDisplayDto.awayTitle}" />
					</td>
				</tr>
				<script type="text/javascript">
					setPredictedScore(<c:out value="${idx.index}" />, <c:out value="${predictDisplayDto.homeScore}" />, <c:out value="${predictDisplayDto.awayScore}" />);
				</script>
			</c:forEach>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td colspan="3" align="center">
					<c:if test="${live == 1}">
						<br/>
						<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" />
					</c:if>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#week").val("${week}");
		});
		
		$("#week").change(function(){
			location = '${predictIndex}' + "/" + $("#week").val();
		});
		
		$("#buttonSave").click(function(){
			ajaxPost('${predictSave}', $('#formMain').serialize(), header, token, function(response) {
				if (response.result == "success") {
					$("#successMsgDiv").show();
				}
			});
		});
		
		if (("${week}"*1) >= 14) {
			$("#90").show();
		}
	</script>
</body>
</html>