<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>

<h1>Spring MVC internationalization example</h1>

<h3>${message}</h3>
 
<div class="container">
  <div class="row">
   <div class="col-sm-9">
    <h2><spring:message code="main_menu.Home"/></h2>
    <div class="row">
     <a href="?lang=en" class="btn btn-sm btn-success">English</a> <a href="?lang=ru" class="btn btn-sm btn-success">French</a>
    </div>
    <form class="form-horizontal" role="form" method="post" name="employeeForm" id="employeeForm">
     <div class="form-group">
      <label for="firstName" class="col-lg-3 control-label"><spring:message code="main_menu.Home" /></label>
      <div class="col-lg-4"><input type="text" class="form-control" name="firstName" id="firstName" value="">
      </div>
     </div>
     <div class="form-group">
      <label for="lastName" class="col-lg-3 control-label"><spring:message code="main_menu.Home" /></label>
      <div class="col-lg-4">
       <input type="text" class="form-control" name="lastName" id="lastName" value="">
      </div>
     </div>     
    </form>
   </div>
  </div>
 </div>
 
Current Locale : ${pageContext.response.locale}