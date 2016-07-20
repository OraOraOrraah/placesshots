<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="fixtureList" value="/adm/fixture/list" />
<c:url var="fixtureSave" value="/adm/fixture/save" />
<c:url var="fixtureUpdateScore" value="/adm/fixture/update_score" />
<c:url var="predictGettime" value="/predict/gettime" />

<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript">
	function setScore(index, homeScore, awayScore) {
		$("#homeScore" + index).val(homeScore);
		$("#awayScore" + index).val(awayScore);
	}
</script>
</head>
<body>
<div id="showTime"></div>
	<br/><br/>
	week 
	<select id="week">
		<c:forEach var="data" items="${listWeek}">
			<option value="${data.value}">${data.value}</option>
		</c:forEach>
	</select>
	<br/><br/>
	<table>
	<c:forEach var="fixtureDto" items="${listFixtureDto}" varStatus="idx">
		<input type="hidden" id="id<c:out value="${idx.index}" />" value="<c:out value="${fixtureDto.id}" />" />
		<tr>
			<td><c:out value="${fixtureDto.homeTitle}" /> - <c:out value="${fixtureDto.awayTitle}" /></td>
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
			<td>
				<select id="homeExtraTimeScore<c:out value="${idx.index}" />" name="homeExtraTimeScore">
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
			<td>
				<select id="awayExtraTimeScore<c:out value="${idx.index}" />" name="awayExtraTimeScore">
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
			<td>
				<select id="homePenaltyScore<c:out value="${idx.index}" />" name="homePenaltyScore">
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
			<td>
				<select id="awayPenaltyScore<c:out value="${idx.index}" />" name="awayPenaltyScore">
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
			<td>
				<input type="button" id="buttonSave" value='Save' style="width: 100px; font-family: Tahoma; font-size: 13px; font-style: normal; cursor: pointer;" onclick="updateScore(<c:out value="${idx.index}" />)" />
			</td>
		</tr>
		<script type="text/javascript">
			setScore(<c:out value="${idx.index}" />, <c:out value="${fixtureDto.homeScore}" />, <c:out value="${fixtureDto.awayScore}" />);
		</script>
	</c:forEach>
	</table>
	
	<form:form name="formFixture" id="formFixture">
		<input type="text" id="formFixtureId" name="id" />
		<input type="text" id="formFixtureHomeScore" name="homeScore" />
		<input type="text" id="formFixtureAwayScore" name="awayScore" />
		<input type="text" id="formFixtureHomeExtraTimeScore" name="homeExtraTimeScore" />
		<input type="text" id="formFixtureAwayExtraTimeScore" name="awayExtraTimeScore" />
		<input type="text" id="formFixtureHomePenaltyScore" name="homePenaltyScore" />
		<input type="text" id="formFixtureAwayPenaltyScore" name="awayPenaltyScore" />
	</form:form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#week").val("${week}");
			
			setInterval(function() {
				$.ajax({
					  url: '${predictGettime}',
					  cache: false,
					  type: "POST",
					  success: function(response){
						  $("#showTime").html(response.result);
					  }
					});
			}, 1000);
		});
		
		$("#week").live('change', function(){
			location = '${fixtureList}' + "/" + $("#week").val();
		});
		
		function updateScore(idx) {
			$("#formFixtureId").val($("#id" + idx).val());
			$("#formFixtureHomeScore").val($("#homeScore" + idx).val());
			$("#formFixtureAwayScore").val($("#awayScore" + idx).val());
			$("#formFixtureHomeExtraTimeScore").val($("#homeExtraTimeScore" + idx).val());
			$("#formFixtureAwayExtraTimeScore").val($("#awayExtraTimeScore" + idx).val());
			$("#formFixtureHomePenaltyScore").val($("#homePenaltyScore" + idx).val());
			$("#formFixtureAwayPenaltyScore").val($("#awayPenaltyScore" + idx).val());
			
			ajaxPost('${fixtureUpdateScore}', $('#formFixture').serialize(), function(response) {
				if (response.result == "success") {
					$("#successMsgDiv").show();
				}
			});
		}
	</script>
</body>
</html>