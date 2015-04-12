<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty msg}" >
	<div class="alert alert-info" role="alert">${msg}</div>
</c:if>
