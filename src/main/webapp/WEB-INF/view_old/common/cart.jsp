<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<h1>
	<fmt:message key="cart.Cart" />
</h1>
<br>

<h3>${message}</h3>
<br>

<c:if test="${ !(productMap eq null) }">

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

			<b>
				<fmt:message key="cart.Amount" />
			</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${order.amount}
		<br>
		<br>
		<br>

			<form action="order" method="post">
				<button type="submit" name="make_an_order" >
					<fmt:message key="cart.Make_an_order_button" />
				</button>
			</form>

		<br>

			<form action="cart" method="post">
				<button type="submit" name="clear_cart" >
					<fmt:message key="cart.Clear_cart_button" />
				</button>
			</form>
	</c:when>
	<c:otherwise>
		<fmt:message key="cart.Your_cart_is_empty" />.
	</c:otherwise>
</c:choose>

</c:if>
