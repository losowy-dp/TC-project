
var today = new Date();

var date = today.getFullYear();

if(today.getMonth() + 1 < 10){
    date+='-0'+(today.getMonth()+1)+'-'+today.getDate();
}
else{
    date+='-'+(today.getMonth()+1)+'-'+today.getDate();
}
$(document).ready( function() {
    $('#date2').val(date);
})

console.log(date)