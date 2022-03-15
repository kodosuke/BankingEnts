<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">

<title>Home - Profile</title>

<!-- Custom styling -->
<style type="text/css">
.bg {
	margin-top: 80;
}

.text {
	margin-top: 40
}

#targetHead {
	text-transform: capitalize;
}

.btn {
	text-transform: capitalize;
	height: 60;
	margin-top: 50;
	border-radius: 50px !important;
	margin-top: 25;
	border-radius: 40px !important;;
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
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/pages/headers.jsp" />
<jsp:include page="/pages/navigations.jsp" />

</head>
<body>

	<div class="container col-8 bg">

		<input hidden="true" id="action" value="${sessionScope.action}" />

		<s:form class="container col-10" name="bankingForm" action="">
			<p id="message"
				class="container p-20 text-success text-justify ml-20">
				<s:actionmessage />
			</p>
			<p id="error" class="container p-20 text-danger text-justify ml-20">
				<s:actionerror />
			</p>
			<p id="targetHead" class="text-primary"></p>
			<%
			if (session.getAttribute("action").equals("transfer")) {
			%>
			<s:textfield name="recipient" type="number" min="786001" step="1"
				label="Enter the receipent account Number" class="form-control"
				required="true" />
			<s:textfield name="recipientContact"
				label="Enter the e mail address of the recipient" required="true"
				type="email" class="form-control" />
			<%
			}
			%>
			<s:textfield name="amount" label="Enter the amount" min="1"
				type="number" step="0.01" class="form-control" id="number"/>
			<s:submit id="action-btn" onclick="return validateThisForm();"
				class="btn btn-dark container col-6 bg-dark bg-gradient text-white align-middle action-btn" />
			<s:reset value="Reset"
				class="btn btn-warning container col-6 bg-gradient" />

		</s:form>
	</div>
</body>
<script type="text/javascript" src="scripts/banking.js"></script>
</html>