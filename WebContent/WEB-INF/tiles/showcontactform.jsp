<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<span id="homeInfo">Send Message</span>

<f:form method="post" commandName="message">
   <input type ="hidden" name ="_flowExecutionKey" value="${flowExecutionKey}"/>
   <input type ="hidden" name ="_eventId" value="send"/>
   
	<table class="formtable">
		<tr>
			<td class="label">Your Name:</td>
			<td><f:input class="control" path="name" name="name" type="text" value="${fromName}"/><br />
				<div class="error">
					<f:errors path="name"></f:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your email</td>
			<td><f:input class="control" path="email" name="email"
					type="text" value="${fromEmail}" /><br />
				<div class="error">
					<f:errors path="email"></f:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Subject</td>
			<td><f:input class="control" path="subject" name="subject"
					type="text" /><br />
				<div class="error">
					<f:errors path="subject"></f:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Message:</td>
			<td><f:textarea class="control" path="content" name="content"
					type="text" /><br />
				<div class="error">
					<f:errors path="content"></f:errors>
				</div></td>
		</tr>

		<tr>
			<td class="label"></td>
			<td><input class="control" value="Send Message" type="submit" /></td>
		</tr>

	</table>
</f:form>
