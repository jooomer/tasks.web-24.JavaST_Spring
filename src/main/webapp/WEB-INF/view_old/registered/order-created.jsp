<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<h1>
	<fmt:message key="order_created.Order" />
</h1>
<br>

<h3>${message}</h3>
<br>

<pre>
	<b><fmt:message key="order_created.orderId" />:</b>	${order.id}

	<b><fmt:message key="order_created.Customer" />:</b>	${user.firstName} ${user.lastName}

	<b><fmt:message key="order_created.Date" />:</b>		${order.date}

	<b><fmt:message key="order_created.Amount" />:</b>		${order.amount}

	<b><fmt:message key="order_created.Order_status" />:</b>	${order.orderStatus}

	<b><fmt:message key="order_created.Ship_to" />:</b>	${user.address}

</pre>
<br>

<c:set var="productMap" value='${sessionScope["productMap"]}' />
<ulist:cart-table rows="${ productMap.size }" >
	${ productMap.productId } </td><td>
	${ productMap.productType } </td><td>
	${ productMap.productName } </td><td>
	${ productMap.price } </td><td>
	${ productMap.description }
</ulist:cart-table>
<br>

<b><fmt:message key="order_created.Your_comments" />:</b><br>
${order.comments}
