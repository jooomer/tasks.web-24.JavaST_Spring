<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

	<h1>
		<fmt:message key="login.Sign_in" />
	</h1>
	<br> 

	<form action="<c:url value="/j_spring_security_check" />" method="post">
		<p>
			<fmt:message key="login.User_name" />:
			<input type="text" name="j_username" />
		</p>
		<p>
			<fmt:message key="login.Password" />:
			<input type="password" name="j_password" />
		</p>
		<p>
			<button type="submit" name="command" value="login" >
				<fmt:message key="login.Sign_in_button" />
			</button>	
			<a href="<spring:url value="/register" />" >or register</a>
		</p>
	</form>
