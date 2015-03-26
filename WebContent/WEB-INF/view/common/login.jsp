<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
		</p>
	</form>
