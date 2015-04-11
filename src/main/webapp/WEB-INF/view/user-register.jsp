<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<c:if test="${ error == true }">
	<div class="alert alert-danger">Please fill out the register form correctly.</div>
</c:if>

<form:form commandName="userRegisterDto" cssClass="form-horizontal">

	<c:if test="${success eq true }">
		<div class="alert alert-success">Congratulations! You are successfully registered. Now you can sign in.</div>
	</c:if>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name (*):</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="firstName" class="col-sm-2 control-label">First name:</label>
			<div class="col-sm-10">
				<form:input path="firstName" cssClass="form-control"/>
				<form:errors path="firstName" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="lastName" class="col-sm-2 control-label">Last name:</label>
			<div class="col-sm-10">
				<form:input path="lastName" cssClass="form-control"/>
				<form:errors path="lastName" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email (*):</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">Phone:</label>
			<div class="col-sm-10">
				<form:input path="phone" cssClass="form-control"/>
				<form:errors path="phone" class="text-danger" />
			</div>
		</div>
			<div class="form-group">
			<label for="address" class="col-sm-2 control-label">Address:</label>
			<div class="col-sm-10">
				<form:input path="address" cssClass="form-control"/>
				<form:errors path="address" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password (*):</label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control"/>
				<form:errors path="password" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="password_confirm" class="col-sm-2 control-label">Confirm password (*):</label>
			<div class="col-sm-10">
				<form:password path="confirmPassword" cssClass="form-control"/>
				<form:errors class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<strong>(*) - required fields</strong>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<input type="submit" value="Register" class="btn btn-lg btn-primary" />
			</div>
		</div>

</form:form> 


