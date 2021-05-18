
var reset = document.getElementById("link_reset");

window.onload = function () {
    document.getElementById("link_reset").onclick = fun;
}

function fun() {
    if($('.form_reset_password').height() == 0){
        $('.form_reset_password').height("150px")
        $('.form_reset_password').css("display","flex")
    } else {
        $('.form_reset_password').height("0")
        $('.form_reset_password').css("display","none")
    }
}