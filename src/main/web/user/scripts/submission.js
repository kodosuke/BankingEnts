function validateMail(mail) {
	let mailFormat = /^[a-z0-9._-]+@[a-z0-9]+\.[a-z]{2,}$/;
	return mailFormat.test(mail);
}

function checkPasswordStrength(password) {
	let strongPwd = /^(?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[~!@#$%^&*()_\-+{}\[\]|\\;:'",.<>?\/]).{8,}$/;
	return strongPwd.test(password);
}

const submitCreditBalance = () => {

	document.getElementById("creditBalance_message").textContent = "";
	document.getElementById("creditBalance_error").textContent = "";

	const amount = document.creditBalance.amount.value;

	if (amount <= 0 || amount > 100000) {
		document.getElementById("creditBalance_error").innerText =
			"It is recommended that the deposit amount should not exceed 100000 but not be zero.";
		return false;
	}

	let form = $("#creditBalance");


	$.getJSON({
		url: form.attr("action"),
		data: form.serialize(),
		type: "POST",
		cache: false,
		success: (data) => {

			if (data[0].error != null) {
				document.getElementById("creditBalance_error").innerText =
					data[0].error;
				return false;
			} else {
				sessionStorage.setItem("ACCOUNT", JSON.stringify(data[1]));
				document.getElementById("creditBalance_message").innerText =
					data[0].message;

				return true;
			}
		},
		error: (data) => {
			console.error("Bad request made");
			window.location.href = "/banking/user/login";

		},

	})
}

const submitDebitBalance = () => {

	document.getElementById("debitBalance_error").innerText = "";
	document.getElementById("debitBalance_message").innerText = "";

	let amount = document.debitBalance.amount.value;

	if (amount <= 0 || amount > 10000) {
		document.getElementById("debitBalance_error").innerText =
			"It is recommended that the amount should not exceed 10000 but not be zero.";
		return false;
	}

	let form = $("#debitBalance");

	$.getJSON({
		url: form.attr("action"),
		data: form.serialize(),
		type: "POST",
		cache: false,
		success: (data) => {
			if (data[0].error != null) {
				document.getElementById("debitBalance_error").innerText = data[0].error;
				return false;
			} else {
				sessionStorage.setItem("ACCOUNT", JSON.stringify(data[1]));
				document.getElementById("debitBalance_message").innerText =
					data[0].message;

				return true;
			}
		},
		error: (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
		}
	});
}

const submitTransferBalance = () => {




document.getElementById("transferBalance_error").innerText = "";
  document.getElementById("transferBalance_message").innerText = "";

  let mail = document.transferBalance.recipientContact.value;
  let amount = document.transferBalance.amount.value;
  let accountNumber = document.transferBalance.recipientAccountNumber.value;

  if (accountNumber <= 0) {
    document.getElementById("transferBalance_error").innerText =
      "Account number should be greater than 0.";
    return false;
  } else if (amount > 10000 || amount <= 0) {
    document.getElementById("transferBalance_error").innerText =
      "Amount should be between 0 and 50000.";
    return false;
  } else if (!validateMail(mail)) {
    document.getElementById("transferBalance_error").innerText =
      "Please enter a valid e-mail address.";
    return false;
  }

  let form = $("#transferBalance");
  let actionUrl = form.attr("action");

  $.getJSON({
    url: actionUrl,
    data: form.serialize(),
    type: "POST",
    dataType: "json",
    cache: false,
    success: (data) => {
      if (data[0].error != null) {
        document.getElementById("transferBalance_error").innerText =
          data[0].error;
        return false;
      } else {
        sessionStorage.setItem("ACCOUNT", JSON.stringify(data[1]));
        document.getElementById("transferBalance_message").innerText =
          data[0].message;
        return true;
      }
    },
    error: (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
		}
  });
}

const submitChangePassword = () => {
	
  document.getElementById("changePassword_error").innerText = "";
  document.getElementById("changePassword_message").innerText = "";

  let currentPassword = document.changePassword.currentPassword.value;
  let confirmPassword = document.changePassword.confirmPassword.value;
  let newPassword = document.changePassword.newPassword.value;

  if (currentPassword.localeCompare(newPassword) == 0) {
    document.getElementById("changePassword_error").innerText =
      "Current password cannot be the new password.";
    return false;
  }

  if (!checkPasswordStrength(newPassword)) {
    document.getElementById("changePassword_error").innerText =
      "Password should contain 8 characters, with at least a symbol, upper and lower case letters and a number to be secure.";
    return false;
  }

  if (newPassword.localeCompare(confirmPassword) != 0) {
    document.getElementById("changePassword_error").innerText =
      "New password and confirm password mismatch.";
    return false;
  }

  document.getElementById("changePassword_error").innerText = "";
  
  let form = $("#changePassword");
  
  $.getJSON({
    url: form.attr("action"),
    data: form.serialize(),
    type: "POST",
    dataType: "json",
    cache: false,
    success: (data) => {
      console.log("Success.");
      console.log(data);

      if (data[0].error != null) {
        	document.getElementById("changePassword_error").innerText =
          	data[0].error;
        	return false;
      } else {
        document.getElementById("changePassword_message").innerText =
         	data[0].message;
        	alert(data[0].message);
        	
        	window.location.href = "/banking/user/";
        	}
		}, 
		error : (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
		}
  })

}
const submitNewBeneficiary = () => {
	
	document.getElementById("addNewBeneficiary_error").innerText = "";
	document.getElementById("addNewBeneficiary_message").innerText = "";

	let mail = document.newBeneficiary.beneficiaryContact.value;
	let accountNumber =
		document.newBeneficiary.beneficiaryAccountNumber.value;

	if (accountNumber <= 0) {
		document.getElementById("addBeneficiary_error").innerText =
			"Account number should be greater than 0.";
		return false;
	} else if (!validateMail(mail)) {
		document.getElementById("addBeneficiary_error").innerText =
			"Make sure you enter a valid email address.";
		return false;
	}

	let form = $("#newBeneficiary");


	$.getJSON({
		url: form.attr("action"),
		data: form.serialize(),
		type: "POST",
		dataType: "json",
		cache: false,
		success: (data) => {
			if (data[0].error != null) {
				document.getElementById("addNewBeneficiary_error").innerText =
					data[0].error;
				return false;
			} else {
				document.getElementById("addNewBeneficiary_message").innerText =
					data[0].message;
				alert(data[0].message);
				sendToTransfer(accountNumber, mail);
				return true;
			}
		},
		error: (data) => {
			console.error("Bad request made.");
			window.location.href = "/banking/user/login";
			}
		});

 }
 
 function resetForms() {
	let forms = document.getElementsByTagName("form");
	for(let i = 0; i < forms.length; i++) {
		forms[i].reset();
	}
	
	let errors = document.querySelectorAll("p.error");
	for(let i = 0; i < errors.length; i++) {
		errors[i].innerText = "";
	}
	
	let messages = document.querySelectorAll("p.message");
	for(let i =0; i < messages.length; i++) {
		messages[i].innerText = "";
	}
	
	sessionStorage.removeItem("recipientContact");
	sessionStorage.removeItem("recipient");
}

const sendToTransfer = (accountNumber, contact) => {

	window.history.pushState({}, "", window.location.origin + "/banking/user/transferBalance");
	transferBalance();
	document.transferBalance.recipientAccountNumber.value = accountNumber;
	document.transferBalance.recipientContact.value = contact;
	document.transferBalance.recipientAccountNumber.readOnly = true;
	document.transferBalance.recipientContact.readOnly = true;
		
}

