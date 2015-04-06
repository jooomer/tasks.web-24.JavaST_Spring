<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${not (message eq null)}">
	<div class="alert alert-success" >${message}</div>
</c:if>

<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>Product name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${myProducts}" var="product">
			<tr>
				<td>
					<a href='<spring:url value="/my-products/${product.id}" />'>${product.name}</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


	