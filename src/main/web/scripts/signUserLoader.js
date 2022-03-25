function fetchSignUser() {
	fetch("/Banking/pages/components/signUser.html")
		.then(response => {
			return response.text();
		})
		.then(data => {
			document.getElementById("signUser_container").innerHTML = data;
		})
		.then(() => {
			$("#registerUser_container").hide(400);
			sessionStorage.setItem("SIGNED", "true");
		});
}

fetchSignUser();


function showRegisterUserContainer() {
	$(".hidden").hide(0, () => {
		$("#registerUser_container").show(0)
	})
}

function showLoginUserContainer() {
	$(".hidden").hide(0, () => {
		$("#loginUser_container").show(0)
	})	
}
/**
 * 
 */
