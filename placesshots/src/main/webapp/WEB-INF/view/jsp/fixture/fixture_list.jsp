<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="fixtureLoad" value="/adm/fixture/load" />

<script type="text/javascript">

$(document).ready(function() {
	var fixtures;
	
// 	$('#fixtures').on( 'click', '.btn-details', function () {
//         alert($(this).attr("accessKey"));
// 	});

	$('#btnSearch').click(function () {
		if ($('#initFixtures').val() == "") {
			fixtures = $('table#fixtures').DataTable({
		        'ajax': {
		        	'url': 'http://localhost:9090/placesshots/fixture/load',
		        	'data': {
		        		 'params' : JSON.stringify($("#formParam").serializeArray())
		                //'customSearchs': [{'name': 'team', 'value': $('#team').val()}]
		             }
		        },
		        'serverSide': true,
		        'processing': true,
		        'preDrawCallback': function(settings) {
		        	$('#initFixtures').val("1");
		        	//alert(settings.aoData.length);
		        	if (settings.aoData.length == 0) {
		        		//$('table#fixtures').hide();
		        		//$('#fixtures_wrapper').hide();
		        	}
		        },
		        'initComplete': function() {
		        },
		        'language': {
		            'search': "ค้นหาจากชื่อทีม:",
		            'infoFiltered': "123"
		        },
		        'lengthMenu': [[10, 25, 50, -1], [10, 25, 50, 'All']],
		        'columns': [{data: 'fixtureDate'}, {data: 'round'}],
		        'columnDefs': [{"targets": 0,"class": "dt-center"},{"targets": 1,"class": "dt-center","sortable": false},{
		            'targets': 2,
		            'data': null,
		            'class': "dt-center",
		            /*'defaultContent': "555",*/
		            'render': function(data, type, row, meta) {
		                return row['homeTitle'] + " <img src='http://localhost:9090/placesshots/img/flag/" + row['homeShortTitle'] + ".png' width='32' height='16' />&nbsp;&nbsp;-&nbsp;&nbsp;<img src='http://localhost:9090/placesshots/img/flag/" + row['awayShortTitle'] + ".png' width='32' height='16' /> " + row['awayTitle'];
		            },
		        }]
		    });
		}
		else {
			//$('#team').val(JSON.stringify(fixtures.context[0].ajax));
			//fixtures.context[0].ajax.data = {'customSearchs': [{'name': 'homeTeam', 'value': $('#team').val()}]};
		}
		
		//fixtures.search("France").draw();
		fixtures.draw();
    });

});
</script>
<input type="hidden" id="initFixtures" />
<form id="formParam">
<input type="text" id="homeTeam" name="homeTeam" />
<input type="text" id="awayTeam" name="awayTeam" />
</form>
<input type="button" value="ok" id="btnSearch" />
<div class="container" style="padding-top: 20px; padding-bottom: 20px;">
	<table id="fixtures" class="table">
		<thead>
			<tr>
				<th class="dt-center">วันที่</th>
				<th class="dt-center">รอบ</th>
				<th class="dt-center">คู่ที่แข่งขัน</th>
			</tr>
		</thead>
	</table>
</div>