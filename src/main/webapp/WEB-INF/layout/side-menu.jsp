<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<security:authorize access="hasRole('ROLE_ADMIN')" >
<h2><spring:message code="side_menu.Administrator_menu" text="Administrator menu" /></h2>
      <div class="list-group">
        <a href='<spring:url value="/users" />' class="list-group-item">
        	<spring:message code="side_menu.All_users" text="All users" /></a>
        <a href='<spring:url value="/catalog" />' class="list-group-item">
        	<spring:message code="side_menu.All_products" text="All products" /></a>
        <a href='<spring:url value="/add-product" />' class="list-group-item">
        	<spring:message code="side_menu.Add_new_product" text="Add new product" /></a>
        <a href='<spring:url value="/categories" />' class="list-group-item">
        	<spring:message code="side_menu.All_categories" text="All categories" /></a>
      </div>
      <br>
</security:authorize>

<c:set var="SELECT_PRODUCT_CATEGORY" >
	<spring:message code="side_menu.SELECT_PRODUCT_CATEGORY" text="SELECT PRODUCT CATEGORY" /></c:set>
<c:set var="All_categories" >
	<spring:message code="side_menu.All_categories" text="All categories" /></c:set>
        
<c:if test="${catalog eq true}">
	<h2><spring:message code="side_menu.Categories" text="Categories" /></h2>
	<form:form commandName="category">
		<form:select path="Category.name" cssClass="form-control" onchange="submit()" >
			<form:option value="${SELECT_PRODUCT_CATEGORY}" />
			<form:option value="${All_categories}" />
			<form:options items="${listOfCategories}" itemValue="name" itemLabel="name" />
		</form:select>
	</form:form>
</c:if>


