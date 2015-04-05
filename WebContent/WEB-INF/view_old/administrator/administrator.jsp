<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.User"%>

<c:set var="user" value='${sessionScope["user"]}' />

<h1>
	<fmt:message key="administrator.Administrator_page" />
</h1>
<br>

<p>
	<fmt:message key="administrator.Hi" />
	${user.userName}!
</p>
<p>
	<fmt:message key="administrator.You_are_Administrator" />
</p>
