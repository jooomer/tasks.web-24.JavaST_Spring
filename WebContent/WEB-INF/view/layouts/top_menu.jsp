<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<c:set var="language" value="${pageContext.response.locale}"/>

<div id="top-navigation">
	<form method="get">
		<a title="My Account" href="<spring:url value="/account" />"> <fmt:message
				key="top_menu.MY_ACCOUNT" />
		</a> <a class="cart" title="Shopping Cart" href="<spring:url value="/cart" />"> <fmt:message
				key="top_menu.SHOPPING_CART" />
		</a>&nbsp;&nbsp; <select id="language" name="lang" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
		</select>
	</form>
</div>

<div class="welcome-message">
	<security:authorize access="!isAuthenticated()">
		<fmt:message key="top_menu.Hi_guest" />&nbsp;&nbsp;
			<a href="<spring:url value="/login" />"> <fmt:message key="top_menu.Sign_in" />&nbsp;</a>
			<a href="<spring:url value="/register" />"> <fmt:message key="top_menu.Register" />&nbsp;&nbsp;</a>
	</security:authorize>

	<security:authentication var="principal" property="principal" />
	<security:authorize access="isAuthenticated()">
		<fmt:message key="top_menu.Hi" />&nbsp;${principal.username}!&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<spring:url value="/logout" />"> <fmt:message
				key="top_menu.Logout" />&nbsp;&nbsp;
		</a>
	</security:authorize>
</div>

