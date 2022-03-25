function fetchUserProfile() {
  fetch("/Banking/pages/components/userProfile.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("userProfile_container").innerHTML = data;
    })
    .then(() => {
      $.getJSON({
        url: "/Banking/api/profile",
        dataType: "json",
        cache: false,
        success: (data) => {
          console.log(data);
          let customer = data[0];
          let account = data[1];
          let table = $("#profileInformation");
          table.empty();

          table.append(
            `<tr> <td> Customer ID </td> <td>  ${customer.customerID} </td> </tr>`
          );
          table.append(
            `<tr> <td> Customer Name </td> <td>  ${customer.customerName} </td> </tr>`
          );
          table.append(
            `<tr> <td> Contact </td> <td>  ${customer.contact} </td> </tr>`
          );
          table.append(
            `<tr> <td> Gender </td> <td>  ${customer.gender} </td> </tr>`
          );
          table.append(
            `<tr> <td> Date of Birth </td> <td>  ${customer.dateOfBirth} </td> </tr>`
          );
          table.append(
            `<tr> <td> Account Number </td> <td>  ${account.accountNumber} </td> </tr>`
          );
          table.append(
            `<tr> <td> Balance </td> <td>  ${account.balance} </td> </tr>`
          );
          table.append(
            `<tr> <td> Account Type </td> <td>  ${account.accountType} </td> </tr>`
          );
          table.append(
            `<tr> <td> Created on </td> <td>  ${new Date(
              account.creationTime
            ).toUTCString()} </td> </tr>`
          );
        },
      });
    })
    .then(() => {
      $(".hidden").hide(0, () => {
        $("#userProfile_container").show(0);
      });
    });
}

function fetchNavigations() {
  fetch("/Banking/pages/components/navigations.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("navigations_container").innerHTML = data;
    });
}

function fetchTransferBalance() {
  fetch("/Banking/pages/components/transferBalance.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("transferBalance_container").innerHTML = data;
    });
}

function fetchDebitBalance() {
  fetch("/Banking/pages/components/debitBalance.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("debitBalance_container").innerHTML = data;
    });
}
function fetchCreditBalance() {
  fetch("/Banking/pages/components/creditBalance.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("creditBalance_container").innerHTML = data;
    });
}

function fetchAddBeneficiary() {
  fetch("/Banking/pages/components/addBeneficiary.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("addBeneficiary_container").innerHTML = data;
    });
}

function fetchChangePassword() {
  fetch("/Banking/pages/components/changePassword.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("changePassword_container").innerHTML = data;
    });
}

function fetchTxnStatements() {
  $.getJSON({
    url: "/Banking/api/getTransactions",
    type: "POST",
    dataType: "json",
    cache: false,
    success: (data) => {
      console.log("Success.");
      console.log(data);

      let statements = $("#statements");
      for (let i = 0; i < data.length; i++) {
        statements.append(
          `<tr><td>	${data[i].txnHash}	</td><td>	${data[i].amount}	</td><td>	${
            data[i].mode
          }	</td><td>	${new Date(data[i].creationTime).toUTCString()}	</td></tr>`
        );
      }
    },
  });
}

function fetchTransactions() {
  fetch("/Banking/pages/components/viewTransactions.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("viewTransactions_container").innerHTML = data;
    })
    .then(() => {
      $(".hidden").hide(0, () => {
        $("#viewTransactions_container").show(0);
      });
    })
    .then(() => {
      fetchTxnStatements();
    });
}

function fetchBeneficiaries() {
  fetch("./pages/components/viewBeneficiaries.html")
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.querySelector("#viewBeneficiaries_container").innerHTML = data;
      $(".hidden").hide(0, () => {
        $("#viewBeneficiaries_container").show(0);
      });
    });

  $.getJSON({
    url: "/Banking/api/getBeneficiaries",
    type: "POST",
    dataType: "json",
    cache: false,
    success: (data) => {
      console.log("Success.");
      console.log(data);

      let statements = $("#beneficiaries");
      for (let i = 0; i < data.length; i++) {
        statements.append(
          `<tr><td>	${i + 1}	</td><td>	${data[i].recipient}	</td><td>	${
            data[i].recipientContact
          }	</td><td>	<button onclick='sendToTransfer(${data[i].recipient}, \"${
            data[i].recipientContact
          }\");' class=\'btn btn-primary container\'> Send Money </button>	</td></tr>`
        );
      }
    },
  });
}

function loadUser() {
  if (sessionStorage.getItem("SIGNED") != "true") {
    window.location.href = "login";
  } else {
    fetchBeneficiaries();
    fetchCreditBalance();
    fetchDebitBalance();
    fetchTransferBalance();
    fetchChangePassword();
    fetchNavigations();
    fetchAddBeneficiary();
    console.log("Loaded Home.");
    fetchUserProfile();
  }
}

$(document).ready(() => {
  loadUser();
});

function sendToTransfer(accountNumber, mail) {

  $(".hidden").hide(0, () => {
    $("#transferBalance_container").show(0, () => {

      document.transferBalanceForm.recipientAccountNumber.value = accountNumber;
      document.transferBalanceForm.recipientContact.value = mail;
      document.getElementById("recipientAccountNumber").readOnly = true;
      document.getElementById("recipientContact").readOnly = true;


    });
  });
}
