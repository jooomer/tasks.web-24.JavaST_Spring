<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<security:authorize access="hasRole('ROLE_ADMIN')" >
<h2>Administrator menu</h2>
      <div class="list-group">
        <a href='<spring:url value="/users" />' class="list-group-item">All users</a>
        <a href='<spring:url value="/catalog" />' class="list-group-item">All products</a>
        <a href='<spring:url value="/add-product" />' class="list-group-item">Add new product</a>
        <a href='<spring:url value="/categories" />' class="list-group-item">All categories</a>
      </div>
      <br>
</security:authorize>
        
<c:if test="${catalog eq true}">
	<h2>Categories</h2>
	<form:form commandName="category">
		<form:select path="Category.name" cssClass="form-control" onchange="submit()" >
			<form:option value="">SELECT PRODUCT CATEGORY</form:option>
			<form:option value="All products" />
			<form:options items="${listOfCategories}" itemValue="name" itemLabel="name" />
		</form:select>
	</form:form>
</c:if>


