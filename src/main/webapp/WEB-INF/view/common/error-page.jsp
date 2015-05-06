<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/WEB-INF/layout/taglib.jsp" %>
	
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title" /></title>

<%-- <c:set var="titleDefault" ><tiles:getAsString name="titleDefault" /></c:set> --%>
<%-- <title><spring:message code="${title}" text="${titleDefault}" /></title> --%>


</head>

<body>
	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				
				<h1><tiles:getAsString name="title" /></h1>
				<%@ include file="/WEB-INF/layout/message.jsp" %>

				
			</div>
		</div>
		<!--/row-->
	</div>
	<!-- End of container -->



</body>
</html>