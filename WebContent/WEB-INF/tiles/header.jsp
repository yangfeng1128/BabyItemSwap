<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);
	}

	function onLoad() {
		updatePage();
		window.setInterval(updatePage, 10000);
	}

	function updatePage() {
		$.getJSON("<c:url value="/getmessages"/>", updateMessageLink);
	}

	$(document).ready(onLoad);
</script>

<div class="title">Baby Item Swap</div>

<div id="loginDiv">
	<ul>
		<li><sec:authorize access="!isAuthenticated()">
				<a class="login" href="<c:url value='/login' />">Log In</a>
			</sec:authorize></li>
		<li><sec:authorize access="isAuthenticated()">
				<a class="login" href="<c:url value='/j_spring_security_logout' />">Log
					Out</a>
			</sec:authorize></li>
		<li><sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="login" href="<c:url value='/adminpage' />">Admin</a>
			</sec:authorize></li>
		<li><sec:authorize access="isAuthenticated()">
				<a class="login" href="<c:url value='/messages' />">Messages(<span
					id="numberOfMessages">0</span>)
				</a>

			</sec:authorize></li>
       <li>
		<c:choose>
			<c:when test="${hasOffer}">
				<a class="login"
					href="${pageContext.request.contextPath}/createoffer">Edit your offer</a>
			</c:when>
			<c:otherwise>
				<p>
					<a class="login"
						href="${pageContext.request.contextPath}/createoffer">Add a
						new offer</a>
				</p>
			</c:otherwise>
		</c:choose>
      </li>
	</ul>
</div>

