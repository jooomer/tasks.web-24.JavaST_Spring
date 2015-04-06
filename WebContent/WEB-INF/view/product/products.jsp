<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

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
