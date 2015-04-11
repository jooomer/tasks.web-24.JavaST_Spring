<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<c:if test="${ !(register_success eq true) }">
	<div class="alert alert-success">Congratulations! You are successfully registered. Now you can sign in.</div>

	<!-- Button trigger modal -->
	<div align="center">
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		  Press to sign in
	</button>
	</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">&nbsp;</h4>
      </div>
      <div class="modal-body">
	      			
			<form class="form-signin" action="<spring:url value="/j_spring_security_check" />" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				
<!-- 				<label for="inputEmail" class="sr-only">Email address</label>  -->
				<input type="text" name="j_username" class="form-control" placeholder="Name" required autofocus> 
				<br>
<!-- 				<label for="inputPassword" class="sr-only">Password</label>  -->
				<input type="password" name="j_password" class="form-control" placeholder="Password" required>
				<br>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
	      
	  </div>
      <div class="modal-footer">
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>

</c:if>






<!-- <p class="pull-right visible-xs"> -->
<!--   <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button> -->
<!-- </p> -->
<!-- <div class="jumbotron"> -->
<!--   <h1>Hello, world!</h1> -->
<!--   <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p> -->
<!-- </div> -->
<!-- <div class="row"> -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!--   <div class="col-xs-6 col-lg-4"> -->
<!--     <h2>Heading</h2> -->
<!--     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p> -->
<!--     <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
<!--   </div>/.col-xs-6.col-lg-4 -->
<!-- </div>/row -->


