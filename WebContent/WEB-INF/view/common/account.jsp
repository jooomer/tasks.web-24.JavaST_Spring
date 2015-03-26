<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/view/tld/custom.tld"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<h1>
	<fmt:message key="my_account.My_account" />
</h1>
<br>
<jsp:useBean id="user" scope="session" class="ua.store.model.entity.User" />

<form action="account" method="post" accept-charset="UTF-8">
<pre>

<b><fmt:message key="my_account.userName" />:</b>	<c:out value="${user.name}"/>

<b><fmt:message key="my_account.First_name" />:</b>	<c:out value="${user.firstName}"/>
		<input type="text" name="firstName" />

<b><fmt:message key="my_account.Last_name" />:</b>	<c:out value="${user.lastName}"/>
		<input type="text" name="lastName" />

<b><fmt:message key="my_account.Email" />:</b>		<c:out value="${user.email}"/>
		<input type="text" name="email" />

<b><fmt:message key="my_account.Phone" />:</b>		<c:out value="${user.phone}"/>
		<input type="text" name="phone" />

<b><fmt:message key="my_account.Address" />:</b>	<c:out value="${user.address}"/>
		<input type="text" name="address" />

		<button type="submit" name="command" value="update_user"><fmt:message key="my_account.Update_button" /></button>
</pre>
</form>
<br>
<fmt:message key="my_account.Go_to" /> <a href="orders" >&nbsp;&nbsp;--- <fmt:message key="my_account.list_of_orders" /> ---</a>

