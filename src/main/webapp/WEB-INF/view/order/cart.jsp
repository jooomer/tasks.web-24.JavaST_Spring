<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<c:if test="${not empty listOfOrderItems}">
	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th><spring:message code="cart.Category" /></th>
				<th><spring:message code="cart.Product_name" /></th>
				<th><spring:message code="cart.Description" /></th>
				<th style="width:100px"><spring:message code="cart.Price" />, $</th>
				<th><spring:message code="cart.Quantity" /></th>
				<th style="width:100px"><spring:message code="cart.Amount" />, $</th>
				<th><spring:message code="cart.Action" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderItem" items="${listOfOrderItems}">
				<tr>
					<td>
						${orderItem.product.category.name}
					</td>
					<td>
						<a href='<spring:url value="/products/${orderItem.product.id}" />'>${orderItem.product.name}</a>
					</td>
					<td>
						${orderItem.product.description}
					</td>
					<td>
						<fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.product.price}"/>
					</td>
					<td>
						${orderItem.productsQuantity}
					</td>
					<td>
						<fmt:formatNumber type="number"  minFractionDigits="2" value="${orderItem.amount}"/>
					</td>
					<td>
						<form:form>
						 	<button type="submit" name="remove_from_cart" value="${orderItem.product.id}" 
						 	class="btn btn-danger btn-sm"><spring:message code="cart.Remove" /></button>
						</form:form>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<th><spring:message code="cart.Summary" /></th>
					<th><fmt:formatNumber type="number"  minFractionDigits="2" value="${order.amount}"/></th>
					<td></td>
				</tr>
		</tbody>
	</table>

	<div class="row">
		<div class="col-xs-12 col-md-9">
			<form:form action="order">
				<p class="text-right" >
					<input type="submit" name="make_order" value="<spring:message code="cart.Make_order" />" class="btn btn-success btn-lg" />
				</p>
			</form:form>
		</div>
		<div class="col-xs-6 col-md-2">
			<form:form>
				<p class="text-right" >
					<input type="submit" name="clean_cart" value="<spring:message code="cart.Clean_cart" />" class="btn btn-primary btn-lg" />
				</p>
			</form:form>
		</div>
	</div>
	
</c:if>

