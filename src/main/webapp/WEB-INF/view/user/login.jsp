<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/WEB-INF/layout/taglib.jsp" %>
	
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title" /></title>

<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>

</head>

<body>

<form class="form-signin" action="<spring:url value="/j_spring_security_check" />" method="post">

	<h2 class="form-signin-heading"><spring:message code="login.Please_sign_in" /></h2>
	
	<c:if test="${login_error eq true}">
		<div class="alert alert-warning"><spring:message code="login.Error_message" /></div>
	</c:if>

	<input type="text" name="j_username" class="form-control" placeholder="<spring:message code="login.Name" />" required autofocus> 
	<input type="password" name="j_password" class="form-control" placeholder="<spring:message code="login.Password" />" required>
	<div class="row">
		<div class="col-md-8"><button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.Sign_in" /></button></div>
		<div><a href="#" onclick="window.history.go(-1); return false;" class="btn btn-lg btn-default" role="button"><spring:message code="login.Cancel" /></a></div>
	</div>
</form>

</body>
</html>