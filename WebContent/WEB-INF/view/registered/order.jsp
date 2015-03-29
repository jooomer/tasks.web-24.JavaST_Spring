<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<h1>
	<fmt:message key="order.Order" />
</h1>
<br>

<pre>
	<b><fmt:message key="order.Customer" />:</b>	${user.firstName} ${user.lastName}

	<b><fmt:message key="order.Date" />:</b>		${order.date}

	<b><fmt:message key="order.Amount" />:</b>		${order.amount}

	<b><fmt:message key="order.Ship_to" />:</b>	${user.address}

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

<form action="order" method="post">
	<b><fmt:message key="order.Your_comments" />:</b><br>
	<textarea name="comments" rows="5" cols="70"></textarea>
	<br>
	<br>
	<button type="submit" name="save_and_checkout" ><fmt:message key="order.Save_and_checkout_button" /></button>
</form>
<br>
