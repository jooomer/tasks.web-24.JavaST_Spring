<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<form:form method="get">
	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Product type</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productTypes}" var="productType">
				<tr>
					<td>
						<a href='<spring:url value="/product-types/${productType.id}" />'>${productType.name}</a>
					</td>
					<td>
						<button formaction="<spring:url value="/product-types/update/${productType.id}" />" type="submit" class="btn btn-sm btn-primary" >Update</button>
						<button formaction="<spring:url value="/product-types/delete/${productType.id}" />" type="submit" class="btn btn-sm btn-danger" >Delete</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
	
	<button formaction="<spring:url value="/add-product-type" />" type="submit" class="btn btn-lg btn-primary" >Create new product type</button>
</form:form>
