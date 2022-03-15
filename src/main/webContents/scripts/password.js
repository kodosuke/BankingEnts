function changePassword() {
	
	$("#changePwdForm").submit(function(e) {
		
		e.preventDefault();

	    var form = $(this);
	    var actionUrl = form.attr('action');
	    
	    $.ajax({
	        type: "POST",
	        url: actionUrl,
	        data: form.serialize()
	    });
	    
	});
}




function checkPassword(str) {

	let re = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
	return re.test(str);
	
}

function validatePasswords() {

	let currentPassword = document.changePwdForm.currentPassword.value;
	let newPassword = document.changePwdForm.newPassword.value;
	let confirmPassword = document.changePwdForm.confirmPassword.value;
	console.log(currentPassword, newPassword, confirmPassword);
	
	if(currentPassword.localeCompare(newPassword) == 0) {
		document.getElementById("error").innerText = "Current password cannot be the new password."
		return false;
	}

	if( !checkPassword(newPassword)) {
		document.getElementById("error").innerText = "Password should contain 8 characters, with at least a symbol, upper and lower case letters and a number to be secure."
		return false;
	}

	if( newPassword.localeCompare(confirmPassword) != 0) {
		document.getElementById("error").innerText = "New password and confirm password mismatch.";
		return false;
	}
		
	document.getElementById("error").innerText = "";
	changePassword();
	return true;
}


