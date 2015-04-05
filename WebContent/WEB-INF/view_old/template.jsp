<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="ua.store.model.entity.User"%>

<!DOCTYPE html>
<html lang="${language}" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<c:set var="title" value='${sessionScope["title"]}' />
<title>${title}</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<link rel="shortcut icon"
	href="<c:url value="/resources/css/images/favicon.ico" />">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"
	type="text/css" media="all" />
<script src="<c:url value="/resources/js/jquery-1.7.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery.jcarousel.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/DD_belatedPNG-min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/functions.js" />"
	type="text/javascript"></script>
</head>
<body>
	<div class="shell">

		<!-- Top navigation -->
				<c:import url="layouts/top_menu.jsp" />
		<!-- END Top navigation -->

		<div class="cl"></div>
		<div id="wrapper-top"></div>
		<!-- Wrapper Middle -->
		<div id="wrapper-middle">

			<!-- Header -->
			<c:import url="layouts/header.jsp" />
			<!-- END Header -->

			<!-- Navigation -->
			<c:import url="layouts/main_menu.jsp" />
			<!-- END Navigation -->

			<!-- Main -->
			<div id="main">

				<!-- Slider -->
				<!-- END Slider -->

				<!-- Sidebar -->
				<c:import url="layouts/sidebar.jsp" />
				<!-- END Sidebar -->

				<!-- Content -->
				<div id="content">
					<c:import url="${jspPage}" />
				</div>

				<!-- END Content -->

				<div class="cl"></div>
			</div>
			<!-- END Main -->
		</div>
		<!-- END Wrapper Middle -->

		<div id="wrapper-bottom"></div>
		<!-- Footer  -->
		<!-- 		<c:import url="layouts/footer.jsp" />  -->
		<!-- END Footer -->
	</div>
</body>
</html>