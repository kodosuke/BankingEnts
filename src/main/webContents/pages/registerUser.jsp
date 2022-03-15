<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/pages/headers.jsp" />

<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>

<!-- Custom styling -->
<style type="text/css">
.btn {
	margin-top: 25;
	border-radius: 40px;
	padding: 15;
}

.btn-warning {
	margin-top: 5;
}

.btn-primary {
	margin-top: 0;
}

li {
	color: #ff7b1c;
	list-style: none;
}
</style>


</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Expires", "0");
		if (session.getAttribute("signed") == "true") {
			System.out.println("Invalid login/register attempted.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} 
	%>


	<div class="container p-5">


		<h1 class="text-justify m-20">Register yourself to start using
			account.</h1>
		<p id="message" class="container p-20 text-success text-justify ml-20"></p>
		<p id="error" class="container p-20 text-danger text-justify ml-20">
			<s:actionerror />
		</p>

		<s:form name="registrationForm" action="registerUser">

			<s:textfield name="customerName" label="Enter your full name "
				required="true" class="form-control" placeholder="eg. Peter Parker" />

			<s:textfield name="contact" label="Enter your e mail address "
				required="true" class="form-control" type="email"
				placeholder="eg. consumer@corp.com" />

			<s:textfield name="password" label="Create a password "
				required="true" class="form-control" type="password" />

			<s:textfield name="cPassword" label="Confirm password "
				required="true" class="form-control" type="password" />

			<s:textfield name="dateOfBirth" label="Enter your date of birth "
				required="true" class="form-control" type="date"
				placeholder="DD/MM/YYYY" max="11/02/2022" />

			<s:select name="gender" label="Select your gender" required="true"
				class="form-select" list="{'Male', 'Female'}" />

			<s:select name="accountType" list="{'SAVINGS', 'CURRENT'}"
				class="form-select" label="Select the account type " />

			<s:submit class="btn btn-dark container bg-gradient"
				value="Register Account" onclick="return validateThis();" />


			<s:reset value="Reset" class="btn btn-warning container bg-gradient" />
		<s:token/>	
		</s:form>
		<p class="inline">Already have an account ?</p>
		<a href="login"
			class="btn btn-primary container col-3 bg-light bg-gradient text-dark">
			Login here</a>
	</div>

</body>

<script src="scripts/registerUser.js"></script>
</html>