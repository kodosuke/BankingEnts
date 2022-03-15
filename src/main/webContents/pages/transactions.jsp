<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">

<title>Transactions</title>

<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/pages/headers.jsp" />
<jsp:include page="/pages/navigations.jsp" />

<%@ page import="org.beans.Transaction"%>
<%@ page import="java.util.*"%>


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

<!-- Custom styling -->
<style type="text/css">
table {

	margin-top:80px;
}
tr, td, th {
 padding:10px;
 font-family : JetBrains Mono;
 }


</style>
</head> 

<body>
<%
  HashMap<Long, Transaction> txnsMap = (HashMap<Long, Transaction>) session.getAttribute("transactionsMap");
%>


</body>

<div class="container">

<% if(!txnsMap.isEmpty()) { %>
<table class="table table-dark">
<thead>
  <tr>
    <th>Transaction Hash</th>
    <th>Amount</th>
    <th>Mode</th>
    <th>Time</th>
  </tr>
  </thead>
  <tbody class="table table-striped">
	<% 
	for( Map.Entry<Long, Transaction> entry : txnsMap.entrySet()) {
		out.println(	"<tr><td>" + entry.getValue().getTxnHash() 
					+ 	"</td><td>" + entry.getValue().getAmount() 
					+ 	"</td><td>" + entry.getValue().getMode().name() 
					+	"</td><td>" + new Date(entry.getValue().getCreationTime()).toString() 
					+ "</td></tr>");
		}
	
	  %>
	  </tbody>
</table>
  <% } else { %>
	 <img src="https://cdn.dribbble.com/users/1753953/screenshots/3818675/media/f59ed80d5c527e2461d8ba49adc36160.gif" class="container col-8">
	<p class="text-center">No transactions yet   :(</p>
<% } %>
</div>


</html>