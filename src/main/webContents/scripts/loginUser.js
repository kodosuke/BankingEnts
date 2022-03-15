function validateThis() {
 		
	let mail = document.loginForm.contact.value;
	function validateEmail(mail)
	{
		let mailformat= /[a-zA-Z]+([a-zA-Z0-9\-\.]+)?([a-z0-9])@[a-zA-Z]{4,}\.[a-zA-Z]{2,4}(\.[a-zA-Z]{2,3})?/gm;
		return mailformat.test(mail);
	}
	if (!validateEmail(mail) ) {

		document.getElementById("error").innerText = "Enter a valid e-mail address.";
		return false;
	}  else {
		document.getElementById("error").innerText = "";
		loginUser();
		return true;
	}
}

function loginUser() {
		
	$("#loginForm").submit( function( e) {
			
		e.preventDefault();

	    	let form = $(this);
	    	let actionUrl = form.attr('action');
		    
	    	$.ajax({
			type: "POST",
			url: actionUrl,
			data: form.serialize()
	    	});
		    
	});
}
