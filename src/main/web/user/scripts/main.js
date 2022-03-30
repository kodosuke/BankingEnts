const home =  () => {
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Welcome, dear customer.";
	let baseContainer = cleanBaseContainer();
	baseContainer.appendChild(textHead);
	
	const account = getAccount();
	const customer = getCustomer();
	delete account.customerID;
	delete customer.password;
	
	const profile = document.createElement("table");
	profile.id = "profile";
	
	for(const [field, value] of Object.entries(account)) {
		let row = document.createElement("tr");
		let fieldColumn = document.createElement("td");
		fieldColumn.textContent = field;
		let valueColumn = document.createElement("td");
		valueColumn.textContent = value;
		
		row.appendChild(fieldColumn);
		row.appendChild(valueColumn);
		profile.appendChild(row);
	}
	
	for(const [field, value] of Object.entries(customer)) {
		let row = document.createElement("tr");
		let fieldColumn = document.createElement("td");
		fieldColumn.textContent = field;
		let valueColumn = document.createElement("td");
		valueColumn.textContent = value;
		
		row.appendChild(fieldColumn);
		row.appendChild(valueColumn);
		profile.appendChild(row);
	}
	
	baseContainer.appendChild(profile);
};

const getCustomer = () => {
	return customer = JSON.parse(sessionStorage.CUSTOMER);
}

const getAccount = () => {
	return account = JSON.parse(sessionStorage.ACCOUNT);
}


