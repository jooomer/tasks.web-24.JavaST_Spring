<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<!-- Modal of Sign in-->
<div class="modal fade" id="sign_in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
			<form class="form-signin" action="<spring:url value="/j_spring_security_check" />" method="post">
			
				<h2 class="form-signin-heading">Please sign in</h2>
				
				<c:if test="${login_error eq true}">
					<div class="alert alert-warning">Invalid name or password!</div>
				</c:if>
			
				<input type="text" name="j_username" class="form-control" placeholder="Name" required autofocus> 
				<input type="password" name="j_password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>	
<!-- END Modal of Sign in-->
				
<!-- Modal of Sign in-->
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Register</h4>
      </div>
      <div class="modal-body">
			<form class="form-signin" action="<spring:url value="/j_spring_security_check" />" method="post">
			
				<h2 class="form-signin-heading">Please register</h2>
				
				<c:if test="${login_error eq true}">
					<div class="alert alert-warning">Invalid name or password!</div>
				</c:if>
			
				<input type="text" name="j_username" class="form-control" placeholder="Name" required autofocus> 
				<input type="password" name="j_password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>	
<!-- END Modal of Sign in-->
				
				
				
	
