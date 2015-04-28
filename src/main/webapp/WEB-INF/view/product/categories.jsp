<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>
<%@ include file="/WEB-INF/layout/message.jsp" %>

<c:if test="${ success eq true }">
	<div class="alert alert-success">Congratulations! New category is successfully created.</div>
</c:if>

<form method="post">
	<table class="table table-bordered table-hover table-stripped">
		<thead>
			<tr>
				<th>Category</th>
				<th style="width:200px">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categories}" var="category">
				<tr>
					<td>
						<a href='<spring:url value="/category/${category.id}" />'>${category.name}</a>
					</td>
					<td>
						<button formaction="<spring:url value="/categories/${category.id}/update" />" 
						type="submit" class="btn btn-sm btn-primary" >Update</button>

<%-- 						<a href='<spring:url value="/categories/${category.id}/delete" />'  --%>
<!-- 						type="button" data-toggle="modal" data-target="#myModal" -->
<!-- 						class="btn btn-danger btn-sm" >Delete</a>						 -->
						<a href='<spring:url value="/categories/${category.id}/delete" />' 
						type="button" data-toggle="modal" data-target="#myModal"
						class="btn btn-danger btn-sm" >Delete</a>						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
	
	<button formaction="<spring:url value="/add-category" />" type="submit" name="add-category" class="btn btn-lg btn-primary" >Create new category</button>


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
<!-- 				You are going to delete category permanently.<br> -->
<!-- 	      			Press "Delete category" to delete or "Cancel" to return to previous page.  -->
<!-- 			</div> -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> -->
<%-- 		<button type="submit" name="delete_category" value="${id}"  --%>
<!-- 		class="btn btn-danger btn-lg" >Delete category</button> -->
<!--       </div> -->

    </div>
  </div>
</div>

</form>
 	