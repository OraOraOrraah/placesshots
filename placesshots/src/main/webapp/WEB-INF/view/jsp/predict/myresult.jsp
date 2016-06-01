<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	<div align="center">
	<br/><br/>
	<table cellspacing="0" cellpadding="0"  border="0">
		<c:forEach var="predictResultDto" items="${listPredictResultDto}" varStatus="idx">
			<tr>
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
</body>
</html>