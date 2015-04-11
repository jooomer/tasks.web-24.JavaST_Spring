<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="form-horizontal">
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2>${user.name}</h2>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">First name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${user.firstName}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Last name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${user.lastName}
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${user.email}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Phone:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${user.phone}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Address:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${user.address}
		</div>
	</div>
</div>

<br>

<c:forEach items="${user.products}" var="product">

	<h3>${product.name}</h3>

	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${product.id}
				<td>${product.name}</td>
				<td>${product.description}</td>
			</tr>
		</tbody>
	</table>

</c:forEach>