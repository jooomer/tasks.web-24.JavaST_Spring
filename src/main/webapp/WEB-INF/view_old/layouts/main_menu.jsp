<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<%-- <%@ page import="ua.store.model.entity.User"%> --%>

<div id="navigation"> 
	<ul>
		<li class="first">
			<a title="Home" href="<spring:url value="/" />">
				<fmt:message key="main_menu.Home" />
			<span class="home">&nbsp;</span>
			</a>
		</li>
		<li>
			<a title="Cabinets" href="<spring:url value="/categories/Cabinets" />">
				<fmt:message key="main_menu.Cabinets" />
			</a>
		</li>
		<li>
			<a title="Sofas" href="<spring:url value="/categories/Sofas" />">
				<fmt:message key="main_menu.Sofas" />
			</a>
		</li>

		<li>
			<a title="Armchairs" href="<spring:url value="/categories/Armchairs" />">
				<fmt:message key="main_menu.Armchairs" />
			</a>
		</li>
		<li>
			<a title="Tables" href="<spring:url value="/categories/Tables" />">
				<fmt:message key="main_menu.Tables" />
			</a>
		</li>
	</ul>
	<div class="cl"></div>
</div>