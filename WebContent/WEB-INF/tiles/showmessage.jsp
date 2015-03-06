<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<div id="message">
    
    <div>${message.subject}</div>
    <div>${message.content}</div>
    <div><a href="<c:url value='/message?uid=${message.name}'/>">Reply</a></div>
 
</div>


