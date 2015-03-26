<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${not (message eq null)}">
	<div class="alert alert-success" >${message}</div>
</c:if>

<form:form commandName="productType" cssClass="form-horizontal">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Enter new product type:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="submit" value="Submit" class="btn btn-lg btn-primary" />
			</div>
		</div>

</form:form> 