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
	<div style="margin-left: 20px;">
		Date :
		<select id="week">
			<c:forEach var="data" items="${listWeek}">
				<option value="${data.key}">${data.value}</option>
			</c:forEach>
		</select>
	</div>
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
								<td style="width:250px;" valign="top">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td align="right" style="width:100px;">
												<c:out value="${predictResultDetailDto.homeShortTitle}" />
												<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictResultDetailDto.homeShortTitle)}.png" width="32" height="16" />
											</td>
											<td align="center" style="width:50px;">
												<span class="highlightScore">
													<c:out value="${predictResultDetailDto.homeScore}" />
													-
													<c:out value="${predictResultDetailDto.awayScore}" />
												</span>
											</td>
											<td align="left" style="width:100px;">
												<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictResultDetailDto.awayShortTitle)}.png" width="32" height="16" />
												<c:out value="${predictResultDetailDto.awayShortTitle}" />
											</td>
										</tr>
										<c:if test="${not empty predictResultDetailDto.homeExtraTimeScore}">
											<tr>
												<td align="right" style="width:100px;">&nbsp;</td>
												<td align="center" style="width:50px;">
													<c:out value="${predictResultDetailDto.homeTotalScore}" /> - <c:out value="${predictResultDetailDto.awayTotalScore}" />
												</td>
												<td align="left" style="width:100px;">
													(AET)
												</td>
											</tr>
										</c:if>
										<c:if test="${not empty predictResultDetailDto.homePenaltyScore}">
											<tr>
												<td align="right" style="width:100px;">&nbsp;</td>
												<td align="center" style="width:50px;">
													<c:out value="${predictResultDetailDto.homePenaltyScore}" /> - <c:out value="${predictResultDetailDto.awayPenaltyScore}" />
												</td>
												<td align="left" style="width:100px;">
													(PEN)
												</td>
											</tr>
										</c:if>
									</table>
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
				<td style="width:200px;height:50px;" valign="top">
					<c:out value="${predictResultDto.username}" />
				</td>
				<td style="height:50px;" valign="top">
					<table cellspacing="0" cellpadding="0">
						<tr>
						<c:forEach var="predictResultDetailDto" items="${predictResultDto.listPredictResultDetailDto}">
							<td style="width:250px;height:50px;" valign="top">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td align="right" style="width:100px;">&nbsp;</td>
										<td style="width:50px;">
											<c:if test="${not empty predictResultDetailDto.predictHomeScore}">
												<div style="text-align: center;">
													<span class="highlightScore">
														<c:out value="${predictResultDetailDto.predictHomeScore}" /> - <c:out value="${predictResultDetailDto.predictAwayScore}" />
													</span>
												</div>
												<c:if test="${not empty predictResultDetailDto.homeScore}">
													<div style="text-align: center;">
														<span class="displayPoint">
															<c:out value="${predictResultDetailDto.point}" />
														</span>
													</div>
												</c:if>
											</c:if>
										</td>
										<td align="left" style="width:100px;">&nbsp;</td>
									</tr>
								</table>
							</td>
						</c:forEach>
							<td style="width:100px;height:50px;" valign="top">
								<c:if test="${not empty predictResultDto.displayPoint}">
									<div style="text-align: center;">
										<br/>
										<span class="displayPoint">
											<c:out value="${predictResultDto.point}" />
										</span>
									</div>
								</c:if>
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