<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="fixtureSave" value="/adm/fixture/save" />

<form:form id="formFixture" action="${fixtureSave}" method="POST" modelAttribute="fixtureDto" class="form-horizontal absolute-center">
	<input type="hidden" id="mode" name="mode" value="${mode}" />
	<div class="form-group">
		<label class="control-label col-md-2" for="week">Week</label>
		<div class="col-md-4">
			<form:input path="week" id="week" maxlength="2" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2" for="date">Date</label>
		<div class="col-md-4">
			<form:input path="fixtureDate" id="fixtureDate" maxlength="10" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2" for="home">Home</label>
		<div class="col-md-4">
			<form:select path="homeId" id="homeId" items="${teams}" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2" for="away">Away</label>
		<div class="col-md-4">
			<form:select path="awayId" id="awayId" items="${teams}" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2" for="away">&nbsp;</label>
		<div class="col-md-4">
			<input type="button" id="buttonSave" value="Submit" class="btn btn-default" />
		</div>
	</div>
</form:form>
	
<script type="text/javascript">
$(function () {
    $('#fixtureDate').datepicker();
});

$("#buttonSave").click(function(){
	ajaxPost('${fixtureSave}', $('#formFixture').serialize(), function(response) {
		if (response.result == "success") {
			$("#successMsgDiv").show();
			$("#homeId").prop('selectedIndex', 0);
			$("#awayId").prop('selectedIndex', 0);
		}
	});
});
</script>