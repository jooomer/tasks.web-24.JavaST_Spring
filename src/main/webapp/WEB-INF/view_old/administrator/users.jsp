<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/view/layouts/taglib.jsp"%> --%>

<%@ page import="ua.store.model.entity.User"%>

<h1>
	<fmt:message key="users.List_of_users" />
</h1>
<br>

<!-- <form action="administrator/search" method="post"> -->
<%-- 	<fmt:message key="users.Search" />: --%>
<!-- 	<input type="text" name="userName" /> -->
<!-- 	<button type="submit" name="command" value="search_user_by_userName"> -->
<%-- 		<fmt:message key="users.Search_button" /> --%>
<!-- 	</button> -->
<!-- </form> -->
<!-- <br> -->

<!-- <hr/> -->
<br>
<ulist:users-table rows="${ userList.size }" >
	${ userList.userId } </td><td>
	${ userList.userType } </td><td>
	${ userList.userName } </td><td>
	${ userList.firstName } </td><td>
	${ userList.lastName } </td><td>
	${ userList.email } </td><td>
	${ userList.phone } </td><td>
	${ userList.address } </td><td>
	${ userList.comments } </td><td>
	${ userList.inBlackList }
</ulist:users-table>

