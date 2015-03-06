<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function onDeleteClick(event){
	var doDelete = confirm("Are you sure you want to delete this offer?");
	if (doDelete == false)
		{
		event.preventDefault();
		}
}


function onReady(){
	$("#delete").click(onDeleteClick);
} 


$(document).ready(onReady);



</script>


<f:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer" enctype="multipart/form-data">
<f:input type="hidden" name="id" path="id"/>

<table class="formtable">
	<tr> <td class="label">Your offer</td><td><f:textarea class="control" path="text" name="text" rows="10" cols="10"></f:textarea><br/><f:errors path="text" cssClass="error"></f:errors>  </td></tr>
	<tr></tr>
	<tr> <td class="label"><c:out value="JPEG <1M"></c:out></td><td class="label"><input id="uploadimage" type="file" name="uploadimage"/> </td></tr>
	<tr> <td class="label"> </td><td><input name="save" value="Save Offer" type="submit" /></td></tr>
	<tr> </tr>
	<c:if test="${offer.id !=0}">
	
	<tr> <td class="label"></td><td>&nbsp;</td></tr>
	<tr> <td class="label"> </td><td><input id="delete" class="delete" name ="delete" value="Delete this offer" type="submit" /></td></tr>
	</c:if>
</table>
</f:form>
