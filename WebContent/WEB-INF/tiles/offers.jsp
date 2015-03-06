<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="offerdiv">
   <c:if test="${offers.size()}<1">
       <c:out value="No Items are available at present."></c:out>
   </c:if>
	<table>
        
		<c:forEach var="offer" items="${offers}">
			<tr>
				<td class="name"><c:out value="${offer.user.name}"></c:out></td>
				<td class="contact"><a
					href="<c:url value='/message?uid=${offer.user.username}'/>">Contact</a></td>
				<td class="text"><c:out value="${offer.text}"></c:out></td>
				<td class="image"><a
					href="<c:url value='/images?userName=${offer.user.username}&offerId=${offer.id}'/>">View
						Image</a></td>

			</tr>
		</c:forEach>
	</table>
</div>