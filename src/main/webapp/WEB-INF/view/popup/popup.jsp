<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>


<!-- Modal -->
<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content"> -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Attention!</h4>
      </div>
      <div class="modal-body">
			<div class="alert alert-warning">
				You are going to delete object permanently.<br>
	      		Press "Delete" to delete or "Cancel" to return to previous page.
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		<button type="submit" name="${action}" value="${id}" 
		class="btn btn-danger btn-lg" >Delete</button>
      </div>
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
