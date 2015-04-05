<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

	<h1>
		<fmt:message key="login.Register" />
	</h1>
	<br>
	
	<h3>${message}</h3>
	<br>


<form:form commandName="user" >
<div id="container">
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.User_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="name" />
			</p>
		</div>
		<div id="right">
				<form:errors path="name"/>
		</div>
	</div>
	
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.First_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="firstName" />
			</p>
		</div>
		<div id="right">
		</div>
	</div>
	
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.Last_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="lastName" />
			</p>
		</div>
		<div id="right">
			<p>
			</p>
		</div>
	</div>
	
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.email" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="email" />
			</p>
		</div>
		<div id="right">
			<p>
				<form:errors path="email"/>
			</p>
		</div>
	</div>
	
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="login.Create_your_password" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="password" />
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
				<fmt:message key="login.Confirm_your_password" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<form:input path="confirmPassword" />
			</p>
		</div>
		<div id="right">
			<p>
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
				<input type="submit" value="<fmt:message key="login.Register_button" />" />
			</p>
		</div>
		<div id="right">
		</div>
	</div>
</div>
</form:form>


	
	