<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${not (message eq null)}">
	<div class="alert alert-success" >${message}</div>
</c:if>

<c:if test="${ error eq true }">
	<div class="alert alert-danger">Please fill out account fields correctly.</div>
</c:if>

<form:form commandName="category" cssClass="form-horizontal">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Enter new category:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" class="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<button type="submit" name="do-add-category" value="" class="btn btn-lg btn-primary" >Create new category</button>
			</div>
		</div>

</form:form> 