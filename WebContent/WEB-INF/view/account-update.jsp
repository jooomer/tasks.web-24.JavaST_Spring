<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${ error eq true }">
	<div class="alert alert-danger">Please fill out account fields correctly.</div>
</c:if>

<form:form commandName="updatedUser" action="account" class="form-horizontal">
 
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2><spring:message text="${user.name}" /></h2>
			<form:input path="id" type="hidden" />
			<form:input path="name" type="hidden" />
			<form:input path="password" type="hidden" />
			<form:input path="confirmPassword" type="hidden" />
			<form:input path="userType" type="hidden" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">First name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form:input path="firstName" cssClass="form-control" placeholder="${user.firstName}" />
			<form:errors path="firstName" class="text-danger" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Last name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form:input path="lastName" cssClass="form-control" placeholder="${user.lastName}" />
			<form:errors path="lastName" class="text-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form:input path="email" cssClass="form-control" placeholder="${user.email}" />
			<form:errors path="email" class="text-danger" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Phone:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form:input path="phone" cssClass="form-control" placeholder="${user.phone}" />
			<form:errors path="phone" class="text-danger" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Address:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form:input path="address" cssClass="form-control" placeholder="${user.address}" />
			<form:errors path="address" class="text-danger" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-10">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>
 
</form:form>