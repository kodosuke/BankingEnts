<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/pages/headers.jsp" />
<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">
<title>Login User</title>

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
	color : #ff7b1c;
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
	
		
		<h1 class="text-justify m-20">Welcome back! Login to your account.</h1>
		<p id="message" class="container p-20 text-success text-justify ml-20">
		<s:actionmessage/>
		</p>	
		<p id="error" class="container p-20 text-danger text-justify ml-20">
			<s:actionerror/>
		</p>
		
		<s:form class="mt-100" name="loginForm" action="loginUser"> 
			<s:textfield name="contact" label="Enter your e mail address "
				required="true" class="form-control" type="email"
				placeholder="eg. consumer@corp.com" />
				
			<s:textfield name="password" label="Enter your account password "
				required="true" class="form-control" type="password" />
			<s:token/>	
			<s:submit value="Log In" class="btn btn-dark text-white container bg-gradient pt-25" onclick="return validateThis();"/>
			<s:reset value="Reset" class="btn btn-warning container bg-gradient" />
		</s:form>
		<p class="inline">No account ?</p>
		<a href="register"
			class="btn btn-primary container-sm col-3 bg-light bg-gradient text-dark align-middle pt-25">
			Register new account here</a>
	</div>

</body>
<script src="scripts/loginUser.js"></script>
</html>

