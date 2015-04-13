<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty message_info}" >
	<div class="alert alert-info" role="alert">${message_info}</div>
</c:if>

<c:if test="${not empty message_success}">
	<div class="alert alert-success">${message_success}</div>
</c:if>

<c:if test="${empty listOfOrders}" >
	<div class="alert alert-info" role="alert">You don't have any orders yet.</div>
</c:if>

<c:if test="${not empty listOfOrders}" >

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>Order #</th>
			<th>Date</th>
			<th>Status</th>
			<th>Amount</th>
			<th>Comments</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOfOrders}" var="order">
			<tr>
				<td>
					<a href='<spring:url value="/orders/${order.id}" />' class="btn btn-default" >${order.id}</a>
				</td>
				<td>
					${order.date}
				</td>
				<td>
					${order.orderStatus}
				</td>
				<td>
					${order.amount}
				</td>
				<td>
					${order.comments}
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<!-- <nav> -->
<!--   <ul class="pager"> -->
<%--   	<c:if test="${page > 1}"> --%>
<%--     	<li><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />">Previous</a></li> --%>
<%--   	</c:if> --%>
<%--   	<c:if test="${page < totalPages}"> --%>
<%--     	<li><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />">Next</a></li> --%>
<%--   	</c:if> --%>
<!--   </ul> -->
<!-- </nav> -->

</c:if>