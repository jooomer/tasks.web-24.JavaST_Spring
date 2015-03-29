<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<h1>
	<fmt:message key="orders.Orders" />
</h1>
<br>

<h3>${message}</h3>
<br>

<c:choose>
	<c:when test="${ordersList.size != 0}" >
		<ulist:orders-table rows="${ ordersList.size }" >
			${ ordersList.orderId } </td><td>
			${ ordersList.orderDate } </td><td>
			${ ordersList.amount } </td><td>
			${ ordersList.orderStatus } </td><td>
			${ ordersList.comments } </td><td>
			${ ordersList.productNamesList }
		</ulist:orders-table>
	</c:when>
	<c:otherwise>
		<fmt:message key="orders.list_is_empty" />.
	</c:otherwise>
</c:choose>
