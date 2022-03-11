<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap')
	;

* {
	font-family: "Poppins";
}

table,th,td,tr {
	border-collapse:collapse;
	border: 1px solid black;
	padding:10px;
}
</style>


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

<p>Welcome to your account</p>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:actionmessage/>
<s:actionerror/>
<hr />
<hr />
<%@ page import="org.beans.Account"%>
<%@page import="java.sql.Timestamp"%>
<%
Account account = (Account) session.getAttribute("account");
%>

<table>
	<tr>
		<th>Property</th>
		<th>Value</th>
	</tr>
	<tr>
		<td>Account Number</td>
		<td>
			<%
			out.print(account.getAccountNumber());
			%>
		</td>
	</tr>
	<tr>
		<td>Customer Name</td>
		<td>
			<%
			out.print(account.getCustomerName());
			%>
		</td>
	</tr>
	<tr>
		<td>Balance</td>
		<td>
			<%
			out.print(account.getBalance());
			%>
		</td>
	</tr>
	<tr>
		<td>Account type</td>
		<td>
			<%
			out.print(account.getAccountType());
			%>
		</td>
	</tr>
	<tr>
		<td>Created On</td>
		<td>
			<%
			out.print(new Timestamp(account.getCreateTime()));
			%>
		</td>
	</tr>
</table>



