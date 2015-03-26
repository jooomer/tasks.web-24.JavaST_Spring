<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->

<div id="top-navigation">
	<form method="post">
		<a title="My Account" href="account"> <fmt:message
				key="top_menu.MY_ACCOUNT" />
		</a> <a class="cart" title="Shopping Cart" href="cart"> <fmt:message
				key="top_menu.SHOPPING_CART" />
		</a>&nbsp;&nbsp; <select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
		</select>
	</form>
</div>

<div class="welcome-message">
	<security:authorize access="!isAuthenticated()">
		<fmt:message key="top_menu.Hi_guest" />&nbsp;&nbsp;
			<a href="login"> <fmt:message key="top_menu.Sign_in" />&nbsp;</a>
			<a href="register"> <fmt:message key="top_menu.Register" />&nbsp;&nbsp;</a>
	</security:authorize>

	<security:authentication var="principal" property="principal" />
	<security:authorize access="isAuthenticated()">
		<fmt:message key="top_menu.Hi" />&nbsp;${principal.username}!&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="logout"> <fmt:message
				key="top_menu.Logout" />&nbsp;&nbsp;
		</a>
	</security:authorize>
</div>

