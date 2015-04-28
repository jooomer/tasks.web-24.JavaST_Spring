<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<form method="post">
<table class="table table-bordered table-hover table-stripped">
	<thead>
		<tr>
			<th>User name</th>
			<th>Roles</th>
			<th>Name</th>
<!-- 			<th>Email</th> -->
<!-- 			<th>Phone</th> -->
<!-- 			<th>Address</th> -->
<!-- 			<th>Comments</th> -->
			<th>In black list</th>
			<th>Enabled</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					<a href='<spring:url value="/users/${user.id}" />'>${user.name}</a>
				</td>
				<td>
					<c:forEach var="role" items="${user.roles}" >
						${role.name.name}					
					</c:forEach>
				</td>
				<td>
					<c:out value="${user.firstName}" />&nbsp;
					<c:out value="${user.lastName}" />
				</td>
<!-- 				<td> -->
<%-- 					<c:out value="${user.email}" /> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:out value="${user.phone}" /> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:out value="${user.address}" /> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:out value="${user.comments}" /> --%>
<!-- 				</td> -->
				<td>
    				<p class="text-center">
	    				<input type="checkbox" name="inBlackList" value="${user.id}" 
	    				${user.inBlackList == true ? 'checked' : ''} >
					</p>
				<td>
    				<p class="text-center">
	    				<input type="checkbox" name="enabled" value="${user.id}" 
	    				${user.enabled == true ? 'checked' : ''} >
    				</p>
				</td>
				<td>
					<c:set var="userId" value="${user.id}" />
<!-- 					<button type="button" data-toggle="modal" data-target="#myModal" -->
<%-- 					class="btn btn-danger btn-sm" name="delete_user" value="${user.id}">Delete</button> --%>
					<a href='<spring:url value="/users/${user.id}/delete" />' 
					type="button" data-toggle="modal" data-target="#myModal"
					class="btn btn-danger btn-sm" >Delete</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<p class="text-right">
	<button type="submit" name="update_users" value="${users}" class="btn btn-success btn-lg" >Update users</button>
</p>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" id="myModalLabel">Attention!</h4> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!-- 			<div class="alert alert-warning"> -->
<!-- 				You are going to delete user permanently.<br> -->
<!-- 	      			Press "Delete user" to delete or "Cancel" to return to previous page.  -->
<!-- 			</div> -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> -->
<%-- 		<button type="submit" name="delete_user" value="${userId}"  --%>
<!-- 		class="btn btn-danger btn-lg" >Delete user</button> -->
<!--       </div> -->

    </div>
  </div>
</div>

</form>