<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%-- <%@ include file="/WEB-INF/layout/modal.jsp" %> --%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<tilesx:useAttribute name="current"/> 
<tilesx:useAttribute name="title" /> 

<c:set var="titleDefault" ><tiles:getAsString name="titleDefault" /></c:set>
<title><spring:message code="${title}" text="${titleDefault}" /></title>

</head>

<body>

	<div class="container">

		<div>
			<tiles:insertAttribute name="header" />
		</div>

		<div>
	    	<!-- Static navbar -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">

					<a class="navbar-brand" href='<spring:url value="/" />'>
					  <span class="glyphicon glyphicon-home" aria-hidden="true"></span> <spring:message code="main_menu.Home" />
					</a>

					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="${current == 'catalog' ? 'active' : ''}"><a href='<spring:url value="/catalog" />'><spring:message code="main_menu.Catalog" /></a></li>
						</ul>
					 	<ul class="nav navbar-nav navbar-right">
							<security:authorize access="!isAuthenticated()">
								<li class="${current == 'register' ? 'active' : ''}"><a href='<spring:url value="/register" />'><spring:message code="main_menu.Register" /></a></li>
								<li class="${current == 'login' ? 'active' : ''}"><a href='<spring:url value="/login" />'><spring:message code="main_menu.Sign_in" /></a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-expanded="false">
										<security:authentication property="principal.username" /> 
										<span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="<spring:url value="/account"/>"><spring:message code="main_menu.My_account" /></a></li>
										<li class="divider"></li>
										<li class="dropdown-header"><spring:message code="main_menu.Products" /></li>
										<li><a href="<spring:url value="/orders"/>"><spring:message code="main_menu.My_orders" /></a></li>
									</ul>
								</li>
								<li><a href='<spring:url value="/logout" />'><spring:message code="main_menu.Logout" /></a></li>
							</security:authorize>
							<li><a>|</a></li>
							<li class="${current == 'cart' ? 'active' : ''}"><a href='<spring:url value="/cart" />'><spring:message code="main_menu.Cart" /></a></li>
<%-- 							<li class="${current == 'search' ? 'active' : ''}"><a href='<spring:url value="/search" />'><spring:message code="main_menu.Search" /></a></li> --%>
	          			</ul>
	          			
						
					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>
			<!-- End of Static navbar -->
		</div>

		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">

				<tiles:insertAttribute name="side_menu" />

			</div>
			<!--/.sidebar-offcanvas-->
			<div class="col-xs-12 col-sm-9">
				
				<h1><spring:message code="${title}" text="${titleDefault}" /></h1>
				
				<tiles:insertAttribute name="body" />
				
			</div>
			<!--/.col-xs-12.col-sm-9-->
		</div>
		<!--/row-->

		<br> <br>
		<hr>
		<div>
			<p class="text-center" >
				<tiles:insertAttribute name="footer" />
			</p>
		</div>
	</div>
	<!-- End of container -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
				
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>