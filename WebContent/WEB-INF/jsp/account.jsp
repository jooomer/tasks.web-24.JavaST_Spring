<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<form action="account-update" class="form-horizontal">

<%-- 
	<c:if test="${param.success eq true }">
		<div class="alert alert-success">Congratulations! Your account is successfully updated.</div>
	</c:if>
 --%>
 
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2><c:out value="${user.name}" /></h2>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">First name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.firstName}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Last name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.lastName}" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.email}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Phone:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.phone}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Address:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.address}" />
		</div>
	</div>
	
<!-- 	
	<div class="form-group">
		<div class="col-sm-10">
			<input type="submit" value="Change your account data" class="btn btn-lg btn-primary" />
		</div>
	</div>
 -->
 
</form>