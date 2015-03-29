<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<h1>
	<fmt:message key="checkout.Checkout" />
</h1>
<br>

<h3><c:out value='${requestScope["message"]}' /></h3>
<br>
<fmt:message key="checkout.all_orders" />

<a href="account" >
<fmt:message key="checkout.your_account" />
</a>
