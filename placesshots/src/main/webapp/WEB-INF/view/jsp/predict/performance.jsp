<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
<!-- 	<div align="right"> -->
<%-- 		คะแนนรวม : <span class="highlightScore">${totalPoint}</span> --%>
<!-- 	</div> -->
	<div align="center">
	ผู้ทาย : <span class="highlightScore">${predictUser}</span>
	<br/><br/><br/>
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td valign="top">
				<table cellspacing="0" cellpadding="0" border="0" style="width:565px;">
					<tr style="height:40px;">
						<th align="center" valign="top" style="width:450px;" colspan="3">ผลการแข่งขัน</th>
						<th align="center" valign="top" style="width:50px;">ทาย</th>
						<th style="width:15px;">&nbsp;</th>
						<th align="center" valign="top" style="width:50px;">คะแนน</th>
					</tr>
				</table>
			</td>
		</tr>
		<c:forEach var="dto" items="${listFixture}" varStatus="idx">
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">
					${dto.day}
				</td>
				<td valign="top">&nbsp;</td>
				<td valign="top">
					<c:forEach var="dto2" items="${dto.listFixture}" varStatus="idx">
						<table cellspacing="0" cellpadding="0" border="0" style="width:565px;">
							<tr>
								<td align="right" valign="top" style="width:200px;">
									${dto2.homeTitle}
									<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(dto2.homeShortTitle)}.png" width="32" height="16" />
								</td>
								<td align="center" valign="top" style="width:50px;">
									<span class="highlightScore">
										${dto2.homeScore} - ${dto2.awayScore}
									</span>
								</td>
								<td align="left" valign="top" style="width:200px;">
									<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(dto2.awayShortTitle)}.png" width="32" height="16" />
									${dto2.awayTitle}
								</td>
								<td align="center" valign="top" style="width:50px;">
									<c:if test="${not empty dto2.predictHomeScore}">
										<span class="highlightScore">
											${dto2.predictHomeScore} - ${dto2.predictAwayScore}
										</span>
									</c:if>
								</td>
								<td style="width:15px;">&nbsp;</td>
								<td align="center" valign="top" style="width:50px;">
									<c:if test="${not empty dto2.point}">
										<span class="displayPoint">${dto2.point}</span>
									</c:if>
								</td>
							</tr>
							<c:if test="${not empty dto2.homeExtraTimeScore}">
								<tr>
									<td align="right" valign="top" style="width:200px;">&nbsp;</td>
									<td align="center" valign="top" style="width:50px;">
										${dto2.homeScore + dto2.homeExtraTimeScore} - ${dto2.awayScore + dto2.awayExtraTimeScore}
									</td>
									<td align="left" valign="top" style="width:200px;">
										(AET)
									</td>
									<td align="center" valign="top" style="width:50px;">&nbsp;</td></td>
									<td style="width:15px;">&nbsp;</td>
									<td align="center" valign="top" style="width:50px;">&nbsp;</td></td>
								</tr>
							</c:if>
							<c:if test="${not empty dto2.homePenaltyScore}">
								<tr>
									<td align="right" valign="top" style="width:200px;">&nbsp;</td>
									<td align="center" valign="top" style="width:50px;">
										${dto2.homePenaltyScore} - ${dto2.awayPenaltyScore}
									</td>
									<td align="left" valign="top" style="width:200px;">
										(PEN)
									</td>
									<td align="center" valign="top" style="width:50px;">&nbsp;</td></td>
									<td style="width:15px;">&nbsp;</td>
									<td align="center" valign="top" style="width:50px;">&nbsp;</td></td>
								</tr>
							</c:if>
						</table>
						<br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="3"><hr/></td>
			</tr>
		</c:forEach>
			<tr>
				<td valign="top">&nbsp;</td>
				<td valign="top">&nbsp;</td>
				<td valign="top">
					<table cellspacing="0" cellpadding="0" border="0" style="width:565px;">
						<tr style="height:50px;">
							<td align="right" valign="top" style="width:200px;">คะแนนรวม</td>
							<td align="center" valign="top" style="width:50px;">&nbsp;</td>
							<td align="left" valign="top" style="width:200px;">&nbsp;</td>
							<td align="center" valign="top" style="width:50px;">&nbsp;</td>
							<td style="width:15px;">&nbsp;</td>
							<td align="center" valign="top" style="width:50px;">
								<span class="displayPoint">${totalPoint}</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	</div>
</body>
</html>