<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${ register_success eq true }">
	<div class="alert alert-success"><spring:message code="index.Congratulations_register" />
		<br><br>
		<a class="btn btn-primary btn-lg" href="<spring:url value="login" />" role="button">
		<spring:message code="index.Press_to_sign_in" />
		</a>
	</div>
</c:if>

<c:if test="${ login_success eq true }" >
	<security:authorize access="isAuthenticated()">
		<div class="alert alert-success"><spring:message code="index.Congratulations_sign_in" />
		</div>
	</security:authorize>
</c:if>

<!-- <p class="pull-right visible-xs"> -->
<!--   <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button> -->
<!-- </p> -->
<div class="jumbotron">
  <h1><spring:message code="index.Hello_everybody" /></h1>
  <p><spring:message code="index.Welcome_to_our_store" /></p>
</div>