<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp"%>

<c:if test="${ error == true }">
	<div class="alert alert-danger">
		<spring:message code="error.fill_out_fields_correctly" text="Please fill out all fields correctly." />
	</div>
</c:if>

<form:form commandName="productDto" cssClass="form-horizontal">

		<div class="form-group">
			<label for="category" class="col-sm-2 control-label">Product category (*):</label>
			<div class="col-sm-10">
				<form:select path="category.name" cssClass="form-control">
					<form:option value="">
						<spring:message code="product.SELECT_PRODUCT_CATEGORY" text="--- SELECT PRODUCT CATEGORY ---" />
					</form:option>
					<form:options items="${listOfCategories}" itemValue="name" itemLabel="name" />
				</form:select>
				<form:errors path="category" class="text-danger"/>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">
				<spring:message code="product.Name" text="Name" /> (*):
			</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" class="text-danger"/>
			</div>
		</div>
		<div class="form-group">
			<label for="price" class="col-sm-2 control-label">
				<spring:message code="product.Price" text="Price" /> (*):
			</label>
			<div class="col-sm-10">
				<form:input path="price" cssClass="form-control"/>
				<form:errors path="price" class="text-danger"/>
			</div>
		</div>
		<div class="form-group">
			<label for="quantityInStock" class="col-sm-2 control-label">
				<spring:message code="product.Quantity_in_stock" text="Quantity in stock" /> (*):
			</label>
			<div class="col-sm-10">
				<form:input path="quantityInStock" cssClass="form-control"/>
				<form:errors path="quantityInStock" class="text-danger"/>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">
				<spring:message code="product.Description" text="Description" />:
			</label>
			<div class="col-sm-10">
				<form:textarea path="description" rows="3" cssClass="form-control"/>
				<form:errors path="description" class="text-danger"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<strong>
					(*) - <spring:message code="product.required_fields" text="required fields" />
				</strong>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<input type="submit" value="Submit" class="btn btn-lg btn-primary" />
			</div>
		</div>

</form:form> 