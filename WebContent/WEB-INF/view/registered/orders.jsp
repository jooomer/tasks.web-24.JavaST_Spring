<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<%-- <%@ page import="ua.store.model.entity.Product"%> --%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->

<h1>
	<fmt:message key="orders.Orders" />
</h1>
<br>

<h3>${message}</h3>
<br>

<%-- <c:set var="ordersList" value='${sessionScope["ordersList"]}' /> --%>

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
