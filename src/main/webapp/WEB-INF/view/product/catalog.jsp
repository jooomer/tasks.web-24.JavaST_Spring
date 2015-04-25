<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not empty message_info}" >
	<div class="alert alert-info" role="alert">${message_info}</div>
</c:if>

<c:if test="${not empty message_success}">
	<div class="alert alert-success">${message_success}</div>
</c:if>

<h2>Category "${categoryName}"</h2>
<br>

<form:form commandName="selectSortBy" class="form-inline">
	<div class="row">
	  <div class="col-xs-6 col-md-4">
	  Sort by direction:
		<form:select path="sortDirection" cssClass="form-control input-sm" onchange="submit()" >
			<option value="ASC" ${catalog_sortDirection == 'ASC' ? 'selected' : ''}>Low to High</option>
			<option value="DESC" ${catalog_sortDirection == 'DESC' ? 'selected' : ''}>High to Low</option>
		</form:select>
	  </div>
	  <div class="col-xs-6 col-md-4">
	  Sort by field:
		<form:select path="sortField" cssClass="form-control input-sm" onchange="submit()" >
			<option value="name" ${catalog_sortField == 'name' ? 'selected' : ''}>Name</option>
			<option value="price" ${catalog_sortField == 'price' ? 'selected' : ''}>Price</option>
		</form:select>
	  </div>
	  <div class="col-xs-6 col-md-4">
	  Group by:
		<form:select path="itemsOnPage" cssClass="form-control input-sm" onchange="submit()" >
			<option value="5" ${catalog_itemsOnPage == 5 ? 'selected' : ''}>5 items on a page</option>
			<option value="10" ${catalog_itemsOnPage == 10 ? 'selected' : ''}>10 items on a page</option>
			<option value="15" ${catalog_itemsOnPage == 15 ? 'selected' : ''}>15 items on a page</option>
			<option value="20" ${catalog_itemsOnPage == 20 ? 'selected' : ''}>20 items on a page</option>
		</form:select>
	  </div>
	</div>
</form:form>

<nav>
	<div class="row">
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page > 1}">
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />">Previous</a></li>
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${1}" />">First</a></li>
		  	</c:if>
		  	<c:if test="${page <= 1}">
		    	<li class="next"><a>Previous</a></li>
		    	<li class="next"><a>First</a></li>
		  	</c:if>
	  </ul>
	  </div>
	  <div class="col-md-2">
	  <ul class="pager">
		   	<li>Rage ${page} of ${totalPages}</li>
	  </ul>
	  </div>
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page < totalPages}">
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />">Next</a></li>
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${totalPages}" />">Last</a></li>
		  	</c:if>
		  	<c:if test="${page >= totalPages}">
		    	<li class="previous"><a>Next</a></li>
		    	<li class="previous"><a>Last</a></li>
		  	</c:if>
	  </ul>
	  </div>
	</div>
</nav>

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>Category</th>
			<th>Product name</th>
			<th>Description</th>
			<th style="width:80px">Price, $</th>
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
					<fmt:formatNumber type="number"  minFractionDigits="2" value="${product.price}"/>
				</td>
				<td>
					<form method="post">
					 	<button type="submit" name="send_to_cart" value="${product.id}" class="btn btn-primary btn-sm">Send to cart</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<nav>
	<div class="row">
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page > 1}">
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />">Previous</a></li>
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${1}" />">First</a></li>
		  	</c:if>
		  	<c:if test="${page <= 1}">
		    	<li class="next"><a>Previous</a></li>
		    	<li class="next"><a>First</a></li>
		  	</c:if>
	  </ul>
	  </div>
	  <div class="col-md-2">
	  <ul class="pager">
		   	<li>Rage ${page} of ${totalPages}</li>
	  </ul>
	  </div>
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page < totalPages}">
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />">Next</a></li>
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${totalPages}" />">Last</a></li>
		  	</c:if>
		  	<c:if test="${page >= totalPages}">
		    	<li class="previous"><a>Next</a></li>
		    	<li class="previous"><a>Last</a></li>
		  	</c:if>
	  </ul>
	  </div>
	</div>
</nav>

