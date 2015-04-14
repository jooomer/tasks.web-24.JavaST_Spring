<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty message_success}">
	<div class="alert alert-success">${message_success}</div>
</c:if>

<c:if test="${not empty message_info}" >
	<div class="alert alert-info" role="alert">${message_info}</div>
</c:if>

<c:if test="${not empty message_warning}">
	<div class="alert alert-warning">${message_warning}</div>
</c:if>

<c:if test="${not empty message_danger}">
	<div class="alert alert-danger">${message_danger}</div>
</c:if>
