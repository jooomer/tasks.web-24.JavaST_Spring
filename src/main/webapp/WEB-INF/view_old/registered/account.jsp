<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<h1>
	<fmt:message key="my_account.My_account" />
</h1>
<br>

<form:form commandName="newUser" >

<div id="container">
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.userName" />
				:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.name}" />
			</p>
		</div>
		<div id="right">
			
		</div>
	</div>
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.First_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.firstName}" />
			</p>
		</div>
		<div id="right">
			<p><input type="text" name="firstName" /></p>
		</div>
	</div>
		<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.Last_name" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.lastName}" />
			</p>
		</div>
		<div id="right">
			<p><input type="text" name="lastName" /></p>
		</div>
	</div>
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.Email" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.email}" />
			</p>
		</div>
		<div id="right">
			<p>
				<form:input path="email" />
				<br>
				<form:errors path="email"/>
			</p>
		</div>
	</div>
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.Phone" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.phone}" />
			</p>
		</div>
		<div id="right">
			<p><input type="text" name="phone" /></p>
		</div>
	</div>
	<div id="row">
		<div id="left">
			<h3>
				<fmt:message key="my_account.Address" />:
			</h3>
		</div>
		<div id="middle">
			<p>
				<c:out value="${user.address}" />
			</p>
		</div>
		<div id="right">
			<p><input type="text" name="address" /></p>
		</div>
	</div>
	<div id="row">
		<div id="left">
			<h3>
			</h3>
		</div>
		<div id="middle">
			<p>
			</p>
		</div>
		<div id="right">
			<button type="submit" name="command" value="update_user">
				<fmt:message key="my_account.Update_button" />
			</button>
		</div>
	</div>
</div>

</form:form>
<br>
<fmt:message key="my_account.Go_to" />
<a href="orders">&nbsp;&nbsp;--- <fmt:message
		key="my_account.list_of_orders" /> ---
</a>



