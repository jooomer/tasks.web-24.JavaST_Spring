<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<!-- Products -->
<div class="products">
	<div class="title">
		<h2>
			<fmt:message key="main.Products" />
		</h2>
		<img class="bullet" src="<c:url value="/resources/css/images/bullet.png" /> "
			alt="small grey bullet" />
	</div>

	<div class="row">
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="<spring:url value="/categories/Cabinets" />"><img src="<c:url value="/resources/css/images/1.jpg" />" alt="Yin Yang shaped bookshelf" /></a> <img class="top-label"
					src="<c:url value="/resources/css/images/top.png" />" alt="top sign" />
				<div class="desc">
						<fmt:message key="main.Cabinets" />
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="<spring:url value="/categories/Sofas" />"><img src="<c:url value="/resources/css/images/6.jpg"/>"
					alt="Brown table with pink, blue and two green stools" /></a> <img
					class="top-label" src="<c:url value="/resources/css/images/top.png" />" alt="top sign"/>
				<div class="desc">
						<fmt:message key="main.Sofas" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>
	</div>

	<div class="row">
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="<spring:url value="/categories/Armchairs" />"><img src="<c:url value="/resources/css/images/5.jpg" />"
					alt="table" /></a>
				<div class="desc">
						<fmt:message key="main.Armchairs" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="<spring:url value="/categories/Tables" />"><img src="<c:url value="/resources/css/images/4.jpg" />" alt="Armchair with a round stool" /></a> <img class="new-label"
					src="<c:url value="/resources/css/images/new.png" />" alt="new sign" />
				<div class="desc">
						<fmt:message key="main.Tables" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>

	</div>
	<div class="cl"></div>
</div>
<!-- END Products -->