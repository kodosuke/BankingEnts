<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">

<title>Home - Profile</title>

<!-- Custom styling -->
<style type="text/css"></style>
<jsp:include page="/pages/headers.jsp" />
<jsp:include page="/pages/navigations.jsp" />
</head>
<body class="">
<!-- bg-secondary bg-gradient -->

	<div class="container col-6">
		<p id="error" class="container p-20 text-danger text-justify ml-20">
			<s:actionerror />
		</p>


		<%@ page import="org.beans.Account"%>
		<%@page import="java.sql.Timestamp"%>
		<%
			Account account = (Account) session.getAttribute("account");
		%>

		<table class="table table-bordered bg-white">
			<tr class="bg-dark text-white">
				<th>Property Name</th>
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
				<td>E mail Address</td>
				<td>
					<%
					out.print(account.getContact());
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
					out.print(new Timestamp(account.getCreationTime()));
					%>
				</td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<%
					out.print(account.getGender());
					%>
				</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td>
					<%
					out.print(account.getDateOfBirth());
					%>
				</td>
			</tr>
		</table>


	</div>


</body>
</html>