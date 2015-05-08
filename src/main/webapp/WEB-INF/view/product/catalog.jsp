<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<h2><spring:message code="catalog.Category" text="Catalog" /> "${categoryName}"</h2>
<br>

<form:form commandName="catalogSelectFormHandler" class="form-inline">
	<div class="row">
	  <div class="col-xs-6 col-md-4">
	  <spring:message code="catalog.Sort_by_direction" />:
		<form:select path="sortDirection" cssClass="form-control input-sm" onchange="submit()" >
			<option value="ASC" ${catalog_sortDirection == 'ASC' ? 'selected' : ''}><spring:message code="catalog.Low_to_High" text="Low to high" /></option>
			<option value="DESC" ${catalog_sortDirection == 'DESC' ? 'selected' : ''}><spring:message code="catalog.High_to_Low" text="High to low" /></option>
		</form:select>
	  </div>
	  <div class="col-xs-6 col-md-4">
	  <spring:message code="catalog.Sort_by_field" />:
		<form:select path="sortField" cssClass="form-control input-sm" onchange="submit()" >
			<option value="name" ${catalogSelectFormHandler.sortField == 'name' ? 'selected' : ''}><spring:message code="catalog.Name" text="Name" /></option>
			<option value="price" ${catalogSelectFormHandler.sortField == 'price' ? 'selected' : ''}><spring:message code="catalog.Price" text="Price" /></option>
		</form:select>
	  </div>
	  <div class="col-xs-6 col-md-4">
	  <spring:message code="catalog.Group_by" />:
		<form:select path="itemsOnPage" cssClass="form-control input-sm" onchange="submit()" >
			<option value="5" ${catalogSelectFormHandler.itemsOnPage == 5 ? 'selected' : ''}><spring:message code="catalog.5_items_on_a_page" /></option>
			<option value="10" ${catalogSelectFormHandler.itemsOnPage == 10 ? 'selected' : ''}><spring:message code="catalog.10_items_on_a_page" /></option>
			<option value="15" ${catalogSelectFormHandler.itemsOnPage == 15 ? 'selected' : ''}><spring:message code="catalog.15_items_on_a_page" /></option>
			<option value="20" ${catalogSelectFormHandler.itemsOnPage == 20 ? 'selected' : ''}><spring:message code="catalog.20_items_on_a_page" /></option>
		</form:select>
	  </div>
	</div>
</form:form>

<nav>
	<div class="row">
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page > 1}">
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />"><spring:message code="catalog.Previous" /></a></li>
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${1}" />"><spring:message code="catalog.First" /></a></li>
		  	</c:if>
		  	<c:if test="${page <= 1}">
		    	<li class="next"><a><spring:message code="catalog.Previous" /></a></li>
		    	<li class="next"><a><spring:message code="catalog.First" /></a></li>
		  	</c:if>
	  </ul>
	  </div>
	  <div class="col-md-2">
	  <ul class="pager">
		   	<li><spring:message code="catalog.Rage" /> ${page} <spring:message code="catalog.of" /> ${totalPages}</li>
	  </ul>
	  </div>
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page < totalPages}">
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />"><spring:message code="catalog.Next" /></a></li>
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${totalPages}" />"><spring:message code="catalog.Last" /></a></li>
		  	</c:if>
		  	<c:if test="${page >= totalPages}">
		    	<li class="previous"><a><spring:message code="catalog.Next" /></a></li>
		    	<li class="previous"><a><spring:message code="catalog.Last" /></a></li>
		  	</c:if>
	  </ul>
	  </div>
	</div>
</nav>

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th><spring:message code="catalog.Category" /></th>
			<th><spring:message code="catalog.Product_name" /></th>
			<th><spring:message code="catalog.Description" /></th>
			<th style="width:80px"><spring:message code="catalog.Price" />, $</th>
			<th><spring:message code="catalog.Action" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOfProducts}" var="product">
			<tr>
				<td>
					${product.category.name}
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
					 	<button type="submit" name="send_to_cart" value="${product.id}" class="btn btn-success btn-sm"><spring:message code="catalog.Send_to_cart" /></button>
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
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${page - 1}" />"><spring:message code="catalog.Previous" /></a></li>
		    	<li class="next"><a href="<spring:url value="/category/${catId}/products/page/${1}" />"><spring:message code="catalog.First" /></a></li>
		  	</c:if>
		  	<c:if test="${page <= 1}">
		    	<li class="next"><a><spring:message code="catalog.Previous" /></a></li>
		    	<li class="next"><a><spring:message code="catalog.First" /></a></li>
		  	</c:if>
	  </ul>
	  </div>
	  <div class="col-md-2">
	  <ul class="pager">
		   	<li><spring:message code="catalog.Rage" /> ${page} <spring:message code="catalog.of" /> ${totalPages}</li>
	  </ul>
	  </div>
	  <div class="col-md-5">
	  <ul class="pager">
		  	<c:if test="${page < totalPages}">
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${page + 1}" />"><spring:message code="catalog.Next" /></a></li>
		    	<li class="previous"><a href="<spring:url value="/category/${catId}/products/page/${totalPages}" />"><spring:message code="catalog.Last" /></a></li>
		  	</c:if>
		  	<c:if test="${page >= totalPages}">
		    	<li class="previous"><a><spring:message code="catalog.Next" /></a></li>
		    	<li class="previous"><a><spring:message code="catalog.Last" /></a></li>
		  	</c:if>
	  </ul>
	  </div>
	</div>
</nav>
