<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

	<h1>
		<fmt:message key="login.Sign_in" />
	</h1>
	<br> 

<form action="<c:url value="/j_spring_security_check" />" method="post">
<div id="container">
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.User_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<input type="text" name="j_username" />
			</p>
		</div>
		<div id="right">
		</div>
	</div>
	
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.Password" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<input type="password" name="j_password" />
			</p>
		</div>
		<div id="right">
			<p>
				<form:errors path="password"/>
			</p>
		</div>
	</div>
	
	
	<div id="row">
		<div id="left">
			<h3>
			</h3>
		</div>
		<div id="middle">
			<p>
				<button type="submit" name="command" value="login">
					<fmt:message key="login.Sign_in_button" />
				</button>
				<strong><a href="<spring:url value="/register" />" >or register</a></strong>
			</p>
		</div>
		<div id="right">
		</div>
	</div>
</div>
</form>

	
