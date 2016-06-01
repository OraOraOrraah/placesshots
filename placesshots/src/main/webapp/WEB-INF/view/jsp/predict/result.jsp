<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="predictResult" value="/predict/result" />

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	Date :
	<select id="week">
		<c:forEach var="data" items="${listWeek}">
			<option value="${data.key}">${data.value}</option>
		</c:forEach>
	</select>
<!-- 	<br/><br/> -->
<!-- 	<span class="require">* นับคะแนนเฉพาะผลการแข่งขันใน 90 นาที</span> -->
	<div align="center">
	<br/><br/>
	<table cellspacing="0" cellpadding="0"  border="0">
		<c:forEach var="predictResultDto" items="${listPredictResultHeaderDto}" varStatus="idx">
			<tr>
				<td style="width:200px;" valign="top">
					&nbsp;
				</td>
				<td>
					<table cellspacing="0" cellpadding="0">
						<tr>
							<c:forEach var="predictResultDetailDto" items="${predictResultDto.listPredictResultDetailDto}">
								<td style="width:200px;" valign="top">
									<div style="text-align: center;">
										&nbsp;&nbsp;
										<c:out value="${predictResultDetailDto.homeShortTitle}" />
										<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictResultDetailDto.homeShortTitle)}.png" width="32" height="16" />
										<span class="highlightScore">
											&nbsp;<c:out value="${predictResultDetailDto.homeScore}" />
											-
											<c:out value="${predictResultDetailDto.awayScore}" />&nbsp;
										</span>
										<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictResultDetailDto.awayShortTitle)}.png" width="32" height="16" />
										<c:out value="${predictResultDetailDto.awayShortTitle}" />
										&nbsp;&nbsp;
										<br/>
										<c:if test="${not empty predictResultDetailDto.homeExtraTimeScore}">
											<br/>
											<c:out value="${predictResultDetailDto.homeTotalScore}" />
											-
											<c:out value="${predictResultDetailDto.awayTotalScore}" />
											(AET)
										</c:if>
										<c:if test="${not empty predictResultDetailDto.homePenaltyScore}">
											<br/>
											<c:out value="${predictResultDetailDto.homePenaltyScore}" />
											-
											<c:out value="${predictResultDetailDto.awayPenaltyScore}" />
											(PEN)
										</c:if>
									</div>
								</td>
							</c:forEach>
							<td style="width:100px;" valign="top">
								<div style="text-align: center;">
									คะแนน
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="width:200px;" valign="top">
				<hr/>
			</td>
			<td>
				<hr/>
			</td>
		</tr>
		<c:forEach var="predictResultDto" items="${listPredictResultDto}" varStatus="idx">
			<tr>
				<td style="width:200px;height:70px;" valign="top">
					<c:out value="${predictResultDto.username}" />
				</td>
				<td style="height:70px;" valign="top">
					<table cellspacing="0" cellpadding="0">
						<tr>
						<c:forEach var="predictResultDetailDto" items="${predictResultDto.listPredictResultDetailDto}">
							<td style="width:200px;height:70px;" valign="top">
								<div style="text-align: center;">
									<c:out value="${predictResultDetailDto.predictHomeScore}" /> - <c:out value="${predictResultDetailDto.predictAwayScore}" />
									<br/>
									<c:choose>
										<c:when test="${not empty predictResultDetailDto.point}">
											<span class="displayPoint">
												<c:out value="${predictResultDetailDto.point}" />
											</span>
										</c:when>
										<c:otherwise>
										-
										</c:otherwise>
									</c:choose>
								</div>
							</td>
						</c:forEach>
							<td style="width:100px;height:70px;" valign="top">
								<div style="text-align: center;">
									<br/>
									<c:choose>
										<c:when test="${not empty predictResultDto.point}">
											<span class="displayPoint">
												<c:out value="${predictResultDto.point}" />
											</span>
									    </c:when>
										<c:otherwise>
										-
										</c:otherwise>
									</c:choose>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#week").val("${week}");
		});
		
		$("#week").live('change', function(){
			location = '${predictResult}' + "/" + $("#week").val();
		});
	</script>
</body>
</html>