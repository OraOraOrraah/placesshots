<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="authenUser" value="/authenUser" />
<c:url var="signinFacebook" value="/signin/facebook" />
<c:url var="forgotpassword" value="/forgotpassword" />
<c:url var="predictIndex" value="/predict/index" />
<c:url var="checkUsername" value="/signup/checkUsername" />
<c:url var="signup" value="/signup/save" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    	<div class="panel panel-info">
        	<div class="panel-heading">
            	<div class="panel-title">Sign In</div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="${forgotpassword}">Forgot password?</a></div>
            </div>
			<div style="padding-top:30px" class="panel-body">
				<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<!-- formLogin -->
				<form id="formLogin" class="form-horizontal" role="form">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div style="margin-bottom: 25px" class="input-group">
                	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="login-username" type="text" class="form-control" name="username" placeholder="username" />                                        
                </div>       
				<div style="margin-bottom: 25px" class="input-group">
                	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="login-password" type="password" class="form-control" name="password" placeholder="password" />
                </div>
                </form>
                <div>
                	<div id="login-error" class="msg-error"></div>
                </div>
                <div class="input-group">
                	<div class="checkbox">
                    	<label>
                        	<input id="login-remember" type="checkbox" name="_spring_security_remember_me" value="1"> Remember me
                        </label>
                    </div>
                </div>
                <!-- formFacebookSignin -->
                <form id="formFacebookSignin" action="${signinFacebook}" method="POST">
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="scope" value="public_profile" />
				</form>
                <div style="margin-top:10px" class="form-group">
					<div class="col-sm-12 controls">
						<a id="btn-login" href="#" class="btn btn-success">Login</a>
                        <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-12 control">
						<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                        	Don't have an account!
                            <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">Sign Up Here</a>
                        </div>
                    </div>
				</div>
			</div>
        </div>
    </div>
	<div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
        	<div class="panel-heading">
            	<div class="panel-title">Sign Up</div>
               	<div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
            </div>
			<div class="panel-body">
            	<form id="signupform" class="form-horizontal" role="form">
					<div id="signupalert" style="display:none" class="alert alert-danger">
                    	<p>Error:</p>
                        <span></span>
                    </div>
                    <div class="form-group">
                    	<label for="email" class="col-md-3 control-label">Username</label>
                        <div class="col-md-9">
                        	<input type="text" id="signupUsername" name="username" class="form-control" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-group">
                    	<label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                        	<input type="password" id="signupPassword" name="password" class="form-control" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                    	<label for="password" class="col-md-3 control-label">Password Confirm</label>
                        <div class="col-md-9">
                        	<input type="password" id="signupPasswordConfirm" name="passwordConfirm" class="form-control" placeholder="Password Confirm">
                        </div>
                    </div>
                    <div class="form-group">
                    	<div class="col-md-offset-3 col-md-9">
                        	<input type="button" id="btnSignup" type="button" class="btn btn-info" value="Sign Up" />
<!--                             <span style="margin-left:8px;">or</span> -->
                        </div>
                    </div>
<!--                     <div style="border-top: 1px solid #999; padding-top:20px"  class="form-group"> -->
<!--                     	<div class="col-md-offset-3 col-md-9"> -->
<!--                         	<input type="button" id="btn-fbsignup" type="button" class="btn btn-primary" value="Sign Up with Facebook" /> -->
<!--                         </div> -->
<!--                     </div> -->
				</form>
			</div>
		</div>
	</div> 
</div>
<script type="text/javascript">
$("#btn-login").click(function(){
	$("#login-error").html("");
	ajaxPost('${authenUser}', $('#formLogin').serialize(), header, token, function(response) {
		if (response.result == "success") {
			location = '${predictIndex}';
		}
		else {
			$("#login-error").html("Username or Password is incorrect!");
		}
	});
});
$("#btn-fblogin").click(function(){
	$("#formFacebookSignin").submit();
});

/* SignUp */
$("#signupUsername").on("keypress", function(event) {
    var englishAlphabetAndWhiteSpace = /[A-Za-z0-9]/g;
    var key = String.fromCharCode(event.which);
    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || englishAlphabetAndWhiteSpace.test(key)) {
        return true;
    }
    return false;
});
$('#signupUsername').on("paste",function(e) {
    e.preventDefault();
});
$('#signupUsername').on("blur",function() {
	if ($('#signupUsername').val() != "" && $('#signupUsername').val().length >= 4) {
		ajaxPost('${checkUsername}', {'username': $('#signupUsername').val()}, header, token, function(response) {
			if (response.result == "success") {
				if (response.returnValue == "Y") {
					alert("Username Exists!");
				}
			}
		});
	}
});
$("#btnSignup").click(function(){
	$(".fieldError").hide();
	//
	ajaxPost('${checkUsername}', {'username':$('#signupUsername').val()}, header, token, function(response) {
		if (response.result == "success") {
			if (response.returnValue == "Y") {
				alert("Username Exists!");
			}
			else {
				//
				ajaxPost('${signup}', $('#signupform').serialize(), header, token, function(response) {
					if (response.result == "success") {
						ajaxPost('${authenUser}', $('#signupform').serialize(), header, token, function(response) {
							if (response.result == "success") {
								location = '${predictIndex}';
							}
							else {
								alert("Error!");
							}
						});
					}
					else {
						for (var key in response.errorsMap) {
				            $("[name='"+key+"']").after("<span class=\"require fieldError\" id=\""+key+"Id\">&nbsp;"+response.errorsMap[key]+"</span>");
						}
					}
				});
			}
		}
		else {
			alert("Error!");
		}
	});
});
</script>
</body>
</html>