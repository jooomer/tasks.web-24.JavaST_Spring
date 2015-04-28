<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<security:authorize access="hasRole('ROLE_ADMIN')" >
	<h2>User ${user.name}</h2>
</security:authorize>

<c:if test="${empty listOfOrders}" >
	<div class="alert alert-info" role="alert">List of orders is empty.</div>
</c:if>

<c:if test="${not empty listOfOrders}" >

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th style="width:70px">Order #</th>
			<th style="width:120px">Date</th>
			<th>Status</th>
			<th style="width:100px">Amount, $</th>
			<th>Comments</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOfOrders}" var="order">
			<tr>
				<td>
					<a href='<spring:url value="/orders/${order.orderNumber}" />' class="btn btn-default" >${order.orderNumber}</a>
				</td>
				<td>
					<fmt:formatDate value="${order.date}" type="both" dateStyle="long" timeStyle="short" />
				</td>
				<td>
					${order.orderStatus.name}
				</td>
				<td>
					 <fmt:formatNumber type="number"  minFractionDigits="2" value="${order.amount}"/>
				</td>
				<td>
					${order.comments}
				</td>
				<td>
					<c:if test="${order.orderStatus eq 'WAITING_FOR_PAIMENT'}" >
						<form action="<spring:url value="/orders" />" method="post">
							<button type="submit" name="cancel_order" value="${order.id}" class="btn btn-danger btn-sm">Cancel</button>
						</form>
					</c:if>
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