<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<c:if test="${ error eq true }">
	<div class="alert alert-danger">
		<spring:message code="error.fill_out_fields_correctly" text="Please fill out all fields correctly." />
	</div>
</c:if>

<form:form commandName="category" cssClass="form-horizontal">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">
				<spring:message code="category.Enter_new_category" text="Enter new category" />:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<button type="submit" name="do-add-category" value="" class="btn btn-lg btn-primary" >
					<spring:message code="category.Create_new_category" text="Create new category" />
				</button>
			</div>
		</div>

</form:form> 