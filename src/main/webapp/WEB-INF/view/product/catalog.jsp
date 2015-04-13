<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty msg}" >
	<div class="alert alert-info" role="alert">${msg}</div>
</c:if>

<form:form commandName="category">
	<form:select path="ProductCategory.name" cssClass="form-control" onchange="submit()" >
		<form:option value="">--- SELECT PRODUCT CATEGORY ---</form:option>
		<form:option value="All products" />
		<form:options items="${listOfProductCategories}" itemValue="name" itemLabel="name" />
	</form:select>
</form:form>
<br>

<h2>Category "${categoryName}"</h2>
<br>

<form:form commandName="selectSortBy" class="form-inline">
  <div class="form-group">
  Group by:
	<form:select path="itemsOnPage" cssClass="form-control input-sm" onchange="submit()" >
		<option value="5" ${catalog_itemsOnPage == 5 ? 'selected' : ''}>5 items on a page</option>
		<option value="10" ${catalog_itemsOnPage == 10 ? 'selected' : ''}>10 items on a page</option>
		<option value="15" ${catalog_itemsOnPage == 15 ? 'selected' : ''}>15 items on a page</option>
		<option value="20" ${catalog_itemsOnPage == 20 ? 'selected' : ''}>20 items on a page</option>
	</form:select>
  </div>
  <div class="form-group">
 	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </div>
  <div class="form-group">
  Sort by:
	<form:select path="sortDirection" cssClass="form-control input-sm" onchange="submit()" >
		<option value="ASC" ${catalog_direction == 'ASC' ? 'selected' : ''}>Low to High</option>
		<option value="DESC" ${catalog_direction == 'DESC' ? 'selected' : ''}>High to Low</option>
	</form:select>
  </div>
</form:form>
<br>
<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>Category</th>
			<th>Product name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOfProducts}" var="product">
			<tr>
				<td>
					${product.productCategory.name}
				</td>
				<td>
					<a href='<spring:url value="/products/${product.id}" />'>${product.name}</a>
				</td>
				<td>
					${product.description}
				</td>
				<td>
					${product.price}
				</td>
				<td>
					<form  action="${pageContext.request.contextPath}/products/${product.id}" method="post">
						<input type="submit" name="send-to-cart" value="Send to cart" class="btn btn-primary btn-sm"/>
					</form>
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