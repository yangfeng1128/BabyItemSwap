<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<span id="homeInfo">Create New Account</span>

	<f:form id= "details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">User Name:</td>
				<td><f:input class="control" path="username" name="username"
						type="text" /><br />
				<div class="error">
						<f:errors path="username"></f:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Name:</td>
				<td><f:input class="control" path="name" name="name"
						type="text" /><br />
				<div class="error">
						<f:errors path="name"></f:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><f:input class="control" path="email" name="email"
						type="text" />
					<div class="error">
						<f:errors path="email" cssClass="error"></f:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><f:input id="password" class="control" path="password"
						name="password" type="password" />
					<div class="error">
						<f:errors path="password" cssClass="error"></f:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" class="control"
					name="confirmpassword" type="password" />
				<div id="passmatch"></div></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="create account" type="submit" /></td>
			</tr>

		</table>
	</f:form>
