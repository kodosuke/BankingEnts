<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">

<title>Change password</title>

<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/pages/headers.jsp" />
<jsp:include page="/pages/navigations.jsp" />


<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Expires", "0");
	if (session.getAttribute("signed") != "true") {
		System.out.println("Invalid login attempted.");
		request.getRequestDispatcher("/pages/loginUser.jsp").forward(request, response);
	} else {
		System.out.println("valid request to profile.");
	}
%>

<style type="text/css">

.heading {
 margin:80px;
 }
.btn-dark{
	margin-top:50px;
	border-radius:40px;
	height:60px;
}
</style>
</head>
<body>

	<div class="container col-8">

		<p class="h5 text-primary heading">Change password.</p>
		
			<p id="message"
				class="container p-20 text-success text-justify ml-20">
				<s:actionmessage />
			</p>
			<p id="error" class="container p-20 text-danger text-justify ml-20">
				<s:actionerror />
			</p>
		
		<s:form class="container col-10" name="changePwdForm" action="changePassword" id="changePwdForm">
			<s:textfield name="currentPassword" type="password" label = "Enter the current password " class="form-control"/>
			<s:textfield name="newPassword" type="password" label = "Enter the new password " class="form-control"/>
			<s:textfield name="confirmPassword" type="password" label = "Confirm the new password " class="form-control"/>
			<s:token/>
			<s:submit value="Change" class="btn btn-dark container" onclick="return validatePasswords()"/>

		</s:form>
	</div>
</body>
<script type="text/javascript" src="scripts/password.js"></script>
</html>