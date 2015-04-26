<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<c:if test="${ success eq true }">
	<div class="alert alert-success">Congratulations! Your account is successfully updated.</div>
</c:if>
 
<form:form class="form-horizontal">

	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2><spring:message text="${user.name}" /></h2>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">First name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<spring:message text="${user.firstName}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Last name:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.lastName}" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.email}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Phone:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.phone}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Address:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<c:out value="${user.address}" />
		</div>
	</div>
	
	<security:authorize access="hasRole('ROLE_ADMIN')">
	
	<div class="form-group">
		<label class="col-sm-2 control-label">In black list:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<input type="checkbox" name="inBlackList" value="true" disabled="disabled"
			${user.inBlackList == true ? 'checked' : ''} >
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">Enabled:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<input type="checkbox" name="enabled" value="true" disabled="disabled"
			${user.enabled == true ? 'checked' : ''} >
		</div>
	</div>
		
		<div class="form-group">
		<label class="col-sm-2 control-label">Orders:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<a href="<spring:url value="/users/${user.id}/orders" />" >Click to view user orders</a> 
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-2 control-label">Comments:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<textarea name="comments" rows="1" cols="70" class="form-control"></textarea>
		</div>
	</div>
	
	</security:authorize>	
	
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="row">
			<div class="col-md-4">
				<div class="col-sm-10">
					<button type="submit" name="update_account" value="${user.id}" class="btn btn-lg btn-primary">Click to update account</button>
				</div>
			</div>
			<div class="col-md-3">
			 	<security:authorize access="hasRole('ROLE_ADMIN')" >
					<c:set var="userName">
						<security:authentication property="principal.username" />
					</c:set>
					<c:if test="${not (user.name eq userName)}" >
						<div class="col-sm-10">
							<button type="button" type="button" data-toggle="modal" data-target="#myModal"
							class="btn btn-lg btn-danger">Delete user</button>
						</div>
					</c:if>
			 	</security:authorize>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Attention!</h4>
	      </div>
	      <div class="modal-body">
				<div class="alert alert-warning">
					You are going to delete user permanently.<br>
		      			Press "Delete user" to delete or "Cancel" to return to previous page. 
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			<button type="submit" name="delete_user" value="${user.id}" 
			class="btn btn-danger btn-lg" >Delete user</button>
	      </div>
	    </div>
	  </div>
	</div>
	
 
 	
</form:form>