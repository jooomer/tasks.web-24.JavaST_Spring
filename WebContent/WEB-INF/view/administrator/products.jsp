<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.Product"%>

<h1>
	<fmt:message key="products.List_of_products" />
</h1>
<br>

<h3>${message}</h3>
<br>

<form action="administrator/product" method="post">
	<fmt:message key="products.Search" />:
	<input type="text" name="productName" />
	<button type="submit" name="command" value="search_product_by_productName">
		<fmt:message key="products.Search_button" />
	</button>
</form>
<br>

<hr/>
<br>
<ulist:product-table rows="${ productMap.size }" >
	
	<c:set var="productId" value="${ productMap.productId }" />
	
	${productId} </td><td>
	${ productMap.productType } </td><td>
	<a href="<c:url value="/products/${ productId }" />" >${ productMap.productName }</a> </td><td>
	${ productMap.price } </td><td>
	${ productMap.quantityInStock } </td><td>
	${ productMap.description }
</ulist:product-table>

