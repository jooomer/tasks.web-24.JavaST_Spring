<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h1>
		<fmt:message key="login.Register" />
	</h1>
	<br>
	<h3>${message}</h3>

	<form action="register" method="post" >
		<p>
			<fmt:message key="login.User_name" />:
			<input type="text" name="name" />
		</p>
		<p>
			<fmt:message key="login.First_name" />:
			<input type="text" name="firstName" />
		</p>
		<p>
			<fmt:message key="login.Last_name" />:
			<input type="text" name="lastName" />
		</p>
		<p>
			<fmt:message key="login.Create_your_password" />:
			<input type="password" name="password" />
		</p>
		<p>
			<fmt:message key="login.Confirm_your_password" />:
			<input type="password" name="confirm_password" />
		</p>
		<p>
			<button type="submit" name="command" value="register" >
				<fmt:message key="login.Register_button" />
			</button>
		</p>
	</form>
