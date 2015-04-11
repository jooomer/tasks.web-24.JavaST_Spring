<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty msg}" >
	${msg}
</c:if>

<%-- <form:form> --%>
	<form:select path="ProductCategory.name" cssClass="form-control" onchange="submit()" >
		<form:option value="">--- SELECT PRODUCT CATEGORY ---</form:option>
		<form:option value="All products" />
		<form:options items="${listOfProductCategories}" itemValue="name" itemLabel="name" />
	</form:select>
<%-- </form:form> --%>
<br>

<h2>${categoryName}</h2>

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>Product name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOfProducts}" var="product">
			<tr>
				<td>
					<a href='<spring:url value="/products/${product.id}" />'>${product.name}</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<nav>
  <ul class="pager">
  	<c:if test="${page > 1}">
    	<li><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />">Previous</a></li>
  	</c:if>
  	<c:if test="${page < totalPages}">
    	<li><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />">Next</a></li>
  	</c:if>
  </ul>
</nav>