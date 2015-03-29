<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.Product"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/view/tld/custom.tld"%>

<h1>
	<fmt:message key="cart.Cart" />
</h1>
<br>

<h3>${message}</h3>
<br>

<c:set var="productMap" value='${sessionScope["productMap"]}' />

<c:choose>
	<c:when test="${productMap.size != 0}" >
		<ulist:cart-table rows="${ productMap.size }" >
			${ productMap.productId } </td><td>
			${ productMap.productType } </td><td>
			${ productMap.productName } </td><td>
			${ productMap.price } </td><td>
			${ productMap.description }
		</ulist:cart-table>
		<br>

			<c:set var="cart" value="${sessionScope['cart']}" />
			<b>
				<fmt:message key="сart.Amount" />
			</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cart.amount}
		<br>
		<br>
		<br>

			<form action="order" method="post">
				<button type="submit" name="make_an_order" >
					<fmt:message key="сart.Make_an_order_button" />
				</button>
			</form>

		<br>

			<form action="cart" method="post">
				<button type="submit" name="command" >
					<fmt:message key="сart.Clear_cart_button" />
				</button>
			</form>
	</c:when>
	<c:otherwise>
		<fmt:message key="сart.Your_cart_is_empty" />.
	</c:otherwise>
</c:choose>
