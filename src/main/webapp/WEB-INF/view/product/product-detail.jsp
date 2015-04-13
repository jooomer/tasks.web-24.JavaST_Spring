<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${not empty message}">
	<div class="alert alert-success">${message}</div>
</c:if>

<div class="form-horizontal">
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2>${product.name}</h2>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Id:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.id}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Published date:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.publishedDate}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Price:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.price}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Product category:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.productCategory.name}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Description:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.description}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form  method="post">
				<input type="submit" name="send_to_cart" value="Send to cart" class="btn btn-primary btn-lg"/>
			</form>
		</div>
	</div>
</div>

<br>


<c:if test="${success eq true}" >
	<a href="<spring:url value="/my-products/update/${product.id}" />" class="btn btn-primary">Update product</a>
	<a href="<spring:url value="/my-products/remove/${product.id}" />" class="btn btn-danger">Remove product</a>
</c:if>