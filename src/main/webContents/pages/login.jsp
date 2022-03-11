<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<style> 
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
* {
	font-family: "Poppins";
}

</style>

<% 
	if(session.getAttribute("signed") == "true") { 
		System.out.println("Invalid login attempted.");
		response.sendRedirect("/.action");
	} 
%>

<h2>
	LogIn to your account.
</h2>

<h4>	
	<s:actionmessage/>
	<s:actionerror/>
</h4>


<s:form action="loginAction">

<s:textfield name="accountNumber" requiredLabel="true" label="Enter your username " required="true" type="number" min="1001"/>	
<s:textfield name="password" requiredLabel="true" label="Enter your password " required="true" type="password"/>


<s:submit value="Login"/>
</s:form>

<a href="<s:url action="register"/>">Register</a>

