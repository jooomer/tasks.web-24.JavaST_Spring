<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<h1>
	<fmt:message key="my_account.My_account" />
</h1>
<br>

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

