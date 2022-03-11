<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<meta charset="UTF-8">
<title>
	Show transactions
</title>
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
<%@ page import="org.beans.Transaction"%>
<%@ page import="java.util.*"%>
<s:actionmessage/>
<s:actionerror/>
<body>
<%
  HashMap<Long, Transaction> txnsMap = (HashMap<Long, Transaction>) session.getAttribute("transactionsMap");
%>

<table>
  <tr>
    <th>Transaction Hash</th>
    <th>Amount</th>
    <th>Mode</th>
    <th>Time</th>
  </tr>
	<% 
	for( Map.Entry<Long, Transaction> entry : txnsMap.entrySet()) {
		out.println("<tr><td>" + entry.getValue().getTxnHash() + "</td><td>" + 
				entry.getValue().getAmount() + "</td><td>" + entry.getValue().getMode().name() +
				"</td><td>" + new Date(entry.getValue().getCreateTime()).toString() + "</td></tr>");
		}%>

</table>

</body>
</html>