<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<div class="messagediv">

<table>
<tr>
	<td class="from">From</td>
	<td class="subject">Subject</td>

</tr>
<c:forEach var="message" items="${messages}">
<tr>
   <td class="from"><c:out value="${message.name}(${message.email})"></c:out></td>
   
   <td class="subject"><a href="<c:url value='/showmessage?id=${message.id}'/>">${message.subject}</a></td>
</tr>
</c:forEach>
</table>
</div>