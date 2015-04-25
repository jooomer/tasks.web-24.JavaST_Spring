<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<%-- <c:if test="${empty listOfOrderItems}"> --%>
<!-- 		<fmt:message key="cart.Your_cart_is_empty" />. -->
<!-- 	<div class="alert alert-info" role="alert">Your cart is empty.</div> -->
<%-- </c:if> --%>

<c:if test="${not empty listOfOrderItems}">
	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Category</th>
				<th>Product name</th>
				<th>Description</th>
				<th style="width:100px">Price, $</th>
				<th>Quantity</th>
				<th style="width:100px">Amount, $</th>
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
						<fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.product.price}"/>
					</td>
					<td>
						${orderItem.productsQuantity}
					</td>
					<td>
						<fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.amount}"/>
					</td>
					<td>
						<form:form>
						 	<button type="submit" name="remove_from_cart" value="${orderItem.product.id}" class="btn btn-danger btn-sm">Remove</button>
						</form:form>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<th>Summary</th>
					<th>${order.amount}</th>
					<td></td>
				</tr>
		</tbody>
	</table>

	<div class="row">
		<div class="col-xs-12 col-md-10">
			<form:form action="order">
				<p class="text-right" >
					<input type="submit" name="make_order" value="Make order" class="btn btn-success btn-lg" />
				</p>
			</form:form>
		</div>
		<div class="col-xs-6 col-md-2">
			<form:form>
				<p class="text-right" >
					<input type="submit" name="clean_cart" value="Clean cart" class="btn btn-primary btn-lg" />
				</p>
			</form:form>
		</div>
	</div>
	
</c:if>

