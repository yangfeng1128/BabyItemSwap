<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<ul>
		<li><a href="<c:url value='/' />">Home</a></li>
		<li><a href="<c:url value='/offers' />">Baby Items</a></li>
		<li><a href="<c:url value='/contactus' />">Contact Us</a></li>
		<li><a href="<c:url value='/aboutus' />">About Us</a></li>

	</ul>
   <script type="text/javascript">
    function updateMessageLink(data)
    {
    	$("span#numberOfMessages").text(data.number);
    }
	function onLoad(){
		updatePage();
		window.setInterval(updatePage,5000);
	}
	
	function updatePage(){
		$.getJSON("<c:url value="/getmessages"/>",updateMessageLink);
	}
	
	$(document).ready(onLoad);	

</script>