<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<div align="center">
	<table cellspacing="0" cellpadding="0"  border="0" style="width:600px;">
		<tr>
			<th>&nbsp;</th>
			<th>ทาย</th>
			<th>รอบที่ทาย</th>
			<th>คะแนน</th>
		</tr>
		<tr>
			<td><hr/></td>
			<td><hr/></td>
			<td><hr/></td>
			<td><hr/></td>
		</tr>
		<c:forEach var="predictChampionDisplayDto" items="${listPredictChampionDisplayDto}" varStatus="idx">
			<tr>
				<td style="width:200px;height:30px;" valign="top">
					<c:out value="${predictChampionDisplayDto.user}" />
				</td>
				<td style="width:200px;height:30px;text-align:center;" valign="top">
					&nbsp;
					<c:if test="${not empty predictChampionDisplayDto.teamShortTitle}">
						<c:out value="${predictChampionDisplayDto.teamTitle}" />
						<img src="${pageContext.request.contextPath}/img/flag/${fn:toLowerCase(predictChampionDisplayDto.teamShortTitle)}.png" width="32" height="16" />
					</c:if>
				</td>
				<td style="width:100px;height:30px;text-align:center;" valign="top">
					<c:choose>
						<c:when test="${predictChampionDisplayDto.round == '8'}">
							8 ทีม
						</c:when>
						<c:when test="${predictChampionDisplayDto.round == '4'}">
							รองชนะเลิศ
						</c:when>
						<c:when test="${predictChampionDisplayDto.round == '2'}">
							ชิงชนะเลิศ
						</c:when>
						<c:otherwise>
						&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
				<td style="width:100px;height:30px;text-align:center;" valign="top">
					&nbsp;
					<c:out value="${predictChampionDisplayDto.point}" />
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>