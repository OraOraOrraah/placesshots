<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="fixtureList" value="/adm/fixture/list" />
<c:url var="fixtureSave" value="/adm/fixture/save" />
<c:url var="fixtureUpdateScore" value="/adm/fixture/update_score" />
<c:url var="predictGettime" value="/predict/gettime" />


<script type="text/javascript">

init = function() {
    var table = $('table#users').DataTable({
        'ajax' : '/data/users',
        'serverSide' : true,
        'lengthMenu' : [[10, 25, 50, -1], [10, 25, 50, 'All']],
        columns : [ {
            data : 'id'
        }, {
            data : 'name'
        }, {
            data : 'role'
        }, {
            data : 'status'
        }, {
            data : 'address.town',
            render: function(data, type, row) {
                return data ? data : '';
            }
        }, {
            data : 'likes'
        }]
    });

    function handleClick(columnId, selector) {
        return function() {
            $(this).toggleClass('active');
            var filter = '';
            $(selector).each(function() {
                filter += (filter === '' ? '' : '+') + $(this).text();
            });
            table.columns(columnId).search(filter).draw();
        };
    }
    $('div#role_selector button').click(
        handleClick(2, 'div#role_selector button.active'));
    $('div#status_selector button').click(
        handleClick(3, 'div#status_selector button.active'));

    function handleInputUpdate() {
        var likes_lowerbound = $('input#likes_lt').val(),
            likes_upperbound = $('input#likes_gt').val(),
            filter = likes_lowerbound + ':' + likes_upperbound;
        table.columns(5).search(filter).draw();
    }
    $('input#likes_lt').on('change paste keyup', handleInputUpdate);
    $('input#likes_gt').on('change paste keyup', handleInputUpdate);
};

$(document).ready(init);

</script>

<div class="container" style="padding-top: 20px; padding-bottom: 20px;">

		<div class="row" style="margin-bottom: 20px;">
			<button class="btn btn-primary" type="button" data-toggle="collapse"
				data-target="#collapseFilters" aria-expanded="false"
				aria-controls="collapseFilters">Show filters</button>
		</div>

		<div class="panel panel-default collapse" id="collapseFilters">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-2">Role</label>
					<div id="role_selector" class="btn-group" role="group"
						aria-label="role">
						<button type="button" class="btn btn-default">ADMIN</button>
						<button type="button" class="btn btn-default">AUTHOR</button>
						<button type="button" class="btn btn-default">USER</button>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2">Status</label>
					<div id="status_selector" class="btn-group" role="group"
						aria-label="status">
						<button type="button" class="btn btn-default">ACTIVE</button>
						<button type="button" class="btn btn-default">BLOCKED</button>
					</div>
				</div>
				<!-- [Eclipse] remove 'input' from Preferences > Web > HTML Files > Editor -->
				<div class="form-inline form-group">
					<label class="col-sm-2">Likes</label>
					<div class="input-group">
						<span class="input-group-addon">&gt;</span>
						<input id="likes_lt" class="form-control"
							placeholder="More than..."></input>
					</div>
					<div class="input-group">
						<span class="input-group-addon">&lt;</span>
						<input id="likes_gt" class="form-control"
							placeholder="Less than..."></input>
					</div>
				</div>
			</div>
		</div>
		<table id="users" class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>role</th>
					<th>status</th>
					<th>town</th>
					<th>likes</th>
				</tr>
			</thead>
		</table>
	</div>