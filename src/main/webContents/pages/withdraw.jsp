<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<meta charset="UTF-8">
<title>
	Deposit
</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap')
	;

* {
	font-family: "Poppins";
}
</style>
</head>
<body>

<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
response.setHeader("Expires", "0");
if (session.getAttribute("signed") != "true") {
	System.out.println("Invalid login attempted.");
	request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
} else {
	System.out.println("valid request to home.");
}
%>

<%@include file="/pages/navigations.jsp"%>
<s:actionmessage/>
<s:actionerror/>
<h1> Withdraw from your account </h1>

<s:form action="processWithdraw">
<s:label>Enter the amount to deposit</s:label>
	<s:textfield name="amount" required="true"/>
	<s:submit value="Withdraw"/>
</s:form>

</body>
</html>