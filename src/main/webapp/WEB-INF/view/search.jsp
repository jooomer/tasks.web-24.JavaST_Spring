<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<form:form commandName="searchString" cssClass="form-horizontal">

	
		<div class="form-group">
			<div class="col-sm-10">
				<form:input path="searchString" cssClass="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="submit" value="Search" class="btn btn-lg btn-primary" />
			</div>
		</div>

</form:form> 

<c:out value="${searchString.searchString}" />