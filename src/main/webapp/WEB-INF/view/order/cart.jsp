<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${not empty msg}">
	<div class="alert alert-info" role="alert">${msg}</div>
</c:if>
<br>

<c:if test="${empty listOfOrderItems}">
	<!-- 	<fmt:message key="cart.Your_cart_is_empty" />. -->
	<div class="alert alert-info" role="alert">Your cart is empty.</div>
</c:if>

<c:if test="${not empty listOfOrderItems}">
	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Category</th>
				<th>Product name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderItem" items="${listOfOrderItems}">
				<tr>
					<td>
						${orderItem.product.productCategory.name}
					</td>
					<td>
						<a href='<spring:url value="/products/${orderItem.product.id}" />'>${orderItem.product.name}</a>
					</td>
					<td>
						${orderItem.product.description}
					</td>
					<td>
						${orderItem.product.price}
					</td>
					<td>
						${orderItem.productsQuantity}
					</td>
					<td>
						${orderItem.amount}
					</td>
					<td>
						Remove
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><strong>${order.amount}</strong></td>
					<td></td>
				</tr>
		</tbody>
	</table>
	
	<form:form>
		<p class="text-right" >
			<input type="submit" name="clean_cart" value="Clean a cart" class="btn btn-primary btn-lg" />
		</p>
	</form:form>
	<form:form action="order">
		<p class="text-right" >
			<input type="submit" name="make_order" value="Make an order" class="btn btn-success btn-lg" />
		</p>
	</form:form>
	
	
</c:if>

