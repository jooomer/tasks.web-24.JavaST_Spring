<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<%@ page import="ua.store.model.entity.Product"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/view/tld/custom.tld"%>

<h1>
	<fmt:message key="initialization.Initialization" />
</h1>
<br>

<h3><c:out value="${sessionScope['message']}" /></h3>
<br>

<c:if test="${sessionScope['init_success'] != 'init_success'}">
	<form action="initialization" method="post">
		<button type="submit" name="command" value="initialize_DB">
			<fmt:message key="initialization.initialize_button" />
		</button>
	</form>
</c:if>