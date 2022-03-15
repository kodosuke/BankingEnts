let action = document.getElementById("action").value;

document.getElementById("targetHead").innerHTML = action + " your balance";
document.bankingForm.action = action;
document.getElementById("action-btn").value = action;


function validateEmail( str) {

	let mailformat = /[a-zA-Z]+([a-zA-Z0-9\-\.]+)?([a-z0-9])@[a-zA-Z]{4,}\.[a-zA-Z]{2,4}(\.[a-zA-Z]{2,3})?/;
	return mailformat.test(str);
}

function validateThisForm() {

	let mail = document.bankingForm.recipientContact.value;
	
	if( !validateEmail( mail)) {
		document.getElementById("error").innerText = "Please enter a valid e-mail address.";
		return false;
	}
	
	document.getElementById("error").innerText = "";
	submitThisForm();
	return true;
}

function submitThisForm() {
	
	$("bankingForm").submit( function( e) {
		
		e.preventDefault();
	
		let form = $(this);
		let actionUrl = form.attr('action');
		
		$.ajax( {
			type : "POST",
			url : actionUrl,
			data :form.serialize() 
		});
	});
}
