<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">Date:</th>
			<td><fmt:formatDate value="${order.date}" type="both" dateStyle="long" timeStyle="short" /></td>
		</tr>
		<tr>
			<th>Amount, $:</th>
			<td><fmt:formatNumber type="number"  minFractionDigits="2" value="${order.amount}"/></td>
		</tr>
		<tr>
			<th>Ship address:</th>
			<td><c:out value="${user.address}" /></td>
		</tr>
	</tbody>
</table>

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
				<th width="80px">Price, $</th>
				<th>Quantity</th>
				<th width="100px">Amount, $</th>
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
					<td><fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.product.price}"/></td>
					<td>${orderItem.productsQuantity}</td>
					<td><fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.amount}"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<th>Summary</th>
				<th><fmt:formatNumber type="number"  minFractionDigits="2" value="${order.amount}"/></th>
			</tr>
		</tbody>
	</table>

	<form:form action="order">
		<table>
			<thead>
				<tr>
					<th></th>
					<th width="50%">Your comments:</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td><textarea name="comments" rows="1" cols="70" class="form-control"></textarea></td>
				</tr>
			</tbody>
		</table>
		<br>
		<p class="text-right">
			<input type="submit" name="save_and_checkout"
				value="Save and checkout" class="btn btn-success btn-lg" />
			<a href="#" onclick="window.history.go(-1); return false;" class="btn btn-lg btn-default" role="button">Cancel</a>
		</p>
	</form:form>

</c:if>

