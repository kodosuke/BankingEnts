
	let dateOfBirth = document.registrationForm.dateOfBirth;

	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear() - 1;

	if (dd < 10) {
		dd = '0' + dd;
	}

	if (mm < 10) {
		mm = '0' + mm;
	}
	maxDate = yyyy + '-' + mm + '-' + dd;
	dateOfBirth.setAttribute("max", maxDate);
	dateOfBirth.setAttribute("min", "01/01/1920");

	function checkPassword(str) {

		var re = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
		return re.test(str);
	}
	
	function checkName(str) {
		var re = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
		return re.test(str)
	}
	
	
	function validateThis() {
		let name = document.registrationForm.customerName.value;
		let password = document.registrationForm.password.value;
		let cPassword = document.registrationForm.cPassword.value;
		let mail = document.registrationForm.contact.value;

		
		
		if(!checkName(name)) {
			document.getElementById("error").innerText = "Enter your full name including first name and last name, shouldn't contain any numerals."
			return false;
		}
		
		function validateEmail(mail)
			{
				var mailformat= /[a-zA-Z]+([a-zA-Z0-9\-\.]+)?([a-z0-9])@[a-zA-Z]{4,}\.[a-zA-Z]{2,4}(\.[a-zA-Z]{2,3})?/gm;
				return mailformat.test(mail);
	}
		if (!validateEmail(mail) ) {
	
			document.getElementById("error").innerText = "Enter a valid e-mail address.";
			return false;
		} 
		
		if (!checkPassword(password)) {
			document.getElementById("error").innerText = "Password must have 8 characters, with at least a symbol, upper and lower case letters and a number to be secure.";
			return false;
		} 
		
		if (!password.localeCompare(cPassword) == 0) {
				document.getElementById("error").innerText = "Passwords mismatch."
				return false;
			}
			
		document.getElementById("error").innerText = "";
		return true;
			

	}
	
	
	function registerUser() {
		
		$("#registrationForm").submit(function(e) {
			
			e.preventDefault();
			validateThis();

		    var form = $(this);
		    var actionUrl = form.attr('action');
		    
		    $.ajax({
		        type: "POST",
		        url: actionUrl,
		        data: form.serialize()
		    });
		    
		});
	}
