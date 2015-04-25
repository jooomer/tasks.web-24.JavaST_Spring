<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">Order #:</th>
			<td>${order.id}</td>
		</tr>
		<tr>
			<th>Date:</th>
			<td><fmt:formatDate value="${order.date}" type="both" dateStyle="long" timeStyle="short" /></td>
		</tr>
		<tr>
			<th>Amount, $:</th>
			<td><fmt:formatNumber type="number"  minFractionDigits="2" value="${order.amount}"/></td>
		</tr>
		<tr>
			<th>Customer:</th>
<%-- 			<td>${user.firstName}&nbsp;${user.lastName}</td> --%>
			<td>${order.user.firstName}&nbsp;${order.user.lastName}</td>
		</tr>
		<tr>
			<th>Ship address:</th>
<%-- 			<td><c:out value="${user.address}" /></td> --%>
			<td><c:out value="${order.user.address}" /></td>
		</tr>
		<tr>
			<th>Order status:</th>
			<td>${order.orderStatus.name}
<%-- 			<c:if test="${not empty listOfOrderStatuses}" > --%>
<!-- 				&nbsp; -->
<%-- 				<form:form commandName="orderStatusStr" > --%>
<%-- 					<form:select path="orderStatusStr" onchange="submit()"> --%>
<%-- 						<form:option value="--- Set required status ---" /> --%>
<%-- 						<form:options items="${listOfOrderStatuses}" itemValue="name" itemLabel="name"/>					 --%>
<%-- 					</form:select> --%>
<%-- 				</form:form>				 --%>
<%-- 			</c:if> --%>
			</td>
		</tr>
		<tr>
			<th>Your comments:</th>
			<td><c:out value="${order.comments}" /></td>
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
				<th width="80px">Price, $</th>
				<th>Quantity</th>
				<th width="100px">Amount, $</th>
			</tr>
		</thead>
		<tbody>
<%-- 			<c:forEach var="orderItem" items="${listOfOrderItems}"> --%>
			<c:forEach var="orderItem" items="${order.orderItems}">
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
				<td><fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.amount}"/></td>
			</tr>
		</tbody>
	</table>

<c:if test="${order.orderStatus eq 'WAITING_FOR_PAIMENT'}" >
	<form action="<spring:url value="/orders" />" method="post">
		<p class="text-right">
			<button type="submit" name="cancel_order" value="${order.id}" class="btn btn-danger">Cancel this order</button>
		</p>
	</form>
</c:if>
