<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<%-- <%@ page import="ua.store.model.entity.User"%> --%>

<c:set var="user" value='${sessionScope["user"]}' />

<div id="sidebar">
	<div class="box">

		<security:authorize
			access="isAuthenticated() and hasRole('ROLE_ADMIN')">

			<div class="title">
				<h2>
					<fmt:message key="sidebar.Administrator_menu" />
				</h2>
				<img class="bullet"
					src="<c:url value="/resources/css/images/bullet.png" />"
					alt="small grey bullet" />
			</div>
			<ul>
				<li><a title="Users" href="<spring:url value="/users" />">
						<fmt:message key="sidebar.Users" />
				</a></li>
				<li><a title="Add new product"
					href="<spring:url value="/add-product" />"> <fmt:message
							key="sidebar.Add_product" />
				</a></li>

				<!-- 					<li> -->
				<!-- 						<a title="Delete user" href="administrator/orders"> -->
				<%-- 							<fmt:message key="sidebar.Orders" /> --%>
				<!-- 						</a> -->
				<!-- 					</li> -->

			</ul>

			<br>
			<br>
			<br>
		</security:authorize>

		<div class="title">
			<h2>
				<fmt:message key="sidebar.Catalog" />
			</h2>
			<img class="bullet"
				src="<c:url value="/resources/css/images/bullet.png" />"
				alt="small grey bullet" />
		</div>
		<ul>
			<li><a title="All products"
				href="<spring:url value="/products" />"> <fmt:message
						key="sidebar.Products" />
			</a></li>
		</ul>

		<br> <br> <br>


		<!-- 					<div class="title"> -->
		<!-- 						<h2> -->
		<%-- 							<fmt:message key="sidebar.Initialization_menu" /> --%>
		<!-- 						</h2> -->
		<!-- 						<img class="bullet" -->
		<%-- 							src="<c:url value="/resources/css/images/bullet.png" />" --%>
		<!-- 							alt="small grey bullet" /> -->
		<!-- 					</div> -->
		<!-- 					<ul> -->
		<%-- 						<li><a title="Users" href="initialization"> <fmt:message --%>
		<%-- 									key="sidebar.Initialization" /> --%>
		<!-- 						</a></li> -->
		<!-- 					</ul> -->
	</div>
</div>