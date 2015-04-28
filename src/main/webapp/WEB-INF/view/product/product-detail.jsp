<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/layout/message.jsp"%>

<c:if test="${not empty message}">
	<div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${ success eq true }">
	<div class="alert alert-success">Congratulations! The product is successfully updated.</div>
</c:if>

<div class="form-horizontal">
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<h2>${product.name}</h2>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Category:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.category.name}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Price, $:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.price}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Quantity in stock:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.quantityInStock}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Description:</label>
		<div style="padding-top: 7px;" class="col-sm-10">
			${product.description}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<form  method="post">
				<input type="submit" name="send_to_cart" value="Send to cart" class="btn btn-success btn-lg"/>
			</form>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div style="padding-top: 7px;" class="col-sm-10">
			<security:authorize access="hasRole('ROLE_ADMIN')" >
				<form method="post">
					<button type="submit" name="update_product" value="${product.id}" class="btn btn-primary">Update product</button>
					<button type="button" type="button" data-toggle="modal" data-target="#myModal" 
					class="btn btn-danger">Delete product</button>
				</form>
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
					You are going to delete product permanently.<br>
		      			Press "Delete product" to delete or "Cancel" to return to the previous page. 
				</div>
	      </div>
	      <div class="modal-footer">
				<form:form>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="submit" name="delete_product" value="${product.id}"
						class="btn btn-danger btn-lg">Delete product</button>
				</form:form>
			</div>
	    </div>
	  </div>
	</div>



