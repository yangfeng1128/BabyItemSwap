<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);

		$("#confirmpass").keyup(checkPasswordsMatch);
		$("#details").submit(canSubmit);
	}
	
	function canSubmit(){
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if(passowrd !=confirmpass)
		{
			alert("Password do not match");
			return false;
		} else {
		return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if (password.length > 3 || confirmpass.length > 3) {

			if (password == confirmpass) {
				$("#passmatch").text("<fmt:message key='Matchedpasswords.user.password'/>");
				$("#passmatch").addClass("valid");
				$("#passmatch").removeClass("error");
				
			} else {

				$("#passmatch").text("<fmt:message key='UnmatchedPasswords.user.password' />");
				$("#passmatch").addClass("error");
				$("#passmatch").removeClass("valid");
			}
		}
	}
	$(document).ready(onLoad);
</script>