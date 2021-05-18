
//Pieriechod ot logowania k riegistracji
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});


// var sub = document.getElementById('sub');


// sub.onclick = function () {
// 	var username = document.getElementById('username');
// 	if(username === null){
// 		username.setCustomValidity("Pole nie moze byc puste.");
// 	}
// }

// window.onload = function () {
//     document.getElementById("username_input").onchange = checkUsername;
// }


// function checkUsername() {
// 	var username = document.getElementById('username_input');

// 	// if(username.validity.valueMissing){
// 	// 	username.setCustomValidity("kurwa.");
// 	// }

// 	if(username.value == ""){
// 		username.setCustomValidity("kurwa.");
// 	}
	
// 	if(username.value.length < 4){
// 		username.setCustomValidity("Logo powinno zawierac minimalnie 4 symboli. U was " + username.value.length + ".");
// 	}
// }

// //validacja username
// var username = document.getElementById('username_input');
// console.log("kostia-1" + username);
// username.addEventListener("input", function (event) {
// 	console.log("lox")
// 	//pole puste validity.valueMissing
// 	if(username.validity.valueMissing){
// 		username.setCustomValidity("kurwa.");
// 	} else {
// 		console.log("pierdolie")
// 		username.setCustomValidity("");
// 	}


// 	//username < 4
// 	if(username.value.length < 4){
// 		username.setCustomValidity("Logo powinno zawierac minimalnie 4 symboli. U was " + username.value.length + ".");
// 	} else {
// 		username.setCustomValidity("");
// 	}

// 	//username > 150
// 	if(username.value.length > 40){
// 		username.setCustomValidity("Logo powinno zawierac od 4 od 30 symboli. U was "+ username.value.length + ".");
// 	} else {
// 		username.setCustomValidity("");
// 	}
// });


// //validacja email
// var pattern = '/^.+@.+$/';
// var email = document.getElementById('email');

// // email.addEventListener("input", function (event) {
// // 	if (email.validity.patternMismatch) {

// // 		//jesc li @
// // 		$("#email").attr("pattern",'^.*@.*$');
// // 		if(email.validity.patternMismatch){
// // 			email.setCustomValidity("Adres e-mail musi zawierać symbol „@”. W adresie '" + email.value + "' brakuje symbolu „@”.");
// // 			$("#email").attr("pattern",pattern);
// // 		} else {

// // 			//jesli li czasc pierid @
// // 			$("#email").attr("pattern",'^.+@.*$');
// // 			if(email.validity.patternMismatch){
// // 				email.setCustomValidity("Wprowadżcie część adresu do symbolu „@”. Adres '" + email.value + "' nie jest pełny.");
// // 				$("#email").attr("pattern",pattern);
// // 			}
// // 			else {

// // 				//jesc li czasc addresa poslie @
// // 				$("#email").attr("pattern",'^.*@.+$');
// // 				if(email.validity.patternMismatch){
// // 					email.setCustomValidity("Wprowadżcie część adresu po symbolu „@”. Adres '" + email.value + "' nie jest pełny.");
// // 					$("#email").attr("pattern",pattern);
// // 				}
// // 			}	
// // 		}	
// // 	} else {
// // 		email.setCustomValidity("");
// // 	}	
// // });





// //validacja porolej
// var password1 = document.getElementById('password1');
// var password2 = document.getElementById('password2');

// // password2.addEventListener("input", function (event) {
// // 	if(password2.value != password1.value){
// // 		password2.setCustomValidity("Hasła różnio się.");
// // 	} else {
// // 		password1.setCustomValidity("");
// // 	}
// // });


