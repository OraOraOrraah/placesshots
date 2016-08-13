<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>

</head>
<body>
	<br/><br/>
	<div id="tabs">
		<ul>
	    	<li><a href="#tabs-11">รอบแรก นัด 1</a></li>
	    	<li><a href="#tabs-12">รอบแรก นัด 2</a></li>
	    	<li><a href="#tabs-13">รอบแรก นัด 3</a></li>
	    	<li><a href="#tabs-16">รอบ 16 ทีม</a></li>
	    	<li><a href="#tabs-8">รอบ 8 ทีม</a></li>
	    	<li><a href="#tabs-4">รอบรอง</a></li>
	    	<li><a href="#tabs-2">รอบชิง</a></li>
	    	<li><a href="#tabs-0">รวม</a></li>
	    </ul>
	    <div id="tabs-11">
	    	<table>
				<tr>
					<td valign="top" style="width:150px;">
						<div style="text-align:left;">
							&nbsp;
						</div>
					</td>
					<td valign="top" style="width:150px;">
						<div style="text-align:center;">
							<b>จำนวนนัดที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:180px;">
						<div style="text-align:center;">
							<b>จำนวนนัดที่ทายถูก(4)</b>
						</div>
					</td>
					<td valign="top" style="width:180px;">
						<div style="text-align:center;">
							<b>จำนวนนัดที่ทายถูก(2)</b>
						</div>
					</td>
					<td valign="top" style="width:180px;">
						<div style="text-align:center;">
							<b>จำนวนนัดที่ทายผิด(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings11}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-12">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings12}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-13">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings13}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-16">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings16}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-8">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings8}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-4">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings4}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-2">
	    	<table>
				<tr>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทาย</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
						</div>
					</td>
					<td valign="top" style="width:100px;">
						<div style="text-align:center;">
							<b>คะแนน</b>
						</div>
					</td>
				<tr>
			<c:forEach var="userPointDto" items="${standings2}" varStatus="idx">
				<tr>
					<td style="height:25px;">
						<c:out value="${userPointDto.username}" />
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.predictCount}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResultAndScore}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.correctResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="highlightScore">
								<c:out value="${userPointDto.incorrectResult}" />
							</span>
						</div>
					</td>
					<td style="height:25px;">
						<div style="text-align:center;">
							<span class="displayPoint">
								<c:out value="${userPointDto.point}" />
							</span>
						</div>
					</td>
				</tr>
			</c:forEach>
			</table>
	    </div>
	    <div id="tabs-0">
	    	<table border="0">
			<tr>
<!-- 				<td valign="top" style="width:80px;"> -->
<!-- 					<div style="text-align:center;"> -->
<!-- 						<b>อันดับ</b> -->
<!-- 					</div> -->
<!-- 				</td> -->
				<td valign="top" style="width:150px;">
					<div style="text-align:left;">
						<b>ผู้ทาย</b>
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
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>จำนวนนัด<br/>ที่ทาย</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>จำนวนนัด<br/>ที่ทายถูก<br/>(4)</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>จำนวนนัด<br/>ที่ทายถูก<br/>(2)</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>จำนวนนัด<br/>ที่ทายผิด<br/>(1)</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>คะแนน<br/>ทายผล
						</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>คะแนน<br/>ทายแชมป์
						</b>
					</div>
				</td>
				<td valign="top" style="width:100px;">
					<div style="text-align:center;">
						<b>คะแนนรวม
						</b>
					</div>
				</td>
				<td>&nbsp;</td>
			<tr>
			<c:forEach var="userPointDto" items="${standings}" varStatus="idx">
			<tr>
<!-- 				<td style="height:25px;"> -->
<!-- 					<div style="text-align:center;"> -->
<%-- 						<c:out value="${userPointDto.rank}" /> --%>
<!-- 					</div> -->
<!-- 				</td> -->
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
						<span class="highlightScore">
							<c:out value="${userPointDto.predictCount}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="highlightScore">
							<c:out value="${userPointDto.correctResultAndScore}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="highlightScore">
							<c:out value="${userPointDto.correctResult}" />
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="highlightScore">
							<c:out value="${userPointDto.incorrectResult}" />
						</span>
					</div>
				</td>
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
							<c:if test="${userPointDto.extraPoint == -1}">
								&nbsp;
							</c:if>
							<c:if test="${userPointDto.extraPoint == 1}">
								?
							</c:if>
							<c:if test="${userPointDto.extraPoint == 0}">
								0
							</c:if>
							<c:if test="${userPointDto.extraPoint > 1}">
								<c:out value="${userPointDto.extraPoint}" />
							</c:if>
						</span>
					</div>
				</td>
				<td style="height:25px;">
					<div style="text-align:center;">
						<span class="displayPoint">
							<c:out value="${userPointDto.totalPoint}" />
						</span>
					</div>
				</td>
				<td>
					<a href="<c:url value="/predict/history/${userPointDto.username}" />">
						<img src="${pageContext.request.contextPath}/img/icon-view.png" />
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	    </div>
	</div>
</body>
</html>