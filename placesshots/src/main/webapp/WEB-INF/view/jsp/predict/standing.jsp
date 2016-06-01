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
		<table style="width:700px;" border="0">
			<tr>
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>อันดับ</b>
					</div>
				</td>
				<td valign="top">
					<div style="text-align:left;">
						<b>&nbsp;</b>
					</div>
				</td>
<!-- 				<td valign="top" style="width:80px;"> -->
<!-- 					<div style="text-align:center;"> -->
<!-- 						<b>คะแนน<br/>รวม</b> -->
<!-- 					</div> -->
<!-- 				</td> -->
<!-- 				<td valign="top" style="width:80px;"> -->
<!-- 					<div style="text-align:center;"> -->
<!-- 						<b>คะแนน<br/>Extra</b> -->
<!-- 					</div> -->
<!-- 				</td> -->
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>คะแนน
<!-- 						<br/>ทายผล -->
						</b>
					</div>
				</td>
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>ทาย</b>
					</div>
				</td>
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>ถูก (4)</b>
					</div>
				</td>
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>ถูก (1)</b>
					</div>
				</td>
				<td valign="top" style="width:80px;">
					<div style="text-align:center;">
						<b>ผิด</b>
					</div>
				</td>
			<tr>
			<c:forEach var="userPointDto" items="${listUserPointDto}" varStatus="idx">
			<tr>
				<td style="height:25px;">
					<div style="text-align:center;">
						<c:out value="${userPointDto.rank}" />
					</div>
				</td>
				<td style="height:25px;">
					<c:out value="${userPointDto.username}" />
				</td>
<!-- 				<td style="height:25px;"> -->
<!-- 					<div style="text-align:center;"> -->
<!-- 						<span class="displayPoint"> -->
<%-- 							<c:out value="${userPointDto.totalPoint}" /> --%>
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 				</td> -->
<!-- 				<td style="height:25px;"> -->
<!-- 					<div style="text-align:center;"> -->
<!-- 						<span class="displayPoint"> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${userPointDto.extraPointFlag == 'Y'}"> --%>
<%-- 									<c:if test="${userPointDto.extraPoint == 99}"> --%>
<!-- 										? -->
<%-- 									</c:if> --%>
<%-- 									<c:if test="${userPointDto.extraPoint < 99}"> --%>
<%-- 										<c:out value="${userPointDto.extraPoint}" /> --%>
<%-- 									</c:if> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									- -->
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 				</td> -->
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.point}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.predictCount}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.correctResultAndScore}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.correctResult}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.incorrectResult}" />
						</span>
					</div>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>