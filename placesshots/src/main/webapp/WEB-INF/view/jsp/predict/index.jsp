<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="predictIndex" value="/predict/index" />
<c:url var="predictSave" value="/predict/save" />

<script type="text/javascript">
	function setPredictedScore(index, homeScore, awayScore) {
		$("#homeScore" + index).val(homeScore);
		$("#awayScore" + index).val(awayScore);
	}
</script>

<!-- 	<div id="90" style="display:none;"> -->
<!-- 		<br/> -->
<!-- 		<span class="require">* คิดคะแนนจากผลการแข่งขัน 90 นาที</span> -->
<!-- 	</div> -->
	
<div class="container">
	<div class="row">
        <div class="text-center">
        <select id="week">
			<c:forEach var="data" items="${listWeek}">
				<option value="${data.key}">${data.value}</option>
			</c:forEach>
		</select>
		<br/><br/>
		</div>
	</div>
	<div class="row">
		<form id="form-predict" name="form-predict">
		<input type="hidden" name="week" value="${week}" />
		<div class="table-responsive">
  			<table class="table">
			<c:forEach var="predictDisplayDto" items="${listPredictDisplayDto}" varStatus="idx">
			<input type="hidden" id="fixtureId" name="fixtureId" value="${predictDisplayDto.fixtureId}" />
			<tr>
				<td class="col-md-6" style="border-bottom: 1px solid #ddd;" align="right">
					<c:out value="${predictDisplayDto.homeTitle}" />
					<img src="${pageContext.request.contextPath}/img/flag/${predictDisplayDto.homeShortTitle}.png" width="32" height="16" />
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
				<td style="border-bottom: 1px solid #ddd;" align="center">-</td>
				<td class="col-md-6" style="border-bottom: 1px solid #ddd;" align="left">
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
					<img src="${pageContext.request.contextPath}/img/flag/${predictDisplayDto.awayShortTitle}.png" width="32" height="16" />
					<c:out value="${predictDisplayDto.awayTitle}" />						
				</td>
			</tr>
			<script type="text/javascript">
				setPredictedScore(<c:out value="${idx.index}" />, <c:out value="${predictDisplayDto.homeScore}" />, <c:out value="${predictDisplayDto.awayScore}" />);
			</script>
			</c:forEach>
			</table>
		</div>
		</form>
    </div>
    <div class="row">
        <div class="text-center">
        	<input type="button" id="buttonSave" value='Save' class="btn btn-primary" />
		</div>
	</div>
</div>	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#week").val("${week}");
		});
		
		$("#week").change(function(){
			location = '${predictIndex}' + "/" + $("#week").val();
		});
		
		$("#buttonSave").click(function(){
			ajaxPost('${predictSave}', $('#form-predict').serialize(), header, token, function(response) {
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