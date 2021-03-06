<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${ error eq true }">
	<div class="alert alert-danger">Please fill out account fields correctly.</div>
</c:if>

<form:form commandName="userAccountDto" class="form-horizontal">
 
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2><spring:message text="${user.name}" /></h2>
			
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
		<label for="email" class="col-sm-2 control-label">Email (*):</label>
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
	
	<security:authorize access="hasRole('ROLE_ADMIN')">
	
	<div class="form-group">
		<label class="col-sm-2 control-label">In black list:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<input type="checkbox" name="inBlackList" value="true" 
			${user.inBlackList == true ? 'checked' : ''} >
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">Enabled:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<input type="checkbox" name="enabled" value="true" 
			${user.enabled == true ? 'checked' : ''} >
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-2 control-label">Orders:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<a href="<spring:url value="/users/${user.id}/orders" />" >Click to view user orders</a> 
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-2 control-label">Comments:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<textarea name="comments" rows="1" cols="70" class="form-control"></textarea>
		</div>
	</div>
	</security:authorize>	
	
	<div class="form-group">
		<label class="col-sm-2 control-label"> </label>
		<div class="col-sm-10">
			<strong>(*) - required fields</strong>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-10">
			<button type="submit" name="save_account" value="${user.id}" class="btn btn-lg btn-primary">Save account</button>
			<a href="#" onclick="window.history.go(-1); return false;" class="btn btn-lg btn-default" role="button">Cancel</a>
		</div>
		
	</div>
 
</form:form>