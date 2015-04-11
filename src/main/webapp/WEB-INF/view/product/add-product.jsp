<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<form:form commandName="addProductDto" cssClass="form-horizontal">

		<div class="form-group">
			<label for="productCategory" class="col-sm-2 control-label">Product category:</label>
			<div class="col-sm-10">
				<form:select path="productCategory.name" cssClass="form-control">
					<form:option value="">--- SELECT PRODUCT CATEGORY ---</form:option>
					<form:options items="${listOfProductCategories}" itemValue="name" itemLabel="name" />
				</form:select>
				<form:errors path="productCategory.name" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">Description:</label>
			<div class="col-sm-10">
				<form:input path="description" cssClass="form-control"/>
				<form:errors path="description" />
			</div>
		</div>
		<div class="form-group">
			<label for="price" class="col-sm-2 control-label">Price:</label>
			<div class="col-sm-10">
				<form:input path="price" cssClass="form-control"/>
				<form:errors path="price" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="submit" value="Submit" class="btn btn-lg btn-primary" />
			</div>
		</div>

</form:form> 