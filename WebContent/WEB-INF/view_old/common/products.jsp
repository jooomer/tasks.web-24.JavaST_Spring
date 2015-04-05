<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.Product"%>

<c:set var="productType" value='${sessionScope["productType"]}' />

<h1>
	<fmt:message key="products.List_of" />
	${productType}
	<fmt:message key="products.s" />
</h1>

<br>
<form action="product" method="post">
	<fmt:message key="products.Search_product" />:
	<input type="text" name="productName" />
	<button type="submit" name="command" value="common_search_product_by_productName">
		<fmt:message key="products.Search_product_button" />
	</button>
</form>
<br>

<hr/>
<br>
<ulist:product-table rows="${ productMap.size }" >
	${ productMap.productId } </td><td>
	${ productMap.productType } </td><td>
	${ productMap.productName } </td><td>
	${ productMap.price } </td><td>
	${ productMap.quantityInStock } </td><td>
	${ productMap.description }
</ulist:product-table>

