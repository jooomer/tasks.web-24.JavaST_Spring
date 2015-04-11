<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<jsp:useBean id="product" scope="session"
	class="ua.store.model.entity.Product" />

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
<%-- <form:form action="<spring:url value="/cart" />" method="post"> --%>
<!-- 	<button type="submit" name="command" value="send_to_cart"> -->
<%-- 		<fmt:message key="common_product.Send_to_cart_button" /> --%>
<!-- 	</button> -->
<%-- </form:form> --%>
<br>

<form:form>
	<input type="submit" name="send-to-cart" value="<fmt:message key="common_product.Send_to_cart_button" />" />
	<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
<!-- 		<input type="submit" name="update" value="Update product" /> -->
		<input type="submit" name="delete" value="Delete product" />
	</security:authorize>
</form:form>

