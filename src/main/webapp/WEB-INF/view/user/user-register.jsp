<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${ error == true }">
	<div class="alert alert-danger"><spring:message code="register.Error_message" /></div>
</c:if>

<form:form commandName="userRegisterDto" cssClass="form-horizontal">

	<c:if test="${success eq true }">
		<div class="alert alert-success"><spring:message code="register.Success_message" /></div>
	</c:if>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"><spring:message code="register.Name" /> (*):</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="firstName" class="col-sm-2 control-label"><spring:message code="register.First_name" />:</label>
			<div class="col-sm-10">
				<form:input path="firstName" cssClass="form-control"/>
				<form:errors path="firstName" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="lastName" class="col-sm-2 control-label"><spring:message code="register.Last_name" />:</label>
			<div class="col-sm-10">
				<form:input path="lastName" cssClass="form-control"/>
				<form:errors path="lastName" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label"><spring:message code="register.Email" /> (*):</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label"><spring:message code="register.Phone" />:</label>
			<div class="col-sm-10">
				<form:input path="phone" cssClass="form-control"/>
				<form:errors path="phone" class="text-danger" />
			</div>
		</div>
			<div class="form-group">
			<label for="address" class="col-sm-2 control-label"><spring:message code="register.Address" />:</label>
			<div class="col-sm-10">
				<form:input path="address" cssClass="form-control"/>
				<form:errors path="address" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label"><spring:message code="register.Password" /> (*):</label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control"/>
				<form:errors path="password" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="password_confirm" class="col-sm-2 control-label"><spring:message code="register.Confirm_password" /> (*):</label>
			<div class="col-sm-10">
				<form:password path="confirmPassword" cssClass="form-control"/>
				<form:errors class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<strong>(*) - <spring:message code="register.required_fields" /></strong>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<button type="submit" name="do_register" value="" class="btn btn-lg btn-primary" ><spring:message code="register.Register" /></button>
			</div>
		</div>

</form:form> 


