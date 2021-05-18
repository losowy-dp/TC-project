var sub = document.getElementById('sub');

const us = $("#usernamefield").val().trim();
const em = $("#email").val().trim();

check();

window.onload = function () {
    document.getElementById("usernamefield").oninput = checkUsername;
    document.getElementById("email").oninput = checkEmail;
    document.getElementById("password1").oninput = checkPassword1;
    document.getElementById("password2").oninput = checkPassword2;
    document.getElementById("sub").onclick = check;

    document.getElementById("username_login").oninput = checkUsernameLogin;
    document.getElementById("password_login").oninput = checkPasswordLogin
    document.getElementById("sub_login").onclick = checkLogin;
}

function checkLogin() {
    checkUsernameLogin();
    checkPasswordLogin();
}

function checkUsernameLogin() {
    var username = document.getElementById("username_login");
    var length = $("#username_login").val().length;
    if(length == 0){
        username.setCustomValidity("Wypełnijcie pole");
    } else {
        username.setCustomValidity("");
    }
}

function checkPasswordLogin() {
    var password = document.getElementById("password_login");
    var length = $("#password_login").val().length;
    if(length == 0){
        password.setCustomValidity("Wypełnijcie pole");
    } else {
        password.setCustomValidity("");
    }
}

// var sub = document.getElementById("sub");

function check() {
    checkUsername();
    checkEmail();
    checkPassword1();
    checkPassword2();
}


function checkUsername() {
    var username = document.getElementById("usernamefield");
    var username_text = $("#usernamefield").val().trim();
    var length = $("#usernamefield").val().length;

    // 1. cztoby pole username bylo nie pustym
    if(length == 0){
        username.setCustomValidity("Wypełnijcie pole");
    } else {
        // 2. cztoby nie bylo nie nuznych simwolow
        if(!new RegExp('^[a-zA-Z0-9\-\@\+\_\.]+$').test(username_text)){
            username.setCustomValidity("Wprowadźcie poprawną nazwę użytkownika. Wartość może zawierać jedynie litery, cyfry i znaki @/./+/-/_.");
        } else {
            // 3. cztoby pole username bylo bolsze 4 simwolow
            if(length < 4 && length > 0){
                username.setCustomValidity("Minimalna długość 4 symboli");
            }
            // {% if form_registration.username.errors %}
            else {
                if(username_text == us){
                    username.setCustomValidity("Urzytkownik z loginem " + us +" juz istnieje.");	
                } 
                // {% endif %}
                else {
                    username.setCustomValidity("");
                }
            }
        }
    }
}

function checkEmail() {
    var email = document.getElementById("email");
    var email_val = $("#email").val();
    const regex = new RegExp('^.+@.+$');

    if(email_val.length == 0){
        email.setCustomValidity("Wypełnijcie pole");
    } else{

    if(!new RegExp('^.*@.*$').test(email_val)){
        email.setCustomValidity("W email powinien pojawić się symbol '@'");
    } else {

        if(new RegExp('^.*@.*@.*$').test(email_val)){
            email.setCustomValidity("W email nie może pojawić się symbol '@' więcej niż jeden raz");
        } else {
            if(new RegExp('^.*@$').test(email_val)){
                email.setCustomValidity("Wprowadźcie domen po symbolu '@'");
            } else {
                if(new RegExp('^@.*$').test(email_val)){
                    email.setCustomValidity("Wprowadźcie część przed symbolem '@'");
                } 
                // {% if form_registration.email.errors %}
                else {
                    if(email_val == em){
                        email.setCustomValidity("Użytkownik z email " + em +" już istnieje.");	
                    } 
                    // {% endif %}
                    else {
                        email.setCustomValidity("");
                    }
                }
            }
        }
    }

}
}

function checkPassword1() {
    var password1 = document.getElementById("password1");
    var password1_text = $("#password1").val();
    var error = "";

    const min = 6;
    const regrex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8})$');

    if(password1_text.length ==0){
        password1.setCustomValidity("Wypełnijcie pole");
    } else {
        if(password1_text.length  < 8){
            password1.setCustomValidity("Minimalna długość hasła 8 symboli");
        } else {
            if(!new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,})$').test(password1_text)){
                if(!new RegExp('^(?=.*[0-9])([a-zA-Z0-9]{8,})$').test(password1_text)){
                    error += "W haśle powinna pojawić się przynajmniej jedna cyfra\n";
                }
                if(!new RegExp('^(?=.*[a-zA-Z])([a-zA-Z0-9]{8,})$').test(password1_text)){
                    error += "W haśle powinna pojawić się przynajmniej jedna litera\n";
                }
                
                password1.setCustomValidity(error);
            } else {
                password1.setCustomValidity("");
            }
        }
    }

    
    
}

function checkPassword2() {
    var password2 = document.getElementById("password2");
    var password1_text = $("#password1").val();
    var password2_text = $("#password2").val();

    if(password2_text.length == 0){
        password2.setCustomValidity("Wypełnijcie pole");
    } else {
        if(password2_text != password1_text){
            password2.setCustomValidity("Hasła są różne");
        } else {
            password2.setCustomValidity("");
        }
    }
}