const creditBalance = () => {
	//document.getElementById("baseContainer").innerText = "creditBalance"
	
	const baseContainer = cleanBaseContainer();
	let textHead = document.createElement("h2");
	textHead.textContent = "Credit balance";
	baseContainer.appendChild(textHead);
	
	const message = document.createElement("p");
	message.id = "creditBalance_message";
	message.className = "message";
	baseContainer.appendChild(message);
	
	const error = document.createElement("p");
	error.id = "creditBalance_error";
	error.className = "error";
	baseContainer.appendChild(error);
	
	const creditBalance = document.createElement("form");
	creditBalance.name = "creditBalance";
	creditBalance.action = "/banking/api/creditBalance";
	creditBalance.id = "creditBalance";
	
	const creditBalanceTable = document.createElement("table");
	
	const amountRow = document.createElement("tr");
	const amountLabelColumn = document.createElement("td");
	amountLabelColumn.innerText = "Enter the amount";
	const amountInputColumn = document.createElement("td");
	const amountIn = document.createElement("input");
	amountIn.name = "amount";
	amountIn.type = "number";
	amountIn.min = 1;
	amountIn.max = 50000;
	amountIn.step = 0.01; 
	amountInputColumn.append(amountIn);
	amountRow.appendChild(amountLabelColumn);
	amountRow.appendChild(amountInputColumn);
	creditBalanceTable.appendChild(amountRow);
	
	const submitBtnRow = document.createElement("tr");
	const submitBtnColumn = document.createElement("td");
	submitBtnColumn.colSpan = 2;
	const submitBtn = document.createElement("input");
	submitBtn.type = "button";
	submitBtn.value = "Credit balance";
	submitBtn.style.width = "100%";
	submitBtnColumn.appendChild(submitBtn);
	submitBtnRow.appendChild(submitBtnColumn);
	creditBalanceTable.appendChild(submitBtnRow);
	 
	 const resetBtnRow = document.createElement("tr");
	 const resetBtnColumn = document.createElement("td");
	 resetBtnColumn.colSpan = 2;
	 const resetBtn = document.createElement("input");
	 resetBtn.type = "reset";
	 resetBtn.style.width = "100%";
	 resetBtnColumn.appendChild(resetBtn);
	 resetBtnRow.appendChild(resetBtnColumn);
	 creditBalanceTable.appendChild(resetBtnRow);
	 
	creditBalance.appendChild(creditBalanceTable);
	baseContainer.appendChild(creditBalance);
	
	submitBtn.addEventListener("click", () => {submitCreditBalance()});
	
	
}
const debitBalance = () => {
	// document.getElementById("baseContainer").innerText = "debitBalance"
	
	const baseContainer = cleanBaseContainer();
	let textHead = document.createElement("h2");
	textHead.textContent = "Debit balance";
	baseContainer.appendChild(textHead);
	
	const message = document.createElement("p");
	message.id = "debitBalance_message";
	message.className = "message";
	baseContainer.appendChild(message);
	
	const error = document.createElement("p");
	error.id = "debitBalance_error";
	error.className = "error";
	baseContainer.appendChild(error);
	
	const debitBalance = document.createElement("form");
	debitBalance.name = "debitBalance";
	debitBalance.action = "/banking/api/debitBalance";
	debitBalance.id = "debitBalance";
	
	const debitBalanceTable = document.createElement("table");
	
	const amountRow = document.createElement("tr");
	const amountLabelColumn = document.createElement("td");
	amountLabelColumn.innerText = "Enter the amount";
	const amountInputColumn = document.createElement("td");
	const amountIn = document.createElement("input");
	amountIn.name = "amount";
	amountIn.type = "number";
	amountIn.min = 1;
	amountIn.max = 10000;
	amountIn.step = 0.01; 
	amountInputColumn.append(amountIn);
	amountRow.appendChild(amountLabelColumn);
	amountRow.appendChild(amountInputColumn);
	debitBalanceTable.appendChild(amountRow);
	
	const submitBtnRow = document.createElement("tr");
	const submitBtnColumn = document.createElement("td");
	submitBtnColumn.colSpan = 2;
	const submitBtn = document.createElement("input");
	submitBtn.type = "button";
	submitBtn.value = "Debit balance";
	submitBtn.style.width = "100%";
	submitBtnColumn.appendChild(submitBtn);
	submitBtnRow.appendChild(submitBtnColumn);
	debitBalanceTable.appendChild(submitBtnRow);
	 
	 const resetBtnRow = document.createElement("tr");
	 const resetBtnColumn = document.createElement("td");
	 resetBtnColumn.colSpan = 2;
	 const resetBtn = document.createElement("input");
	 resetBtn.type = "reset";
	 resetBtn.style.width = "100%";
	 resetBtnColumn.appendChild(resetBtn);
	 resetBtnRow.appendChild(resetBtnColumn);
	 debitBalanceTable.appendChild(resetBtnRow);
	 
	debitBalance.appendChild(debitBalanceTable);
	baseContainer.appendChild(debitBalance);
	
	submitBtn.addEventListener("click", () => {submitDebitBalance()});
}
const transferBalance = () => {
	// document.getElementById("baseContainer").innerText = "transferBalance"
	
	const baseContainer = cleanBaseContainer();
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Transfer balance";
	baseContainer.appendChild(textHead);
	
	const message = document.createElement("p");
	message.id = "transferBalance_message";
	message.className = "message";
	baseContainer.appendChild(message);
	
	const error = document.createElement("p");
	error.id = "transferBalance_error";
	error.className = "error";
	baseContainer.appendChild(error);
	
	const transferBalance = document.createElement("form");
	transferBalance.name = "transferBalance";
	transferBalance.action = "/banking/api/transferBalance";
	transferBalance.id = "transferBalance";
	
	const transferBalanceTable = document.createElement("table");
	
	const recipientRow = document.createElement("tr");
	const recipientLabel = document.createElement("td");
	recipientLabel.innerText = "Enter the recipient account number";
	const recipientColumn = document.createElement("td");
	const recipientIn = document.createElement("input");
	recipientIn.type = "number";
	recipientIn.name = "recipientAccountNumber";
	recipientIn.min = 1;
	recipientColumn.appendChild(recipientIn);
	recipientRow.appendChild(recipientLabel);
	recipientRow.appendChild(recipientColumn);
	transferBalanceTable.appendChild(recipientRow);
	
	
	const recipientContactRow = document.createElement("tr");
	const recipientContactLabel = document.createElement("td");
	recipientContactLabel.innerText = "Enter the recipient contact";
	const recipientContactColumn = document.createElement("td");
	const recipientContactIn = document.createElement("input");
	recipientContactIn.type = "text";
	recipientContactIn.name = "recipientContact";
	recipientContactColumn.appendChild(recipientContactIn);
	recipientContactRow.appendChild(recipientContactLabel);
	recipientContactRow.appendChild(recipientContactColumn);
	transferBalanceTable.appendChild(recipientContactRow);
	
	
	const amountRow = document.createElement("tr");
	const amountLabelColumn = document.createElement("td");
	amountLabelColumn.innerText = "Enter the amount";
	const amountInputColumn = document.createElement("td");
	const amountIn = document.createElement("input");
	amountIn.name = "amount";
	amountIn.type = "number";
	amountIn.min = 1;
	amountIn.max = 50000;
	amountIn.step = 0.01; 
	amountInputColumn.append(amountIn);
	amountRow.appendChild(amountLabelColumn);
	amountRow.appendChild(amountInputColumn);
	transferBalanceTable.appendChild(amountRow);
	
	const submitBtnRow = document.createElement("tr");
	const submitBtnColumn = document.createElement("td");
	submitBtnColumn.colSpan = 2;
	const submitBtn = document.createElement("input");
	submitBtn.type = "button";
	submitBtn.value = "Transfer balance";
	submitBtn.style.width = "100%";
	submitBtnColumn.appendChild(submitBtn);
	submitBtnRow.appendChild(submitBtnColumn);
	transferBalanceTable.appendChild(submitBtnRow);
	 
	 const resetBtnRow = document.createElement("tr");
	 const resetBtnColumn = document.createElement("td");
	 resetBtnColumn.colSpan = 2;
	 const resetBtn = document.createElement("input");
	 resetBtn.type = "reset";
	 resetBtn.style.width = "100%";
	 resetBtnColumn.appendChild(resetBtn);
	 resetBtnRow.appendChild(resetBtnColumn);
	 transferBalanceTable.appendChild(resetBtnRow);
	 
	transferBalance.appendChild(transferBalanceTable);
	baseContainer.appendChild(transferBalance);
	
	submitBtn.addEventListener("click", () => { submitTransferBalance()})
}
const changePassword = () => {
	// document.getElementById("baseContainer").innerText = "changePassword"
	
	const baseContainer = cleanBaseContainer();
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Change password";
	baseContainer.appendChild(textHead);

	const message = document.createElement("p");
	message.id = "changePassword_message";
	message.className = "message";
	baseContainer.appendChild(message);
	
	const error = document.createElement("p");
	error.id = "changePassword_error";
	error.className = "error";
	baseContainer.appendChild(error);
		
	const changePassword = document.createElement("form");
	changePassword.name = "changePassword";
	changePassword.action = "/banking/api/changePassword";
	changePassword.id = "changePassword";
	
	const changePasswordTable = document.createElement("table");
	
	const currentPasswordRow = document.createElement("tr");
	const currentPasswordLabel = document.createElement("td");
	currentPasswordLabel.innerText = "Enter the current password";
	const currentPasswordColumn = document.createElement("tr");
	const currentPasswordIn = document.createElement("input");
	currentPasswordIn.type = "password";
	currentPasswordIn.name = "currentPassword";
	currentPasswordColumn.appendChild(currentPasswordIn);
	currentPasswordRow.appendChild(currentPasswordLabel);
	currentPasswordRow.appendChild(currentPasswordColumn);
	changePasswordTable.appendChild(currentPasswordRow);
	
	
	const newPasswordRow = document.createElement("tr");
	const newPasswordLabel = document.createElement("td");
	newPasswordLabel.innerText = "Enter the new password";
	const newPasswordColumn = document.createElement("td");
	const newPasswordIn = document.createElement("input");
	newPasswordIn.type = "password";
	newPasswordIn.name = "newPassword";
	newPasswordColumn.appendChild(newPasswordIn);
	newPasswordRow.appendChild(newPasswordLabel);
	newPasswordRow.appendChild(newPasswordColumn);
	changePasswordTable.appendChild(newPasswordRow);
	
	
	const confirmPasswordRow = document.createElement("tr");
	const confirmPasswordLabel = document.createElement("td");
	confirmPasswordLabel.innerText = "Confirm the new password";
	const confirmPasswordColumn = document.createElement("td");
	const confirmPasswordIn = document.createElement("input");
	confirmPasswordIn.type = "password";
	confirmPasswordIn.name = "confirmPassword";
	confirmPasswordColumn.appendChild(confirmPasswordIn);
	confirmPasswordRow.appendChild(confirmPasswordLabel);
	confirmPasswordRow.appendChild(confirmPasswordColumn);
	changePasswordTable.appendChild(confirmPasswordRow);
	
	const submitBtnRow = document.createElement("tr");
	const submitBtnColumn = document.createElement("td");
	submitBtnColumn.colSpan = 2;
	const submitBtn = document.createElement("input");
	submitBtn.type = "button";
	submitBtn.value = "Transfer balance";
	submitBtn.style.width = "100%";
	submitBtnColumn.appendChild(submitBtn);
	submitBtnRow.appendChild(submitBtnColumn);
	changePasswordTable.appendChild(submitBtnRow);
	 
	 const resetBtnRow = document.createElement("tr");
	 const resetBtnColumn = document.createElement("td");
	 resetBtnColumn.colSpan = 2;
	 const resetBtn = document.createElement("input");
	 resetBtn.type = "reset";
	 resetBtn.style.width = "100%";
	 resetBtnColumn.appendChild(resetBtn);
	 resetBtnRow.appendChild(resetBtnColumn);
	 changePasswordTable.appendChild(resetBtnRow);
	
	changePassword.appendChild(changePasswordTable);
	baseContainer.appendChild(changePassword);
	
	submitBtn.addEventListener("click", () => { submitChangePassword()})
	
}
const viewTransactions= () => {
	// document.getElementById("baseContainer").innerText = "viewTransactions"
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Transactions";
	let baseContainer = cleanBaseContainer();
	baseContainer.appendChild(textHead);
	
	const txnTable = document.createElement("table");
	
	let thead = document.createElement("thead");
	let headerRow = document.createElement("tr");
	let txnHash = document.createElement("th");
	txnHash.textContent = "Transaction Hash";
	let amount = document.createElement("th");
	amount.textContent = "Amount";
	let mode = document.createElement("th");
	mode.textContent = "Transaction Mode";
	let creationTime = document.createElement("th");
	creationTime.textContent = "Transaction time" ;
	headerRow.append(txnHash, amount, mode, creationTime);
	thead.append(headerRow);
	txnTable.appendChild(thead);
	
	$.getJSON({
		url : "/banking/api/viewTransactions",
		success : (data) => {
			sessionStorage.setItem("txns", JSON.stringify(data));
			for(let i = 0; i < data.length; i++) {
				let row = document.createElement("tr");
				let txnHash = document.createElement("td");
				txnHash.textContent = data[i].txnHash;
				let amount = document.createElement("td");
				amount.textContent = data[i].amount;
				let mode = document.createElement("td");
				mode.textContent = data[i].mode;
				let creationTime = document.createElement("td");
				creationTime.textContent = new Date(data[i].creationTime).toUTCString();
				
				row.append(txnHash, amount, mode, creationTime);
				txnTable.appendChild(row);
			}
			baseContainer.appendChild(txnTable);
		},
		error: (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
			}
		
	})
	
	
}
const viewBeneficiaries = () => {
	// document.getElementById("baseContainer").innerText = "viewBeneficiaries"
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Beneficiaries";
	let baseContainer = cleanBaseContainer();
	baseContainer.appendChild(textHead);
	
	let table = document.createElement("table");
	
	let thead = document.createElement("thead");
	let headerRow = document.createElement("tr");
	let serialNo = document.createElement("th");
	serialNo.textContent = "S.No.";
	let beneficiary = document.createElement("th");
	beneficiary.textContent = "Beneficiary Account Number";
	let contact = document.createElement("th");
	contact.textContent = "Beneficiary contact";
	let action = document.createElement("th");
	action.textContent = "Action" ;
	headerRow.append(serialNo, beneficiary, contact, action);
	thead.append(headerRow);
	table.appendChild(thead);
	
	$.getJSON({
		url 	: "/banking/api/viewBeneficiaries",
		cache 	: false,
		type 	: "POST",
		success : (data) => {
			
			for(let i = 0; i < data.length; i++) {
				let row = document.createElement("tr");
				let serialNo = document.createElement("td");
				serialNo.textContent = i + 1;
				let beneficiary = document.createElement("td");
				beneficiary.textContent = data[i].recipient;
				let contact = document.createElement("td");
				contact.textContent = data[i].recipientContact;;
				let action = document.createElement("td");
				let actionBtn = document.createElement("button");
				actionBtn.textContent = "Transfer money";
				actionBtn.addEventListener("click", () => { sendToTransfer(data[i].recipient,data[i].recipientContact ); })
				action.appendChild(actionBtn);
				row.append(serialNo, beneficiary, contact, action);
				table.append(row);
			
			}
			
			baseContainer.appendChild(table);
		},
		error: (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
		}
	})
	
	baseContainer.append(table);
	
	
	
}
const addNewBeneficiary = () => {
	// document.getElementById("baseContainer").innerText = "addNewBeneficiary"
	
	const baseContainer = cleanBaseContainer();
	
	let textHead = document.createElement("h2");
	textHead.textContent = "Change password";
	baseContainer.appendChild(textHead);

	const message = document.createElement("p");
	message.id = "addNewBeneficiary_message";
	message.className = "message";
	baseContainer.appendChild(message);
	
	const error = document.createElement("p");
	error.id = "addNewBeneficiary_error";
	error.className = "error";
	baseContainer.appendChild(error);
	
	const newBeneficiary = document.createElement("form");
	newBeneficiary.action = "/banking/api/newBeneficiary";
	newBeneficiary.name = "newBeneficiary";
	newBeneficiary.id = "newBeneficiary";
	
	const newBeneficiaryTable = document.createElement("table");
	
	const beneficiaryRow = document.createElement("tr");
	const beneficiaryLabel = document.createElement("td");
	beneficiaryLabel.innerText = "Enter the beneficiary account number";
	const beneficiaryColumn = document.createElement("td");
	const beneficiaryIn = document.createElement("input");
	beneficiaryIn.type = "number";
	beneficiaryIn.min = 1;
	beneficiaryIn.name = "beneficiaryAccountNumber";
	beneficiaryColumn.appendChild(beneficiaryIn);
	beneficiaryRow.appendChild(beneficiaryLabel);
	beneficiaryRow.appendChild(beneficiaryColumn);
	newBeneficiaryTable.appendChild(beneficiaryRow);
	
	const beneficiaryContactRow = document.createElement("tr");
	const beneficiaryContactLabel = document.createElement("td");
	beneficiaryContactLabel.innerText = "Enter the beneficiary contact";
	const beneficiaryContactColumn = document.createElement("td");
	const beneficiaryContactIn = document.createElement("input");
	beneficiaryContactIn.type = "email";
	beneficiaryContactIn.name = "beneficiaryContact";
	beneficiaryContactColumn.appendChild(beneficiaryContactIn);
	beneficiaryContactRow.appendChild(beneficiaryContactLabel);
	beneficiaryContactRow.appendChild(beneficiaryContactColumn);
	newBeneficiaryTable.appendChild(beneficiaryContactRow);
	
	const submitBtnRow = document.createElement("tr");
	const submitBtnColumn = document.createElement("td");
	submitBtnColumn.colSpan = 2;
	const submitBtn = document.createElement("input");
	submitBtn.type = "button";
	submitBtn.value = "Add New Beneficiary";
	submitBtn.style.width = "100%";
	submitBtnColumn.appendChild(submitBtn);
	submitBtnRow.appendChild(submitBtnColumn);
	newBeneficiaryTable.appendChild(submitBtnRow);
	 
	 const resetBtnRow = document.createElement("tr");
	 const resetBtnColumn = document.createElement("td");
	 resetBtnColumn.colSpan = 2;
	 const resetBtn = document.createElement("input");
	 resetBtn.type = "reset";
	 resetBtn.style.width = "100%";
	 resetBtnColumn.appendChild(resetBtn);
	 resetBtnRow.appendChild(resetBtnColumn);
	 newBeneficiaryTable.appendChild(resetBtnRow);	
	 
	 newBeneficiary.appendChild(newBeneficiaryTable);
	 baseContainer.appendChild(newBeneficiary);
	 
 	submitBtn.addEventListener("click", () => { submitNewBeneficiary();})
	
	
}
const logOut = () => {
	// document.getElementById("baseContainer").innerText = "logOut"
	
	sessionStorage.setItem("SIGN", "false");
	window.location.href = "/banking/user/logOutUser";
}

