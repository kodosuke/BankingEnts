function validateMail(mail) {
	let mailFormat = /^[a-z0-9._-]+@[a-z0-9]+\.[a-z]{2,}$/;
	return mailFormat.test(mail);
}

function checkPasswordStrength(password) {
	let strongPwd = /^(?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[~!@#$%^&*()_\-+{}\[\]|\\;:'",.<>?\/]).{8,}$/;
	return strongPwd.test(password);
}

function checkFullName(userName) {

	let nameRegEx = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
	return nameRegEx.test(userName)
}

function submitLoginUser() {

	let mail = document.loginUserForm.contact.value;

	if (!validateMail(mail)) {
		document.getElementById("loginUser_error").innerText = "Make sure the email address you provide is valid.";
		return false;
	} else {
		document.getElementById("loginUser_error").innerText = "";

		let form = $("#loginUserForm");


		$.getJSON({
			url: form.attr("action"),
			data: form.serialize(),
			type: "POST",
			dataType: 'json',
			cache: false,
			success: (data) => {

				if (data[0].error != null) {
					
					document.getElementById("loginUser_error").innerText = data[0].error;
					return false;
				} else {
					
					sessionStorage.setItem("SIGN", "true");
					sessionStorage.setItem("ACCOUNT", JSON.stringify(data[2]));
					sessionStorage.setItem("CUSTOMER", JSON.stringify(data[1]));
					console.log(data[0].message);

					window.location.href = "/banking/user/";
				}

			},
			error: (data) => {
				console.error("An error occurred during login.")
				console.error(data);
			}

		})
	}
}

function submitRegisterUser() {

	let name = document.registerUserForm.customerName.value;
	let password = document.registerUserForm.password.value;
	let confirmPassword = document.registerUserForm.confirmPassword.value;
	let mail = document.registerUserForm.contact.value;

	if (!checkFullName(name)) {
		document.getElementById("registerUser_error").innerText = "Provide your full name including first name and last name, shouldn't contain any numerals."
		return false;
	}
	if (!validateMail(mail)) {

		document.getElementById("registerUser_error").innerText = "Provide a valid e-mail address.";
		return false;
	}

	if (!checkPasswordStrength(password)) {
		document.getElementById("registerUser_error").innerText = "Password must have 8 characters, with at least a symbol, upper and lower case letters and a number to be secure.";
		return false;
	}

	if (!password.localeCompare(confirmPassword) == 0) {
		document.getElementById("registerUser_error").innerText = "Passwords mismatch."
		return false;
	}

	document.getElementById("registerUser_error").innerText = "";

	let form = $("#registerUserForm");

	$.getJSON({
		url: form.attr("action"),
		data: form.serialize(),
		type: "POST",
		dataType: 'json',
		cache: false,
		success: (data) => {

			if (data[0].error != null) {
				document.getElementById("registerUser_error").innerText = data[0].error;
				return false;
			} else {
				
				sessionStorage.setItem("SIGN", "true");
				sessionStorage.setItem("ACCOUNT", JSON.stringify(data[2]));
				sessionStorage.setItem("CUSTOMER", JSON.stringify(data[1]));
				console.log(data[0].message);

				window.location.href = "/banking/user/";
			}
		},
		error: (data) => {

			console.error("Error on registration.")
			console.error(data);
		}

	});

}