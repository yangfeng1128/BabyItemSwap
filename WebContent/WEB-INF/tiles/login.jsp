<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<script type="text/javascript">
	$(document).ready(function(){
		document.f.j_username.focus();
	});
</script>
	<div id="homeInfo">Login with Username and Password</div>
	
	<c:if test="${param.error ==true }">
		<p class="error">Login failed.Check your username and password.</p>
	</c:if>
	<form class ="formlogin" name='f' action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<table class="formtable">
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td>Remember me</td>
				<td><input type='checkbox' name='_spring_security_remember_me' checked="checked"/></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
	<p><a class="login" href="${pageContext.request.contextPath}/newaccount"> Create New Account</a> </p>
