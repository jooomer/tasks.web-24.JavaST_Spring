<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="lang"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"
	scope="session" />

<div class="row">
	<div class="col-md-10">
		<h1>BOUTIQUE FurnitureStore</h1>
	</div>
<!-- 	<div class="col-md-1"> -->
<!-- 		<br> -->
<!-- 		<button type="button" class="btn btn-primary btn-sm" -->
<!-- 			data-toggle="modal" data-target="#sign_in">Sign in -->
<!-- 		</button> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-1"> -->
<!-- 		<br> -->
<!-- 		<button type="button" class="btn btn-primary btn-sm" -->
<!-- 			data-toggle="modal" data-target="#register">Register -->
<!-- 		</button> -->
<!-- 	</div> -->
	<div class="col-md-2">
		<br>
		<form:form>
			<select id="lang" name="lang" onchange="submit()"
				class="form-control input-sm">
				<option value="en_US" ${lang == 'en_US' ? 'selected' : ''}>English</option>
				<option value="ru_RU" ${lang == 'ru_RU' ? 'selected' : ''}>Русский</option>
			</select>
		</form:form>
	</div>
</div>
