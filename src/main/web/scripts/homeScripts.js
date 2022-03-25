function showCreditBalance() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#creditBalance_container").show(0);
  });
}

function showDebitBalance() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#debitBalance_container").show(0);
  });
}

function showTransferBalance() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#transferBalance_container").show(0, () => {
      document.getElementById("recipientAccountNumber").readOnly = false;
      document.getElementById("recipientContact").readOnly = false;
});
  });
}

function showChangePassword() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#changePassword_container").show(0);
  });
}
function showBeneficiaries() {
	resetForms();
  fetchBeneficiaries(() => {
    $(".hidden").hide(0, () => {
      $("#viewBeneficiaries_container").show(0);
    });
  });
}

function showTransactions() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#viewTransactions_container").show(0, () => {
      fetchTransactions();
    	let statements = $("#statements");

      let data = JSON.parse(sessionStorage.getItem("TRANSACTIONS"));

      for (let i = 0; i < data.length; i++) {
        statements.append(
          `<tr><td>	${data[i].txnHash}	</td><td>	${data[i].amount}	</td><td>	${
            data[i].mode
          }	</td><td>	${new Date(data[i].creationTime).toUTCString()}	</td></tr>`
        );
      }
    });
  });
}

function showAddBeneficiary() {
	resetForms();
  $(".hidden").hide(0, () => {
    $("#addBeneficiary_container").show(0,() => {});
  });
}


function submitChangePassword() {
  document.getElementById("changePassword_error").innerText = "";
  document.getElementById("changePassword_message").innerText = "";

  let currentPassword = document.changePwdForm.currentPassword.value;
  let confirmPassword = document.changePwdForm.confirmPassword.value;
  let newPassword = document.changePwdForm.newPassword.value;

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

  let form = $("#changePwdForm");
  let actionUrl = form.attr("action");

  $.getJSON({
    url: actionUrl,
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

        $(".hidden").hide(0, () => {
          $("#userProfile_container").show();
        });
        
		
        return true;
      }
    },
  });
}

function submitCreditBalance() {
  document.getElementById("creditBalance_error").innerText = "";
  document.getElementById("creditBalance_message").innerText = "";

  let amount = document.creditBalanceForm.amount.value;

  if (amount <= 0 || amount > 100000) {
    document.getElementById("creditBalance_error").innerText =
      "It is recommended that the deposit amount should not exceed 100000 but not be zero.";
    return false;
  }

  let form = $("#creditBalanceForm");
  let actionUrl = form.attr("action");

  $.getJSON({
    url: actionUrl,
    data: form.serialize(),
    type: "POST",
    dataType: "json",
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
  });
}

function submitDebitBalance() {
  document.getElementById("debitBalance_error").innerText = "";
  document.getElementById("debitBalance_message").innerText = "";

  let amount = document.debitBalanceForm.amount.value;

  if (amount <= 0 || amount > 10000) {
    document.getElementById("debitBalance_error").innerText =
      "It is recommended that the amount should not exceed 10000 but not be zero.";
    return false;
  }

  let form = $("#debitBalanceForm");
  let actionUrl = form.attr("action");
  console.log(form.serialize());

  $.getJSON({
    url: actionUrl,
    data: form.serialize(),
    type: "POST",
    dataType: "json",
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
  });
}

function submitTransferBalance() {
  document.getElementById("transferBalance_error").innerText = "";
  document.getElementById("transferBalance_message").innerText = "";

  let mail = document.transferBalanceForm.recipientContact.value;
  let amount = document.transferBalanceForm.amount.value;
  let accountNumber = document.transferBalanceForm.recipientAccountNumber.value;

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

  let form = $("#transferBalanceForm");
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
  });
}

function submitNewBeneficiary() {
  document.getElementById("addBeneficiary_error").innerText = "";
  document.getElementById("addBeneficiary_message").innerText = "";

  let mail = document.addBeneficiaryForm.beneficiaryContact.value;
  let accountNumber =
    document.addBeneficiaryForm.beneficiaryAccountNumber.value;

  if (accountNumber <= 0) {
    document.getElementById("addBeneficiary_error").innerText =
      "Account number should be greater than 0.";
    return false;
  } else if (!validateMail(mail)) {
    document.getElementById("addBeneficiary_error").innerText =
      "Make sure you enter a valid email address.";
    return false;
  }

  sessionStorage.setItem(
    "BENEFICIARY",
    JSON.stringify({
      ACCOUNT_NUMBER: accountNumber,
      CONTACT: mail,
    })
  );

  let form = $("#addBeneficiaryForm");
  let actionUrl = form.attr("action");

  $.getJSON({
    url: actionUrl,
    data: form.serialize(),
    type: "POST",
    dataType: "json",
    cache: false,
    success: (data) => {
      if (data[0].error != null) {
        document.getElementById("addBeneficiary_error").innerText =
          data[0].error;
        return false;
      } else {
        document.getElementById("addBeneficiary_message").innerText =
          data[0].message;
        alert(data[0].message);
        sendToTransfer(accountNumber, mail);
        return true;
      }
    },
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
}