const cleanBaseContainer = () => {
	let baseContainer = document.getElementById("baseContainer");
	while(baseContainer.firstChild) {
		baseContainer.removeChild(baseContainer.firstChild);
	}
	return baseContainer;
}

const routes = {
	
	"/banking/user/" : home,
	"/banking/user/creditBalance" : creditBalance,
	"/banking/user/debitBalance" : debitBalance,
	"/banking/user/transferBalance" : transferBalance,
	"/banking/user/changePassword" : changePassword,
	"/banking/user/viewTransactions" : viewTransactions,
	"/banking/user/viewBeneficiaries" : viewBeneficiaries,
	"/banking/user/addNewBeneficiary" : addNewBeneficiary,
	"/banking/user/logOut" : logOut
}

const handleLocation = () => {
	
    const path = window.location.pathname;
    const route = routes[decodeURI(path)] || routes[404];
	route.call();
	
	
}

const route = ( event ) => {
	
	event = event || window.event;
	event.preventDefault();
	window.history.pushState(
		{}, "", encodeURI( event.target.href )
	);
	handleLocation();
}


document.addEventListener( "DOMContentLoaded", () => {
	
	let isSigned = sessionStorage.getItem("SIGN");
	
	if(isSigned == "true") {
			fetch("/banking/user/components/navigations.html")
	.then((response) => {
		return response.text();
	})
	.then((data) => {
		document.getElementById("navBar").innerHTML = data;
	}) 
	} else {
		window.location.href = "/banking/user/login";
	}
	
	window.route = route;
	window.onpopstate = handleLocation;
	
	handleLocation();
	

})

const getRecipient = () => {
	return sessionStorage.getItem("recipientAccountNumber") || "";
}

const getRecipientContact = () => {
	return sessionStorage.getItem("recipientContact") || "";
}