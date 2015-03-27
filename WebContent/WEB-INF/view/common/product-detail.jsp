<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<jsp:useBean id="product" scope="session" class="ua.store.model.entity.Product" />

<h1>
	<fmt:message key="common_product.Product_page" />
</h1>
<br>

	<pre>
	<b><fmt:message key="common_product.Product_ID" />:</b>			${product.id}

	<b><fmt:message key="common_product.Product_type" />:</b> 			${product.productType.name}

	<b><fmt:message key="common_product.Product_name" />:</b>			${product.name}

	<b><fmt:message key="common_product.Product_price" />:</b>			${product.price}

	<b><fmt:message key="common_product.Product_description" />:</b> 	${product.description}
	</pre>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<form action="cart" method="post" >
	<button type="submit" name="command" value="send_to_cart" ><fmt:message key="common_product.Send_to_cart_button" /></button>

<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
	
	<a href="<spring:url value="/products/update/${product.id}" />" >Update product</a>
	<a href="<spring:url value="/products/remove/${product.id}" />" >Remove product</a>

	<button type="submit" name="command" value="send_to_cart" >Update product></button>
	<button type="submit" name="command" value="send_to_cart" >Remove product</button>

</security:authorize>


</form>

<form:form commandName="product">

	<button type="submit" name="command" value="send_to_cart" >Update product></button>
	<button type="submit" name="command" value="send_to_cart" >Remove product</button>

</form:form>

