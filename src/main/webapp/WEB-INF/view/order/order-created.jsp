<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">Order #:</th>
			<td>${order.id}</td>
		</tr>
		<tr>
			<th>Date:</th>
			<td>${order.date}</td>
		</tr>
		<tr>
			<th>Amount:</th>
			<td>${order.amount}</td>
		</tr>
		<tr>
			<th>Customer:</th>
			<td>${user.firstName}&nbsp;${user.lastName}</td>
		</tr>
		<tr>
			<th>Ship address:</th>
			<td>${user.address}</td>
		</tr>
		<tr>
			<th>Order status:</th>
			<td>${order.orderStatus}</td>
		</tr>
		<tr>
			<th>Your comments:</th>
			<td>${order.comments}</td>
		</tr>
	</tbody>
</table>
<br>

	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Category</th>
				<th>Product name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderItem" items="${listOfOrderItems}">
				<tr>
					<td>${orderItem.product.productCategory.name}</td>
					<td><a
						href='<spring:url value="/products/${orderItem.product.id}" />'>${orderItem.product.name}</a>
					</td>
					<td>${orderItem.product.description}</td>
					<td>${orderItem.product.price}</td>
					<td>${orderItem.productsQuantity}</td>
					<td>${orderItem.amount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<th>Summary</th>
				<th>${order.amount}</th>
			</tr>
		</tbody>
	</table>
<br>
