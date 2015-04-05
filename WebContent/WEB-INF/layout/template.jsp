<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title" /></title>

</head>

<body>


<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>

<tilesx:useAttribute name="current"/> 

	<div class="container">

		<div>
			<tiles:insertAttribute name="header" />
		</div>

		<div>
	    	<!-- Static navbar -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar" aria-expanded="false"
							aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href='<spring:url value="/" />'>BOUTIQUE FurnitureStore</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="${current == 'index' ? 'active' : ''}"><a href='<spring:url value="/" />'>Home</a>
							</li>
						</ul>
					 	<ul class="nav navbar-nav navbar-right">
							<security:authorize access="!isAuthenticated()">
								<li class="${current == 'register' ? 'active' : ''}"><a href='<spring:url value="/register" />'>Register</a></li>
								<li class="${current == 'login' ? 'active' : ''}"><a href='<spring:url value="/login" />'>Sign in</a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-expanded="false">
										<security:authentication property="principal.username" /> 
										<span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="<spring:url value="/account"/>">My account</a></li>
										<li class="divider"></li>
										<li class="dropdown-header">Products</li>
										<li><a href="<spring:url value="/my-products"/>">My products for rent</a></li>
										<li><a href="<spring:url value="/add-product"/>">Add new product</a></li>
									</ul>
								</li>
								<li><a href='<spring:url value="/logout" />'>Logout</a></li>
							</security:authorize>
							<li class="${current == 'search' ? 'active' : ''}"><a href='<spring:url value="/search" />'>Search</a></li>
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
				
				<h1><tiles:getAsString name="title" /></h1>
				<tiles:insertAttribute name="body" />

			</div>
			<!--/.col-xs-12.col-sm-9-->
		</div>
		<!--/row-->

		<br> <br>
		<hr>
		<div>
			<center>
				<tiles:insertAttribute name="footer" />
			</center>
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